public class Investidor {
    private final int id;
    private final String nome;
    private double saldo;
    private final Corretora corretora;

    public Investidor(String nome, int id, Corretora corretora) {
        this.nome = nome;
        this.id = id;
        this.saldo = 0;
        this.corretora = corretora;
        this.corretora.getClientes().add(this);
    }

    public void transferir(double quantidade){
        if (quantidade <=0 ){
            throw new IllegalArgumentException();
        }
        saldo+=quantidade;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public Corretora getCorretora() {
        return corretora;
    }

    public double getSaldo() {
        return saldo;
    }

    public void comprarAcao(Ativo ativo, int quantidade) {
        double custoTotal = ativo.getValorAtual() * quantidade;
        if (custoTotal <= saldo) {
            saldo -= custoTotal;
            Historico historico = new Historico(ativo, quantidade, (float) custoTotal, 0);
            corretora.getCarteira().addTransacao(historico);
            System.out.println(nome + " comprou " + quantidade + " ações da empresa " + ativo.getNome());
        } else {
            throw new IllegalArgumentException("Saldo insuficiente para comprar ações.");
        }
    }

    public void venderAcao(Ativo ativo, int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade de ações a ser vendida não pode ser negativa.");
        }
        if (corretora.getCarteira().getAtivos().getIndex(ativo) == -1){
            throw new IllegalArgumentException("Voce nao possui essa ação para vender");
        }

        double valorVenda = ativo.getValorAtual() * quantidade;
        saldo += valorVenda;
        var historico = new Historico(ativo, quantidade, (float) valorVenda, 1);
        corretora.getCarteira().addTransacao(historico);
        System.out.println(nome + " vendeu " + quantidade + " ações da empresa " + ativo.getNome());
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            string.append((i < String.valueOf(id).length())?String.valueOf(id).toCharArray()[i]:" ");
        }

        for (int i = 0; i < 50; i++) {
            string.append((i < nome.length())?nome.toCharArray()[i]:" ");
        }
        for (int i = 0; i < 12; i++) {
            string.append((i < String.valueOf(saldo).length())?String.valueOf(saldo).toCharArray()[i]:" ");
        }

        return string.toString();
    }
}