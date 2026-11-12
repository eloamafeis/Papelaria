package papelaria.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import papelaria.conexao.Conexao;
import papelaria.estoque.Produto;

// realiza todas as operações do banco de dados referentes a produtos 


public class ProdutoDAO {

    // --- MÉTODO CADASTRAR ---
    
    // Insere um novo produto no banco de dados
    public boolean cadastrar(Produto p) {
        String sql = "INSERT INTO produto (nome, preco, estoque, imagem) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = Conexao.getConexao();
            
            // Define os valores dos produtos, a partir das informações 
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setInt(3, p.getEstoque());
            stmt.setString(4, p.getImagem());
            stmt.executeUpdate();// Executa o comando de inserção
            return true;
            // Em caso de erro, exibe mensagem
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar produto: " + e.getMessage());
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Erro do Banco: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexao: " + e.getMessage());
            }
        }
    }

    // --- MÉTODO EDITAR ---
    
    // Atualiza os dados de um produto existente no banco
    public boolean editar(Produto p) {
        String sql = "UPDATE produto SET nome = ?, preco = ?, estoque = ?, imagem = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = Conexao.getConexao();
            // Define os novos valores
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setInt(3, p.getEstoque());
            stmt.setString(4, p.getImagem());
            stmt.setInt(5, p.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao editar produto: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexao: " + e.getMessage());
            }
        }
    }

    
    // --- MÉTODO EXCLUIR ---
    
    // Remove um produto do banco de dados
    public boolean excluir(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir produto: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexao: " + e.getMessage());
            }
        }
    }

    // --- MÉTODO LISTAR TODOS ---
    
    // Consulta todos os registros da tabela produto e retorna uma lista preenchida
    public List<Produto> listarTodos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
                p.setEstoque(rs.getInt("estoque"));
                p.setImagem(rs.getString("imagem"));
                lista.add(p);// Adiciona o produto preenchido à lista
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexao: " + e.getMessage());
            }
        }
        return lista;
    }
    // --- MÉTODO ATUALIZAR
    
    
public boolean atualizar(Produto p) {
        String sql = "UPDATE produto SET nome = ?, preco = ?, estoque = ?, imagem = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setInt(3, p.getEstoque());
            stmt.setString(4, p.getImagem());
            stmt.setInt(5, p.getId());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
            return false;
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar conexao: " + e.getMessage());
            }
        }
}
}
   