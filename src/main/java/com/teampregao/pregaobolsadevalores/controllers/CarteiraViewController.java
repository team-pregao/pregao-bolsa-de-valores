package com.teampregao.pregaobolsadevalores.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CarteiraViewController {

    @FXML
    private TableView<?> carteiraTableView;

    @FXML
    private Label corretoraLabel;

    @FXML
    private Label empresaLabel;

    @FXML
    private ComboBox<?> empresasComboBox;

    @FXML
    private VBox graphicPane;

    @FXML
    private TextField saldoAtualField;

    @FXML
    private TextField totalAcoesField;

    @FXML
    private Button verCarteiraButton;

    @FXML
    void verCarteiraButtonAction(ActionEvent event) {

    }

}
