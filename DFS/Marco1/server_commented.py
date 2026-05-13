"""
=====================================================================================
  Projeto e Implementação de um Sistema de Arquivos Distribuído (DFS)- Marco 1
  Servidor DFS
  
  Aluno(a): Vitória Mendonça Justino
  Matricula: 202004699
  Disciplina: Sistemas Distribuídos 1 - 2026.1
=====================================================================================
"""


import socket
import threading
import os
import dfs_pb2


HOST = "0.0.0.0" # Aceita conexões de qualquer endereço de rede
PORT = 65432 # Porta TCP utilizada para receber conexões
STORAGE_DIR = "./storage" # Pasta local onde os arquivos serão salvos


# Garante que a pasta de armazenamento exista
os.makedirs(STORAGE_DIR, exist_ok=True)
# exist_ok=True evita erro caso a pasta já exista


# recv_all: função auxiliar para ler exatamente o tamanho em bytes de uma mensagem de um socket
# Assim o servidor sabe exatamente quantos bytes ler, mesmo que a mensagem seja grande ou chegue em partes
# size: 4 bytes são usados para indicar o tamanho da mensagem, e depois lemos a mensagem completa serializada
def recv_all(conn: socket.socket, size: int):
    data = b"" # Inicializa um buffer vazio para acumular os bytes recebidos

    while len(data) < size:
        chunk = conn.recv(size - len(data))

        # Se chunk vier vazio, significa que a conexão foi fechada antes do recebimento completo da mensagem
        if not chunk:
            raise ConnectionError("Conexão encerrada antes do fim da mensagem.")

        data += chunk # Acumula os bytes recebidos no buffer

    return data


# Desserialização: Lê a mensagem do cliente e reconstrói o objeto ProtoBuf da requisição
# receive_message: lê uma mensagem ProtoBuf enviada no formato: [4 bytes com tamanho][mensagem serializada]
def receive_message(conn):
    # Lê os 4 bytes que indicam o tamanho da mensagem
    size_bytes = recv_all(conn, 4)
    
    if size_bytes is None:
        raise ConnectionError("Conexão encerrada antes de receber o tamanho da mensagem.")

    # Converte os 4 bytes para inteiro
    size = int.from_bytes(size_bytes, byteorder="big") # "big" indica big-endian

    # Agora lê exatamente a quantidade de bytes da mensagem serializada
    message = recv_all(conn, size)

    # Reconstrói o objeto ProtoBuf da requisição
    request = dfs_pb2.Request() # Cria uma instância vazia de Request do ProtoBuf para criar um objeto a partir dos bytes recebidos
    request.ParseFromString(message) # ParseFromString é o método do ProtoBuf para desserializar os bytes em um objeto Request

    return request # Retorna o objeto Request reconstruído a partir dos bytes recebidos


# send_response: serializa uma resposta ProtoBuf e envia usando o mesmo padrão: [4 bytes com tamanho][mensagem serializada]
# sendall() é usado em vez de send(), porque sendall tenta transmitir todos os bytes, o que é mais apropriado aqui, já que queremos garantir que a mensagem completa seja enviada
def send_response(conn, response: dfs_pb2.Response): # response é o objeto ProtoBuf que queremos enviar de volta para o cliente
    message = response.SerializeToString() # SerializeToString é o método do ProtoBuf para serializar o objeto Response em bytes, que podem ser enviados pela rede
    size = len(message)
    
    # Primeiro enviamos o tamanho da mensagem como 4 bytes, depois enviamos a mensagem serializada
    conn.sendall(size.to_bytes(4, byteorder="big"))
    conn.sendall(message)


# safe_filename: sanitiza o nome do arquivo recebido do cliente
# Sem esse cuidado, o cliente poderia tentar enviar algo como: "../../arquivo.txt"
# Isso poderia fazer o servidor acessar ou sobrescrever arquivos fora da pasta storage
def safe_filename(filename: str):
    return os.path.basename(filename)
    # os.path.basename() mantém apenas o nome final do arquivo


# upload: salva o arquivo recebido no diretório local do servidor
# Decisão arquitetural: no Marco 1, o servidor é o único nó de armazenamento, então toda escrita ocorre localmente no filesystem do próprio nó
def upload(request: dfs_pb2.Request):
    response = dfs_pb2.Response() 

    # Se o cliente não informar um nome de arquivo, respondemos com erro
    # Isso é importante para evitar erros posteriores ao tentar salvar um arquivo sem nome.
    if not request.filename:
        response.status = "ERROR" # Indicamos que houve um erro na operação
        response.message = "Nome do arquivo não informado."
        return response

    filename = safe_filename(request.filename)
    filepath = os.path.join(STORAGE_DIR, filename) # Construímos o caminho completo do arquivo no diretório de armazenamento

    try:
        # Escreve os bytes recebidos no arquivo local
        # "wb" indica que estamos escrevendo em modo binário, o que é importante para preservar o conteúdo original do arquivo, seja ele texto ou binário
        with open(filepath, "wb") as f: # with é usado para garantir que o arquivo seja fechado corretamente após a escrita, mesmo que ocorra um erro durante o processo
            f.write(request.data)

        response.status = "OK" # Indicamos que a operação foi bem-sucedida
        response.message = f"Arquivo '{filename}' salvo com sucesso ({len(request.data)} bytes)."

    except Exception as e:
        response.status = "ERROR"
        response.message = f"Falha no upload do arquivo '{filename}': {str(e)}"

    return response


# download: lê um arquivo local e devolve seu conteúdo ao cliente
def download(request):
    response = dfs_pb2.Response()

    if not request.filename:
        response.status = "ERROR"
        response.message = "Nome do arquivo não informado."
        return response

    filename = safe_filename(request.filename)
    filepath = os.path.join(STORAGE_DIR, filename)

    # Se o arquivo não existir, o servidor responde com erro
    if not os.path.exists(filepath):
        response.status = "ERROR"
        response.message = f"Arquivo '{filename}' não encontrado."
        return response

    try:
        # "rb" indica que estamos lendo em modo binário, o que é importante para preservar o conteúdo original do arquivo, seja ele texto ou binário
        with open(filepath, "rb") as f:
            content = f.read()

        response.status = "OK"
        response.message = f"Arquivo '{filename}' lido com sucesso."
        response.data = content # O conteúdo do arquivo é colocado no campo data da resposta, que é um campo bytes no ProtoBuf

    except Exception as e:
        response.status = "ERROR"
        response.message = f"Falha no download do arquivo '{filename}': {str(e)}"

    return response


# delete: remove um arquivo do armazenamento local
def delete(request):
    response = dfs_pb2.Response()

    if not request.filename:
        response.status = "ERROR"
        response.message = "Nome do arquivo não informado."
        return response

    filename = safe_filename(request.filename)
    filepath = os.path.join(STORAGE_DIR, filename)

    if not os.path.exists(filepath):
        response.status = "ERROR"
        response.message = f"Arquivo '{filename}' não encontrado."
        return response

    try:
        os.remove(filepath)

        response.status = "OK"
        response.message = f"Arquivo '{filename}' removido com sucesso."

    except Exception as e:
        response.status = "ERROR"
        response.message = f"Falha ao excluir arquivo '{filename}': {str(e)}"

    return response


# list: lista os arquivos presentes no diretório de armazenamento local do servidor
# repeated string files do ProtoBuf é preenchido com extend() para adicionar os nomes dos arquivos encontrados
def list():
    response = dfs_pb2.Response()

    try:
        files = []

        for item in os.listdir(STORAGE_DIR):
            path = os.path.join(STORAGE_DIR, item)

            # Verifica se o item é um arquivo (e não uma subpasta ou outro tipo de entrada)
            if os.path.isfile(path):
                files.append(item)

        response.status = "OK"
        response.message = f"{len(files)} arquivo(s) encontrado(s)."
        response.files.extend(files) # extend() é usado para adicionar os arquivos encontrados à lista de arquivos do ProtoBuf

    except Exception as e:
        response.status = "ERROR"
        response.message = f"Falha ao listar arquivos de {STORAGE_DIR}: {str(e)}"

    return response


# process_request: central que recebe uma requisição do cliente, identifica o comando e delega para a função específica de cada comando
# Isso melhora a organização do código, porque:
# - separa comunicação de lógica de negócio
# - deixa mais fácil adicionar novos comandos depois
def process_request(request):
    command = request.command.lower() # Converte o comando para minúsculas para evitar problemas de case-sensitive

    if command == "upload":
        return upload(request)

    elif command == "download":
        return download(request)

    elif command == "list":
        return list()

    elif command == "delete":
        return delete(request)

    else:
        response = dfs_pb2.Response()
        response.status = "ERROR"
        response.message = f"Comando inválido: {request.command}"
        return response


# handle_client: cada cliente conectado é tratado nesta função
# Esta função é executada em uma thread separada para cada cliente, permitindo que o servidor atenda múltiplos clientes simultaneamente sem bloqueio
# Nesta implementação, a conexão é mantida aberta enquanto o cliente continuar enviando comandos
def handle_client(conn, addr):
    print(f"Cliente conectado: {addr}")

    try:
        while True:
            try:
                request = receive_message(conn)
            except ConnectionError:
                # Cliente fechou a conexão ou houve um problema na comunicação
                break

            # Identifica o comando e executa a função correspondente, retornando uma resposta que é enviada de volta para o cliente
            response = process_request(request)
            send_response(conn, response)

    except Exception as e:
        print(f"[ERROR] Problema ao atender o cliente {addr}: {e}")

    finally:
        conn.close()
        print(f"Cliente desconectado: {addr}")


# start_server: inicializa o socket do servidor
# Etapas:
# 1. cria socket TCP
# 2. associa IP e porta com bind()
# 3. coloca em modo de escuta com listen()
# 4. aceita conexões com accept()
# 5. cria uma thread para cada cliente conectado, delegando o atendimento para handle_client()
def start_server():
    # AF_INET = IPv4
    # SOCK_STREAM = TCP
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    # SO_REUSEADDR permite reutilizar a porta mais rapidamente após reinício do servidor
    server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

    server.bind((HOST, PORT))
    server.listen()

    print(f"[SERVIDOR] Escutando em {HOST}:{PORT}")
    print(f"[SERVIDOR] Armazenamento local em: {os.path.abspath(STORAGE_DIR)}")

    try:
        while True:
            conn, addr = server.accept()

            # Cada cliente em uma thread separada
            thread = threading.Thread(
                target=handle_client,
                args=(conn, addr),
                daemon=True # daemon=True faz com que as threads sejam encerradas automaticamente quando o programa principal terminar, evitando que o servidor fique preso esperando threads de clientes que nunca terminam
            )
            thread.start()

    except KeyboardInterrupt:
        # Captura Ctrl + C
        print("\n[SERVIDOR] Encerrando servidor DFS...")

    finally:
        # Fecha o socket do servidor
        server.close()
        print("[SERVIDOR] Socket fechado. Servidor DFS encerrado.")


if __name__ == "__main__":
    start_server()