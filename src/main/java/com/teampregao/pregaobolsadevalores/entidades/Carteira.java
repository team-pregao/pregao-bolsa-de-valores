package com.teampregao.pregaobolsadevalores.entidades;

import com.teampregao.pregaobolsadevalores.ed.*;

public class Carteira {
    private final Id id;
    private final Investidor investidor;
    private final Corretora corretora;
    public Carteira(Id id, Investidor investidor, Corretora corretora) {
        this.id = id;
        this.investidor = investidor;
        this.corretora = corretora;
    }

    public Id getId() {
        return id;
    }

    public Investidor getInvestidor() {
        return investidor;
    }

    public Corretora getCorretora() {
        return corretora;
    }

    public ListaEncadeada<Historico> getHistorico (){
        return null;
    }
}

