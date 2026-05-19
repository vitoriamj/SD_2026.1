# Roteiro de Apresentação (15 Minutos)
**Autores:** Vitória Mendonça Justino e Higor Ferreira Silva

## Divisão sugerida do tempo

| Tempo | Parte | Conteúdo Principal |
|---:|---|---|
| 0:00 a 2:00 | O Problema | Acoplamento, Heterogeneidade e o "Abismo de Serialização" |
| 2:00 a 5:00 | A Solução | O arquivo `.proto` como "Contrato Canônico" |
| 5:00 a 8:00 | Boas Práticas | Modelagem Avançada (Enums = 0 e Nested Messages) |
| 8:00 a 11:00 | Demonstração | Execução interativa da Calculadora Java <-> Python |
| 11:00 a 13:00 | Arquitetura | Onde o Protobuf encontra o gRPC (Stubs e Boilerplate) |
| 13:00 a 15:00 | Fechamento | Trade-offs e Espaço para Perguntas |

## Roteiro Falado

**1. Abertura e o Problema (2 min)**
* *(Vitória/Higor)*: Comece por apresentar o título: "Protobuf em Profundidade: Serialização e Contratos para Sistemas Distribuídos Multiplataforma". 
* Explique a imagem do **Abismo de Serialização**: de um lado um servidor Java, do outro um Python. O JSON é lento e incerto. Como garantir que eles conversam sem falhas?

**2. O Contrato Canônico (3 min)**
* Apresente a solução: o Protobuf. Mostre como o arquivo `.proto` atua como o **contrato canônico** de todo o sistema. Ele define a interface remota de forma centralizada.

**3. Modelagem Avançada (3 min)**
* Aprofunde-se tecnicamente. Explique a Regra de Ouro dos Enums: o primeiro valor DEVE ser sempre `0` (usando `STATUS_UNSPECIFIED = 0` como fallback).
* Explique a regra de *Nested Messages*: só as usamos se o escopo for estritamente local. Se a mensagem for partilhada, movemo-la para a raiz.

**4. Demonstração Prática (3 min)**
* Mostre a Calculadora Distribuída a funcionar. Execute o Java no fundo, abra o Python e faça um cálculo. 
* Mostre o que acontece quando tentamos dividir por zero (como o erro é transportado de forma tipada do Java para o Python pelo contrato).

**5. Fechamento e Geração de Código (2 min)**
* Explique "Onde o Protobuf encontra o gRPC": não escrevemos rotas HTTP. O gRPC gera *Stubs* no cliente e *Esqueletos* no servidor, eliminando o código *boilerplate*.

**6. Debate / Perguntas (2 min)**
* "Considerando o *abismo de serialização*, em que cenários do vosso dia a dia acham que o REST/JSON seria ainda melhor que o gRPC/Protobuf?"