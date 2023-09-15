public class Ativo {
    private final String nome;
    private final String codigo;
    private final String tipo;
    private final double valorAtual;

    public Ativo(String nome, String codigo, String tipo, double valorAtual) {
        this.nome = nome;
        this.codigo = codigo;
        this.tipo = tipo;
        this.valorAtual = valorAtual;
    }

    public String getNome() {
        return nome;
    }
    public String getTipo() {
        return tipo;
    }
    public double getValorAtual() {
        return valorAtual;
    }
    public String getCodigo() {
        return codigo;
    }

}