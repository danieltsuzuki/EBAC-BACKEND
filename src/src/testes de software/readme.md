## Testando Exceções:

Crie um método em sua aplicação que, dado um ID de usuário, retorne os detalhes do usuário. Caso o usuário não seja encontrado, o método deve lançar uma exceção do tipo UserNotFoundException.

Escreva um teste unitário para garantir que, quando o ID de um usuário não for encontrado, a exceção UserNotFoundException seja lançada corretamente.

**Exemplo**: Se o método de serviço getUserById(Long id) não encontrar o usuário, ele deve lançar a exceção. O teste deve verificar se a exceção é lançada quando o ID não existe no banco de dados.

## Criando Mock:

Crie um teste unitário para o método de serviço que chama o repositório. Use o when().thenReturn() para simular a resposta do repositório.

Exemplo: Quando você chamar o método de servico "getUserById(Long id)", dentro dele vai ter a chamada para o repositório "userRepository.findById(1L)", que deve retornar um usuário fictício com o ID 1, que você simulou na resposta do repositório.

Aprofunde-se no uso de verify() para validar se o método de repositório foi chamado corretamente dentro do serviço.

**Exemplo**: No teste unitário, após chamar o método getUserById(Long id), use verify(userRepository).findById(1L), para garantir que o método de repositório foi chamado com o ID correto.

## Testes de Integração:

Crie um teste de integração que simule o fluxo completo de uma requisição REST para obter um usuário, incluindo a interação com o banco de dados em memória (H2).

Utilize a anotação @SpringBootTest para configurar o ambiente de teste.

Simule uma requisição HTTP GET para "/users/{id}" e verifique se o usuário retornado tem os dados corretos.