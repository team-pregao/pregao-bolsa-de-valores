import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Criando uma Bolsa de Valores
        Bolsa bolsaDeValores = new Bolsa("B3");

        // Criando Corretoras
        Corretora corretoraA = new Corretora("Corretora Alpha");
        Corretora corretoraB = new Corretora("Corretora Beta");
        Corretora corretoraC = new Corretora("Corretora Gama");

        // Criando Investidores
        SaverManager sm = new SaverManager();
        sm.clean();
        Investidor investidorAlice = new Investidor("Alice", 1, corretoraA);
        sm.insert(EntityManager.lineInvestidor(investidorAlice), Type.INVESTIDOR);
        Investidor investidorBob = new Investidor("Bob", 2, corretoraB);
        sm.insert(EntityManager.lineInvestidor(investidorBob), Type.INVESTIDOR);
        Investidor investidorCarol = new Investidor("Carol", 3, corretoraC);
        sm.insert(EntityManager.lineInvestidor(investidorCarol), Type.INVESTIDOR);

        // Transferindo fundos para investidores
        investidorAlice.transferir(10000);
        investidorBob.transferir(15000);
        investidorCarol.transferir(20000);

        // Criando algumas ações
        AcaoOrdinaria acaoApple = new AcaoOrdinaria("Apple Inc.", 150.0);
        AcaoPreferencial acaoGoogle = new AcaoPreferencial("Alphabet Inc.", 250.0, 'A');
        AcaoOrdinaria acaoMicrosoft = new AcaoOrdinaria("Microsoft Corporation", 300.0);
        AcaoPreferencial acaoAmazon = new AcaoPreferencial("Amazon.com Inc.", 350.0, 'B');

        // Simulando compra de ações
        investidorAlice.comprarAcao(acaoApple, 15, corretoraA);
        investidorBob.comprarAcao(acaoGoogle, 8, corretoraB);
        investidorCarol.comprarAcao(acaoMicrosoft, 12, corretoraC);
        investidorAlice.comprarAcao(acaoAmazon, 7, corretoraA);

        corretoraA.executarOrdens();
        corretoraB.executarOrdens();
        corretoraC.executarOrdens();

        // Simulando venda de ações
        investidorAlice.venderAcao(acaoApple, 5, corretoraA);
        investidorBob.venderAcao(acaoGoogle, 3, corretoraB);
        investidorCarol.venderAcao(acaoMicrosoft, 6, corretoraC);

        corretoraA.executarOrdens();
        corretoraB.executarOrdens();
        corretoraC.executarOrdens();

        // Exibindo informações sobre os investidores e suas carteiras
        System.out.println("Investidor Alice:\n" + investidorAlice);
        System.out.println("Investidor Bob:\n" + investidorBob);
        System.out.println("Investidor Carol:\n" + investidorCarol);

        // Exibindo informações sobre as ações custodiadas por um custodiante fictício
        Custodiante custodianteX = new Custodiante("Custodiante X");
        custodianteX.adicionarAtivoCustodiado(acaoApple);
        custodianteX.adicionarAtivoCustodiado(acaoGoogle);
        custodianteX.adicionarAtivoCustodiado(acaoMicrosoft);
        custodianteX.adicionarAtivoCustodiado(acaoAmazon);
        custodianteX.ListaEncadeadaarAtivosCustodiados();

        SaverManager saverManager = new SaverManager();
        saverManager.addInsert(EntityManager.lineBolsa(bolsaDeValores), Type.BOLSA);

        saverManager.addInsert(EntityManager.lineCorretora(corretoraA), Type.CORRETORA);
        saverManager.addInsert(EntityManager.lineCorretora(corretoraB), Type.CORRETORA);
        saverManager.addInsert(EntityManager.lineCorretora(corretoraC), Type.CORRETORA);

        saverManager.addInsert(EntityManager.lineAtivo(acaoAmazon), Type.ATIVO);
        saverManager.addInsert(EntityManager.lineAtivo(acaoApple), Type.ATIVO);
        saverManager.addInsert(EntityManager.lineAtivo(acaoGoogle), Type.ATIVO);
        saverManager.addInsert(EntityManager.lineAtivo(acaoMicrosoft), Type.ATIVO);

        saverManager.addInsert(EntityManager.lineCustodiante(custodianteX), Type.CUSTODIANTE);

        saverManager.update(new Id(1, Type.INVESTIDOR), EntityManager.lineInvestidor(investidorAlice));

        saverManager.execute();

        System.out.println(saverManager.read(Type.INVESTIDOR));
        System.out.println(saverManager.read(Type.INVESTIDOR, 2).substring(0, 3));


    }
}
