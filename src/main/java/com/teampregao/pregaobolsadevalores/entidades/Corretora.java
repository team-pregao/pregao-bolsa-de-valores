package com.teampregao.pregaobolsadevalores.entidades;

import com.teampregao.pregaobolsadevalores.ed.*;
import com.teampregao.pregaobolsadevalores.manager.EntityManager;
import com.teampregao.pregaobolsadevalores.manager.SaverManager;

public class Corretora {
    private final Id id;
    private final String nome;
    private final Bolsa bolsa;

    private final int COMPRA = 0;
    private final int VENDA = 1;

    public Corretora(Id id, String nome, Bolsa bolsa) {
        this.nome = nome;
        this.id = id;
        this.bolsa = bolsa;
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

    public void ordenarComprarAcao(Ativo ativo, double quantidade, Investidor investidor){
        double custoTotal = ativo.getValorAtual() * quantidade;
        Historico pedido = new Historico(ativo, quantidade, custoTotal, COMPRA, investidor, this);
        bolsa.adicionarPedidoDeCompra(pedido);
        System.out.println("ordenar compra");
    }


    public void ordenarComprarAcao(Ativo ativo, double quantidade, Investidor investidor, boolean ordemDeCompraInicial){
        double custoTotal = ativo.getValorAtual() * quantidade;
        Historico pedido = new Historico(ativo, quantidade, custoTotal, COMPRA, investidor, this);
        SaverManager saverManager = new SaverManager();
        saverManager.insert(EntityManager.lineHistorico(pedido), Type.HISTORICO);

        investidor.getCustodiante().adicionarAtivoCustodiado(ativo, quantidade);
        investidor.transferir(custoTotal * -1);
    }

    public void ordenarVenderAcao(Ativo ativo, double quantidade, Investidor investidor){
        if (investidor.getCustodiante().getAtivosCustodiados().get(ativo).getQuantidade() < quantidade){
            throw new IllegalArgumentException("Voce nao tem esse quantidade toda pra vender nao amigao");
        }

        double custoTotal = ativo.getValorAtual() * quantidade;
        Historico pedido = new Historico(ativo, quantidade, custoTotal, VENDA, investidor, this);
        bolsa.adicionarPedidoDeVenda(pedido);
    }

}