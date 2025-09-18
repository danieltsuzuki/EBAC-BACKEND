Descrição: Crie um programa em Java que simule o cadastro de usuários em um sistema.

<b>Passo a passo:</b>

- Peça o nome e a idade do usuário.

- Se a idade for menor que 18 anos, lance uma exceção personalizada chamada IdadeInvalidaException, informando que o usuário precisa ser maior de idade para se cadastrar.

- Se for maior de idade, imprima uma mensagem informando que o "usuário {nome}, foi cadastrado com sucesso."

- Utilize try/catch/finally para tratar a exceção.

- No bloco finally, sempre exiba a mensagem: "Processo de cadastro finalizado."

<b>Dicas:</b>

- Crie a classe IdadeInvalidaException que herda de Exception.

- Crie uma classe CadastroUsuario, onde vai ter o método main do seu sistema, e antes do metódo main, crie o método "cadastrarUsuario(String nome, int idade) throws IdadeInvalidaException{}",que deve ser chamado dentro do main para verificar a idade do usuário.

- No método main, utilize Scanner para ler o nome e a idade do usuário.