package papelaria.operacao;

import papelaria.pessoa.Cliente;
import java.util.ArrayList;
import java.util.List;

//atribuidos
public class Venda {
    private int id;
    private Cliente cliente;
    private double valorTotal;
    private List<ItemVenda> itens;

    // os bob
    public Venda() {
        this.itens = new ArrayList<>();
    }
// e os birgnith do get set
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public double getValorTotal() { return valorTotal; }
    public void setValorTotal(double valorTotal) { this.valorTotal = valorTotal; }
    public List<ItemVenda> getItens() { return itens; }
    public void setItens(List<ItemVenda> itens) { this.itens = itens; }
}