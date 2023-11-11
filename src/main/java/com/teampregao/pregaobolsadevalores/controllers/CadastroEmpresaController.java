package com.teampregao.pregaobolsadevalores.controllers;

import com.teampregao.pregaobolsadevalores.MainApp;
import com.teampregao.pregaobolsadevalores.entidades.*;
import com.teampregao.pregaobolsadevalores.manager.EntityManager;
import com.teampregao.pregaobolsadevalores.manager.SaverManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

public class CadastroEmpresaController {
    public TextField nomeEmpresaField;
    public MenuButton tipoAcaoMenuButton;
    public MenuButton classesAcaoPreferencial;
    public Region classesRegion;

    @FXML
    private TextField txfValorAcao;

    @FXML
    private TextField txfIPO;

    private AcaoOrdinaria acaoOrdinaria;
    private AcaoPreferencial acaoPreferencial;
    private FundoInvestimentoImobiliario fundoInvestimentoImobiliario;

    private int selection;
    private char charClasse;

    public void onActionCadastrarEmpresa(){
        try {
            String nome = nomeEmpresaField.getText();
            double valorAcao = Double.parseDouble(txfValorAcao.getText());
            int IPO = Integer.parseInt(txfIPO.getText());

            SaverManager saverManager = new SaverManager();

            switch (selection) {
                case 1:
                    acaoPreferencial = new AcaoPreferencial(new Id(Type.ATIVO), nome, valorAcao, charClasse);
                    saverManager.insert(EntityManager.lineAtivo(acaoPreferencial), acaoPreferencial.getId().getType());
                case 2:
                    acaoOrdinaria = new AcaoOrdinaria(new Id(Type.ATIVO), nome, valorAcao);
                    saverManager.insert(EntityManager.lineAtivo(acaoOrdinaria), acaoOrdinaria.getId().getType());
                case 3:
                    fundoInvestimentoImobiliario = new FundoInvestimentoImobiliario(new Id(Type.ATIVO), nome, valorAcao);
                    saverManager.insert(EntityManager.lineAtivo(fundoInvestimentoImobiliario), fundoInvestimentoImobiliario.getId().getType());
            }

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("SUCESSO");
            alert.setHeaderText(nome + " cadastrado");
            alert.setContentText("A empresa foi cadastrada com sucesso, volte e inicie a simulação");
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

    public void acaoPreferencialAction(ActionEvent event) {
        showRegionAndMenuClasses();
        tipoAcaoMenuButton.setText("AÇÃO PREFERENCIAL");
        selection = 1;

    }

    public void acaoOrdinariaAction(ActionEvent event) {
        hideRegionAndMenuClasses();
        tipoAcaoMenuButton.setText("AÇÃO ORDINÁRIA");
        selection = 2;
    }

    public void FIIAction(ActionEvent event) {
        hideRegionAndMenuClasses();
        tipoAcaoMenuButton.setText("FUNDO DE INVESTIMENTO IMOBILIÁRIO");
        selection = 3;
    }

    public void choiceA(ActionEvent event) {
        classesAcaoPreferencial.setText("A");
        charClasse = 'A';
    }

    public void choiceB(ActionEvent event) {
        classesAcaoPreferencial.setText("B");
        charClasse = 'B';

    }

    public void choiceC(ActionEvent event) {
        classesAcaoPreferencial.setText("C");
        charClasse = 'C';
    }

    public void choiceD(ActionEvent event) {
        classesAcaoPreferencial.setText("D");
        charClasse = 'D';
    }
    
    public void showRegionAndMenuClasses(){
        classesRegion.setVisible(true);
        classesAcaoPreferencial.setVisible(true);

        classesRegion.setMaxHeight(35.0);
        classesRegion.setPrefHeight(35.0);
        classesRegion.setMinHeight(35.0);

        classesAcaoPreferencial.setMaxHeight(40.0);
        classesAcaoPreferencial.setPrefHeight(40.0);
        classesAcaoPreferencial.setMinHeight(40.0);
    }

    public void hideRegionAndMenuClasses(){
        classesRegion.setVisible(false);
        classesAcaoPreferencial.setVisible(false);

        classesRegion.setMaxHeight(0.0);
        classesRegion.setPrefHeight(0.0);
        classesRegion.setMinHeight(0.0);

        classesAcaoPreferencial.setMaxHeight(0.0);
        classesAcaoPreferencial.setPrefHeight(0.0);
        classesAcaoPreferencial.setMinHeight(0.0);
    }
}
