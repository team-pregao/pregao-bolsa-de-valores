public abstract class Ativo {
    protected final Id id;
    protected final String ticker;
    protected final String empresa;
    protected final double valorAtual;

    public Ativo(String empresa, double valorAtual) {
        this.valorAtual = valorAtual;
        this.empresa = empresa;
        id = new Id(Type.ATIVO);
        ticker = getTicker();
    }

    public Ativo(Id id, String ticker, String empresa, double valorAtual) {
        this.id = id;
        this.ticker = ticker;
        this.empresa = empresa;
        this.valorAtual = valorAtual;
    }

    public abstract String getTicker();
    public double getValorAtual() {
        return valorAtual;
    }
    public Id getId() {
        return id;
    }
    public String getEmpresa() {
        return empresa;
    }
}