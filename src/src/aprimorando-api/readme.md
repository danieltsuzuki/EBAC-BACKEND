Vamos cria uma API RESTful com Validação de Dados e Documentação Automática (Swagger)

## Objetivo:

### Criar uma API RESTful simples utilizando Spring Boot que permita a criação de usuários e que tenha:

- Validação de dados de entrada.

- Documentação automática gerada pelo Swagger.

- Utilização de métodos HTTP seguros e idempotentes.

- Implementação de um simples endpoint de criação de usuário com validação.

## Passo a passo:

### Criação de uma API RESTful:

- Crie um endpoint POST para criar um usuário.

- O corpo da requisição deve conter os dados do usuário, como nome (String) e idade (Integer).

- Utilize os métodos @NotNull e @Size para garantir que o nome não seja nulo ou muito curto e que a idade seja um valor válido.

### Validação de Dados de Entrada:

- Aplique as anotações @NotNull e @Size nas propriedades do usuário para garantir que o nome tenha pelo menos 3 caracteres e que a idade seja um número positivo.

- Utilize @Valid para validar o corpo da requisição no método do controlador.

- trate erros de validação utilizando @ControllerAdvice para retornar uma mensagem personalizada de erro quando os dados não forem válidos.

### Swagger - Documentação Automática da API:

- Adicione o Swagger ao projeto e configure para gerar a documentação da API automaticamente.

- A documentação gerada deve ser acessível por meio de uma interface interativa.

- O Swagger deve documentar o endpoint de criação de usuário e os campos necessários para a requisição.

### Métodos HTTP Seguros e Idempotentes:

- O endpoint de criação de usuário deve ser idempotente, o que significa que, se a mesma requisição for enviada várias vezes, o resultado será o mesmo.

- Utilize o método POST para criação, mas garanta que ele seja idempotente, respondendo com o mesmo status (201 Created) e os mesmos dados, mesmo em requisições repetidas.

- Garanta que a resposta para requisições válidas seja o HTTP status 201.