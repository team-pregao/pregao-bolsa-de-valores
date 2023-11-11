package com.teampregao.pregaobolsadevalores.controllers;

import com.teampregao.pregaobolsadevalores.Cache;
import com.teampregao.pregaobolsadevalores.MainApp;
import com.teampregao.pregaobolsadevalores.ed.ListaEncadeada;
import com.teampregao.pregaobolsadevalores.manager.EntityManager;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import com.teampregao.pregaobolsadevalores.entidades.*;
import javafx.scene.layout.VBox;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static com.teampregao.pregaobolsadevalores.manager.EntityManager.*;

public class EmpresasView {
    public Label corretoraLabel;
    public ComboBox<String> empresasComboBox;
    public Button verCarteiraButton;
    public TextField saldoAtualField;
    public TextField totalAcoesField;
    public Label empresaLabel;
    public VBox graphicPane;
    public TextField qntComprarAcaoField;
    public TextField valorComprarAcaoField;
    public Button comprarAcaoButton;
    public TextField qntVenderAcaoField;
    public TextField valorVenderAcaoField;
    public Button venderAcaoButton;

    private double saldo;
    private double totalAcao;
    private Investidor investidor;
    private Ativo ativo;
    private Corretora corretora;

    private boolean isShowSaldo = true;
    private boolean isShowTotalAcao = true;

    @FXML
    void initialize(){
        ListaEncadeada<Ativo> resultAtivos = readAtivo();

        for (Ativo ativo:
             resultAtivos) {
            empresasComboBox.getItems().add(ativo.getEmpresa());
        }

        empresasComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            empresaLabel.setText(newValue);
            ListaEncadeada<Ativo> ativos = EntityManager.readAtivo();
            for (Ativo ativo1 : ativos) {
                if (ativo1.getEmpresa().equals(newValue)){
                    ativo = ativo1;
                }
            }
            saldoEAcaoRoutine();
            graphicRoutine();
        });

        investidor = EntityManager.refinedReadInvestidor((Investidor) Cache.getObject("user"));
        ativo = (Ativo) Cache.getObject("ativo");
        corretora = (Corretora) Cache.getObject("corretora");

        saldoEAcaoRoutine();

        if (ativo == null){
            empresaLabel.setText("Escolha um Ativo");
        } else {
            empresaLabel.setText(ativo.getEmpresa());
        }

        corretoraLabel.setText(corretora.getNome());

        qntComprarAcaoField.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (!newValue){
                double valorAtivo = ativo == null ? 0.0 : ativo.getValorAtual();
                valorComprarAcaoField.setText(String.valueOf(Double.parseDouble(qntComprarAcaoField.getText().isEmpty() ? "0" : qntComprarAcaoField.getText()) * valorAtivo));
            }
        }));

        qntVenderAcaoField.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (!newValue){
                double valorAtivo = ativo == null ? 0.0 : ativo.getValorAtual();
                valorVenderAcaoField.setText(String.valueOf(Double.parseDouble(qntVenderAcaoField.getText().isEmpty() ? "0" : qntVenderAcaoField.getText()) * valorAtivo));
            }
        }));

        valorComprarAcaoField.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (!newValue){
                double valorAtivo = ativo == null ? 1.0 : ativo.getValorAtual();
                qntComprarAcaoField.setText(String.valueOf(Double.parseDouble(valorComprarAcaoField.getText().isEmpty() ? "0" : valorComprarAcaoField.getText()) / valorAtivo));
            }
        }));

        valorVenderAcaoField.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (!newValue){
                double valorAtivo = ativo == null ? 1.0 : ativo.getValorAtual();
                qntVenderAcaoField.setText(String.valueOf(Double.parseDouble(valorVenderAcaoField.getText().isEmpty() ? "0" : valorVenderAcaoField.getText()) / valorAtivo));
            }
        }));
    }


    public void verCarteiraButtonAction() {
        MainApp.openPane("carteira-view");
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

    public void comprarAcaoButtonAction() {
        try {
            if (qntComprarAcaoField.getText().isEmpty() || qntComprarAcaoField.getText().isBlank()) {
                investidor.comprarAcao(ativo, Double.parseDouble(valorComprarAcaoField.getText()) / ativo.getValorAtual(), corretora);
            } else {
                investidor.comprarAcao(ativo, Double.parseDouble(qntComprarAcaoField.getText()), corretora);
            }
        } catch (IllegalArgumentException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERRO");
            alert.setHeaderText("Erro ao comprar");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERRO");
            alert.setHeaderText("Erro ao comprar");
            alert.setContentText("Nenhum ativo selecionado");
            alert.showAndWait();
        }
        saldoEAcaoRoutine();
        graphicRoutine();
    }

    public void venderAcaoButtonAction() {
        try {
            if (qntVenderAcaoField.getText().isEmpty() || qntVenderAcaoField.getText().isBlank()){
                investidor.venderAcao(ativo, Double.parseDouble(valorVenderAcaoField.getText())/ativo.getValorAtual(), corretora);
            } else {
                investidor.venderAcao(ativo, Double.parseDouble(qntVenderAcaoField.getText()), corretora);
            }
        }  catch (IllegalArgumentException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERRO");
            alert.setHeaderText("Erro ao vender");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        } catch (NullPointerException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERRO");
            alert.setHeaderText("Erro ao comprar");
            alert.setContentText("Nenhum ativo selecionado");
            alert.showAndWait();
        }

        saldoEAcaoRoutine();
        graphicRoutine();
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
    private void graphicRoutine() {
        if (ativo == null){
            return;
        }
        ListaEncadeada<Flutuacao> flutuacaoLista = readFlutuacao();

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
/*
        yAxis.setLowerBound((double) corretora.getFaixa() /100 - 3);
        yAxis.setUpperBound((double) corretora.getFaixa() /100 + 2);
*/
        // Criando o gráfico de linha
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        // Criando uma série de dados
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Flutuação do Ativo");

        AtomicInteger count = new AtomicInteger();
        flutuacaoLista.forEach(flutuacao -> {
            if (flutuacao.getAtivo().getId().getId() == ativo.getId().getId()){
                System.out.println(flutuacao.getPrecoAtivo());
                System.out.println(flutuacao.getFlutuacao());
                series.getData().add(new XYChart.Data<>(count.get(), flutuacao.getPrecoAtivo()));
                count.getAndIncrement();
            }
        });
        series.getData().add(new XYChart.Data<>(count.get(), ativo.getValorAtual()));
        lineChart.setTitle(ativo.getEmpresa() + " R$" + ativo.getValorAtual() + " | " + Investidor.round((flutuacaoLista.get(flutuacaoLista.getSize() - 1).getFlutuacao() - 1) * 100, 2) + "%");

        // Adicionando a série ao gráfico
        lineChart.getData().add(series);
        lineChart.setPrefWidth(flutuacaoLista.getSize() * 50);
        if (graphicPane.getChildren().size() == 1)
            graphicPane.getChildren().remove(0);
        graphicPane.getChildren().add(lineChart);
    }

}
