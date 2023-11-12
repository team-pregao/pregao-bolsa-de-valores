package com.teampregao.pregaobolsadevalores;

import com.teampregao.pregaobolsadevalores.controllers.EmpresasView;
import com.teampregao.pregaobolsadevalores.entidades.Ativo;
import com.teampregao.pregaobolsadevalores.entidades.Corretora;
import com.teampregao.pregaobolsadevalores.entidades.Investidor;

public class BackgroundTask implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            Investidor.getCustoTotal((Ativo) Cache.getObject("ativo"), (Corretora) Cache.getObject("corretora"), 1);
        } catch (InterruptedException ignored){
        }
    }
}
