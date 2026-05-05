"""
=====================================================================================
  Projeto e Implementação de um Sistema de Arquivos Distribuído (DFS)- Marco 1
  Cliente DFS
  
  Aluno(a): Vitória Mendonça Justino
  Matricula: 202004699
  Disciplina: Sistemas Distribuídos 1 - 2026.1
=====================================================================================
"""

import socket
import os
import sys
import dfs_pb2


HOST = None
PORT = None


def parse_args():
    if len(sys.argv) != 3:
        print("[ERRO] Formato de execução incorreto. Use: python client.py <host> <porta>")
        sys.exit(1)

    host = sys.argv[1]

    if host == "0.0.0.0":
        print("[ERRO] 0.0.0.0 não é um endereço válido para o cliente se conectar.")
        sys.exit(1)

    try:
        port = int(sys.argv[2])
    except ValueError:
        print("[ERRO] A porta deve ser um número inteiro.")
        sys.exit(1)

    if not (1025 <= port <= 65535):
        print("[ERRO] A porta deve estar entre 1025 e 65535.")
        sys.exit(1)

    return host, port


def recv_all(sock, size):
    data = b""

    while len(data) < size:
        chunk = sock.recv(size - len(data))

        if not chunk:
            raise ConnectionError("Conexão encerrada antes da resposta completa.")

        data += chunk

    return data


def send_request(sock, request):
    message_bytes = request.SerializeToString()
    message_size = len(message_bytes)

    sock.sendall(message_size.to_bytes(4, byteorder="big"))
    sock.sendall(message_bytes)

    size_bytes = recv_all(sock, 4)
    response_size = int.from_bytes(size_bytes, byteorder="big")

    response_bytes = recv_all(sock, response_size)

    response = dfs_pb2.Response()
    response.ParseFromString(response_bytes)

    return response

    
def upload(sock, filepath):
    if not os.path.exists(filepath):
        print(f"[ERRO] Arquivo '{filepath}' não encontrado.")
        return

    if not os.path.isfile(filepath):
        print(f"[ERRO] '{filepath}' não é um arquivo válido.")
        return

    filename = os.path.basename(filepath)

    try:
        list_request = dfs_pb2.Request()
        list_request.command = "list"

        list_response = send_request(sock, list_request)

        if list_response.status != "OK":
            print(f"[{list_response.status}] {list_response.message}")
            return

        if filename in list_response.files:
            while True:
                choice = input(
                    f"[AVISO] O arquivo '{filename}' já existe no servidor. Deseja substituí-lo? (s/n): "
                ).strip().lower()

                if choice == "s":
                    break
                elif choice == "n":
                    print("Upload cancelado.")
                    return
                else:
                    print("Digite 's' para sim ou 'n' para não.")


        with open(filepath, "rb") as f:
            file_data = f.read()

        upload_request = dfs_pb2.Request()
        upload_request.command = "upload"
        upload_request.filename = filename
        upload_request.data = file_data

        upload_response = send_request(sock, upload_request)
        print(f"[{upload_response.status}] {upload_response.message}")

    except Exception as e:
        print(f"[ERRO] Falha no upload do arquivo '{filename}': {str(e)}")
        
def download(sock, filename):    
    request = dfs_pb2.Request()
    request.command = "download"
    request.filename = filename

    response = send_request(sock, request)

    if response.status != "OK":
        print(f"[{response.status}] {response.message}")
        return

    if os.path.exists(filename):
        while True:
            choice = input(f"[AVISO] O arquivo '{filename}' já existe. Deseja substituí-lo? (s/n): ").strip().lower()

            if choice == "s":
                break
            elif choice == "n":
                print("Download cancelado.")
                return
            else:
                print("Digite 's' para sim ou 'n' para não.")

    try:
        with open(filename, "wb") as f:
            f.write(response.data)

        print(f"[OK] Download do arquivo '{filename}' concluído com sucesso.")

    except Exception as e:
        print(f"[ERRO] Falha ao salvar o arquivo '{filename}': {str(e)}")


def list_files(sock):
    request = dfs_pb2.Request()
    request.command = "list"

    response = send_request(sock, request)

    if response.status == "OK":
        print(f"\nArquivo(s) no servidor ({len(response.files)} arquivo(s)):")
        for filename in response.files:
            print(f" - {filename}")
    else:
        print(f"[{response.status}] {response.message}")


def delete(sock, filename):
    request = dfs_pb2.Request()
    request.command = "delete"
    request.filename = filename

    response = send_request(sock, request)
    print(f"[{response.status}] {response.message}")


def main():
    global HOST, PORT

    HOST, PORT = parse_args()

    print(f"Conectando ao servidor DFS {HOST}:{PORT}")
    print("Digite 'help' para ver comandos.")

    try:
        with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as sock:
            sock.connect((HOST, PORT))

            while True:
                cmd = input("\nDFS> ").strip().split()

                if not cmd:
                    continue

                if cmd[0] == "upload":
                    if len(cmd) != 2:
                        print("[ERRO] Formato de execução incorreto. Use: upload <arquivo>")
                    else:
                        upload(sock, cmd[1])

                elif cmd[0] == "download":
                    if len(cmd) != 2:
                        print("[ERRO] Formato de execução incorreto. Use: download <arquivo>")
                    else:
                        download(sock, cmd[1])
                        
                elif cmd[0] == "delete":
                    if len(cmd) != 2:
                        print("[ERRO] Formato de execução incorreto. Use: delete <arquivo>")
                    else:
                        delete(sock, cmd[1])

                elif cmd[0] == "list":
                    if len(cmd) != 1:
                        print("[ERRO] Formato inválido. Use: list")
                    else:
                        list_files(sock)

                elif cmd[0] == "exit":
                    if len(cmd) != 1:
                        print("[ERRO] Formato inválido. Use: exit")
                    else:
                        print("Encerrando cliente DFS.")
                        break

                elif cmd[0] == "help":
                    if len(cmd) != 1:
                        print("[ERRO] Formato inválido. Use: help")
                    else:
                        print("""
Comandos disponíveis:
    upload <arquivo>
    download <arquivo>
    list
    delete <arquivo>
    exit
                        """)

                else:
                    print("Comando inválido. Digite 'help' para ver os comandos disponíveis.")

    except KeyboardInterrupt:
        print("\nCliente encerrado.")
    except ConnectionRefusedError:
        print(f"[ERRO] Não foi possível conectar ao servidor em {HOST}:{PORT}.")
    except Exception as e:
        print(f"[ERRO] Falha no cliente: {e}")


if __name__ == "__main__":
    main()