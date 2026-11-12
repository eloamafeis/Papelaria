package papelaria.repositorio;

import papelaria.conexao.Conexao;
import papelaria.estoque.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


// Definição da classe ClienteDAO responsável pela comunicação 
// com o banco de dados para gerenciar os dados dos clientes
public class ClienteDAO {
    private Conexao conexao;

    public ClienteDAO() {
        this.conexao = new Conexao();
    }

    // --- MÉTODO CADASTRAR ---
    
    
    // Insere um novo registro de cliente no banco de dados
    public boolean cadastrar(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, cpf, telefone, email) VALUES (?, ?, ?, ?)";
        
        // Utilizado para garantir que a conexão e o comando SQL sejam fechados automaticamente
        try (Connection conn = conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Substitui os pontos de interrogação (?) pelos valores reais do objeto
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            
            //mensagem de erro bem humilde
            System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // --- MÉTODO LISTAR TODOS ---
    
    // Consulta todos os clientes cadastrados
    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection conn = conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            // Percorre cada linha retornada pela consulta no banco de dados
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                lista.add(c); // Adiciona o cliente né
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    // --- MÉTODO EDITAR ---
    
    // Atualiza os dados de um cliente existente com base no seu ID
    public boolean editar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, cpf = ?, telefone = ?, email = ? WHERE id = ?";
        try (Connection conn = conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            // Define os novos valores nos parâmetros da instrução SQL
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setInt(5, cliente.getId());
            stmt.execute(); // aqui faz a atualização no banco
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao editar cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // --- MÉTODO EXCLUIR ---
    
    
    // Remove um cliente do banco de dados
    public boolean excluir(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (Connection conn = conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);// Define o ID do cliente que será deletado
            stmt.execute();// Executa a exclusão
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}