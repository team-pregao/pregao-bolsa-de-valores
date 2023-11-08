package com.teampregao.pregaobolsadevalores.ed;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Fila<T> implements Iterable<T> {
    private No<T> front; // Referência para o elemento da frente da fila
    private No<T> rear;  // Referência para o elemento de trás da fila
    private int size;      // Tamanho da fila

    // Classe interna para representar um nó na fila
    private static class No<T> {
        T data;           // Dados armazenados no nó
        No<T> next;     // Referência para o próximo nó

        public No(T data) {
            this.data = data;
            this.next = null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Fila.No<T> currenteNode = front;
            @Override
            public boolean hasNext() {
                return currenteNode != null;
            }

            @Override
            public T next() {
                if (hasNext()){
                    T data = currenteNode.data;
                    currenteNode = currenteNode.next;
                    return data;
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }


    // Construtor para criar uma fila vazia
    public Fila() {
        front = null;
        rear = null;
        size = 0;
    }

    // Verifica se a fila está vazia
    public boolean isEmpty() {
        return size == 0;
    }

    // Retorna o tamanho da fila
    public int size() {
        return size;
    }

    // Adiciona um elemento à fila (insere no final)
    public void enqueue(T item) {
        No<T> newNo = new No<>(item);
        if (isEmpty()) {
            front = newNo;
            rear = newNo;
        } else {
            rear.next = newNo;
            rear = newNo;
        }
        size++;
    }

    // Remove e retorna o elemento da frente da fila
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("A fila está vazia.");
        }
        T data = front.data;
        front = front.next;
        size--;
        if (isEmpty()) {
            rear = null; // Se a fila ficar vazia, atualize a referência rear
        }
        return data;
    }

    // Retorna o elemento da frente da fila sem removê-lo
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("A fila está vazia.");
        }
        return front.data;
    }
}
