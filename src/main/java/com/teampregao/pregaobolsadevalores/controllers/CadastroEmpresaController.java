package com.teampregao.pregaobolsadevalores.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CadastroEmpresaController {
    @FXML
    private Button btnCadastrar;

     @FXML
    private Button btnVoltar;

    @FXML
    private TextField txfValorAcao;

    @FXML
    private TextField txfNome;

    @FXML
    private TextField txfIPO;

    public void onActionCadastrarEmpresa(){
         String nome = txfNome.getText();
         String valorAcao = txfValorAcao.getText();
         String IPO = txfIPO.getText();

        }
    }
