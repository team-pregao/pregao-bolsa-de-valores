package com.teampregao.pregaobolsadevalores.entidades;

import com.teampregao.pregaobolsadevalores.ed.Mapa;
import com.teampregao.pregaobolsadevalores.manager.EntityManager;
import com.teampregao.pregaobolsadevalores.manager.SaverManager;

import java.util.concurrent.atomic.AtomicReference;

public class Custodiante {
    private final Id id;
    private final Mapa<Integer, AtivoCustodiado> ativosCustodiados;
    private Investidor investidor;

    public Custodiante(Id id, Investidor investidor) {
        this.id = id;
        this.investidor = investidor;
        this.ativosCustodiados = new Mapa<>();
    }

    public Id getId() {
        return id;
    }

    public Mapa<Integer, AtivoCustodiado> getAtivosCustodiados() {
        return ativosCustodiados;
    }
    public void adicionarAtivoCustodiado(Ativo ativo) {
        AtivoCustodiado custodiado = new AtivoCustodiado(new Id(Type.CUSTODIANTE), investidor, ativo, 0.0);
        System.out.println("criou");
        SaverManager saverManager = new SaverManager();
        saverManager.insert(EntityManager.lineAtivoCustodiado(custodiado), Type.ATIVO_CUSTODIADO);
        ativosCustodiados.put(ativo.getId().getId(), custodiado);
    }

    public void adicionarAtivoCustodiado(Ativo ativo, Double d){
        AtivoCustodiado custodiado;

        SaverManager saverManager = new SaverManager();
        if (ativosCustodiados.get(ativo.getId().getId()) == null){
            System.out.println("insert custoidado");
            custodiado = new AtivoCustodiado(new Id(Type.ATIVO_CUSTODIADO), investidor, ativo, d);
            saverManager.insert(EntityManager.lineAtivoCustodiado(custodiado), Type.ATIVO_CUSTODIADO);
            System.out.println("inseriu");
            System.out.println(EntityManager.lineAtivoCustodiado(custodiado));
            ativosCustodiados.put(ativo.getId().getId(), custodiado);
        }
        else {
            System.out.println("update custoidado");
            AtomicReference<AtivoCustodiado> atomicCustodiado = new AtomicReference<>();
            EntityManager.readAtivoCustodiado().forEach(ativoCustodiado -> {
                if (ativoCustodiado.getAtivo().getEmpresa().equals(ativo.getEmpresa())){
                    ativoCustodiado.setInvestidor(investidor);
                    ativoCustodiado.setQuantidade(d);
                    System.out.println("quantidade: " + ativoCustodiado.getQuantidade());
                    atomicCustodiado.set(ativoCustodiado);
                    saverManager.update(ativoCustodiado.getId(), EntityManager.lineAtivoCustodiado(ativoCustodiado));
                }
            });
            custodiado = atomicCustodiado.get();
            System.out.println("quantidade fora: " + custodiado.getQuantidade());
            ativosCustodiados.remove(ativo.getId().getId());
            System.out.println("quantidade fora 2: " + custodiado.getQuantidade());
            ativosCustodiados.put(ativo.getId().getId(), custodiado);
            System.out.println("quantidade fora 3: " + ativosCustodiados.get(ativo.getId().getId()).getQuantidade());
        }
        System.out.println("quantidade fora 4: " + ativosCustodiados.get(ativo.getId().getId()).getQuantidade());
    }

    public void removerAtivoCustodiado(Ativo ativo) {
        ativosCustodiados.remove(ativo.getId().getId());
        SaverManager saverManager = new SaverManager();
        saverManager.delete(ativosCustodiados.get(ativo.getId().getId()).getId());
    }

    public void put(Ativo ativo, AtivoCustodiado custodiado){
        ativosCustodiados.put(ativo.getId().getId(), custodiado);
    }

    public Investidor getInvestidor() {
        return investidor;
    }

    public void setInvestidor(Investidor investidor) {
        this.investidor = investidor;
    }
}