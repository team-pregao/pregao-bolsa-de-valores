public class Ativo {
    private String nome;
    private String codigo;
    private String tipo;
    private float valorAtual;
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public float getValorAtual() {
        return valorAtual;
    }
    public void setValorAtual(float valorAtual) {
        this.valorAtual = valorAtual;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}