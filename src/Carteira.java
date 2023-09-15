import ed.ListaEncadeada;

public class Carteira {
    private ListaEncadeada<Ativo> ativos;
    public ListaEncadeada<Historico> historico;
    public ListaEncadeada<Ativo> getAtivos() {
        return ativos;
    }

    public ListaEncadeada<Historico> getHistorico() {
        return historico;
    }
    public void setAtivos(ListaEncadeada<Ativo> ativos) {
        this.ativos = ativos;
    }

    public Carteira() {
        ativos = new ListaEncadeada<>();
        historico = new ListaEncadeada<>();
    }
}

