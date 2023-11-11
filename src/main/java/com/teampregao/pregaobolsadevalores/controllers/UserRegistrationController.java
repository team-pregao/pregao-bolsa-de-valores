package com.teampregao.pregaobolsadevalores.controllers;

import com.teampregao.pregaobolsadevalores.Cache;
import com.teampregao.pregaobolsadevalores.MainApp;
import com.teampregao.pregaobolsadevalores.ed.ListaEncadeada;
import com.teampregao.pregaobolsadevalores.entidades.Ativo;
import com.teampregao.pregaobolsadevalores.entidades.AtivoCustodiado;
import com.teampregao.pregaobolsadevalores.entidades.Corretora;
import com.teampregao.pregaobolsadevalores.entidades.Investidor;
import com.teampregao.pregaobolsadevalores.manager.EntityManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import org.controlsfx.control.ListActionView;

import java.net.Inet4Address;

public class UserRegistrationController {

    @FXML
    private MenuButton corretorasMenuButton;

    @FXML
    private MenuButton investidoresMenuButton;

    @FXML
    public void initialize(){
        try {
            ListaEncadeada<Investidor> investidores = EntityManager.readInvestidor();
            investidores.forEach(investidor -> {
                MenuItem item = new MenuItem();
                item.setText(investidor.getNome());
                item.setOnAction(event -> {
                    investidoresMenuButton.setText(investidor.getNome());
                    Cache.putObject("user", investidor);
                });
                investidoresMenuButton.getItems().add(item);
            });

            ListaEncadeada<Corretora> corretoras = EntityManager.readCorretora();
            corretoras.forEach(corretora -> {
                MenuItem item = new MenuItem();
                item.setText(corretora.getNome());
                item.setOnAction(event -> {
                    corretorasMenuButton.setText(corretora.getNome());
                    Cache.putObject("corretora", corretora);
                });
                corretorasMenuButton.getItems().add(item);
            });
        } catch (Exception e){
            MenuItem item = new MenuItem();

            item.setText("Você ainda não tem corretoras cadastradas");
            item.setOnAction(event -> {
                corretorasMenuButton.setText("Você ainda não tem corretoras cadastradas");
            });
            corretorasMenuButton.getItems().add(item);

            MenuItem item2 = new MenuItem();
            item2.setText("Você ainda não tem investidores cadastrados");
            item2.setOnAction(event -> {
                investidoresMenuButton.setText("Você ainda não tem investidores cadastrados");
            });

            investidoresMenuButton.getItems().add(item2);

            Cache.putObject("corretora", null);
            Cache.putObject("user", null);
        }

    }

    @FXML
    void loginAction(ActionEvent event) {
        if (Cache.getObject("corretora") != null || Cache.getObject("user") != null)
            MainApp.openPane("empresas-view");
    }

}
