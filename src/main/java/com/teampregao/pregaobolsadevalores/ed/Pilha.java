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

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T value = topo.getValue();
        topo = topo.getProximo();
        tamanho--;
        return value;
    }

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
