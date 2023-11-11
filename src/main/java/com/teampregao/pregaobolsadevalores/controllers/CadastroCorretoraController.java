package com.teampregao.pregaobolsadevalores.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CadastroCorretoraController {
    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txfNome;

    @FXML
    private Button btnCadastrar;

    public void onActionCadastrarCorretora(){
        String nome = txfNome.getText();

    }
}
