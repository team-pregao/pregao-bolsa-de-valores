package com.teampregao.pregaobolsadevalores.ed;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;

public class ListaEncadeada<T> implements Iterable<T> {
    private No head;
    private int size;

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            No currenteNode = head;
            @Override
            public boolean hasNext() {
                return currenteNode != null;
            }

            @Override
            public T next() {
                if (hasNext()){
                    T data = currenteNode.dado;
                    currenteNode = currenteNode.no;
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

    class No{
        T dado;
        No no;

        public No(T dado, No no) {
            this.dado = dado;
            this.no = no;
        }

        public No(T dado) {
            this.dado = dado;
            this.no = null;
        }

        public No() {
            this.dado = null;
            this.no = null;
        }

        public boolean next(){
            return no != null;
        }

    }

    private void add(No newNo, No no){
        if (no.next()) {
            add(newNo, no.no);
        } else {
            no.no = newNo;
        }
    }

    private void add(No newNo, int index, int count, No no){
        if (index > size){
            throw new ArrayIndexOutOfBoundsException();
        }
        if (no.next() && count < index - 1) {
            add(newNo, index, ++count, no.no);
        } else {
            No temp = no.no;
            no.no = newNo;
            newNo.no = temp;
        }
    }

    //Insere no final
    public void add(T dado){
        if (head == null){
            head = new No(dado);
            size += 1;
            return;
        }
        add(new No(dado), head);
        size += 1;
    }
    // Insere em uma posição específica
    public void add(int index, T dado){
        if (head == null || index == 0){
            head = new No(dado);
            size += 1;
            return;
        }
        add(new No(dado), index, 0, head);
        size += 1;
    }

    public T get(int index) {
        No no = head;
        int count = 0;
        while (no != null){
            if (count == index){
                return no.dado;
            } else {
                count++;
                no = no.no;
            }
        }
        return null;
    }

    public int getIndex(T dado) {
        No no = head;
        int count = 0;
        while (no != null){
            if (no.dado.equals(dado)){
                return count;
            } else {
                count++;
                no = no.no;
            }
        }
        return -1;
    }

    public void remove(int index){
        if (index >= size){
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.no;
            return;
        }
        No noAnt = head.no;
        for (int i = 1; i < index - 1; i++) {
            if(noAnt.no.next()){
                noAnt = noAnt.no;
            } else {
                noAnt.no = null;
                return;
            }
        }
        No temp = noAnt.no;
        noAnt.no = noAnt.no.no;
        temp.no = null;
        size -= 1;
    }

    public void remove(T dado){
        remove(getIndex(dado));
    }

    public int getSize() {
        return size;
    }
    public T[] toArray(T[] array) {
        if (array.length < size) {
            array = (T[]) Array.newInstance(array.getClass().getComponentType(), size);
        }

        No currentNo = head;
        int i = 0;
        while (currentNo != null) {
            array[i] = currentNo.dado;
            currentNo = currentNo.no;
            i++;
        }

        for (; i < array.length; i++) {
            array[i] = null;
        }

        return array;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        No no = head;
        while (no != null){
            stringBuilder.append(" ").append(no.dado).append(" ");
            no = no.no;
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
