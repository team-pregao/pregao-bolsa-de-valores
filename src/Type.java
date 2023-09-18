public enum Type {
    INVESTIDOR(Investidor.class),
    ATIVO(Ativo.class),
    CORRETORA(Corretora.class),
    CARTEIRA(Carteira.class),
    CUSTODIANTE(Custodiante.class),
    HISTORICO(Historico.class),
    BOLSA(Bolsa.class);

    int type;
    int maxLenght;

    Type(Class<?> type) {
        switch (type.getName()) {
            case "Ativo" -> {
                this.type = 1;
                maxLenght = 121;
            }
            case "Bolsa" -> {
                this.type = 2;
                maxLenght = 10;
            }
            case "Carteira" -> {
                this.type = 3;
                maxLenght = 9;
            }
            case "Corretora" -> {
                this.type = 4;
                maxLenght = 103;
            }
            case "Custodiante" -> {
                this.type = 5;
                maxLenght = 103;
            }
            case "Historico" -> {
                this.type = 6;
                maxLenght = 47;
            }
            case "Investidor" -> {
                this.type = 7;
                maxLenght = 115;
            }
        }
    }
}
