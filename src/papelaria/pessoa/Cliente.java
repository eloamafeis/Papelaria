package papelaria.pessoa;

public class Cliente {
    // ATRIBUTOS
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
// --- CONSTRUTORES ---
    public Cliente() {}
// --- MÉTODOS GETTERS E SETTERS ---
    
    // Retorna o identificador (ID) do cliente
    public int getId() { return id; }
    // Define ou altera o identificador (ID) do cliente
    public void setId(int id) { this.id = id; }
    // Retorna o nome do cliente
    public String getNome() { return nome; }
    // Define ou altera o nome do cliente
    public void setNome(String nome) { this.nome = nome; }
    // Retorna o CPF do cliente
    public String getCpf() { return cpf; }
    // Define ou altera o CPF do cliente
    public void setCpf(String cpf) { this.cpf = cpf; }
    // Retorna o telefone do cliente
    public String getTelefone() { return telefone; }
    // Define ou altera o telefone do cliente
    public void setTelefone(String telefone) { this.telefone = telefone; }

    // Sobrescrita do método toString() para retornar apenas o nome do cliente 
    // (ótimo para exibir o nome diretamente em componentes visuais como JLists ou ComboBoxes)
    @Override
    public String toString() {
        return this.nome;
    }
}