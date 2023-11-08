package com.teampregao.pregaobolsadevalores.entidades;

import com.teampregao.pregaobolsadevalores.ed.Pilha;
import com.teampregao.pregaobolsadevalores.ed.Mapa;
import com.teampregao.pregaobolsadevalores.manager.EntityManager;
import com.teampregao.pregaobolsadevalores.manager.SaverManager;

import java.util.EmptyStackException;

public class Bolsa {
    private final Id id;
    private final String nome;
    private final Mapa<Id, Mapa<String, Pilha<Historico>>> livroDeOfertas;

    public Bolsa(Id id, String nome) {
        this.id = id;
        this.nome = nome;
        this.livroDeOfertas = new Mapa<>();
        EntityManager.readAtivo().iterator().forEachRemaining(ativo -> {
            Mapa<String, Pilha<Historico>> pagina = new Mapa<>();
            Pilha<Historico> vendas = new Pilha<>();
            Pilha<Historico> compras = new Pilha<>();

            SaverManager saverManager = new SaverManager();
            Historico pedido = new Historico();

            Investidor ping = new Investidor(new Id(Type.INVESTIDOR), "ping", 10000000.0);
            //saverManager.insert(EntityManager.lineInvestidor(ping), ping.getId().getType());
            Corretora pong = new Corretora(new Id(Type.CORRETORA), "pong", this);
           // saverManager.insert(EntityManager.lineCorretora(pong), pong.getId().getType());
            ping.comprarAcao(ativo, 1000, pong, true);

            pedido.setInvestidor(ping);
            pedido.setValor(10000.0);
            pedido.setAtivo(ativo);
            pedido.setQuantidade(1000);
            pedido.setTipo(1);
            pedido.setCorretora(pong);
            
            vendas.push(pedido);
            
            pedido.setTipo(0);
            
            compras.push(pedido);
            
            pagina.put("venda", vendas);
            pagina.put("compra", compras);
            livroDeOfertas.put(ativo.getId(), pagina);
        });
    }

    public String getNome() {
        return nome;
    }

    public Mapa<Id, Mapa<String, Pilha<Historico>>> getLivroDeOfertas() {
        return livroDeOfertas;
    }

    public Id getId() {
        return id;
    }

    public void adicionarPedidoDeCompra(Historico pedido) {
        try {
            livroDeOfertas.get(pedido.getAtivo().getId()).get("compra").push(pedido);
        } catch (NullPointerException e){
            criarPagina(pedido);
            livroDeOfertas.get(pedido.getAtivo().getId()).get("compra").push(pedido);
            System.out.println("pagina criada");
        }
        System.out.println("livro de ofertas");
        executarPedidos(pedido);
    }

    public void adicionarPedidoDeVenda(Historico pedido) {
        try {
            livroDeOfertas.get(pedido.getAtivo().getId()).get("venda").push(pedido);
            System.out.println("pagina criada 1");
        } catch (NullPointerException e){
            criarPagina(pedido);
            System.out.println("pagina criada");
            livroDeOfertas.get(pedido.getAtivo().getId()).get("venda").push(pedido);
        }
        executarPedidos(pedido);
    }

    private void criarPagina(Historico pedido){
        Mapa<String, Pilha<Historico>> pagina = new Mapa<>();
        pagina.put("venda", new Pilha<>());
        pagina.put("compra", new Pilha<>());
        livroDeOfertas.put(pedido.getAtivo().getId(), pagina);
        System.out.println("pagina criada");
    }
    
    public void executarPedidos(Historico pedido) {
        Pilha<Historico> pilhaDeCompras = livroDeOfertas.get(pedido.getAtivo().getId()).get("compra");
        Pilha<Historico> pilhaDeVendas = livroDeOfertas.get(pedido.getAtivo().getId()).get("venda");
        System.out.println(pilhaDeCompras.isEmpty());

        while (!pilhaDeCompras.isEmpty() || !pilhaDeVendas.isEmpty()){
            System.out.println("while");
            Historico pedidoCompra;
            Historico pedidoVenda;
            try {
                pedidoCompra = pilhaDeCompras.pop();
                pedidoVenda = pilhaDeVendas.pop();
            } catch (EmptyStackException e){
                System.out.println("pilha vazia");
                return;
            }
            if (pedidoCompra.getQuantidade() > pedidoVenda.getQuantidade()){
                System.out.println("1");
                pedidoCompra.setQuantidade(pedidoCompra.getQuantidade() - pedidoVenda.getQuantidade());
                pilhaDeCompras.push(pedidoCompra);
                pedidoCompra.getInvestidor().getCustodiante().adicionarAtivoCustodiado(pedidoCompra.getAtivo(), pedidoCompra.getQuantidade());
                pedidoVenda.getInvestidor().getCustodiante().removerAtivoCustodiado(pedidoVenda.getAtivo());
                pedidoVenda.getInvestidor().transferir(pedidoVenda.getValor());
                pedidoCompra.getInvestidor().transferir(pedidoVenda.getValor() * -1);
            } else if (pedidoCompra.getQuantidade() < pedidoVenda.getQuantidade()) {
                System.out.println("2");
                pedidoVenda.setQuantidade(pedidoVenda.getQuantidade() - pedidoCompra.getQuantidade());
                pilhaDeVendas.push(pedidoVenda); //falta ainda 3
                pedidoVenda.getInvestidor().getCustodiante().adicionarAtivoCustodiado(pedidoVenda.getAtivo(), pedidoVenda.getQuantidade());
                pedidoCompra.getInvestidor().getCustodiante().adicionarAtivoCustodiado(pedidoCompra.getAtivo(), pedidoCompra.getQuantidade());
                pedidoCompra.getInvestidor().transferir(pedidoCompra.getValor() * -1);
                pedidoVenda.getInvestidor().transferir(pedidoCompra.getValor());
            } else {
                System.out.println("3");
                pedidoVenda.getInvestidor().getCustodiante().removerAtivoCustodiado(pedidoVenda.getAtivo());
                pedidoCompra.getInvestidor().getCustodiante().adicionarAtivoCustodiado(pedidoCompra.getAtivo(), pedidoCompra.getQuantidade());
                pedidoCompra.getInvestidor().transferir(pedidoCompra.getValor() * -1);
                pedidoVenda.getInvestidor().transferir(pedidoCompra.getValor());
            }
        }
    }
    
}

