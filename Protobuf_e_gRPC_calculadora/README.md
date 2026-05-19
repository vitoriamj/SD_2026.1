# Seminário — Protobuf em Profundidade com Calculadora Distribuída gRPC

Este projeto contém a atividade prática atualizada para o seminário de Sistemas Distribuídos (2026.1).

A aplicação utiliza um servidor em Java, um cliente interativo em Python, comunicação RPC (Remote Procedure Call) nativa via gRPC e um contrato rigorosamente tipado definido em Protocol Buffers (Protobuf).

O exemplo clássico e simples de "Hello World" foi evoluído para uma **Calculadora Distribuída** robusta. O cliente atua como a interface interativa (CLI no terminal), capturando os números e a operação escolhida pelo usuário. Esses dados são serializados via Protobuf e enviados pela rede ao servidor Java, que detém a regra de negócio, realiza os cálculos, trata exceções (como divisões por zero) e devolve a resposta perfeitamente estruturada.

---

## 📂 Estrutura do Projeto

```text
seminario-protobuf-calculadora/
├── servidor_java/
│   ├── pom.xml
│   └── src/
│       └── main/
│           ├── proto/calculadora.proto
│           └── java/br/ufg/calculadora/CalculadoraServer.java
├── cliente_python/
│   ├── cliente.py
│   ├── gerar_stubs.bat
│   ├── gerar_stubs.sh
│   └── requirements.txt
├── slides_roteiro.md
├── roteiro_15_min.md
└── relatorio_arquitetura.md
```

> **Nota Arquitetural Importante:** O arquivo `calculadora.proto` reside dentro do diretório do `servidor_java`. Isso é exigido pelo plugin do Maven para compilar os stubs do gRPC nativamente em tempo de *build* sem erros de caminho no Windows. O script Python foi ajustado para ler esse mesmo contrato de forma relativa.

---

## ⚙️ A Arquitetura e os Códigos Principais

A grande vantagem do gRPC é que **um único arquivo (o contrato Protobuf) dita as regras para todo o sistema, em qualquer linguagem.** ### 1. O Contrato (`calculadora.proto`)
Em vez de usar JSON solto ou criar endpoints REST manuais, definimos as funções remotas e as mensagens em Protobuf. Repare que existe um RPC específico para cada operação:

```protobuf
syntax = "proto3";
package calculadora.v1;

option java_multiple_files = true;
option java_package = "br.ufg.calculadora";
option java_outer_classname = "CalculadoraProto";

service Calculadora {
  rpc Somar (OperacaoRequest) returns (OperacaoResponse);
  rpc Subtrair (OperacaoRequest) returns (OperacaoResponse);
  rpc Multiplicar (OperacaoRequest) returns (OperacaoResponse);
  rpc Dividir (OperacaoRequest) returns (OperacaoResponse);
  rpc Potencia (OperacaoRequest) returns (OperacaoResponse);
  rpc Modulo (OperacaoRequest) returns (OperacaoResponse);
  rpc RaizQuadrada (OperacaoRequest) returns (OperacaoResponse);
}

message OperacaoRequest {
  double a = 1;
  double b = 2;
}

message OperacaoResponse {
  double resultado = 1;
  string mensagem = 2;
  bool erro = 3;
}
```

### 2. O Servidor Java (`CalculadoraServer.java`)
O Maven utiliza o `.proto` acima para gerar classes base automaticamente no Java. O nosso papel é apenas "preencher" a regra de negócio de cada função. O servidor processa os dados de forma isolada na porta `50051`.

```java
// Exemplo da implementação do método de Divisão no Servidor Java
@Override
public void dividir(OperacaoRequest req, StreamObserver<OperacaoResponse> obs) {
    if (req.getB() == 0) {
        responder(obs, 0, "Erro: divisão por zero", true);
    } else {
        responder(obs, req.getA() / req.getB(), "Divisão executada com sucesso", false);
    }
}
```

### 3. O Cliente Python (`cliente.py`)
O Python também gera suas classes locais (*Stubs*) a partir do mesmo `.proto`. O *Stub* funciona como um "cliente ilusório" local. Quando chamamos `stub.Dividir(request)` no Python, ele faz a mágica: serializa os dados em binário, trafega pela rede, aciona o Java e nos devolve o objeto de resposta já formatado e tipado.

```python
# Exemplo de chamada no Cliente Python
request = calculadora_pb2.OperacaoRequest(a=10, b=2)
try:
    response = stub.Dividir(request)
    if response.erro:
        print(f"Atenção: {response.mensagem}")
    else:
        print(f"Resultado: {response.resultado}")
except grpc.RpcError as e:
    print("Falha na comunicação com o servidor.")
```

---

## 🚀 Como Executar o Projeto

### Pré-requisitos Essenciais
* **Java:** JDK 17 ou superior e Maven 3.6 ou superior (`java -version` / `mvn -version`)
* **Python:** Python 3.11 ou superior (`python --version`)

### Passo 1: Iniciar o Servidor (Java)
Abra um terminal na pasta `servidor_java/` e execute:
```bash
mvn clean compile
mvn exec:java
```
*Saída esperada:* `Servidor gRPC Java iniciado na porta 50051`
*(Deixe este terminal aberto rodando em segundo plano).*

### Passo 2: Iniciar o Cliente (Python)
Abra um **novo terminal** na pasta `cliente_python/`:

**No Windows (CMD ou PowerShell):**
```cmd
python -m venv venv
venv\Scripts\activate
python -m pip install --upgrade pip setuptools wheel
pip install -r requirements.txt
gerar_stubs.bat
python cliente.py
```

**No Windows (VS Code usando terminal Git Bash):**
```bash
python -m venv venv
source venv/Scripts/activate
python -m pip install --upgrade pip setuptools wheel
pip install -r requirements.txt
bash gerar_stubs.sh
python cliente.py
```

**No Linux / macOS:**
```bash
python3 -m venv venv
source venv/bin/activate
pip install --upgrade pip setuptools wheel
pip install -r requirements.txt
bash gerar_stubs.sh
python3 cliente.py
```

### Passo 3: Geração Manual de Stubs (Apenas se houver erros nos scripts)
Caso os arquivos `.bat` ou `.sh` não rodem no seu sistema, certifique-se de que o ambiente virtual (`venv`) está ativado na pasta `cliente_python` e force a geração com o comando manual abaixo:
```bash
python -m grpc_tools.protoc -I../servidor_java/src/main/proto --python_out=. --grpc_python_out=. ../servidor_java/src/main/proto/calculadora.proto
```

---

## 🎯 Conclusão da Demonstração
Com esta Calculadora Distribuída, provamos de forma prática que duas linguagens de ecossistemas completamente diferentes (Java e Python) conseguem invocar métodos remotos e trocar objetos complexos em formato binário de altíssima velocidade. Tudo isso sem depender do alto processamento e tamanho do JSON ou do roteamento manual trabalhoso de uma API REST comum, usando apenas o forte contrato do Protobuf!