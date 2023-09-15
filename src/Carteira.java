import ed.ListaEncadeada;

public class Carteira {
    private final ListaEncadeada<Ativo> ativos;
    private final ListaEncadeada<Historico> historico;

    public Carteira() {
        ativos = new ListaEncadeada<>();
        historico = new ListaEncadeada<>();
    }
    public ListaEncadeada<Ativo> getAtivos() {
        return ativos;
    }
    public ListaEncadeada<Historico> getHistorico() {
        return historico;
    }
    public void addTransacao(Historico historico){
        this.historico.add(historico);
        ativos.add(historico.getAtivo());
    }
}

