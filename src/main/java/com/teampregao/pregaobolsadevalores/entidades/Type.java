package com.teampregao.pregaobolsadevalores.entidades;

public enum Type {
    INVESTIDOR(1, 118),
    ATIVO(2, 121),
    CORRETORA(3, 109),
    CARTEIRA(4, 9),
    CUSTODIANTE(5, 6),
    HISTORICO(6, 56),
    BOLSA(7, 103),
    ATIVO_CUSTODIADO(8, 21),
    FLUTUACAO(9, 55);

    public final int type;
    public final int maxLenght;

    Type(int type, int maxLenght) {
            this.type = type;
            this.maxLenght = maxLenght;
    }
}
