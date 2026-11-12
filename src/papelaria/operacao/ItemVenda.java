package papelaria.operacao;

import papelaria.estoque.Produto;

// olha uma classe
public class ItemVenda {
    
    private int id;
    private Produto produto;
    private int quantidade;
    private double valorUnitario;
    private double subtotal;

    public ItemVenda() {}

    // Construtor completo e funcional, ele inicializa o produto, quantidade e preço unitário,
    // e já calcula automaticamente o subtotal do item
    public ItemVenda(Produto produto, int quantidade, double valorUnitario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.subtotal = quantidade * valorUnitario;
    }
// get e set dinovo
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public double getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(double valorUnitario) { this.valorUnitario = valorUnitario; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}