import ed.ListaEncadeada;

public class Corretora {
    private final String nome;
    private final ListaEncadeada<Investidor> clientes;

    public Corretora(String nome) {
        this.nome = nome;
        this.clientes = new ListaEncadeada<>();
    }

    public String getNome() {
        return nome;
    }
    public ListaEncadeada<Investidor> getClientes() {
        return clientes;
    }
    public void addClientes(Investidor investidor) {
        clientes.add(investidor);
    }

}