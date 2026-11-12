package papelaria.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConexao() { 
        try {
            
            String url = "jdbc:mysql://localhost:3306/db_papelaria?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = ""; 
            
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erro de Conexão: " + e.getMessage());
            return null;
        }
    }
}