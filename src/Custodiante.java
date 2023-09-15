import ed.ListaEncadeada;

public class Custodiante {
    private String nome;
    private ListaEncadeada<Ativo> ativosCustodiados;
    public Custodiante(String nome) {
        this.nome = nome;
        this.ativosCustodiados = new ListaEncadeada<>();
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public ListaEncadeada<Ativo> getAtivosCustodiados() {
        return ativosCustodiados;
    }
    public void setAtivosCustodiados(ListaEncadeada<Ativo> ativosCustodiados) {
        this.ativosCustodiados = ativosCustodiados;
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
            System.out.println("Código: " + ativo.getCodigo() + ", Nome: " + ativo.getNome());
        }
    }
}