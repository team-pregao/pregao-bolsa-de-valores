package com.teampregao.pregaobolsadevalores.entidades;

import com.teampregao.pregaobolsadevalores.manager.EntityManager;

public abstract class Ativo implements Comparable<Ativo> {
    protected final Id id;
    protected final String empresa;
    protected double valorAtual;

    public Ativo(Id id, String empresa, double valorAtual) {
        this.id = id;
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

    public void setValorAtual(double valorAtual) {
        this.valorAtual = valorAtual;
    }

    @Override
    public int compareTo(Ativo o) {
        return Double.compare(o.getValorAtual(), getValorAtual());
    }
}