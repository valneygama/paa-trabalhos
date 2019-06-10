import java.util.Arrays;
import java.util.Random;

class Quicksort {
    static int trocas = 0;

    public enum Tipo {
        HOARE, LOMUTO;
    }

    static int[] generateArray(int size) {
        int[] array = new int[size];
        Random rand = new Random();
        for (int j = 0; j < size; j++) {
            array[j] = rand.nextInt(size * 3);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] original = generateArray(30);
        int[] listaHoare = Arrays.copyOf(original, original.length);
        int[] listaLomuto = Arrays.copyOf(original, original.length);

        System.out.println("array: "+Arrays.toString(original));
        System.out.println();
        System.out.println("iniciando quicksort (metodo HOARE)...");
        listaHoare = quickSort(Tipo.HOARE, listaHoare, 0, listaHoare.length-1);
        System.out.println("Array ordenado: " + Arrays.toString(listaHoare) 
            + "\nTrocas efetuadas: " + trocas);

        trocas = 0;
        System.out.println();
        System.out.println("iniciando quicksort (metodo LOMUTO)...");
        listaHoare = quickSort(Tipo.LOMUTO, listaLomuto, 0, listaLomuto.length-1);
        System.out.println("Array ordenado: " + Arrays.toString(listaLomuto) 
            + "\nTrocas efetuadas: " + trocas);

    }

    static int[] quickSort(Tipo tipo, int[] lista, int startIndex, int endIndex) {
        if (tipo == Tipo.HOARE) {
            return quickSortHoare(lista, startIndex, endIndex);
        } else {
            return quickSortLomuto(lista, startIndex, endIndex);
        }
    }

    static int[] quickSortHoare(int[] lista, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int pivo = hoarePartition(lista, startIndex, endIndex);
            quickSortHoare(lista, startIndex, pivo);
            quickSortHoare(lista, pivo+1, endIndex);
        }
        return lista;
    }

    static int hoarePartition(int []arr, int low, int high) { 
        int pivot = arr[low]; 
        int i = low - 1, j = high + 1; 
    
        while (true) {
            do { i++; } while (arr[i] < pivot); // Find leftmost element greater than or equal to pivot 
            do { j--; } while (arr[j] > pivot); // Find rightmost element smaller than or equal to pivot 
            if (i >= j) return j;               // If two pointers met. 
            swap(arr, i, j);                    // Senao, troca i com j em suas posicoes
        } 
    }

    static void swap(int[] arr, int i, int j) {
        trocas++;
        int aux = arr[i];
        arr[i] = arr[j];
        arr[j] = aux;
    }

    static int[] quickSortLomuto(int[] lista, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int pivo = lomutoPartition(lista, startIndex, endIndex);
            quickSortLomuto(lista, startIndex, pivo-1);
            quickSortLomuto(lista, pivo+1, endIndex);
        }
        return lista;
    }
    
    static int lomutoPartition(int[] arr, int l, int r) {
        int pivot = arr[r];
        int i =l;
        for(int j=l; j<r; j++) {
            if(arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }   
        } 
        swap(arr, i, r);
        return i;
    } 

    
}