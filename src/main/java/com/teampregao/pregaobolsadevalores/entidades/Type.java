package com.teampregao.pregaobolsadevalores.entidades;

public enum Type {
    INVESTIDOR(1),
    ATIVO(2),
    CORRETORA(3),
    CARTEIRA(4),
    CUSTODIANTE(5),
    HISTORICO(6),
    BOLSA(7),
    ATIVO_CUSTODIADO(8);

    public int type;
    public int maxLenght;

    Type(int type) {
        switch (type) {
            case 2 -> {
                this.type = 1;
                maxLenght = 121;
            }
            case 7 -> {
                this.type = 2;
                maxLenght = 103;
            }
            case 4 -> {
                this.type = 3;
                maxLenght = 9;
            }
            case 3 -> {
                this.type = 4;
                maxLenght = 106;
            }
            case 5 -> {
                this.type = 5;
                maxLenght = 6;
            }
            case 6 -> {
                this.type = 6;
                maxLenght = 30;
            }
            case 1 -> {
                this.type = 7;
                maxLenght = 118;
            }
            case 8 -> {
                this.type = 8;
                maxLenght = 21;
            }

        }
    }
}
