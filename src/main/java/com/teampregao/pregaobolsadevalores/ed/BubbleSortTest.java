public class BubbleSortTest {
    public static void main(String[] args) {
        // Criar um array para teste
        int[] array = {64, 34, 25, 12, 22, 11, 90};

        // Exibir o array antes da ordenação
        System.out.println("Array antes da ordenação:");
        printArray(array);

        // Chamar o método de ordenação bubbleSort
        bubbleSort(array);

        // Exibir o array após a ordenação
        System.out.println("\nArray após a ordenação:");
        printArray(array);
    }

    // Método de ordenação bubbleSort (mesmo código do exemplo anterior)
    static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    // Método auxiliar para imprimir o array
    static void printArray(int[] array) {
        int n = array.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}

