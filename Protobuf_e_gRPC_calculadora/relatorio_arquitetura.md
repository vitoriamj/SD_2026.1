# Relatório de Arquitetura — Protobuf em Profundidade

**Autores:** Vitória Mendonça Justino e Higor Ferreira Silva  
**Disciplina:** Sistemas Distribuídos 1 - 2026.1 | Curso: Engenharia de Computação  
**Projeto:** Calculadora Distribuída Multiplataforma (Java/Python)

## 1. Visão Geral

Este projeto implementa um sistema cliente-servidor robusto usando gRPC e Protocol Buffers (Protobuf). O sistema funciona como uma Calculadora Distribuída, em que o cliente (Python) atua como terminal de entrada e o servidor (Java) executa a lógica de negócio pesada, provando a interoperabilidade entre ecossistemas distintos.

## 2. O Problema: Acoplamento e Heterogeneidade

Em sistemas distribuídos reais, temos frequentemente Serviços em Java e Clientes em Python (ou vice-versa) separados por uma rede. Esta divisão cria o que chamamos de **Abismo de Serialização**. Se não houver um formato rigoroso, os dados perdem-se em conversões ou requerem alto processamento de texto (como acontece com o JSON), gerando acoplamento oculto e erros de tipagem entre as equipas.

## 3. Onde o Protobuf encontra o gRPC

Para transpor o "Abismo de Serialização", introduzimos o arquivo `.proto` centralizado. Ele atua como o **contrato canônico** para todo o sistema.

Neste ficheiro, fazemos a definição unificada: descrevemos tanto as estruturas de dados (as *messages* como input/output) quanto as operações de serviço (os *endpoints* invocáveis nativamente, como o método `Somar` ou `Dividir`). O gRPC utiliza este contrato para garantir uma tipagem forte na rede.

## 4. Geração Automática e Fuga do Boilerplate

O grande trunfo arquitetural deste modelo é a geração de código automática. A partir do `.proto`, as ferramentas compilam *Stubs* para o cliente (em Python) e *Esqueletos/Bases* para o servidor (em Java). Isto elimina completamente a escrita de código *boilerplate* de comunicação HTTP, serialização e desserialização manuais.

## 5. Modelagem Avançada: Enums e Nested Messages

O projeto segue as regras estritas de modelagem avançada apresentadas no seminário:

* **Regras Estritas para Enums:** A "regra de ouro" exige que o primeiro valor numérico seja sempre `0` (atuando como fallback/default). Adotamos a convenção `STATUS_UNSPECIFIED = 0` para acomodar falhas de preenchimento ou estados desconhecidos antes de definir os erros reais (ex: divisão por zero).
* **Nested Messages (Composição Interna):** Utilizamos mensagens aninhadas apenas quando o contexto da entidade é estritamente local (para não poluir o namespace global). Se uma estrutura tiver de ser reaproveitada por outras entidades arquiteturais, ela é extraída para o nível raiz do contrato.

## 6. Conclusão

A arquitetura provou que o Protobuf e o gRPC formam uma aliança perfeita para microsserviços. Eles resolvem o problema da heterogeneidade garantindo um contrato forte e uma serialização binária ultrarrápida, permitindo que a Engenharia de Computação construa pontes seguras sobre o "abismo de serialização".