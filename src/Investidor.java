import ed.ListaEncadeada;

public class Investidor {
    private final Id id;
    private final String nome;
    private double saldo;
    private final ListaEncadeada<Corretora> corretora;

    public Investidor(String nome, int id, Corretora corretora) {
        this.nome = nome;
        this.id = new Id(Type.INVESTIDOR);
        this.saldo = 0;
        this.corretora = new ListaEncadeada<>();
        addCorretora(corretora);
        corretora.addClientes(this);
    }

    public Investidor(Id id, String nome, double saldo, ListaEncadeada<Corretora> corretora) {
        this.id = id;
        this.nome = nome;
        this.saldo = saldo;
        this.corretora = corretora;
    }

    public String getNome() {
        return nome;
    }

    public Id getId() {
        return id;
    }

    public ListaEncadeada<Corretora> getCorretora() {
        return corretora;
    }

    public double getSaldo() {
        return saldo;
    }
    public void addCorretora(Corretora corretora){
        corretora.addClientes(this);
        this.corretora.add(corretora);
    }

    public void comprarAcao(Ativo ativo, int quantidade, Corretora corretora) {
        if (this.corretora.getIndex(corretora) == -1){
            throw new IllegalArgumentException("Voce nao é cliente dessa corretora");
        }
        double custoTotal = ativo.getValorAtual() * quantidade;
        if (custoTotal > saldo){
            throw new IllegalArgumentException("Saldo insuficiente para comprar ações." + "\nSaldo: " + saldo + " Custo Total: " + custoTotal);
        }

        corretora.ordenarComprarAcao(ativo, quantidade, this);
        saldo -= custoTotal;
    }

    public void venderAcao(Ativo ativo, int quantidade, Corretora corretora) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade de ações a ser vendida não pode ser negativa.");
        }
        corretora.ordenarVenderAcao(ativo, quantidade, this);

        double valorVenda = ativo.getValorAtual() * quantidade;
        saldo += valorVenda;

        System.out.println(nome + " vendeu " + quantidade + " ações da empresa " + ativo.getTicker());
    }

    public void transferir(int i) {
        saldo += i;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id.getId()).append("\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Saldo: ").append(saldo).append("\n");
        sb.append("Corretoras: ");
        for (Corretora corretora : corretora) {
            sb.append(corretora.getNome()).append(", ");
        }
        sb.setLength(sb.length() - 2); // Remover a vírgula extra no final
        sb.append("\n");

        return sb.toString();
    }

}