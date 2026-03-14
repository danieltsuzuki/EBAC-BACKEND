<strong>Objetivo</strong>: criar uma arquitetura de microsserviços com no mínimo 4 serviços.

Pense em uma arqutetura de microsserviços, tente pensar nas questões de domínio, resiliência e padrões de microsserviços.

Os serviços devem ser independentes e possuir seus próprios mecanismos de resiliência.

<strong>Passo a passo</strong>:

1. Para simularmos o padrão database per service em forma de diferentes tabelas ao invés de diferentes bancos de dados, cada microsserviço deve ser sua tabela ou domínio específico de tabelas, sem relações com tabelas de outros domínios.

2. Implemente o padrão Circuit Breaker e com método fallback.

3. Ao menos 1 fluxo deve ter comunicação com todos os microsserviços.