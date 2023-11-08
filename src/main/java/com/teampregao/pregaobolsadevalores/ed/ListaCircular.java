package com.teampregao.pregaobolsadevalores.ed;

public class ListaCircular<T> {
    Node head;
    int size;

    public class Node {
        Node next;
        T date;
        public Node(T date) {
            next = null;
            this.date = date;
        }
    }
}
