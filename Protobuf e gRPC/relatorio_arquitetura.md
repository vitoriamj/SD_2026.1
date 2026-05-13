# Relatório de arquitetura — Hello World com Protobuf e gRPC

## 1. Visão geral

Este projeto implementa um sistema cliente-servidor simples usando gRPC e Protocol Buffers. O servidor foi escrito em Java e o cliente foi escrito em Python. A aplicação tem apenas uma função principal: o cliente envia um nome e o servidor responde com uma mensagem no formato `Hello, nome!`.

A simplicidade do tema foi uma decisão intencional. Como o objetivo do seminário é explicar Protobuf em profundidade, a regra de negócio foi reduzida ao mínimo para que a atenção fique no contrato, na geração de código e na comunicação entre linguagens.

## 2. Arquitetura

A arquitetura possui três partes principais. A primeira é o contrato `hello.proto`, que define as mensagens e o serviço. A segunda é o servidor Java, que implementa o método remoto `SayHello`. A terceira é o cliente Python, que chama esse método remotamente.

O servidor escuta na porta `50051`. O cliente cria um canal gRPC para `localhost:50051`, instancia o stub gerado e chama o método `SayHello`.

## 3. Contrato Protobuf

O arquivo `hello.proto` define o pacote `hello.v1`. O sufixo `v1` ajuda a indicar a primeira versão da API. Se futuramente a API precisar mudar de forma incompatível, uma possibilidade seria criar `hello.v2`.

O serviço definido é `HelloService`. Ele contém o método `SayHello`, que recebe `HelloRequest` e retorna `HelloResponse`. A mensagem de entrada possui o campo `name`. A mensagem de saída possui o campo `message`.

Cada campo possui um número. Esses números são usados pelo Protobuf no formato binário e fazem parte do contrato entre cliente e servidor. Por isso, não devem ser alterados ou reutilizados de forma descuidada.

## 4. Servidor Java

O servidor Java usa Maven para baixar as dependências e gerar o código a partir do `.proto`. O plugin `protobuf-maven-plugin` executa o `protoc` e o `protoc-gen-grpc-java`. Com isso, o Java recebe classes geradas automaticamente, como `HelloRequest`, `HelloResponse` e `HelloServiceGrpc`.

A classe `HelloServer` cria um servidor gRPC na porta `50051` e registra a implementação `HelloServiceImpl`. Essa implementação sobrescreve o método `sayHello`. Quando o cliente chama o método, o servidor lê o campo `name`, monta a resposta e envia `Hello, nome!`.

## 5. Cliente Python

O cliente Python usa os pacotes `grpcio`, `grpcio-tools` e `protobuf`. O comando de geração cria os arquivos `hello_pb2.py` e `hello_pb2_grpc.py`. O primeiro contém as mensagens Protobuf. O segundo contém os stubs gRPC.

No código do cliente, o canal é criado com `grpc.insecure_channel("localhost:50051")`. Em seguida, o stub `HelloServiceStub` é usado para chamar `SayHello`. A resposta recebida já vem desserializada como objeto Python.

## 6. Decisões de design

A chamada escolhida foi unary, ou seja, uma requisição e uma resposta. Esse modelo é o mais simples para demonstrar gRPC em uma apresentação curta.

O sistema não usa banco de dados porque o objetivo não é persistência. Também não usa autenticação nem TLS porque a demonstração é local. Em um ambiente real, esses pontos deveriam ser adicionados.

A escolha de Java no servidor e Python no cliente demonstra interoperabilidade. Isso é importante porque, em sistemas distribuídos, diferentes equipes podem usar linguagens diferentes.

## 7. Trade-offs

O gRPC com Protobuf oferece contratos bem definidos, boa performance e geração automática de código. Ele é muito útil em comunicação interna entre serviços, especialmente quando há várias linguagens envolvidas.

Por outro lado, o gRPC exige etapa de geração de código e não é tão fácil de testar manualmente em navegador quanto REST com JSON. Além disso, mudanças no `.proto` exigem disciplina de versionamento.

## 8. Boas práticas de compatibilidade

A principal boa prática é não reutilizar números de campos. Se um campo for removido, o ideal é reservá-lo. Também é recomendável adicionar campos novos em vez de alterar o significado de campos antigos. Mudanças incompatíveis devem ser tratadas como uma nova versão da API.

## 9. Conclusão

A atividade mostra que o Protobuf funciona como contrato de comunicação e que o gRPC usa esse contrato para permitir chamadas remotas entre sistemas. Mesmo com um exemplo simples de Hello World, é possível visualizar os principais conceitos: schema, serialização, geração de código, interoperabilidade e compatibilidade entre versões.
