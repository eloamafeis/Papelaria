package papelaria.estoque;




// --- ATRIBUTOS muito bem selcionados ---
public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int estoque;
    private String imagem;
    private String caminhoImagem;
    
    
   // --- Bob e os construtores ---
    public Produto() {
    }
    
// Somente o que precisa - ID, nome, preço e estoque de uma vez
    public Produto(int id, String nome, double preco, int estoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

   // --- Meus get e set ---
    public int getId() {
        return id;
    }
// do ID
    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
// Do nome
    public void setNome(String nome) {
        this.nome = nome;
    }
// Do preço
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
// Do estoque
    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
// das belas fotografias
    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
// --- os pirinplimplim ---
    
    // --- retornar apenas o nome do produto
    @Override
    public String toString() {
        return this.nome;
    }
    // Retorna o caminho completo ou relativo onde a imagem está armazenada
    public String getCaminhoImagem() {
        return caminhoImagem;
    }
// Define ou altera o caminho da imagem do produto
    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }
}