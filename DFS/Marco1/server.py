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


HOST = "0.0.0.0"
PORT = 65432
STORAGE_DIR = "./storage"

os.makedirs(STORAGE_DIR, exist_ok=True)


def recv_all(conn: socket.socket, size: int):
    data = b""

    while len(data) < size:
        chunk = conn.recv(size - len(data))

        if not chunk:
            raise ConnectionError("Conexão encerrada antes do fim da mensagem.")

        data += chunk

    return data


def receive_message(conn):
    size_bytes = recv_all(conn, 4)
    
    if size_bytes is None:
        raise ConnectionError("Conexão encerrada antes de receber o tamanho da mensagem.")

    size = int.from_bytes(size_bytes, byteorder="big")

    message = recv_all(conn, size)

    request = dfs_pb2.Request()
    request.ParseFromString(message)

    return request


def send_response(conn, response: dfs_pb2.Response):
    message = response.SerializeToString()
    size = len(message)
    
    conn.sendall(size.to_bytes(4, byteorder="big"))
    conn.sendall(message)


def safe_filename(filename: str):
    return os.path.basename(filename)


def upload(request: dfs_pb2.Request):
    response = dfs_pb2.Response() 

    if not request.filename:
        response.status = "ERROR"
        response.message = "Nome do arquivo não informado."
        return response

    filename = safe_filename(request.filename)
    filepath = os.path.join(STORAGE_DIR, filename)

    try:
        with open(filepath, "wb") as f:
            f.write(request.data)

        response.status = "OK"
        response.message = f"Arquivo '{filename}' salvo com sucesso ({len(request.data)} bytes)."

    except Exception as e:
        response.status = "ERROR"
        response.message = f"Falha no upload do arquivo '{filename}': {str(e)}"

    return response


def download(request):
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
        with open(filepath, "rb") as f:
            content = f.read()

        response.status = "OK"
        response.message = f"Arquivo '{filename}' lido com sucesso."
        response.data = content

    except Exception as e:
        response.status = "ERROR"
        response.message = f"Falha no download do arquivo '{filename}': {str(e)}"

    return response


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


def list():
    response = dfs_pb2.Response()

    try:
        files = []

        for item in os.listdir(STORAGE_DIR):
            path = os.path.join(STORAGE_DIR, item)
            
            if os.path.isfile(path):
                files.append(item)

        response.status = "OK"
        response.message = f"{len(files)} arquivo(s) encontrado(s)."
        response.files.extend(files)

    except Exception as e:
        response.status = "ERROR"
        response.message = f"Falha ao listar arquivos de {STORAGE_DIR}: {str(e)}"

    return response


def process_request(request):
    command = request.command.lower()
    
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


def handle_client(conn, addr):
    print(f"Cliente conectado: {addr}")

    try:
        while True:
            try:
                request = receive_message(conn)
            except ConnectionError:
                break

            response = process_request(request)
            send_response(conn, response)

    except Exception as e:
        print(f"[ERROR] Problema ao atender o cliente {addr}: {e}")

    finally:
        conn.close()
        print(f"Cliente desconectado: {addr}")


def start_server():
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

    server.bind((HOST, PORT))
    server.listen()

    print(f"[SERVIDOR] Escutando em {HOST}:{PORT}")
    print(f"[SERVIDOR] Armazenamento local em: {os.path.abspath(STORAGE_DIR)}")

    try:
        while True:
            conn, addr = server.accept()

            thread = threading.Thread(
                target=handle_client,
                args=(conn, addr),
                daemon=True
            )
            thread.start()

    except KeyboardInterrupt:
        print("\n[SERVIDOR] Encerrando servidor DFS...")

    finally:
        server.close()
        print("[SERVIDOR] Socket fechado. Servidor DFS encerrado.")


if __name__ == "__main__":
    start_server()