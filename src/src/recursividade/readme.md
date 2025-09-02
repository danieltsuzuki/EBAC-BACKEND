# Você deverá resolver esse problema recursivamente. Para isso, considere o problema:

- Você está subindo uma escada. São necessários n degraus para chegar ao topo, sendo que podemos subir 1 ou 2 degraus por vez.
- Receba uma variável n que corresponde ao número de degraus que precisaremos "subir" para chegar ao topo.
- Se podemos subir 1 ou 2 degraus por vez, de quantas maneiras distintas é possível chegar ao topo?

Exemplo 1:
```
Entrada: n = 2

Saída correta: 2

Explicação: Há duas maneiras distintas de subir até o topo, isto é, escalar 2 degraus:

1. 1 degrau + 1 degrau

2. 2 degraus
```
Exemplo 2:
```
Entrada: n = 3

Saída correta: 3

Explicação: Há três maneiras distintas de subir até o topo, isto é, escalar 3 degraus:

1. 1 degrau + 1 degrau + 1 degrau

2. 1 degrau + 2 degraus

3. 2 degraus + 1 degrau
```
Exemplo 3:
```
Entrada: n = 4

Saída correta: 5

Explicação: Há cinco maneiras distintas de subir até o topo, isto é, escalar 4 degraus:

1. 1 degrau + 1 degrau + 1 degrau + 1 degrau

2. 2 degraus + 1 degrau + 1 degrau

3. 1 degrau + 2 degraus + 1 degrau

4. 1 degrau + 1 degrau + 2 degraus

5. 2 degraus + 2 degraus
```