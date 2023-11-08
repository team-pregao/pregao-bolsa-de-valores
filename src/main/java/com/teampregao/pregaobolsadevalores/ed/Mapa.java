package com.teampregao.pregaobolsadevalores.ed;

public class Mapa<K, V>{
    No head;
    int size;

    public class No{
        V value;
        K key;
        No no;

        public No(K key, V value) {
            this.value = value;
            this.key = key;
            this.no = null;
        }

        public boolean next(){
            return no != null;
        }
        
    }

    private void put(No newNo, No no){
        if (no.no == null || no.no.key.equals(newNo.key) ) {
            if (no.no == null){
                no.no = newNo;
            } else {
                no.no.value = newNo.value;
            }
        } else {
            put(newNo, no.no);
        }
    }

    public void put(K key, V value){
        if (head == null){
            head = new No(key, value);
            size += 1;
            return;
        }
        put(new No(key, value), head);
        size += 1;
    }

    public V get(K key) {
        No no = head;
        while (no != null){
            if (key.equals(no.key)){
                return no.value;
            } else {
                no = no.no;
            }
        }
        return null;
    }

    public K getKey(V value) {
        No no = head;
        while (no != null){
            if (no.value.equals(value)){
                return no.key;
            } else {
                no = no.no;
            }
        }
        return null;
    }

    public void remove(K key){
        if (key.equals(head.key)) {
            head = head.no;
            return;
        }
        No no = head;
        while (no.no != null){
            if (no.no.key.equals(key)){
                No temp = no.no;
                no.no = no.no.no;
                temp.no = null;
            } else {
                no = no.no;
            }
        }
        size -= 1;
    }

    public void removeByValue(V value){
        remove(getKey(value));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        No no = head;
        while (no != null){
            stringBuilder.append(" ").append(no.key).append(": ").append(get(no.key));
            no = no.no;
        }
        stringBuilder.append(" }");
        return stringBuilder.toString();
    }

}
