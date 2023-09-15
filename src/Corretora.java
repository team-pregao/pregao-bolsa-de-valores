import ed.ListaEncadeada;

public class Corretora {
    private String nome;
    private ListaEncadeada<Investidor> clientes;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public ListaEncadeada<Investidor> getClientes() {
        return clientes;
    }
    public void setClientes(ListaEncadeada<Investidor> clientes) {
        this.clientes = clientes;
    }
}