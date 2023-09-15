public class Historico {
    private Ativo ativo;
    private int quantidade;
    private float valor;
    private String tipo; // Pode ser "Compra" ou "Venda";
    public Ativo getAtivo() {
        return ativo;
    }
    public void setAtivo(Ativo ativo) {
        this.ativo = ativo;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
