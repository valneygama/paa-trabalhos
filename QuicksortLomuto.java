class QuicksortLomuto {

    static int[] quickSort(int[] lista, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int pivo = partition(lista, startIndex, endIndex);
            quickSort(lista, startIndex, pivo-1);
            quickSort(lista, pivo+1, endIndex);
        }
        return lista;
    }
    
    static int partition(int[] arr, int low, int right) {
        int pivot = arr[right];
        int i = low;
        for(int j=low; j < right; j++) {
            if(arr[j] <= pivot) {
                int aux = arr[i];
                arr[i] = arr[j];
                arr[j] = aux; 
                i++;
            }   
        } 
        int aux = arr[i];
        arr[i] = arr[right];
        arr[right] = aux; 
        return i;
    } 
}