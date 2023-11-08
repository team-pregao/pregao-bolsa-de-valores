package com.teampregao.pregaobolsadevalores.controllers;

import com.teampregao.pregaobolsadevalores.Cache;
import com.teampregao.pregaobolsadevalores.ed.ListaEncadeada;
import com.teampregao.pregaobolsadevalores.entidades.Type;
import com.teampregao.pregaobolsadevalores.manager.EntityManager;
import com.teampregao.pregaobolsadevalores.manager.SaverManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import com.teampregao.pregaobolsadevalores.entidades.*;
import com.teampregao.pregaobolsadevalores.manager.EntityManager.*;

import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

import static com.teampregao.pregaobolsadevalores.manager.EntityManager.*;

public class EmpresasView {
    public Label corretoraLabel;
    public ComboBox<String> empresasComboBox;
    public Button verCarteiraButton;
    public TextField saldoAtualField;
    public TextField totalAcoesField;
    public Label empresaLabel;
    public Pane graphicPane;
    public TextField qntComprarAcaoField;
    public TextField valorComprarAcaoField;
    public Button comprarAcaoButton;
    public TextField qntVenderAcaoField;
    public TextField valorVenderAcaoField;
    public Button venderAcaoButton;

    private double saldo;
    private int totalAcao;
    private Investidor investidor;
    private Ativo ativo;
    private Corretora corretora;

    private boolean isShowSaldo = true;
    private boolean isShowTotalAcao = true;

    @FXML
    void initialize(){
        SaverManager saverManager = new SaverManager();

        ListaEncadeada<Ativo> resultAtivos = readAtivo();

        for (Ativo ativo:
             resultAtivos) {
            empresasComboBox.getItems().add(ativo.getEmpresa());
            System.out.println(ativo);
        }
        investidor = (Investidor) Cache.getObject("user");
        ativo = (Ativo) Cache.getObject("ativo");
        corretora = (Corretora) Cache.getObject("corretora");
        saldo = investidor.getSaldo();

        Custodiante custodiante = readCustodiante(investidor.getCustodiante().getId().getId());
        ListaEncadeada<Ativo> ativos = new ListaEncadeada<>();
        readAtivoCustodiado().iterator().forEachRemaining(item -> {
            if (item.getInvestidor() == null){

            }
            else if (item.getInvestidor().getId().getId() == investidor.getId().getId()){
                System.out.println(EntityManager.lineAtivoCustodiado(item));
                System.out.println(investidor.getId().getId());
                System.out.println(item.getInvestidor().getId().getId());
                ativos.add(item.getAtivo());
            }
        });

        totalAcao = ativos.getSize();
        saldoAtualField.setText(String.valueOf(saldo));
        totalAcoesField.setText(String.valueOf(totalAcao));

        empresaLabel.setText(ativo.getEmpresa());
        corretoraLabel.setText(corretora.getNome());

        qntComprarAcaoField.focusedProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (!newValue){
                valorComprarAcaoField.setText(String.valueOf(Double.parseDouble(qntComprarAcaoField.getText()) * ativo.getValorAtual()));
            }
        }));
    }

    public void verCarteiraButtonAction() {
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
        investidor.comprarAcao(ativo, Double.parseDouble(qntComprarAcaoField.getText()), corretora);
    }

    public void venderAcaoButtonAction() {
        investidor.venderAcao(ativo, Double.parseDouble(qntVenderAcaoField.getText()), corretora);
    }
}
