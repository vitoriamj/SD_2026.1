# DFS — Sistema de Arquivos Distribuído (Marco 1)

## Visão geral

Este projeto implementa o **Marco 1** de um Sistema de Arquivos Distribuído (DFS), conforme a proposta da disciplina de Sistemas Distribuídos 1. Nesta etapa, o objetivo é construir a base funcional do sistema usando uma arquitetura **cliente-servidor**, com armazenamento local em um único nó.

O sistema permite que um cliente envie comandos para um servidor por meio de **sockets TCP**. As mensagens trocadas entre cliente e servidor são serializadas com **Protocol Buffers (Protobuf)**, garantindo um formato de comunicação estruturado e mais eficiente do que texto puro.

As operações implementadas são:

- `upload`: envia um arquivo do cliente para o servidor;
- `download`: baixa um arquivo armazenado no servidor;
- `list`: lista os arquivos disponíveis no servidor;
- `delete`: remove um arquivo do servidor;
- `exit`: encerra a sessão do cliente.

## Objetivo do Marco 1

O Marco 1 tem como foco criar a base inicial do DFS. Por isso, esta versão ainda não implementa múltiplos nós, replicação, balanceamento de carga ou tolerância a falhas avançada. A proposta desta etapa é validar:

- comunicação cliente-servidor;
- troca de mensagens via socket TCP;
- serialização e desserialização com Protobuf;
- armazenamento local de arquivos em um diretório do servidor;
- interface de linha de comando para interação do usuário.


## Arquitetura do sistema

A arquitetura segue o modelo **cliente-servidor**.

```text
+------------------------+
|         Cliente        |
|  Interface CLI         |
|  client.py             |
+-----------+------------+
            |
            | Socket TCP
            | Mensagens Protobuf
            v
+-----------+------------+
|         Servidor       |
|  server.py             |
|  Processamento         |
|  Armazenamento local   |
+-----------+------------+
            |
            v
+------------------------+
|       ./storage        |
| Diretório dos arquivos |
+------------------------+
```

### Cliente

O cliente é responsável por:

- receber comandos digitados pelo usuário;
- validar argumentos básicos;
- criar requisições Protobuf;
- serializar as mensagens;
- enviar os dados ao servidor por socket TCP;
- receber e desserializar as respostas;
- exibir os resultados na tela.

O cliente mantém a conexão aberta durante toda a sessão, evitando abrir uma nova conexão para cada comando.

### Servidor

O servidor é responsável por:

- abrir um socket TCP na porta configurada;
- aguardar conexões de clientes;
- criar uma thread para tratar cada cliente conectado;
- receber requisições Protobuf;
- identificar o comando solicitado;
- executar a operação correspondente no diretório `./storage`;
- enviar uma resposta Protobuf ao cliente.

### Protocolo de comunicação

A comunicação usa o seguinte padrão:

```text
[4 bytes com o tamanho da mensagem][mensagem Protobuf serializada]
```

Esse controle de tamanho é necessário porque o TCP transmite dados como um fluxo contínuo de bytes. Assim, uma mensagem pode chegar em partes, e o programa precisa saber exatamente quantos bytes deve ler para reconstruir a requisição ou resposta completa.


## Estrutura do projeto

```text
DFS/
├── client.py
├── server.py
├── dfs.proto
├── dfs_pb2.py             # gerado pelo compilador do Protobuf
├── storage/               # criado automaticamente pelo servidor
└── README.md
```

Observação: os arquivos Python importam `dfs_pb2.py`. Por isso, o arquivo `.proto` usado para gerar o código Python deve se chamar `dfs.proto`, ou o import nos arquivos Python deve ser ajustado para o nome gerado.


## Tecnologias utilizadas

- Python 3;
- Sockets TCP;
- Threads;
- Google Protocol Buffers (ProtoBuf);
- Sistema de arquivos local.


## Instalação

### 1. Clonar ou organizar os arquivos

Coloque os arquivos principais na mesma pasta:

```bash
client_commented.py
server_commented.py
dfs_commented.proto
```

### 2. Instalar dependências

```bash
python -m pip install protobuf grpcio-tools
```

### 3. Gerar o arquivo Python do Protobuf

Como o código importa `dfs_pb2.py`, primeiro crie uma cópia do arquivo `.proto` com o nome `dfs.proto`.

No Linux/macOS:

```bash
cp dfs.proto dfs.proto
python -m grpc_tools.protoc -I. --python_out=. dfs.proto
```

No Windows PowerShell:

```powershell
Copy-Item dfs.proto dfs.proto
python -m grpc_tools.protoc -I. --python_out=. dfs.proto
```

Após esse comando, o arquivo `dfs_pb2.py` deverá ser criado na pasta do projeto.


## Execução

### 1. Iniciar o servidor

Em um terminal, execute:

```bash
python server.py
```

O servidor será iniciado em:

```text
0.0.0.0:65432
```

Os arquivos enviados pelos clientes serão armazenados no diretório:

```text
./storage
```

Esse diretório é criado automaticamente caso ainda não exista.

### 2. Iniciar o cliente

Em outro terminal, execute:

```bash
python client.py 127.0.0.1 65432
```

Se o cliente estiver em outra máquina, substitua `127.0.0.1` pelo IP da máquina onde o servidor está executando.

Exemplo:

```bash
python client.py 192.168.0.10 65432
```


## Comandos disponíveis no cliente

Após iniciar o cliente, será exibido o prompt:

```text
DFS>
```

### Enviar arquivo ao servidor

```text
upload <arquivo>
```

Exemplo:

```text
upload teste.txt
```

O cliente verifica se o arquivo existe localmente. Se já houver um arquivo com o mesmo nome no servidor, o usuário será perguntado se deseja substituí-lo.

### Baixar arquivo do servidor

```text
download <arquivo>
```

Exemplo:

```text
download teste.txt
```

Se já existir um arquivo com o mesmo nome no diretório local do cliente, o usuário será perguntado se deseja sobrescrevê-lo.

### Listar arquivos no servidor

```text
list
```

Esse comando exibe os arquivos armazenados no diretório `./storage` do servidor.

### Remover arquivo do servidor

```text
delete <arquivo>
```

Exemplo:

```text
delete teste.txt
```

### Ver ajuda

```text
help
```

### Encerrar o cliente

```text
exit
```


## Fluxo de funcionamento

### Upload

1. O usuário digita `upload <arquivo>` no cliente.
2. O cliente verifica se o arquivo existe localmente.
3. O cliente envia uma requisição `list` para verificar se o arquivo já existe no servidor.
4. Se o arquivo já existir, o cliente pergunta se o usuário deseja substituir.
5. O cliente lê o arquivo em modo binário.
6. O cliente monta uma mensagem `Request` com comando, nome do arquivo e dados.
7. A requisição é serializada com Protobuf.
8. A mensagem é enviada ao servidor via socket TCP.
9. O servidor desserializa a requisição.
10. O servidor salva o arquivo no diretório `./storage`.
11. O servidor envia uma resposta com status `OK` ou `ERROR`.

### Download

1. O usuário digita `download <arquivo>`.
2. O cliente envia uma requisição ao servidor.
3. O servidor verifica se o arquivo existe em `./storage`.
4. Se existir, o servidor lê o conteúdo em modo binário.
5. O servidor envia os bytes do arquivo em uma mensagem `Response`.
6. O cliente recebe e desserializa a resposta.
7. O cliente salva o arquivo localmente, perguntando antes se deve sobrescrever um arquivo já existente.

### List

1. O usuário digita `list`.
2. O cliente envia uma requisição ao servidor.
3. O servidor percorre o diretório `./storage`.
4. O servidor retorna a lista de arquivos encontrados.
5. O cliente exibe os nomes na tela.

### Delete

1. O usuário digita `delete <arquivo>`.
2. O cliente envia uma requisição ao servidor.
3. O servidor verifica se o arquivo existe em `./storage`.
4. Se existir, o servidor remove o arquivo.
5. O servidor retorna uma mensagem de sucesso ou erro.

---

## Estrutura das mensagens Protobuf

O arquivo `dfs.proto` define o contrato de comunicação entre cliente e servidor.

### Request

```proto
message Request {
  string command = 1;
  string filename = 2;
  bytes data = 3;
}
```

Campos:

- `command`: comando solicitado, como `upload`, `download`, `list` ou `delete`;
- `filename`: nome do arquivo alvo da operação;
- `data`: conteúdo binário do arquivo, usado principalmente no upload.

### Response

```proto
message Response {
  string status = 1;
  string message = 2;
  bytes data = 3;
  repeated string files = 4;
}
```

Campos:

- `status`: indica se a operação retornou `OK` ou `ERROR`;
- `message`: mensagem textual explicando o resultado;
- `data`: conteúdo binário do arquivo, usado no download;
- `files`: lista de arquivos, usada na operação `list`.

---

## Decisões de projeto

### Uso de TCP

O TCP foi escolhido por oferecer comunicação confiável, orientada à conexão e com entrega ordenada dos bytes. Isso é adequado para transferência de arquivos, pois a perda ou desordem dos dados poderia corromper o conteúdo transmitido.

### Uso de Protobuf

O Protobuf foi utilizado para serializar e desserializar as mensagens trocadas entre cliente e servidor. Isso permite que as requisições e respostas tenham uma estrutura clara, com campos bem definidos, em vez de depender de strings soltas.

### Uso de framing com 4 bytes

Antes de enviar a mensagem Protobuf, o sistema envia 4 bytes informando o tamanho da mensagem. Essa decisão evita problemas de leitura parcial no TCP, já que o receptor sabe exatamente quantos bytes precisa receber antes de desserializar a mensagem.

### Armazenamento local

Nesta etapa, o servidor salva os arquivos em um único diretório local chamado `storage`. Essa escolha atende ao Marco 1, que exige a base funcional do sistema antes da introdução de múltiplos nós de armazenamento.

### Servidor concorrente

O servidor cria uma thread para cada cliente conectado. Isso permite que mais de um cliente seja atendido sem bloquear completamente o servidor principal, mantendo o socket principal livre para aceitar novas conexões.

### Sanitização do nome do arquivo

O servidor usa `os.path.basename()` para considerar apenas o nome final do arquivo recebido. Isso reduz o risco de um cliente tentar acessar caminhos fora da pasta de armazenamento usando nomes como `../../arquivo.txt`.

---

## Teste manual sugerido

### 1. Criar um arquivo de teste

```bash
echo "hello dfs" > teste.txt
```

### 2. Subir o servidor

```bash
python server.py
```

### 3. Subir o cliente

```bash
python client.py 127.0.0.1 65432
```

### 4. Enviar o arquivo

```text
upload teste.txt
```

### 5. Listar arquivos

```text
list
```

### 6. Baixar o arquivo

```text
download teste.txt
```

### 7. Excluir o arquivo do servidor

```text
delete teste.txt
```

### 8. Encerrar o cliente

```text
exit
```

---

## Tratamento de erros implementado

O projeto possui alguns tratamentos básicos de erro:

- validação de host e porta no cliente;
- rejeição do endereço `0.0.0.0` como destino do cliente;
- verificação de existência do arquivo antes do upload;
- verificação de existência do arquivo antes do download e delete;
- confirmação antes de sobrescrever arquivos no upload e no download;
- tratamento de encerramento do cliente com `Ctrl + C`;
- tratamento de encerramento do servidor com `Ctrl + C`;
- fechamento do socket do servidor ao encerrar;
- retorno de erro para comandos inválidos;
- leitura completa das mensagens com `recv_all`.


## Conclusão

O projeto implementa a base funcional de um Sistema de Arquivos Distribuído em seu Marco 1. A solução já possui comunicação entre processos por sockets TCP, serialização estruturada com Protobuf, interface de linha de comando, servidor concorrente com threads e armazenamento local de arquivos.

Essa base permite evoluir o sistema gradualmente para os próximos marcos, incluindo distribuição real dos dados, replicação, consistência, tolerância a falhas e avaliação de escalabilidade.
