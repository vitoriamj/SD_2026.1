# Roteiro de apresentação em até 15 minutos

## Divisão sugerida do tempo

| Tempo | Parte | Conteúdo |
|---:|---|---|
| 0:00 a 1:00 | Abertura | Problema de comunicação entre sistemas |
| 1:00 a 3:00 | Conceito | O que é Protobuf e como se relaciona com gRPC |
| 3:00 a 5:00 | Contrato | Explicação do arquivo `hello.proto` |
| 5:00 a 7:00 | Geração | Como Java e Python recebem código gerado |
| 7:00 a 10:00 | Demonstração | Rodar servidor Java e cliente Python |
| 10:00 a 12:00 | Versionamento | Campos, números e compatibilidade |
| 12:00 a 14:00 | Trade-offs | Vantagens e limitações |
| 14:00 a 15:00 | Debate | Quando usar gRPC e quando REST pode ser melhor |

## Roteiro falado

Comece explicando que sistemas distribuídos precisam trocar dados entre processos diferentes. Esses processos podem estar em máquinas diferentes e podem ter sido escritos em linguagens diferentes. O problema central é garantir que todos entendam o formato da mensagem.

Depois apresente o Protobuf como um contrato. O arquivo `.proto` define quais mensagens existem e quais métodos o servidor oferece. No exemplo, o serviço é bem simples: o cliente envia um nome e o servidor devolve uma saudação.

Mostre o arquivo `hello.proto`. Explique que `HelloRequest` tem o campo `name`, que `HelloResponse` tem o campo `message` e que o serviço `HelloService` tem o método `SayHello`. Explique também que o número do campo, como `1`, é usado na serialização binária.

Em seguida, explique a geração de código. No Java, o Maven compila o `.proto` e gera classes como `HelloRequest`, `HelloResponse` e `HelloServiceGrpc`. No Python, o `grpcio-tools` gera `hello_pb2.py` e `hello_pb2_grpc.py`.

Na demonstração, abra o terminal do servidor Java e rode `mvn clean compile` e `mvn exec:java`. Depois abra o terminal do cliente Python e rode `python cliente.py`. Mostre a saída `Hello, World!`.

Finalize explicando que, em sistemas reais, a parte mais importante não é a frase retornada. O mais importante é que Java e Python se comunicaram usando um contrato comum. Depois discuta trade-offs: gRPC é ótimo para comunicação serviço a serviço, mas REST com JSON ainda pode ser mais simples para APIs públicas e testes manuais.

## Pergunta para debate

Em um sistema real com vários serviços, por exemplo matrícula, biblioteca e financeiro, quais integrações seriam melhores com gRPC e quais seriam melhores com REST?
