package com.teampregao.pregaobolsadevalores.entidades;

public class FundoInvestimentoImobiliario extends Ativo {
    public FundoInvestimentoImobiliario(Id id, String empresa, double valorAtual) {
        super(id, empresa, valorAtual);
    }
    @Override
    public String getTicker() {
        return super.empresa.substring(0, 4).toUpperCase() + 11;
    }
}
