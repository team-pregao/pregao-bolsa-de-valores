package com.teampregao.pregaobolsadevalores.entidades;

import com.teampregao.pregaobolsadevalores.manager.EntityManager;
import com.teampregao.pregaobolsadevalores.manager.SaverManager;

public class Investidor {
    private final Id id;
    private final String nome;
    private double saldo;
    private Custodiante custodiante;

    public int custodianteId;

    public Investidor(Id id, String nome, double saldo) {
        this.id = id;
        this.nome = nome;
        this.saldo = saldo;
        custodiante = new Custodiante(new Id(Type.CUSTODIANTE), this);
    }

    public Investidor(Id id, String nome, double saldo, Custodiante custodiante) {
        this.id = id;
        this.nome = nome;
        this.saldo = saldo;
        this.custodiante = custodiante;
    }

    public String getNome() {
        return nome;
    }

    public Id getId() {
        return id;
    }

    public double getSaldo() {
        return saldo;
    }

    public Custodiante getCustodiante() {
        return custodiante;
    }

    public void setCustodiante(Custodiante custodiante) {
        this.custodiante = custodiante;
    }

    public void comprarAcao(Ativo ativo, double quantidade, Corretora corretora) {
        double custoTotal = ativo.getValorAtual() * quantidade;

        if (custoTotal > saldo){
            throw new IllegalArgumentException("Saldo insuficiente para comprar ações." + "\nSaldo: " + saldo + " Custo Total: " + custoTotal);
        }

        System.out.println("compra acao");

        corretora.ordenarComprarAcao(ativo, quantidade, this);

        updateInvestidor();
    }


    public void comprarAcao(Ativo ativo, double quantidade, Corretora corretora, boolean compraInicial) {
        double custoTotal = ativo.getValorAtual() * quantidade;

        if (custoTotal > saldo){
            throw new IllegalArgumentException("Saldo insuficiente para comprar ações." + "\nSaldo: " + saldo + " Custo Total: " + custoTotal);
        }

        corretora.ordenarComprarAcao(ativo, quantidade, this, compraInicial);
        updateInvestidor();
    }

    public void venderAcao(Ativo ativo, double quantidade, Corretora corretora) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade de ações a ser vendida não pode ser negativa.");
        }
        corretora.ordenarVenderAcao(ativo, quantidade, this);

        System.out.println(nome + " vendeu " + quantidade + " ações da empresa " + ativo.getTicker());
        updateInvestidor();
    }

    public void transferir(double d) {
        saldo += d;
        updateInvestidor();
    }

    private void updateInvestidor() {
        SaverManager manager = new SaverManager();
        manager.update(id, EntityManager.lineInvestidor(this));
    }
}
