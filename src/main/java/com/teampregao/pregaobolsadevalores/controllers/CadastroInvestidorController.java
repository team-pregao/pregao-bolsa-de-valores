package com.teampregao.pregaobolsadevalores.controllers;

import com.teampregao.pregaobolsadevalores.MainApp;
import com.teampregao.pregaobolsadevalores.entidades.Custodiante;
import com.teampregao.pregaobolsadevalores.entidades.Id;
import com.teampregao.pregaobolsadevalores.entidades.Investidor;
import com.teampregao.pregaobolsadevalores.entidades.Type;
import com.teampregao.pregaobolsadevalores.manager.EntityManager;
import com.teampregao.pregaobolsadevalores.manager.SaverManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CadastroInvestidorController {
    public TextField saldoInvestidorField;
    public TextField nomeInvestidoField;

    public void cadastrarInvestidorAction(ActionEvent event) {
        try {
            Investidor investidor = new Investidor(new Id(Type.INVESTIDOR), nomeInvestidoField.getText(), Double.parseDouble(saldoInvestidorField.getText()));
            SaverManager saverManager = new SaverManager();
            saverManager.insert(EntityManager.lineInvestidor(investidor), investidor.getId().getType());
            Custodiante custodiante = new Custodiante(new Id(Type.CUSTODIANTE), investidor);
            investidor.setCustodiante(custodiante);
            saverManager.insert(EntityManager.lineCustodiante(custodiante), custodiante.getId().getType());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("SUCESSO");
            alert.setHeaderText(nomeInvestidoField.getText() + " cadastrado");
            alert.setContentText("O investidor foi cadastrado com sucesso, volte e inicie a simulação");
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
