1. Configure um simples endpoint seguro no Spring Boot que só pode ser acessado após autenticação com usuário e senha (sem integração com OAuth2 ou JWT, para simplicidade).

2. O usuário e senha devem ser enviados no corpo da requisição.

3. Crie uma classe para receber o usuário e senha.

4. Busque o usuário no banco de dados e verifique se a senha enviada é igual a que está vinculada a esse usuário do banco de dados.

5. Se o usuário não existir, retorne uma mensagem de erro, informando que o usuário não existe.

6. Se a senha estiver errada, retorne uma mensagem de erro, informando que a senha está errada.