package papelaria.repositorio;

import papelaria.conexao.Conexao;
import papelaria.operacao.ItemVenda;
import papelaria.operacao.Venda;
import papelaria.pessoa.Cliente;
import papelaria.estoque.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;  

public class VendaDAO {
    private Conexao conexao;

    public VendaDAO() {
        this.conexao = new Conexao();
    }

    // --- MÉTODO SALVAR VENDA ---
    
    // Realiza o salvamento completo de uma venda
    public boolean salvarVenda(Venda venda) {
        String sqlVenda = "INSERT INTO venda (cliente_id, valor_total) VALUES (?, ?)";
        String sqlItem = "INSERT INTO item_venda (venda_id, produto_id, quantidade, valor_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        String sqlEstoque = "UPDATE produto SET estoque = estoque - ? WHERE id = ?";

        Connection conn = null;
        try {
            conn = conexao.getConexao();
            conn.setAutoCommit(false);

            // Salva o cabeçalho da venda
            PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS);
            stmtVenda.setInt(1, venda.getCliente().getId());
            stmtVenda.setDouble(2, venda.getValorTotal());
            stmtVenda.executeUpdate();
                
            // Captura o ID gerado para a venda recém-criada
            ResultSet rs = stmtVenda.getGeneratedKeys();
            int vendaId = 0;
            if (rs.next()) {
                vendaId = rs.getInt(1);
            }
            
            //prepara o sql para os itens da venda
            PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
            PreparedStatement stmtEstoque = conn.prepareStatement(sqlEstoque);
            //insere o item da venda
            for (ItemVenda item : venda.getItens()) {
                stmtItem.setInt(1, vendaId);
                stmtItem.setInt(2, item.getProduto().getId());
                stmtItem.setInt(3, item.getQuantidade());
                stmtItem.setDouble(4, item.getValorUnitario());
                stmtItem.setDouble(5, item.getSubtotal());
                stmtItem.executeUpdate();
// Atualiza/subtrai a quantidade vendida do estoque
                stmtEstoque.setInt(1, item.getQuantidade());
                stmtEstoque.setInt(2, item.getProduto().getId());
                stmtEstoque.executeUpdate();
            }

            conn.commit();
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            System.out.println("Erro ao salvar venda: " + e.getMessage());
            return false;
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
// --- MÉTODO LISTAR HISTÓRICO DE VENDAS ---
    
    // Consulta o histórico completo
    // retornando uma lista de vetores de objetos
    public List<Object[]> listarHistoricoVendas() {
        List<Object[]> lista = new ArrayList<>();
        
        String sql = "SELECT v.id AS venda_id, c.nome AS cliente_nome, p.nome AS produto_nome, "
                   + "iv.quantidade, iv.valor_unitario, iv.subtotal "
                   + "FROM item_venda iv "
                   + "JOIN venda v ON iv.venda_id = v.id "
                   + "LEFT JOIN cliente c ON v.cliente_id = c.id "
                   + "JOIN produto p ON iv.produto_id = p.id "
                   + "ORDER BY v.id DESC";

        Connection conn = null;
        try {
            conn = conexao.getConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] linha = new Object[]{
                    rs.getInt("venda_id"),
                    rs.getString("cliente_nome") != null ? rs.getString("cliente_nome") : "Cliente não informado",
                    rs.getString("produto_nome"),
                    rs.getInt("quantidade"),
                    rs.getDouble("valor_unitario"),
                    rs.getDouble("subtotal")
                };
                lista.add(linha);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar histórico de vendas: " + e.getMessage());
        } finally {
            if (conn != null) {
                try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
        
        return lista;// Retorna a lista contendo as linhas do histórico
    }
}