package com.teampregao.pregaobolsadevalores.controllers;

import com.teampregao.pregaobolsadevalores.Cache;
import com.teampregao.pregaobolsadevalores.MainApp;
import com.teampregao.pregaobolsadevalores.entidades.*;
import com.teampregao.pregaobolsadevalores.manager.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.controlsfx.control.BreadCrumbBar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.IntStream;

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


    private double saldo;
    private double totalAcao;

    private boolean isShowSaldo = true;
    private boolean isShowTotalAcao = true;

    private Investidor investidor;
    private Ativo ativo;
    private Corretora corretora;

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

        investidor = EntityManager.refinedReadInvestidor((Investidor) Cache.getObject("user"));
        ativo = (Ativo) Cache.getObject("ativo");
        corretora = (Corretora) Cache.getObject("corretora");

        saldoEAcaoRoutine();
    }

    @FXML
    void verAcoesButtonAction(ActionEvent event) {
        MainApp.openPane("empresas-view");
    }


    public void saldoAtualView(MouseEvent mouseEvent) {
        if (isShowSaldo){
            StringBuilder s = new StringBuilder();
            IntStream.range(0, String.valueOf(saldo).length()).forEach(i -> s.append("*"));
            saldoAtualField.setText(s.toString());
        } else {
            saldoAtualField.setText(String.valueOf(saldo));
        }
        isShowSaldo = !isShowSaldo;
    }

    public void totalAcoesView(MouseEvent mouseEvent) {
        if (isShowTotalAcao){
            StringBuilder s = new StringBuilder();
            IntStream.range(0, String.valueOf(totalAcao).length()).forEach(i -> s.append("*"));
            totalAcoesField.setText(s.toString());
        } else {
            totalAcoesField.setText(String.valueOf(totalAcao));
        }
        isShowTotalAcao = !isShowTotalAcao;
    }


    private void saldoEAcaoRoutine(){
        saldo = investidor.getSaldo();

        if (ativo != null && investidor.getCustodiante().getAtivosCustodiados().get(ativo.getId().getId()) != null){
            totalAcao = investidor.getCustodiante().getAtivosCustodiados().get(ativo.getId().getId()).getQuantidade();
        } else {
            totalAcao = 0.0;
        }

        saldoAtualField.setText(String.valueOf(saldo));
        totalAcoesField.setText(String.valueOf(totalAcao));
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
