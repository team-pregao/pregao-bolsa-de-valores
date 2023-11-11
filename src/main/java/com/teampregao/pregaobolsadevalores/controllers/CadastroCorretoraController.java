package com.teampregao.pregaobolsadevalores.controllers;

import com.teampregao.pregaobolsadevalores.MainApp;
import com.teampregao.pregaobolsadevalores.entidades.Corretora;
import com.teampregao.pregaobolsadevalores.entidades.Id;
import com.teampregao.pregaobolsadevalores.entidades.Type;
import com.teampregao.pregaobolsadevalores.manager.EntityManager;
import com.teampregao.pregaobolsadevalores.manager.SaverManager;
import javafx.event.ActionEvent;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class CadastroCorretoraController {
    public TextField nomeCorretoraField;
    public TextField flutuacaoField;

    public void cadastrarCorretoraAction(ActionEvent event) {
        try {
            Corretora corretora = new Corretora(new Id(Type.CORRETORA), nomeCorretoraField.getText(), EntityManager.readBolsa(1), Integer.parseInt(flutuacaoField.getText()));
            SaverManager saverManager = new SaverManager();
            saverManager.insert(EntityManager.lineCorretora(corretora), Type.CORRETORA);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("SUCESSO");
            alert.setHeaderText(nomeCorretoraField.getText() + " cadastrado");
            alert.setContentText("A corretora foi cadastrada com sucesso, volte e inicie a simulação");
            alert.showAndWait();

        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERRO");
            alert.setHeaderText("Erro ao cadastar");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void backButtonAction(ActionEvent event) {
        MainApp.openPane("CadastroBancoDeDados");
    }
}
