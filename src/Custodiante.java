import ed.ListaEncadeada;

public class Custodiante {
    private final String nome;
    private final ListaEncadeada<Ativo> ativosCustodiados;
    public Custodiante(String nome) {
        this.nome = nome;
        this.ativosCustodiados = new ListaEncadeada<>();
    }
    public String getNome() {
        return nome;
    }
    public ListaEncadeada<Ativo> getAtivosCustodiados() {
        return ativosCustodiados;
    }
    public void adicionarAtivoCustodiado(Ativo ativo) {
        ativosCustodiados.add(ativo);
    }
    public void removerAtivoCustodiado(Ativo ativo) {
        ativosCustodiados.remove(ativo);
    }
    public void ListaEncadeadaarAtivosCustodiados() {
        System.out.println("Ativos custodiados por " + nome + ":");
        for (Ativo ativo : ativosCustodiados) {
            System.out.println("Código: " + ativo.getTicker() + ", Nome: " + ativo.getTicker());
        }
    }
}