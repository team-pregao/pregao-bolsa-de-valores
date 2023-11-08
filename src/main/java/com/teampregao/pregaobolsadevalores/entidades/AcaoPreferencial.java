package com.teampregao.pregaobolsadevalores.entidades;

public class AcaoPreferencial extends Ativo {
    private final char classe;
    public AcaoPreferencial(Id id, String empresa, double valorAtual, char classe) {
        super(id, empresa, valorAtual);
        this.classe = classe;
    }
    @Override
    public String getTicker() {
        int num = 4;
        switch (classe){
            case 'A' -> num = 5;
            case 'B' -> num = 6;
            case 'C' -> num = 7;
            case 'D' -> num = 8;
        }
        return super.empresa.substring(0, 4).toUpperCase() + num;
    }
}
