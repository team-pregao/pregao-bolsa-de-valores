package com.teampregao.pregaobolsadevalores.entidades;

import com.teampregao.pregaobolsadevalores.manager.EntityManager;
import com.teampregao.pregaobolsadevalores.manager.SaverManager;

public class Corretora {
    private final Id id;
    private final String nome;
    private final Bolsa bolsa;
    private final int faixa;

    private final int COMPRA = 0;
    private final int VENDA = 1;

    public Corretora(Id id, String nome, Bolsa bolsa, int faixa) {
        this.nome = nome;
        this.id = id;
        this.bolsa = bolsa;
        this.faixa = faixa;
    }

    public String getNome() {
        return nome;
    }

    public Id getId() {
        return id;
    }

    public Bolsa getBolsa() {
        return bolsa;
    }

    public void ordenarComprarAcao(Ativo ativo, double quantidade, double custoTotal, Investidor investidor){
        SaverManager saverManager = new SaverManager();

        Historico pedido = new Historico(ativo, quantidade, custoTotal, COMPRA, investidor, this);

        saverManager.insert(EntityManager.lineHistorico(pedido), pedido.getId().getType());

        bolsa.executarAcao(pedido);

        System.out.println("ordenar compra");
    }

    public void ordenarComprarAcao(Ativo ativo, double quantidade, double custoTotal, Investidor investidor, boolean ordemDeCompraInicial){
        Historico pedido = new Historico(ativo, quantidade, custoTotal, COMPRA, investidor, this);
        SaverManager saverManager = new SaverManager();
        saverManager.insert(EntityManager.lineHistorico(pedido), Type.HISTORICO);

        investidor.getCustodiante().adicionarAtivoCustodiado(ativo, quantidade);
        investidor.transferir(custoTotal * -1);
    }

    public void ordenarVenderAcao(Ativo ativo, double quantidade, double custoTotal, Investidor investidor){
        if (investidor.getCustodiante().getAtivosCustodiados() == null || investidor.getCustodiante().getAtivosCustodiados().get(ativo.getId().getId()).getQuantidade() < quantidade){
            throw new IllegalArgumentException("Voce nao tem esse quantidade toda pra vender nao amigao");
        }

        Historico pedido = new Historico(ativo, quantidade, custoTotal, VENDA, investidor, this);

        SaverManager saverManager = new SaverManager();

        saverManager.insert(EntityManager.lineHistorico(pedido), pedido.getId().getType());
        bolsa.executarAcao(pedido);
    }

    public int getFaixa() {
        return faixa;
    }

    public int getCOMPRA() {
        return COMPRA;
    }

    public int getVENDA() {
        return VENDA;
    }
}