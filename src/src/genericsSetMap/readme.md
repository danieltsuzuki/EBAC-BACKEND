- Crie uma classe Produto com os seguintes atributos:

- - nome (String)

- - preço (double)

- Implemente a interface Comparable<Produto> na classe Produto, de forma que os produtos sejam ordenados pelo preço (menor para maior).

- - Dica: Sobrescreva o método "compareTo" da interface, e pesquise pelo método "compare" da Classe Double.

- Crie uma classe chamada ListaUtil, com um método genérico chamado ordenarExibir, que recebe como parâmetro uma lista de qualquer tipo.

- - Utilize na lógica desse método o método "sorte" da Classe Collections, para ordenar a lista.

- - Depois da ordenação, imprima os itens da lista.

- Crie uma List<Produto> com 3 ou mais produtos com nomes e preços variados.

- Chame ListaUtil.ordenarExibir(lista) e veja o resultado impresso.

- Sobrescreva o toString() na classe Produto para mostrar os detalhes formatados.