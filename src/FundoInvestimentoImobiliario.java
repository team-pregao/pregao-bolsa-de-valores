public class FundoInvestimentoImobiliario extends Ativo{
    public FundoInvestimentoImobiliario(String empresa, double valorAtual) {
        super(empresa, valorAtual);
    }
    @Override
    public String getTicker() {
        return super.empresa.substring(0, 4).toUpperCase() + 11;
    }
}
