import ed.ListaEncadeada;
import out.Acao;

public class Investidor {
    private final int id;
    private final String nome;
    private ListaEncadeada<Carteira> carteiras;

    public Investidor(String nome, int id) {
        this.nome = nome;
        this.id = id;
        this.carteiras = new ListaEncadeada<>();
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public ListaEncadeada<Carteira> getCarteiras() {
        return carteiras;
    }
/*
    public void comprarAcao(Acao acao, int quantidade) throws IllegalAccessException {
        double custoTotal = acao.getPreco() * quantidade;
        double saldo;
        if (custoTotal <= saldo) {
            saldo -= custoTotal;
            acao.comprar(quantidade, this);
            System.out.println(nome + " comprou " + quantidade + " ações da empresa " + acao.getEmpresa().getNome());
        } else {
            throw new IllegalAccessException("Saldo insuficiente para comprar ações.");
        }
    }

    public void venderAcao(Acao acao, int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade de ações a ser vendida não pode ser negativa.");
        }

        double valorVenda = acao.getPreco() * quantidade;
        saldo += valorVenda;
        acao.vender(quantidade, this);
        System.out.println(nome + " vendeu " + quantidade + " ações da empresa " + acao.getEmpresa().getNome());
    }
-/*/
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            string.append((i < String.valueOf(id).length())?String.valueOf(id).toCharArray()[i]:" ");
        }

        for (int i = 0; i < 50; i++) {
            string.append((i < nome.length())?nome.toCharArray()[i]:" ");
        }/*
        for (int i = 0; i < 12; i++) {
            string.append((i < String.valueOf(saldo).length())?String.valueOf(saldo).toCharArray()[i]:" ");
        }*/

        return string.toString();
    }
}