package com.teampregao.pregaobolsadevalores.controllers;

import com.teampregao.pregaobolsadevalores.Cache;
import com.teampregao.pregaobolsadevalores.MainApp;
import com.teampregao.pregaobolsadevalores.entidades.Carteira;
import com.teampregao.pregaobolsadevalores.entidades.Historico;
import com.teampregao.pregaobolsadevalores.entidades.Investidor;
import com.teampregao.pregaobolsadevalores.manager.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.controlsfx.control.BreadCrumbBar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CarteiraViewController {
    public TableColumn<HistoricoTable, String> empresaColumn;
    public TableColumn<HistoricoTable, String> corretoraColumn;
    public TableColumn<HistoricoTable, Double> quantidadeColumn;
    public TableColumn<HistoricoTable, Double> valorColumn;
    public TableColumn<HistoricoTable, LocalDateTime> dateColumn;
    @FXML
    private TableView<HistoricoTable> carteiraTableView;

    @FXML
    private Label corretoraLabel;

    @FXML
    private Label empresaLabel;
    @FXML
    private ComboBox<String> empresasComboBox;

    @FXML
    private VBox graphicPane;

    @FXML
    private TextField saldoAtualField;

    @FXML
    private TextField totalAcoesField;

    @FXML
    void initialize(){
        empresaColumn.setCellValueFactory(new PropertyValueFactory<>("empresa"));
        corretoraColumn.setCellValueFactory(new PropertyValueFactory<>("corretora"));
        quantidadeColumn.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        valorColumn.setCellValueFactory(new PropertyValueFactory<>("valor"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("data"));

        ObservableList<HistoricoTable> observableList = FXCollections.observableList(new ArrayList<>());

        EntityManager.readHistorico().forEach(historico -> {
            if (historico.getInvestidor().getId().getId() == ((Investidor) Cache.getObject("user")).getId().getId()){
                HistoricoTable historicoTable = new HistoricoTable(historico.getAtivo().getEmpresa(),
                        historico.getCorretora().getNome(),
                        historico.getQuantidade(),
                        historico.getValor(),
                        historico.getHorarioDaTransacao().format(DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy")));
                observableList.add(historicoTable);
                System.out.println(historicoTable);
            }
        });

        carteiraTableView.setItems(observableList);
    }

    @FXML
    void verAcoesButtonAction(ActionEvent event) {
        MainApp.openPane("empresas-view");
    }

    public static class HistoricoTable {
        public final String empresa;
        public final String corretora;
        public final double quantidade;
        public final double valor;
        public final String data;

        public HistoricoTable(String empresa, String corretora, double quantidade, double valor, String data) {
            this.empresa = empresa;
            this.corretora = corretora;
            this.quantidade = quantidade;
            this.valor = valor;
            this.data = data;
        }

        public String getEmpresa() {
            return empresa;
        }

        public String getCorretora() {
            return corretora;
        }

        public double getQuantidade() {
            return quantidade;
        }

        public double getValor() {
            return valor;
        }

        public String getData() {
            return data;
        }
    }

}
