Crie um programa Java que conecte a um banco de dados **MySQL** e realize um **CRUD** conforme os requisitos abaixo:

1. **Inserir Usuário**
    - Insira um novo usuário em uma tabela chamada `usuarios`, contendo os campos:
        - `id`
        - `nome`
        - `email`

2. **Excluir Usuário**
    - Exclua um usuário com base no **nome informado**.

3. **Uso de PreparedStatement**
    - Utilize `PreparedStatement` para ambas as operações anteriores.

4. **Atualizar Usuário**
    - Atualize o **email** de um usuário, localizando-o pelo nome na tabela `usuarios`.

5. **Parâmetros do PreparedStatement**
    - Utilize `PreparedStatement` para passar o **novo email** e o **nome** como parâmetros.

6. **Consultar Usuários**
    - Consulte **todos os registros** da tabela `usuarios`.

7. **Listagem no Console**
    - Liste no console o:
        - `id`
        - `nome`
        - `email`
          de cada usuário recuperado.

8. **Uso do ResultSet**
    - Utilize `ResultSet` para percorrer os dados retornados pela consulta.