package com.teampregao.pregaobolsadevalores.controllers;

import com.teampregao.pregaobolsadevalores.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class CadastroBancoDeDadosController {

    @FXML
    void cadastrarCorretoraAction(ActionEvent event) {
        MainApp.openPane("CadastrarCorretora");
    }

    @FXML
    void cadastrarEmpresaAction(ActionEvent event) {
        MainApp.openPane("CadastrarEmpresa");

    }

    @FXML
    void cadastrarInvestidorAction(ActionEvent event) {
        MainApp.openPane("CadastrarInvestidor");
    }

    @FXML
    void startSimulationAction(ActionEvent event) {
        MainApp.openPane("UserRegistration");
    }

}
