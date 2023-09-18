public class EntityManager{
    public static String stringField(String value, int maxLenght){
        StringBuilder string = new StringBuilder();
        String sValue = String.valueOf(value);

        for (int i = 0; i < maxLenght; i++) {
            string.append((i < sValue.length()) ? sValue.toCharArray()[i]:" ");
        }
        return string.toString();
    }

    public static String intField(int value, int maxLenght){
        StringBuilder string = new StringBuilder();
        String sValue = String.valueOf(value);

        for (int i = 0; i < maxLenght; i++) {
            string.append((i < sValue.length()) ? sValue.toCharArray()[i]:" ");
        }
        return string.toString();
    }

    public static String doubleField(double value, int maxLenght){
        StringBuilder string = new StringBuilder();
        String sValue = String.valueOf(value);

        for (int i = 0; i < maxLenght; i++) {
            string.append((i < sValue.length()) ? sValue.toCharArray()[i]:" ");
        }
        return string.toString();
    }

    public static String lineInvestidor(Investidor investidor){
        return intField(investidor.getId().getId(), 3) +
                stringField(investidor.getNome(), 100) +
                doubleField(investidor.getSaldo(), 12);
    }

    public static String lineAtivo(Ativo ativo) {
        return intField(ativo.getId().getId(), 3) +
                stringField(ativo.getTicker(), 6) +
                stringField(ativo.getEmpresa(), 100) +
                doubleField(ativo.getValorAtual(), 12);
    }

    public static String lineCorretora(Corretora corretora) {
        return intField(corretora.getId().getId(), 3) +
                stringField(corretora.getNome(), 100);
    }

    public static String lineCarteira(Carteira carteira) {
        return intField(carteira.getId().getId(), 3) +
                intField(carteira.getIdInvestidor().getId(), 3) +
                intField(carteira.getIdCorretora().getId(), 3);
    }

    public static String lineCustodiante(Custodiante custodiante) {
        return stringField(custodiante.getNome(), 100);
    }

    public static String lineHistorico(Historico historico) {
        return intField(historico.getId().getId(), 3) +
                intField(historico.getAtivo().getId().getId(), 3) +
                intField(historico.getQuantidade(), 5) +
                doubleField(historico.getValor(), 12) +
                intField(historico.getTipo(), 1) +
                historico.getHorarioDaTransacao().toString();
    }

    public static String lineBolsa(Bolsa bolsa) {
        return stringField(bolsa.getNome(), 100);
    }

    public static String lineType(Type type) {
        return intField(type.type, 1);
    }

}
