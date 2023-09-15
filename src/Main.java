public class Main {
    public static void main(String[] args) {
        // Criar uma bolsa de valores
        Bolsa bolsa = new Bolsa();
        bolsa.setNome("Bolsa de Valores XYZ");

        // Criar uma corretora
        Corretora corretora = new Corretora();
        corretora.setNome("Corretora ABC");

        // Criar um investidor
        Investidor investidor = new Investidor("Investidor 1", 1);

        // Criar uma carteira para o investidor
        Carteira carteira = new Carteira();
        investidor.getCarteiras().add(carteira);

        // Adicionar alguns ativos à carteira do investidor
        Ativo ativo1 = new Ativo();
        ativo1.setCodigo("AAPL");
        ativo1.setNome("Apple Inc.");
        ativo1.setTipo("Ação");
        ativo1.setValorAtual(150.0f);

        Ativo ativo2 = new Ativo();
        ativo2.setCodigo("GOOGL");
        ativo2.setNome("Alphabet Inc.");
        ativo2.setTipo("Ação");
        ativo2.setValorAtual(2500.0f);

        carteira.getAtivos().add(ativo1);
        carteira.getAtivos().add(ativo2);

        // Realizar uma compra de ações
        Historico compra = new Historico();
        compra.setAtivo(ativo1);
        compra.setQuantidade(10);
        compra.setValor(160.0f);
        compra.setTipo("Compra");

        // Adicionar o histórico de compra à carteira do investidor
        carteira.getHistorico().add(compra);

        // Simular uma venda de ações
        Historico venda = new Historico();
        venda.setAtivo(ativo1);
        venda.setQuantidade(5);
        venda.setValor(170.0f);
        venda.setTipo("Venda");

        // Adicionar o histórico de venda à carteira do investidor
        carteira.getHistorico().add(venda);

        // Exibir o histórico de transações na carteira do investidor
        System.out.println("Histórico de Transações da Carteira de " + investidor.getNome() + ":");
        for (Historico transacao : carteira.getHistorico()) {
            System.out.println("Ativo: " + transacao.getAtivo().getNome() +
                    ", Tipo: " + transacao.getTipo() +
                    ", Quantidade: " + transacao.getQuantidade() +
                    ", Valor: " + transacao.getValor());
        }
    }
}
