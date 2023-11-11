package com.teampregao.pregaobolsadevalores.entidades;

import com.teampregao.pregaobolsadevalores.Cache;
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

    public static double getCustoTotal(Ativo ativo, Corretora corretora, double quantidade){
        SaverManager saverManager = new SaverManager();
        double flutuacao = ((Math.random() * corretora.getFaixa()) + 1.0) * ((int) (Math.random() * 2) == 0 ? -1.0 : 1.0);
        System.out.println("flutuacao: " + flutuacao);

        flutuacao = 1 + (flutuacao / 100);
        System.out.println("flutuacao: " + flutuacao);
        Cache.putObject("flutuacao", flutuacao);

        System.out.println("ativo antes: " + ativo.getValorAtual());
        Flutuacao flutuacaoClass = new Flutuacao(new Id(Type.FLUTUACAO), ativo, flutuacao, ativo.getValorAtual());

        saverManager.insert(EntityManager.lineFlutuacao(flutuacaoClass), Type.FLUTUACAO);

        ativo.setValorAtual(round(ativo.getValorAtual() * flutuacao, 2));
        if (ativo.getValorAtual() < 0.5)
            ativo.setValorAtual(100);
        System.out.println("ativo depois: " + ativo.getValorAtual());

        return ativo.getValorAtual() * quantidade;
    }

    public static double round(double d, int casas){
        String dString = String.valueOf(d);

        if (dString.split("\\.")[1].length() > casas)
            dString = dString.substring(0, dString.split("\\.")[0].length() + casas + 1);
        return Double.parseDouble(dString);
    }

    public void comprarAcao(Ativo ativo, double quantidade, Corretora corretora, boolean compraInicial) {
        double custoTotal = getCustoTotal(ativo, corretora, quantidade);

        if (custoTotal > saldo){
            throw new IllegalArgumentException("Saldo insuficiente para comprar ações." + "\nSaldo: " + saldo + " Custo Total: " + custoTotal);
        }

        corretora.ordenarComprarAcao(ativo, quantidade, custoTotal, this, compraInicial);
        updateInvestidor();
    }

    public void comprarAcao(Ativo ativo, double quantidade, Corretora corretora) {
        double custoTotal = getCustoTotal(ativo, corretora, quantidade);
        System.out.println("custo total: " + custoTotal);

        if (custoTotal > saldo){
             throw new IllegalArgumentException("Saldo insuficiente para comprar ações." + "\nSaldo: " + saldo + " Custo Total: " + custoTotal);
        }

        System.out.println("compra acao");

        corretora.ordenarComprarAcao(ativo, quantidade, custoTotal, this);

        updateInvestidor();
    }

    private void updateInvestidor() {
        SaverManager manager = new SaverManager();
        manager.update(id, EntityManager.lineInvestidor(this));
    }

    public void venderAcao(Ativo ativo, double quantidade, Corretora corretora) {
        double custoTotal = getCustoTotal(ativo, corretora, quantidade);

        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade de ações a ser vendida não pode ser negativa.");
        }

        corretora.ordenarVenderAcao(ativo, quantidade, custoTotal, this);

        System.out.println(nome + " vendeu " + quantidade + " ações da empresa " + ativo.getTicker());
        updateInvestidor();
    }

    public void transferir(double d) {
        saldo = round(saldo + d, 2);
        updateInvestidor();
    }
}
