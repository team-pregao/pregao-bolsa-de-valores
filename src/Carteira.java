import ed.ListaEncadeada;
import ed.Mapa;

public class Carteira {
    private final Id id;
    private final Id idInvestidor;
    private final Id idCorretora;
    private final Mapa<Ativo, Integer> ativos;
    private final ListaEncadeada<Historico> historico;
    private final int COMPRA = 0;
    private final int VENDA = 1;

    public Carteira(Id idInvestidor, Id idCorretora) {
        this.idInvestidor = idInvestidor;
        this.idCorretora = idCorretora;
        this.id = new Id(Type.CARTEIRA);
        ativos = new Mapa<>();
        historico = new ListaEncadeada<>();
    }
    public Mapa<Ativo, Integer> getAtivos() {
        return ativos;
    }
    public ListaEncadeada<Historico> getHistorico() {
        return historico;
    }
    public Id getId() {
        return id;
    }

    public Id getIdInvestidor() {
        return idInvestidor;
    }

    public Id getIdCorretora() {
        return idCorretora;
    }
    public void addTransacao(Historico historico){
        this.historico.add(historico);
        Integer quantidadeAnt = ativos.get(historico.getAtivo());
        quantidadeAnt = quantidadeAnt == null ? 0 : quantidadeAnt;
        if (historico.getTipo() == COMPRA){
            ativos.put(historico.getAtivo(), historico.getQuantidade() + quantidadeAnt);
            System.out.println(historico.getInvestidor().getNome() + " comprou " + historico.getQuantidade() + " ações da empresa " + historico.getAtivo().getTicker());
        } else if (historico.getTipo() == VENDA){
            if (quantidadeAnt - historico.getQuantidade() < 0){
                throw new RuntimeException();
            }
            if (quantidadeAnt - historico.getQuantidade() == 0){
                ativos.remove(historico.getAtivo());
                historico.getInvestidor().transferir(historico.getQuantidade());
            } else {
                ativos.put(historico.getAtivo(), historico.getQuantidade() - quantidadeAnt);
                historico.getInvestidor().transferir(historico.getQuantidade());
            }
        }
    }
}

