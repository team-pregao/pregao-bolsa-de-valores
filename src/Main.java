public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        // Criar uma bolsa de valores
        Bolsa bolsa = new Bolsa("Bolsa de Valores XYZ");

        // Criar uma corretora
        Corretora corretora = new Corretora("Corretora ABC");

        // Criar um investidor
        Investidor investidor = new Investidor("Investidor 1", 1, corretora);
        investidor.transferir(100000);

        // Criar uma carteira para o investidor
        Carteira carteira = new Carteira();
        investidor.getCorretora().setCarteira(carteira);

        // Adicionar alguns ativos à carteira do investidor
        Ativo ativo1 = new Ativo("AAPL", "Apple Inc.", "Ação", 150.0);

        Ativo ativo2 = new Ativo("GOOGL", "Alphabet Inc.", "Ação", 2500.0);

        investidor.comprarAcao(ativo1, 10);
        investidor.comprarAcao(ativo2, 20);
        investidor.venderAcao(ativo2, 5);

        // Exibir o histórico de transações na carteira do investidor
        System.out.println("Histórico de Transações da Carteira de " + investidor.getNome() + ":");
        for (Historico transacao : carteira.getHistorico()) {
            System.out.println("Ativo: " + transacao.getAtivo().getNome() +
                    ", Tipo: " + transacao.getTipo() +
                    ", Quantidade: " + transacao.getQuantidade() +
                    ", Valor: " + transacao.getValor());
        }
        System.out.println("Saldo atual: " + investidor.getSaldo());
    }
}
