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
      Como o cliente Python também aponta para localhost:50051, os dois processos conseguem se comunicar na mesma máquina.
    */
    private static final int PORT = 50051;

    public static void main(String[] args) throws IOException, InterruptedException {
        /*
          Cria o servidor gRPC e registra a implementação do serviço.
          HelloServiceImpl implementa o contrato definido no hello.proto.
        */
        Server server = ServerBuilder // Cria um objeto builder para configurar o servidor.
                .forPort(PORT) // Define a porta onde o servidor vai escutar.
                .addService(new HelloServiceImpl()) // Registra a implementação do serviço, que é a classe HelloServiceImpl, que diz ao servidor qual classe deve responder quando um cliente chamar o método SayHello.
                .build(); // Constrói o servidor com as configurações definidas.

        /*
          Inicia o servidor em segundo plano, permitindo que o processo continue rodando e aguarde chamadas do cliente.
          Como ele roda em outra thread, sem mais nada acontecendo o programa principal terminaria imediatamente.

          Por isso a última linha, server.awaitTermination(), faz a thread principal travar esperando o servidor parar.
          Sem ela, o servidor subiria, faria o println, e o processo morreria antes de qualquer cliente conseguir se conectar.
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

      HelloServiceGrpc.HelloServiceImplBase é uma classe gerada automaticamente pelo plugin gRPC a partir do arquivo hello.proto.
    */
    static class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

        /*
          Método remoto definido no arquivo .proto.

          O cliente envia HelloRequest.
          O servidor responde HelloResponse.
        */
        @Override // A classe gerada já contém um método sayHello vazio, e o seu trabalho é sobrescrever esse método para colocar a lógica.
        public void sayHello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
            /*
              Recupera o nome enviado pelo cliente.

              StreamObserver existe porque o mesmo modelo também serve para chamadas em streaming, em que várias respostas seriam enviadas uma de cada vez.
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
              Entrega o objeto ao gRPC, que cuida de serializar para bytes e enviar pelo socket.
            */
            responseObserver.onNext(response);

            /*
              Finaliza a chamada RPC.
              Sinaliza que essa é a única resposta, e o cliente pode encerrar a leitura.
            */
            responseObserver.onCompleted();
        }
    }
}
