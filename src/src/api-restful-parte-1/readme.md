# API REST de Cadastro de Produtos com Spring Boot

## 📋 Descrição do Projeto

Desenvolva uma API REST simples utilizando Spring Boot que gerencie um cadastro de produtos. A API deve permitir:

1. **Consultar produtos (GET)**
2. **Adicionar novos produtos (POST)**
3. **Atualizar informações de produtos (PUT)**
4. **Deletar produtos (DELETE)**

O objetivo é praticar a criação de controladores, o mapeamento de endpoints, o recebimento de parâmetros na URL e no corpo da requisição, além do uso correto dos métodos HTTP.

---

## 🎯 Instruções

### 1. Classe Produto

Crie uma classe `Produto` com os seguintes atributos:

- `id` (Integer)
- `nome` (String)
- `preco` (Double)

### 2. Controller

Crie um `@RestController` chamado `ProdutoController` e implemente os seguintes endpoints:

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| **GET** | `/produtos` | Retorna uma lista de produtos |
| **GET** | `/produtos/{id}` | Retorna um produto específico pelo ID |
| **POST** | `/produtos` | Adiciona um novo produto (dados enviados no corpo da requisição) |
| **PATCH** | `/produtos/{id}` | Atualiza os dados de um produto existente (dados enviados no corpo) |
| **DELETE** | `/produtos/{id}` | Remove um produto pelo ID |

### 3. Anotações Utilizadas

- `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PatchMapping`, `@DeleteMapping`
- `@PathVariable`
- `@RequestBody`

### 4. Armazenamento

- Guarde os produtos em uma **lista estática ou em memória** (não precisa banco de dados)


---

## 🚀 Tecnologias

- Java
- Spring Boot
- Spring Web

---

## 📝 Exemplo de Estrutura