package CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/ebaccrud";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);) {
//            inserirUsuario(connection, "teste", "teste@mail.com");
//            removerUsuario(connection, "teste");
//            atualizarEmail(connection ,"teste", "1111@mail.com");
            listarUsuarios(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void inserirUsuario(Connection connection, String nome, String email) {
        String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
            System.out.printf("Usuário inserido %s com sucesso!", nome);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void listarUsuarios(Connection connection) {
        String sql = "SELECT * FROM usuarios";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            var resultSet = preparedStatement.executeQuery();
            System.out.println("Lista de Usuários:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                System.out.printf("ID: %d, Nome: %s, Email: %s%n", id, nome, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void removerUsuario(Connection connection, String nome) {
        String sql = "DELETE FROM usuarios WHERE nome = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, nome);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.printf("Usuário com NOME: '%s' removido com sucesso!%n", nome);
            } else {
                System.out.printf("Nenhum usuário encontrado com NOME '%s'.%n", nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void atualizarEmail(Connection connection, String nome, String novoEmail) {
        String sql = "UPDATE usuarios SET email = ? WHERE nome = ?";
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, novoEmail);
            preparedStatement.setString(2, nome);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.printf("Email do usuário com NOME: '%s' atualizado para '%s' com sucesso!%n", nome, novoEmail);
            } else {
                System.out.printf("Nenhum usuário encontrado com NOME '%s'.%n", nome);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}