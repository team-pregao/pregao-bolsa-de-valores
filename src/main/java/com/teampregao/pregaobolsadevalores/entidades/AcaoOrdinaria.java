package com.teampregao.pregaobolsadevalores.entidades;

public class AcaoOrdinaria extends Ativo {
    public AcaoOrdinaria(Id id, String empresa, double valorAtual) {
        super(id, empresa, valorAtual);
    }
    @Override
    public String getTicker() {
        return super.empresa.substring(0, 4).toUpperCase() + 3;
    }
}
