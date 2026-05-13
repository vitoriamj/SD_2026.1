"""
Cliente Python da demonstração.

Este cliente se conecta ao servidor Java usando gRPC.
O ponto principal do exemplo é mostrar que Python e Java conseguem conversar
porque usam o mesmo contrato Protobuf.
"""

import grpc

# Arquivos gerados automaticamente a partir de hello.proto.
import hello_pb2
import hello_pb2_grpc


def main() -> None:
    """
    Função principal do cliente.

    O canal representa a conexão lógica com o servidor.
    Nesta demonstração usamos canal inseguro porque tudo roda localmente.
    Em um sistema real, o ideal seria usar TLS.
    """
    with grpc.insecure_channel("localhost:50051") as channel:
        """
        Stub gerado automaticamente.
        Ele funciona como um cliente local para o serviço remoto.
        """
        stub = hello_pb2_grpc.HelloServiceStub(channel)

        """
        Cria a requisição usando a classe gerada pelo Protobuf.
        """
        request = hello_pb2.HelloRequest(name="World")

        """
        Chama o método remoto SayHello.
        Por trás dessa linha existe serialização Protobuf, envio pela rede,
        execução no servidor Java e retorno da resposta.
        """
        response = stub.SayHello(request)

        """
        Exibe a resposta enviada pelo servidor.
        """
        print("Resposta do servidor:")
        print(response.message)


if __name__ == "__main__":
    main()
