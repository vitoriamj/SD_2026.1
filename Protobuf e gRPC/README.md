# Seminário — Protobuf em Profundidade com Hello World gRPC

Este projeto contém uma atividade prática simples para o seminário de Sistemas Distribuídos.

A aplicação usa servidor em Java, cliente em Python, comunicação via gRPC e contrato definido em Protocol Buffers.

O exemplo foi simplificado para um cenário Hello World. O cliente envia um nome para o servidor, e o servidor responde com uma mensagem no formato `Hello, nome!`.

## Estrutura do projeto

```text
seminario-protobuf-hello-world/
├── proto/
│   └── hello.proto
├── servidor_java/
│   ├── pom.xml
│   └── src/main/proto/hello.proto
│   └── src/main/java/br/ufg/hello/HelloServer.java
├── cliente_python/
│   ├── cliente.py
│   ├── gerar_stubs.bat
│   ├── gerar_stubs.sh
│   └── requirements.txt
├── slides_roteiro.md
├── roteiro_15_min.md
└── relatorio_arquitetura.md
```

## Pré-requisitos

Servidor Java:

```bat
java -version
javac -version
mvn -version
```

O esperado é Java 17 ou superior e Maven 3.6 ou superior.

Cliente Python:

```bat
python --version
```

Recomendado: Python 3.11 ou 3.12.

## Como rodar o servidor Java

Abra um terminal na pasta do servidor:

```bat
cd servidor_java
mvn clean compile
mvn exec:java
```

Saída esperada:

```text
Servidor gRPC Java iniciado em localhost:50051
Aguardando chamadas do cliente Python...
```

Deixe esse terminal aberto.

## Como rodar o cliente Python no Windows

Abra outro terminal na pasta do cliente:

```bat
cd cliente_python
python -m venv venv
venv\Scripts\activate
python -m pip install --upgrade pip setuptools wheel
pip install -r requirements.txt
gerar_stubs.bat
python cliente.py
```

Saída esperada:

```text
Resposta do servidor:
Hello, World!
```

## Como gerar os stubs manualmente no Windows

Se preferir não usar o arquivo `.bat`, rode:

```bat
python -m grpc_tools.protoc -I..\proto --python_out=. --grpc_python_out=. ..\proto\hello.proto
```

Isso deve gerar:

```text
hello_pb2.py
hello_pb2_grpc.py
```

## Ideia central da demonstração

O arquivo `hello.proto` é o contrato. O Java usa esse contrato para gerar a base do servidor. O Python usa o mesmo contrato para gerar o cliente. Assim, duas linguagens diferentes conseguem trocar mensagens sem depender de JSON ou conversões manuais.
