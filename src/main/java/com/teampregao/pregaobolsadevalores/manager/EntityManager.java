package com.teampregao.pregaobolsadevalores.manager;

import com.teampregao.pregaobolsadevalores.ed.ListaEncadeada;
import com.teampregao.pregaobolsadevalores.entidades.*;
import javafx.util.converter.LocalDateTimeStringConverter;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicReference;

public class EntityManager{
    public static String stringField(String value, int maxLenght){
        StringBuilder string = new StringBuilder();
        String sValue = String.valueOf(value);

        for (int i = 0; i < maxLenght; i++) {
            string.append((i < sValue.length()) ? sValue.toCharArray()[i]:" ");
        }
        return string.toString();
    }

    public static String intField(int value, int maxLenght){
        StringBuilder string = new StringBuilder();
        String sValue = String.valueOf(value);

        for (int i = 0; i < maxLenght; i++) {
            string.append((i < sValue.length()) ? sValue.toCharArray()[i]:" ");
        }
        return string.toString();
    }

    public static String doubleField(double value, int maxLenght){
        StringBuilder string = new StringBuilder();
        String sValue = String.valueOf(value);

        for (int i = 0; i < maxLenght; i++) {
            string.append((i < sValue.length()) ? sValue.toCharArray()[i]:" ");
        }
        return string.toString();
    }

    public static String lineInvestidor(Investidor investidor){
        return intField(investidor.getId().getId(), 3) +
                stringField(investidor.getNome(), 100) +
                doubleField(investidor.getSaldo(), 12) +
                intField(investidor.getCustodiante().getId().getId(), 3);
    }

    public static ListaEncadeada<Investidor> readInvestidor() {
        SaverManager saverManager = new SaverManager();
        String resultSet = saverManager.read(Type.INVESTIDOR);

        ListaEncadeada<Investidor> investidorLista = new ListaEncadeada<>();

        while (resultSet.length() >= Type.INVESTIDOR.maxLenght) {
            int id = Integer.parseInt(resultSet.substring(0, 3).trim());
            String nome = resultSet.substring(3, 103).trim();
            double saldo = Double.parseDouble(resultSet.substring(103, 115).trim());
            int custodianteId = Integer.parseInt(resultSet.substring(115, 118).trim());

            Id idClass = new Id(id, Type.INVESTIDOR);
            Investidor investidor = new Investidor(idClass, nome, saldo, null);
            investidor.custodianteId = custodianteId;

            investidorLista.add(investidor);

            resultSet = resultSet.substring(Type.INVESTIDOR.maxLenght);
        }

        return investidorLista;
    }

    public static Investidor readInvestidor(int investidorId){
        AtomicReference<Investidor> investidor = new AtomicReference<>();
        readInvestidor().iterator().forEachRemaining(item -> {
            if (item.getId().getId() == investidorId)
                investidor.set(item);
        });
        return investidor.get();
    }
    
    public static String lineAtivo(Ativo ativo) {
        return intField(ativo.getId().getId(), 3) +
                stringField(ativo.getEmpresa(), 100) +
                stringField(ativo.getTicker(), 6) +
                doubleField(ativo.getValorAtual(), 12);
    }

    public static ListaEncadeada<Ativo> readAtivo() {
        SaverManager saverManager = new SaverManager();
        String resultSet = saverManager.read(Type.ATIVO);

        ListaEncadeada<Ativo> ativoLista = new ListaEncadeada<>();

        while (resultSet.length() >= Type.ATIVO.maxLenght) {
            int id = Integer.parseInt(resultSet.substring(0, 3).trim());
            String empresa = resultSet.substring(3, 103).trim();
            String ticker = resultSet.substring(103, 109).trim();
            double valorAtual = Double.parseDouble(resultSet.substring(109, 121));

            Id idClass = new Id(id, Type.ATIVO);
            Ativo ativo = null;
            switch (Integer.parseInt(ticker.substring(4))) {
                case 3 -> {
                    ativo = new AcaoOrdinaria(idClass, empresa, valorAtual);
                }
                case 5 -> {
                    ativo = new AcaoPreferencial(idClass, empresa, valorAtual, 'A');
                }
                case 4 -> {
                    ativo = new AcaoPreferencial(idClass, empresa, valorAtual, 'B');
                }
                case 6 -> {
                    ativo = new AcaoPreferencial(idClass, empresa, valorAtual, 'C');
                }
                case 8 -> {
                    ativo = new AcaoPreferencial(idClass, empresa, valorAtual, 'D');
                }
                case 11 -> {
                    ativo = new FundoInvestimentoImobiliario(idClass, empresa, valorAtual);
                }
            }
            ativoLista.add(ativo);

            resultSet = resultSet.substring(Type.ATIVO.maxLenght);
        }

        return ativoLista;
    }

    public static Ativo readAtivo(int ativoId){
        AtomicReference<Ativo> ativo = new AtomicReference<>();
        readAtivo().iterator().forEachRemaining(item -> {
            if (item.getId().getId() == ativoId)
                ativo.set(item);
        });
        return ativo.get();
    }

    public static String lineCorretora(Corretora corretora) {
        return intField(corretora.getId().getId(), 3) +
                stringField(corretora.getNome(), 100) +
                intField(corretora.getBolsa().getId().getId(), 3);
    }

    public static ListaEncadeada<Corretora> readCorretora() {
        SaverManager saverManager = new SaverManager();
        String resultSet = saverManager.read(Type.CORRETORA);

        ListaEncadeada<Corretora> corretoraLista = new ListaEncadeada<>();

        while (resultSet.length() >= Type.CORRETORA.maxLenght) {
            int id = Integer.parseInt(resultSet.substring(0, 3).trim());
            String nome = resultSet.substring(3, 103).trim();
            int bolsaId = Integer.parseInt(resultSet.substring(103, 106).trim());

            Id idClass = new Id(id, Type.CORRETORA);
            Bolsa bolsa = readBolsa(bolsaId);
            Corretora corretora = new Corretora(idClass, nome, bolsa);

            corretoraLista.add(corretora);

            resultSet = resultSet.substring(Type.CORRETORA.maxLenght);
        }

        return corretoraLista;
    }

    public static Corretora readCorretora(int corretoraId){
        AtomicReference<Corretora> corretora = new AtomicReference<>();
        readCorretora().iterator().forEachRemaining(item -> {
            if (item.getId().getId() == corretoraId)
                corretora.set(item);
        });
        return corretora.get();
    }
    
    public static String lineCarteira(Carteira carteira) {
        return intField(carteira.getId().getId(), 3) +
                intField(carteira.getInvestidor().getId().getId(), 3) +
                intField(carteira.getCorretora().getId().getId(), 3);
    }

    public static ListaEncadeada<Carteira> readCarteira() {
        SaverManager saverManager = new SaverManager();
        String resultSet = saverManager.read(Type.CARTEIRA);

        ListaEncadeada<Carteira> carteiraLista = new ListaEncadeada<>();

        while (resultSet.length() >= Type.CARTEIRA.maxLenght) {

            int id = Integer.parseInt(resultSet.substring(0, 3).trim());
            int investidorId = Integer.parseInt(resultSet.substring(3, 6).trim());
            int corretoraId = Integer.parseInt(resultSet.substring(6, 9).trim());

            Id idClass = new Id(id, Type.CARTEIRA);

            Investidor investidor = readInvestidor(investidorId);

            Corretora corretora = readCorretora(corretoraId);

            Carteira carteira = new Carteira(idClass, investidor, corretora);

            carteiraLista.add(carteira);

            resultSet = resultSet.substring(Type.CARTEIRA.maxLenght);
        }

        return carteiraLista;
    }

    public static Carteira readCarteira(int carteiraId){
        AtomicReference<Carteira> carteira = new AtomicReference<>();
        readCarteira().iterator().forEachRemaining(item -> {
            if (item.getId().getId() == carteiraId)
                carteira.set(item);
        });
        return carteira.get();
    }
    
    public static String lineCustodiante(Custodiante custodiante) {
        return intField(custodiante.getId().getId(), 3) +
                intField(custodiante.getInvestidor().getId().getId(), 3) ;
    }

    public static ListaEncadeada<Custodiante> readCustodiante() {
        SaverManager saverManager = new SaverManager();
        String resultSet = saverManager.read(Type.CUSTODIANTE);

        ListaEncadeada<Custodiante> custodianteLista = new ListaEncadeada<>();

        while (resultSet.length() > 0) {
            int id = Integer.parseInt(resultSet.substring(0, 3).trim());
            int investidorId = Integer.parseInt(resultSet.substring(3, 6).trim());

            Id idClass = new Id(id, Type.CUSTODIANTE);

            Custodiante custodiante = new Custodiante(idClass, null);

            custodianteLista.add(custodiante);
            resultSet = resultSet.substring(Type.CUSTODIANTE.maxLenght);
        }

        return custodianteLista;
    }

    public static Custodiante readCustodiante(int custodianteId){
        AtomicReference<Custodiante> custodiante = new AtomicReference<>();
        readCustodiante().iterator().forEachRemaining(item -> {
            if (item.getId().getId() == custodianteId)
                custodiante.set(item);
        });
        return custodiante.get();
    }

    public static String lineHistorico(Historico historico) {
        return intField(historico.getId().getId(), 3) +
                intField(historico.getAtivo().getId().getId(), 3) +
                doubleField(historico.getQuantidade(), 5) +
                doubleField(historico.getValor(), 12) +
                intField(historico.getTipo(), 1) +
                stringField(historico.getHorarioDaTransacao().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), 19) +
                intField(historico.getInvestidor().getId().getId(), 3) +
                intField(historico.getCorretora().getId().getId(), 3);
    }

    public static ListaEncadeada<Historico> readHistorico() {
        SaverManager saverManager = new SaverManager();
        String resultSet = saverManager.read(Type.HISTORICO);

        ListaEncadeada<Historico> historicoLista = new ListaEncadeada<>();

        while (resultSet.length() >= Type.HISTORICO.maxLenght) {
            int id = Integer.parseInt(resultSet.substring(0, 3));
            int ativoId = Integer.parseInt(resultSet.substring(3, 6));
            double quantidade = Double.parseDouble(resultSet.substring(6, 18));
            double valor = Double.parseDouble(resultSet.substring(18, 30));
            int tipo = Integer.parseInt(resultSet.substring(30, 31));
            String horarioDaTransacao = resultSet.substring(31, 49);
            int investidorId = Integer.parseInt(resultSet.substring(49, 52));
            int corretoraId = Integer.parseInt(resultSet.substring(52, 55));

            Id idClass = new Id(id, Type.HISTORICO);

            // Defina o ativo com base no ID
            Ativo ativo = readAtivo(ativoId);

            // Defina o investidor com base no ID
            Investidor investidor = readInvestidor(investidorId);

            // Defina a corretora com base no ID
            Corretora corretora = readCorretora(corretoraId);

            Historico historico = new Historico(idClass, ativo, quantidade, valor, tipo, investidor, corretora, new LocalDateTimeStringConverter().fromString(horarioDaTransacao));
            historicoLista.add(historico);

            resultSet = resultSet.substring(Type.HISTORICO.maxLenght);
        }

        return historicoLista;
    }

    public static Historico readHistorico(int historicoId){
        AtomicReference<Historico> historico = new AtomicReference<>();
        readHistorico().iterator().forEachRemaining(item -> {
            if (item.getId().getId() == historicoId)
                historico.set(item);
        });
        return historico.get();
    }
    
    public static String lineBolsa(Bolsa bolsa) {
        return intField(bolsa.getId().getId(), 3) +
                stringField(bolsa.getNome(), 100);
    }

    public static ListaEncadeada<Bolsa> readBolsa() {
        SaverManager saverManager = new SaverManager();
        String resultSet = saverManager.read(Type.BOLSA);

        ListaEncadeada<Bolsa> bolsaLista = new ListaEncadeada<>();

        while (resultSet.length() >= Type.BOLSA.maxLenght) {
            int id = Integer.parseInt(resultSet.substring(0, 3).trim());
            String nome = resultSet.substring(3, 103).trim();

            Id idClass = new Id(id, Type.BOLSA);
            Bolsa bolsa = new Bolsa(idClass, nome);

            bolsaLista.add(bolsa);

            resultSet = resultSet.substring(Type.BOLSA.maxLenght);
        }

        return bolsaLista;
    }

    public static Bolsa readBolsa(int bolsaId){
        AtomicReference<Bolsa> bolsa = new AtomicReference<>();
        readBolsa().iterator().forEachRemaining(item -> {
            if (item.getId().getId() == bolsaId)
                bolsa.set(item);
        });
        return bolsa.get();
    }

    public static String lineAtivoCustodiado(AtivoCustodiado ativoCustodiado) {
        return intField(ativoCustodiado.getId().getId(), 3) +
                intField(ativoCustodiado.getInvestidor().getId().getId(), 3) +
                intField(ativoCustodiado.getAtivo().getId().getId(), 3) +
                doubleField(ativoCustodiado.getQuantidade(), 12);
    }

    public static ListaEncadeada<AtivoCustodiado> readAtivoCustodiado() {
        SaverManager saverManager = new SaverManager();
        String resultSet = saverManager.read(Type.ATIVO_CUSTODIADO);

        ListaEncadeada<AtivoCustodiado> ativoCustodiadoLista = new ListaEncadeada<>();

        while (resultSet.length() >= Type.ATIVO_CUSTODIADO.maxLenght) {
            int id = Integer.parseInt(resultSet.substring(0, 3).trim());
            int investidorId = Integer.parseInt(resultSet.substring(3, 6).trim());
            int ativoId = Integer.parseInt(resultSet.substring(6, 9).trim());
            double quantidade = Double.parseDouble(resultSet.substring(9, 21).trim());

            Id idClass = new Id(id, Type.ATIVO_CUSTODIADO);

            Ativo ativo = readAtivo(ativoId);

            AtivoCustodiado ativoCustodiado = new AtivoCustodiado(idClass, null, ativo, quantidade);
            ativoCustodiado.investidorId = investidorId;
            ativoCustodiadoLista.add(ativoCustodiado);


            resultSet = resultSet.substring(Type.ATIVO_CUSTODIADO.maxLenght);
        }

        return ativoCustodiadoLista;
    }

    public static Investidor refinedReadInvestidor(Investidor investidor) {
        Custodiante custodiante = readCustodiante(investidor.custodianteId);
        custodiante.setInvestidor(investidor);
        readAtivoCustodiado().forEach(ativoCustodiado -> {
            if (ativoCustodiado.investidorId == investidor.getId().getId()){
                ativoCustodiado.setInvestidor(investidor);
                custodiante.put(ativoCustodiado.getAtivo(), ativoCustodiado);
            }
        });
        investidor.setCustodiante(custodiante);
        return investidor;
    }
}
