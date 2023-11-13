package com.teampregao.pregaobolsadevalores.ed;

import java.util.EmptyStackException;

public class Pilha<T> {
    private No<T> topo;
    private int tamanho;

    public Pilha() {
        topo = null;
        tamanho = 0;
    }
    
    public boolean isEmpty() {
        return topo == null;
    }
    
    public int size() {
        return tamanho;
    }

    //Adiciona um novo elemento ao topo da pilha
    public void push(T value) {
        No<T> novoNo = new No<>(value);
        if (isEmpty()) {
            topo = novoNo;
        } else {
            novoNo.setProximo(topo);
            topo = novoNo;
        }
        tamanho++;
    }

    //Remove e retorna o elemento do topo da pilha
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T value = topo.getValue();
        topo = topo.getProximo();
        tamanho--;
        return value;
    }

    //Retorna sem remover o primeiro elemento da pilha
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return topo.getValue();
    }

    private static class No<T> {
        private final T value;
        private No<T> proximo;

        public No(T value) {
            this.value = value;
            this.proximo = null;
        }

        public T getValue() {
            return value;
        }

        public void setProximo(No<T> proximo) {
            this.proximo = proximo;
        }

        public No<T> getProximo() {
            return proximo;
        }
    }
}
