# Sistema de Gerenciamento de Produtos e Categorias com Spring Boot, JPA e Hibernate

## 🎯 Cenário

Você foi designado para criar um sistema simples de gerenciamento de produtos e categorias usando Spring Boot com JPA e Hibernate. O sistema deve permitir cadastrar produtos, associá-los a categorias e consultar os dados de maneira eficiente, com paginação. O sistema também deve ter consultas personalizadas para filtrar produtos por nome e paginar os resultados.

---

## 📝 Passo a passo

### 1. Configuração e inicialização do JPA

- Configure o Spring Boot para usar o JPA com o banco de dados **H2** (ou qualquer banco relacional).
- Defina as propriedades de conexão no `application.properties`.

### 2. Mapeando Tabelas em Classes Java (ORM)

- Crie as entidades `Produto` e `Categoria`, mapeando-as para suas respectivas tabelas no banco de dados.
- A entidade **Produto** deve ter os seguintes atributos:
    - `id`
    - `nome`
    - `descricao`
    - `preco`
- A entidade **Categoria** deve ter os atributos:
    - `id`
    - `nome`
    - `descricao`
- A relação entre `Produto` e `Categoria` deve ser **ManyToOne**, ou seja, um produto pode pertencer a uma categoria, mas uma categoria pode ter muitos produtos.
- Anote as classes com as anotações do JPA (`@Entity`, `@Id`, `@ManyToOne`, etc.).

### 3. Relacionamentos de Tabelas com Hibernate

- O relacionamento entre `Produto` e `Categoria` deve ser mapeado corretamente.
- Use `@JoinColumn` para definir o relacionamento entre as duas entidades.
- Considere usar o `cascade = CascadeType.ALL` para garantir que a operação de persistência da categoria seja propagada para os produtos, quando necessário.

### 4. Realizando leituras com Hibernate usando Spring Data (JPQL)

- Crie um repositório `ProdutoRepository` extendendo `JpaRepository` e implemente uma consulta personalizada com JPQL:
    - Encontre produtos que possuem um nome específico, por exemplo, "Teclado".
    - Utilize `@Query` e parâmetros nomeados ou posicionais na consulta.

### 5. Persistindo Objetos com Hibernate

- Crie um serviço para gerenciar a criação de produtos e categorias.
- Implemente métodos como `save()`, `saveAll()`, `delete()`, e `findById()` para persistir e recuperar produtos e categorias no banco de dados.
- Garanta que o ciclo de vida das entidades seja gerenciado automaticamente pelo Spring Data JPA.

### 6. Carregamentos Eager e Lazy

- Configure o relacionamento `Produto → Categoria` com **EAGER** ou **LAZY**, e explique o impacto no desempenho:
    - Se usar **EAGER**, todos os produtos serão carregados junto com a categoria.
    - Se usar **LAZY**, as categorias serão carregadas sob demanda quando acessadas.

### 7. Trabalhando com paginação de dados

- Crie um método no repositório `ProdutoRepository` que suporte a paginação usando o parâmetro `Pageable`.
- Use `PageRequest.of(page, size)` para definir o número da página e a quantidade de itens por página.

### 8. Aprimorando a paginação com ordenação e filtros

- Implemente ordenação usando `Sort` para ordenar os produtos por nome.
- Adicione um filtro simples que permita consultar produtos por nome e ordená-los de forma ascendente ou descendente.

---

## 🚀 Tecnologias

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database (ou banco relacional de sua escolha)

---

## 📦 Estrutura Sugerida