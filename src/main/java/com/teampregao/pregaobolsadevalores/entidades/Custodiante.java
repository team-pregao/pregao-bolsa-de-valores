package com.teampregao.pregaobolsadevalores.entidades;

import com.teampregao.pregaobolsadevalores.ed.ListaEncadeada;
import com.teampregao.pregaobolsadevalores.ed.Mapa;
import com.teampregao.pregaobolsadevalores.manager.EntityManager;
import com.teampregao.pregaobolsadevalores.manager.SaverManager;

public class Custodiante {
    private final Id id;
    private final Investidor investidor;
    private final Mapa<Ativo, AtivoCustodiado> ativosCustodiados;

    public Custodiante(Id id, Investidor investidor) {
        this.id = id;
        this.investidor = investidor;
        this.ativosCustodiados = new Mapa<>();
    }

    public Id getId() {
        return id;
    }

    public Mapa<Ativo, AtivoCustodiado> getAtivosCustodiados() {
        return ativosCustodiados;
    }
    public void adicionarAtivoCustodiado(Ativo ativo) {
        AtivoCustodiado custodiado = new AtivoCustodiado(new Id(Type.CUSTODIANTE), investidor, ativo, 0.0);
        System.out.println("criou");
        SaverManager saverManager = new SaverManager();
        saverManager.insert(EntityManager.lineAtivoCustodiado(custodiado), Type.ATIVO_CUSTODIADO);
        ativosCustodiados.put(ativo, custodiado);
    }

    public void adicionarAtivoCustodiado(Ativo ativo, Double d){
        AtivoCustodiado custodiado = new AtivoCustodiado(new Id(Type.CUSTODIANTE), investidor, ativo, d);
        System.out.println("criou");
        SaverManager saverManager = new SaverManager();
        saverManager.insert(EntityManager.lineAtivoCustodiado(custodiado), Type.ATIVO_CUSTODIADO);
        ativosCustodiados.put(ativo, custodiado);
    }

    public void removerAtivoCustodiado(Ativo ativo) {
        ativosCustodiados.remove(ativo);
    }

    public Investidor getInvestidor() {
        return investidor;
    }
}