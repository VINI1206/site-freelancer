Estrutura do Banco de Dados

CREATE DATABASE IF NOT EXISTS minifreela;

USE minifreela;

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(20),
    linkedin VARCHAR(150),
    curriculo VARCHAR(100)
);

CONEXÃO E LÓGICA EM JAVA


Conexao.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/minifreela";
    private static final String USER = "root"; // substitua se tiver outro usuário
    private static final String PASSWORD = ""; // coloque sua senha

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}

CadastroLoginApp.java

import java.sql.*;
import java.util.Scanner;

public class CadastroLoginApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n[1] Cadastrar\n[2] Login\n[3] Sair");
            String opcao = sc.nextLine();

            switch (opcao) {
                case "1" -> cadastrar(sc);
                case "2" -> login(sc);
                case "3" -> {
                    System.out.println("Encerrando...");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrar(Scanner sc) {
        try (Connection conn = Conexao.conectar()) {
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Senha: ");
            String senha = sc.nextLine();
            System.out.print("CPF: ");
            String cpf = sc.nextLine();
            System.out.print("Telefone: ");
            String telefone = sc.nextLine();
            System.out.print("LinkedIn: ");
            String linkedin = sc.nextLine();
            System.out.print("Nome do arquivo do currículo: ");
            String curriculo = sc.nextLine();

            String sql = "INSERT INTO usuarios (email, senha, cpf, telefone, linkedin, curriculo) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            stmt.setString(3, cpf);
            stmt.setString(4, telefone);
            stmt.setString(5, linkedin);
            stmt.setString(6, curriculo);

            stmt.executeUpdate();
            System.out.println("Usuário cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void login(Scanner sc) {
        try (Connection conn = Conexao.conectar()) {
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Senha: ");
            String senha = sc.nextLine();

            String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login realizado com sucesso!");
            } else {
                System.out.println("Email ou senha incorretos.");
            }
        } catch (SQLException e) {
            System.out.println("Erro no login: " + e.getMessage());
        }
    }
}
