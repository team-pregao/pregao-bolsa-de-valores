package com.teampregao.pregaobolsadevalores;

import com.teampregao.pregaobolsadevalores.entidades.*;
import com.teampregao.pregaobolsadevalores.manager.*;
import com.teampregao.pregaobolsadevalores.manager.SaverManager;

public class ScriptDatabase {
    public static void main(String[] args) {
        SaverManager saverManager = new SaverManager();
        saverManager.clean();

        Bolsa bolsa = new Bolsa(new Id(Type.BOLSA), "B3");
        saverManager.insert(EntityManager.lineBolsa(bolsa), Type.BOLSA);

        Ativo amazon = new AcaoOrdinaria(new Id(Type.ATIVO), "Amazon INC", 50.0);
        saverManager.insert(EntityManager.lineAtivo(amazon), Type.ATIVO);
        Ativo apple = new AcaoPreferencial(new Id(Type.ATIVO), "Apple Inc", 30.0, 'A');
        saverManager.insert(EntityManager.lineAtivo(apple), Type.ATIVO);
        Ativo fedex = new AcaoPreferencial(new Id(Type.ATIVO), "Fedex Inc", 30.0, 'A');
        saverManager.insert(EntityManager.lineAtivo(fedex), Type.ATIVO);
        Ativo shopee = new FundoInvestimentoImobiliario(new Id(Type.ATIVO), "Shopee Inc", 30.0);
        saverManager.insert(EntityManager.lineAtivo(shopee), Type.ATIVO);

        Investidor investidor1 = new Investidor(new Id(Type.INVESTIDOR), "Investidor 1", 5000.0);
        saverManager.insert(EntityManager.lineInvestidor(investidor1), Type.INVESTIDOR);
        saverManager.insert(EntityManager.lineCustodiante(investidor1.getCustodiante()), Type.CUSTODIANTE);
        Investidor investidor2 = new Investidor(new Id(Type.INVESTIDOR), "Investidor 2", 8000.0);
        saverManager.insert(EntityManager.lineInvestidor(investidor2), Type.INVESTIDOR);
        saverManager.insert(EntityManager.lineCustodiante(investidor2.getCustodiante()), Type.CUSTODIANTE);

        // Simular algumas corretoras.
        Corretora corretora1 = new Corretora(new Id(Type.CORRETORA), "Corretora ALPHA", bolsa, 100);
        saverManager.insert(EntityManager.lineCorretora(corretora1), Type.CORRETORA);
        Corretora corretora2 = new Corretora(new Id(Type.CORRETORA), "Corretora BETA", bolsa, 200);
        saverManager.insert(EntityManager.lineCorretora(corretora2), Type.CORRETORA);

        Carteira carteira1 = new Carteira(new Id(Type.CARTEIRA), investidor1, corretora1);
        saverManager.insert(EntityManager.lineCarteira(carteira1), Type.CARTEIRA);
        Carteira carteira2 = new Carteira(new Id(Type.CARTEIRA), investidor2, corretora2);
        saverManager.insert(EntityManager.lineCarteira(carteira2), Type.CARTEIRA);

        investidor1.comprarAcao(amazon, 10.0, corretora1);

    }

}
