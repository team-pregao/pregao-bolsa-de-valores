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

    public void executarAcao(Historico pedido) {
        if (pedido.getTipo() == 1){
            System.out.println("1");
            double qntCustodiado = pedido.getInvestidor().getCustodiante().getAtivosCustodiados().get(pedido.getAtivo().getId().getId()).getQuantidade();
            pedido.getInvestidor().getCustodiante().adicionarAtivoCustodiado(pedido.getAtivo(), qntCustodiado - pedido.getQuantidade());
            pedido.getInvestidor().transferir(pedido.getValor());
        } else if (pedido.getTipo() == 0) {
            System.out.println("0");
            if (pedido.getInvestidor().getCustodiante().getAtivosCustodiados().get(pedido.getAtivo().getId().getId()) != null){
                System.out.println("update custoidado");
                double qntCustodiado = pedido.getInvestidor().getCustodiante().getAtivosCustodiados().get(pedido.getAtivo().getId().getId()).getQuantidade();
                pedido.getInvestidor().getCustodiante().adicionarAtivoCustodiado(pedido.getAtivo(), qntCustodiado + pedido.getQuantidade());
            } else {
                System.out.println("insert custodiado");
               pedido.getInvestidor().getCustodiante().adicionarAtivoCustodiado(pedido.getAtivo(), pedido.getQuantidade());
            }
            pedido.getInvestidor().transferir(pedido.getValor() * -1);
        }
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
                pilhaDeVendas.push(pedidoVenda);
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

