# Estrutura e Texto dos Slides

## Slide 1 — Capa
**Protobuf em Profundidade**
Serialização e Contratos para Sistemas Distribuídos Multiplataforma
*Autores: Vitória Mendonça Justino e Higor Ferreira Silva*
*Disciplina: Sistemas Distribuídos 1 - 2026.1 | Curso: Engenharia de Computação*

## Slide 2 — O Problema: Acoplamento e Heterogeneidade
**O Abismo de Serialização**
Sistemas reais têm servidores em Java e clientes em Python separados por uma rede. Transitar dados livremente (como em JSON) gera um "abismo" de tipagem, problemas de conversão e acoplamento perigoso entre as linguagens.

## Slide 3 — A Solução: O Arquivo Centralizado
**O Contrato Canônico**
O arquivo `.proto` é a solução. Ele atua como o contrato canônico, imutável e central, que dita as regras para todo o ecossistema. Ele contém a definição unificada tanto das estruturas de dados quanto das operações remotas.

## Slide 4 — Modelagem Avançada: Enums
**Regras Estritas**
* **Regra de Ouro:** O primeiro valor numérico DEVE ser sempre `0`. Ele atua como um *fallback* seguro.
* **Convenção:** Adotar o padrão `STATUS_UNSPECIFIED = 0` para acomodar falhas de preenchimento ou estados desconhecidos antes do sistema falhar.

## Slide 5 — Modelagem Avançada: Nested Messages
**Composição Interna**
* **Quando Usar:** Apenas se o contexto da entidade for estritamente local, impedindo a poluição do *namespace* global.
* **Quando Evitar:** Se a estrutura tem qualquer hipótese de ser reaproveitada por outras entidades do sistema, extraia para o nível raiz do ficheiro.

## Slide 6 — Onde Protobuf encontra o gRPC
**A Interface da API Remota**
O Protobuf descreve o `service Calculadora` (o método invocável nativamente) e as `messages` (input/output de resposta forte). O gRPC pega nisto e fornece o motor de transporte por baixo (HTTP/2).

## Slide 7 — Fim do Boilerplate
**Geração de Código Automática**
O gRPC acaba com a escrita manual de serializadores. Ferramentas compilam o `.proto` e criam *Stubs* (no cliente Python) e *Esqueletos* (no servidor Java), gerando todo o código necessário para a comunicação em rede.

## Slide 8 — Demonstração da Calculadora
**Atravessando o Abismo na Prática**
*(Apresentação no terminal)*
O cliente Python envia operandos via CLI. O Servidor Java resolve o cálculo e devolve. Tudo através de mensagens binárias perfeitamente estruturadas.

## Slide 9 — Conclusão
**Sistemas Distribuídos sem Fronteiras**
O Protobuf serializa os dados com velocidade inigualável. O gRPC lida com a rede. Juntos, resolvem o problema da heterogeneidade multiplataforma com um contrato canônico inquebrável.

## Slide 10 — Perguntas e Respostas
*(Espaço aberto para dúvidas da turma)*