# Slides — Protobuf em Profundidade

## Slide 1 — Título

Protobuf em Profundidade  
O contrato de dados usado pelo gRPC em sistemas distribuídos

Fala sugerida: neste seminário, vamos entender como o Protocol Buffers ajuda sistemas diferentes a se comunicarem. A prática será um Hello World com servidor Java e cliente Python.

## Slide 2 — Problema em sistemas distribuídos

Em sistemas distribuídos, programas rodam em processos, máquinas ou linguagens diferentes. O cliente precisa enviar dados pela rede e o servidor precisa entender exatamente o que chegou. Se cada lado usa um formato diferente, surgem erros de interpretação, conversão e manutenção.

## Slide 3 — O que é Protobuf

Protocol Buffers, ou Protobuf, é uma forma de definir estruturas de dados em arquivos `.proto`. A partir desse arquivo, ferramentas geram código para várias linguagens. Assim, Java, Python, Go e outras linguagens podem trabalhar com o mesmo contrato.

## Slide 4 — Relação entre Protobuf e gRPC

O gRPC usa o Protobuf para definir mensagens e serviços. As mensagens dizem quais dados trafegam. Os serviços dizem quais métodos remotos podem ser chamados. No exemplo, o cliente chama `SayHello`, envia `HelloRequest` e recebe `HelloResponse`.

## Slide 5 — O arquivo hello.proto

O contrato do projeto define um serviço chamado `HelloService`, um método chamado `SayHello`, uma mensagem de entrada com o campo `name` e uma mensagem de saída com o campo `message`.

## Slide 6 — Campos e números no Protobuf

Cada campo possui um tipo, um nome e um número. Por exemplo, `string name = 1`. O número não é detalhe estético. Ele é usado na serialização binária e faz parte do contrato.

## Slide 7 — Geração de código

O arquivo `.proto` não é executado diretamente. Ele precisa ser compilado. No Java, o Maven usa `protoc` e `protoc-gen-grpc-java`. No Python, usamos `grpcio-tools`. O resultado são classes e stubs gerados automaticamente.

## Slide 8 — Demonstração prática

Na demonstração, vamos iniciar o servidor Java em `localhost:50051`. Depois, o cliente Python vai enviar o nome `World`. O servidor recebe a requisição, monta a resposta e retorna `Hello, World!`.

## Slide 9 — Boas práticas de versionamento

Ao evoluir um schema Protobuf, devemos evitar quebrar clientes antigos. A regra principal é não reutilizar números de campos. Adicionar novos campos costuma ser mais seguro. Quando uma mudança for incompatível, uma alternativa é criar uma nova versão do pacote, como `hello.v2`.

## Slide 10 — Vantagens do gRPC com Protobuf

A abordagem tem boa performance, contratos fortes, geração automática de código e boa integração entre linguagens. Ela é especialmente útil em comunicação interna entre serviços.

## Slide 11 — Limitações

O gRPC pode ser menos simples de testar no navegador do que REST com JSON. Também exige geração de código e cuidado com versionamento. Para APIs públicas simples, REST ainda pode ser uma escolha mais acessível.

## Slide 12 — Fechamento

O Protobuf define o contrato. O gRPC executa chamadas remotas usando esse contrato. A prática mostrou servidor Java e cliente Python trocando mensagens com o mesmo `.proto`.
