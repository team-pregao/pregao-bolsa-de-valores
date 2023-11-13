package com.teampregao.pregaobolsadevalores.ed;

public class InsertionSort<T extends Comparable<T>> {
    public void sort(T[] array) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            T chave = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].compareTo(chave) > 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = chave;
        }
    }

    public void reverseSort(T[] array) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            T chave = array[i];
            int j = i - 1;

            while (j >= 0 && array[j].compareTo(chave) < 0) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = chave;
        }
    }

}
