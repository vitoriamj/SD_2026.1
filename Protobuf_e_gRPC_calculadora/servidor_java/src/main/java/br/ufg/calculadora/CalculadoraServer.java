package br.ufg.calculadora;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class CalculadoraServer {
    private static final int PORT = 50051;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(PORT)
                .addService(new CalculadoraImpl())
                .build()
                .start();

        System.out.println("Servidor gRPC Java iniciado na porta " + PORT);
        System.out.println("Aguardando requisições do Python...");
        server.awaitTermination();
    }

    static class CalculadoraImpl extends CalculadoraGrpc.CalculadoraImplBase {
        
        // Método utilitário para evitar repetição de código
        private void responder(StreamObserver<OperacaoResponse> obs, double res, String msg, boolean erro) {
            OperacaoResponse response = OperacaoResponse.newBuilder()
                    .setResultado(res)
                    .setMensagem(msg)
                    .setErro(erro)
                    .build();
            obs.onNext(response);
            obs.onCompleted();
        }

        @Override
        public void somar(OperacaoRequest req, StreamObserver<OperacaoResponse> obs) {
            responder(obs, req.getA() + req.getB(), "Soma executada com sucesso", false);
        }

        @Override
        public void subtrair(OperacaoRequest req, StreamObserver<OperacaoResponse> obs) {
            responder(obs, req.getA() - req.getB(), "Subtração executada com sucesso", false);
        }

        @Override
        public void multiplicar(OperacaoRequest req, StreamObserver<OperacaoResponse> obs) {
            responder(obs, req.getA() * req.getB(), "Multiplicação executada com sucesso", false);
        }

        @Override
        public void dividir(OperacaoRequest req, StreamObserver<OperacaoResponse> obs) {
            if (req.getB() == 0) {
                responder(obs, 0, "Erro: divisão por zero", true);
            } else {
                responder(obs, req.getA() / req.getB(), "Divisão executada com sucesso", false);
            }
        }

        @Override
        public void potencia(OperacaoRequest req, StreamObserver<OperacaoResponse> obs) {
            responder(obs, Math.pow(req.getA(), req.getB()), "Potência executada com sucesso", false);
        }

        @Override
        public void modulo(OperacaoRequest req, StreamObserver<OperacaoResponse> obs) {
            if (req.getB() == 0) {
                responder(obs, 0, "Erro: divisão por zero", true);
            } else {
                responder(obs, req.getA() % req.getB(), "Módulo executado com sucesso", false);
            }
        }

        @Override
        public void raizQuadrada(OperacaoRequest req, StreamObserver<OperacaoResponse> obs) {
            if (req.getA() < 0) {
                responder(obs, 0, "Erro: raiz de número negativo", true);
            } else {
                responder(obs, Math.sqrt(req.getA()), "Raiz quadrada executada com sucesso", false);
            }
        }
    }
}