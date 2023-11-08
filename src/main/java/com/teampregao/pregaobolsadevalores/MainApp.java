package com.teampregao.pregaobolsadevalores;

import com.teampregao.pregaobolsadevalores.entidades.*;
import com.teampregao.pregaobolsadevalores.manager.*;
import com.teampregao.pregaobolsadevalores.ed.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("empresas-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Simulador Bolsa de Valores");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }

    public static void main(String[] args) {
/*
        SaverManager saverManager = new SaverManager();

        saverManager.clean();


        // Simular alguns ativos.
        Ativo ativo1 = new AcaoOrdinaria(new Id(Type.ATIVO), "Empresa X", 50.0);
        saverManager.insert(EntityManager.lineAtivo(ativo1), Type.ATIVO);
        Ativo ativo2 = new AcaoPreferencial(new Id(Type.ATIVO), "Empresa Y", 30.0, 'A');
        saverManager.insert(EntityManager.lineAtivo(ativo2), Type.ATIVO);

        Bolsa bolsa = new Bolsa(new Id(Type.BOLSA), "B3");
        saverManager.insert(EntityManager.lineBolsa(bolsa), Type.BOLSA);

        // Simular alguns investidores.
        Investidor investidor1 = new Investidor(new Id(Type.INVESTIDOR), "Investidor 1", 10000.0);
        saverManager.insert(EntityManager.lineInvestidor(investidor1), Type.INVESTIDOR);
        saverManager.insert(EntityManager.lineCustodiante(investidor1.getCustodiante()), Type.CUSTODIANTE);
        Investidor investidor2 = new Investidor(new Id(Type.INVESTIDOR), "Investidor 2", 8000.0);
        saverManager.insert(EntityManager.lineInvestidor(investidor2), Type.INVESTIDOR);
        saverManager.insert(EntityManager.lineCustodiante(investidor2.getCustodiante()), Type.CUSTODIANTE);

        // Simular algumas corretoras.
        Corretora corretora1 = new Corretora(new Id(Type.CORRETORA), "Corretora A", bolsa);
        saverManager.insert(EntityManager.lineCorretora(corretora1), Type.CORRETORA);
        Corretora corretora2 = new Corretora(new Id(Type.CORRETORA), "Corretora B", bolsa);
        saverManager.insert(EntityManager.lineCorretora(corretora2), Type.CORRETORA);

        // Simular carteiras para os investidores.
        Carteira carteira1 = new Carteira(new Id(Type.CARTEIRA), investidor1, corretora1);
        saverManager.insert(EntityManager.lineCarteira(carteira1), Type.CARTEIRA);
        Carteira carteira2 = new Carteira(new Id(Type.CARTEIRA), investidor2, corretora2);
        saverManager.insert(EntityManager.lineCarteira(carteira2), Type.CARTEIRA);

        // Realizar algumas transações.
        investidor2.comprarAcao(ativo1, 50, corretora1, true);
        investidor2.comprarAcao(ativo2, 50, corretora2, true);
        investidor1.comprarAcao(ativo1, 100, corretora1, true);

        // Executar operações pendentes.
        saverManager.execute();

        // Recuperar dados do banco de dados.

        ListaEncadeada<Investidor> investidores = EntityManager.readInvestidor();
        ListaEncadeada<Corretora> corretoras = EntityManager.readCorretora();
        ListaEncadeada<Ativo> ativos = EntityManager.readAtivo();

        // Exibir os dados recuperados.
        System.out.println("Investidores:");
        for (Investidor investidor : investidores) {
            System.out.println("Nome: " + investidor.getNome() + ", Saldo: " + investidor.getSaldo());
        }

        System.out.println("\nCorretoras:");
        for (Corretora corretora : corretoras) {
            System.out.println("Nome: " + corretora.getNome() + ", Bolsa: " + corretora.getBolsa().getNome());
        }

        System.out.println("\nAtivos:");
        for (Ativo ativo : ativos) {
            System.out.println("Empresa: " + ativo.getEmpresa() + ", Ticker: " + ativo.getTicker() + ", Valor Atual: " + ativo.getValorAtual());
        }
/**/
        ListaEncadeada<Investidor> investidores = EntityManager.readInvestidor();
        ListaEncadeada<Corretora> corretoras = EntityManager.readCorretora();
        ListaEncadeada<Ativo> ativos = EntityManager.readAtivo();

        Cache.putObject("user", investidores.get(0));
        Cache.putObject("ativo", ativos.get(0));
        Cache.putObject("corretora", corretoras.get(0));
        launch();
    }
}