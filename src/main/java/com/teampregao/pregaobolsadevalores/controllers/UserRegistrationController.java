package com.teampregao.pregaobolsadevalores.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UserRegistrationController {
   
    @FXML
    private TextField txfSeuNome;

    @FXML
    private TextField txfSuaCorretora;

    @FXML
    private Button btnButton;

    public void onActionUserRegistration(){
        String seuNome = txfSeuNome.getText();
        String suaCorretora = txfSuaCorretora.getText();

    }
}