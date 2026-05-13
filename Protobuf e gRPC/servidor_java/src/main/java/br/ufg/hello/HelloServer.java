package br.ufg.hello;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

/*
  Servidor Java da demonstração.

  Este processo abre uma porta local e fica aguardando chamadas gRPC.
  O cliente Python se conecta a essa porta e chama o método SayHello.
*/
public class HelloServer {

    /*
      Porta usada na demonstração.
      Como o cliente Python também aponta para localhost:50051,
      os dois processos conseguem se comunicar na mesma máquina.
    */
    private static final int PORT = 50051;

    public static void main(String[] args) throws IOException, InterruptedException {
        /*
          Cria o servidor gRPC e registra a implementação do serviço.
          HelloServiceImpl implementa o contrato definido no hello.proto.
        */
        Server server = ServerBuilder
                .forPort(PORT)
                .addService(new HelloServiceImpl())
                .build();

        /*
          Inicia o servidor.
        */
        server.start();

        System.out.println("Servidor gRPC Java iniciado em localhost:" + PORT);
        System.out.println("Aguardando chamadas do cliente Python...");
        System.out.println("Pressione Ctrl+C para encerrar.");

        /*
          Encerra o servidor de forma organizada quando o processo for finalizado.
        */
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Encerrando servidor...");
            server.shutdown();
        }));

        /*
          Mantém o processo rodando.
        */
        server.awaitTermination();
    }

    /*
      Implementação real do serviço.

      HelloServiceGrpc.HelloServiceImplBase é uma classe gerada automaticamente
      pelo plugin gRPC a partir do arquivo hello.proto.
    */
    static class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

        /*
          Método remoto definido no arquivo .proto.

          O cliente envia HelloRequest.
          O servidor responde HelloResponse.
        */
        @Override
        public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
            /*
              Recupera o nome enviado pelo cliente.
            */
            String name = request.getName();

            /*
              Regra simples para evitar resposta vazia.
            */
            if (name == null || name.isBlank()) {
                name = "World";
            }

            /*
              Monta a mensagem de resposta.
              Mensagens Protobuf são criadas por meio de builders.
            */
            HelloResponse response = HelloResponse.newBuilder()
                    .setMessage("Hello, " + name + "!")
                    .build();

            /*
              Envia a resposta para o cliente.
            */
            responseObserver.onNext(response);

            /*
              Finaliza a chamada RPC.
            */
            responseObserver.onCompleted();
        }
    }
}
