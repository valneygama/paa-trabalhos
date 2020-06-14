

public class QuicksortHoare {

    static void quickSort(int[] lista, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int pivo = partition(lista, startIndex, endIndex);
            quickSort(lista, startIndex, pivo);
            quickSort(lista, pivo+1, endIndex);
        }
    }

    private static int partition(int []arr, int low, int high) { 
        int pivot = arr[low]; 
        int i = low - 1, j = high + 1; 
        while (true) {
            do { i++; } while (arr[i] < pivot); 
            do { j--; } while (arr[j] > pivot); 
            if (i >= j) return j;               
            int aux = arr[i];
            arr[i] = arr[j];
            arr[j] = aux;
        } 
    }
    
}