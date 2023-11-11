package com.teampregao.pregaobolsadevalores.entidades;

public class AtivoCustodiado {
    private final Id id;
    public int investidorId;
    private final Ativo ativo;
    private double quantidade;
    private Investidor investidor;

    public AtivoCustodiado(Id id, Investidor investidor, Ativo ativo, double quantidade) {
        this.id = id;
        this.investidor = investidor;
        this.ativo = ativo;
        this.quantidade = quantidade;
    }

    public Id getId() {
        return id;
    }

    public Investidor getInvestidor() {
        return investidor;
    }

    public Ativo getAtivo() {
        return ativo;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public void setInvestidor(Investidor investidor){
        this.investidor = investidor;
    }
}
