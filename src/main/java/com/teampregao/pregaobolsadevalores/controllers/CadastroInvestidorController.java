package com.teampregao.pregaobolsadevalores.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CadastroInvestidorController {
    @FXML
    private Button btnCadastar;

     @FXML
    private Button btnVoltar;

    @FXML
    private TextField txfCadastrarInvestidor;

    public void onActionCadastrarInvestidor(){
        String cadastrarInvestidor = txfCadastrarInvestidor.getText();

    }
}
