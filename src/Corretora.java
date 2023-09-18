import ed.ListaEncadeada;
import ed.Pilha;

public class Corretora {
    private final Id id;
    private final String nome;
    private final ListaEncadeada<Investidor> clientes;
    private final ListaEncadeada<Carteira> carteiras;
    private final Pilha<Historico> ordens;

    private final int COMPRA = 0;
    private final int VENDA = 1;

    public Corretora(String nome) {
        this.nome = nome;
        this.ordens = new Pilha<>();
        this.carteiras = new ListaEncadeada<>();
        this.clientes = new ListaEncadeada<>();
        this.id = new Id(Type.CORRETORA);
    }

    public String getNome() {
        return nome;
    }
    public ListaEncadeada<Investidor> getClientes() {
        return clientes;
    }
    public Id getId() {
        return id;
    }
    public ListaEncadeada<Carteira> getCarteiras() {
        return carteiras;
    }

    public void addClientes(Investidor investidor) {
        clientes.add(investidor);
        Carteira carteira = new Carteira(investidor.getId(), getId());
        carteiras.add(carteira);
    }
    public void addOrdem(Historico historico) {
        ordens.push(historico);
    }

    public void executarOrdens(){
        while (!ordens.isEmpty()){
            Historico current = ordens.pop();
            for (Carteira carteira :
                    carteiras) {
                if (carteira.getIdInvestidor().equals(current.getInvestidor().getId())){
                    carteira.addTransacao(current);
                }
            }
        }
    }

    public void ordenarComprarAcao(Ativo ativo, int quantidade, Investidor investidor){
        if (clientes.getIndex(investidor) == -1){
            throw new IllegalArgumentException("Esse investidor nao esta cadastrado");
        }
        double custoTotal = ativo.getValorAtual() * quantidade;
        Historico historico = new Historico(ativo, quantidade, custoTotal, COMPRA, investidor, this);
        addOrdem(historico);

    }

    public void ordenarVenderAcao(Ativo ativo, int quantidade, Investidor investidor){
        if (clientes.getIndex(investidor) == -1){
            throw new IllegalArgumentException("Esse investidor nao esta cadastrado");
        }
        double custoTotal = ativo.getValorAtual() * quantidade;
        Historico historico = new Historico(ativo, quantidade, custoTotal, VENDA, investidor, this);
        addOrdem(historico);
    }

}