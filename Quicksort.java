
import java.util.Arrays;
import java.util.Random;

class Quicksort {
    // Variavel global de trocas para facilitar a contagem apenas.
    static int trocas = 0;

    public static void main2(String[] args) {
        String exec = "0";
        int size = 1000;
        if (args.length>=0) {
            exec = args[0];
            size = Integer.parseInt(args[1]);
        }
        int original[] = generateArray(size);
        switch (exec) {
            case "1": // HOARE
                execucao(original, 1);
                break;
            case "2": // LOMUTO
                execucao(original, 2);
                break;
            default:
                execucaoParaArray(size);    
        }
    }

    // Chama os Testes para cada Array 
    public static void main(String[] args) {
        System.out.println("Testes de Partição Quicksort\tExporte CSV");
        System.out.println("----------------------------\t-----------");
        System.out.println("TIPO\tTAMANHO\tALGO1\tTROCAS\tTEMPO\tALGO2\tTROCAS\tTEMPO");
        // array de valores aleatorios
        execucaoParaArray(100);
        execucaoParaArray(500);
        execucaoParaArray(1000);
        execucaoParaArray(5000);
        execucaoParaArray(30000);
        execucaoParaArray(80000);
        execucaoParaArray(100000);
        execucaoParaArray(150000);
        execucaoParaArray(200000);
        //// array ja ordenado
        //execucaoParaSorted(1000);
        //execucaoParaSorted(5000);
        //// array de zeros
        //execucaoParaZeros(1000);
        //execucaoParaZeros(5000);
    }

    /// Geradores de Arrays: Aleatórios, Ordenados e de Zeros
    // Gerador de ordenads
    static int[] generateSorted(int size) {
        int[] array = new int[size];
        for (int i=0;i<size;i++) array[i] = i;
        return array;
    }
    // Gerador de Aleatorios - Gera numeros em alcance de até 3x o tamanho do array pedido
    // Ex.: 10 numeros aleatorios gera aleatorios de 1 ate 30.
    static int[] generateArray(int size) {
        int[] array = new int[size];
        Random rand = new Random();
        for (int j = 0; j < size; j++) {
            array[j] = rand.nextInt(size * 3);
        }
        return array;
    }
    // Gerador de Zeros
    static int[] generateZeros(int size) {
        int[] array = new int[size];
        for (int j = 0; j < size; j++) array[j] = 0;
        return array;
    }

    /// Cria o array de tamanho especificado para o tipo de execução e chama a execução dos testes para o array;
    // Executa para aleatorios
    static void execucaoParaArray(int size) {
        System.out.print("ALEATORIOS\t"+size);
        int[] arr = generateArray(size);
        execucao(arr, 0);
    }
    // Executa para zeros
    static void execucaoParaZeros(int size) {
        System.out.print("ZEROS\t"+size);
        int[] arr = generateArray(size);
        execucao(arr, 0);
    }
    // Executa para Ordenados
    static void execucaoParaSorted(int size) {
        System.out.print("ORDENADO\t"+size);
        int[] arr = generateSorted(size);
        execucao(arr, 0);
    }

    // Baseado no Array de entrada, chama a execução e caclula os tempos 
    // e trocas (variavel global) de cada quicksort.
    static void execucao(int[] original, int type) {
        double tempo;
        int[] listaHoare = Arrays.copyOf(original, original.length);
        int[] listaLomuto = Arrays.copyOf(original, original.length);
        String t = "";
        trocas = 0;

        if (type==0 || type==1) {
            trocas = 0;        
            //("HOARE");
            tempo = System.nanoTime();
            quickSortHoare(listaHoare, 0, listaHoare.length-1);
            tempo = (System.nanoTime() - tempo)/100000;
            t = tempo + "";
            t = t.replace(".",",");
            System.out.print("\tHOARE\t"+trocas + "\t" + t);
            try {Thread.sleep(200);} catch (Exception e){}
        }
        if (type==0 || type==2) {
            //("LOMUTO");
            tempo = System.nanoTime();
            listaLomuto = quickSortLomuto(listaLomuto, 0, listaLomuto.length-1);
            tempo = (System.nanoTime() - tempo)/100000;
            t = tempo + "";
            t = t.replace(".",",");
            System.out.print("\tLOMUTO\t"+trocas + "\t" + t);
            try {Thread.sleep(200);} catch (Exception e){}
        }
        try {Thread.sleep(600);} catch (Exception e){}
        System.out.println();
    }

    /// QuickSort -- Hoare
    static int[] quickSortHoare(int[] lista, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int pivo = hoarePartition(lista, startIndex, endIndex);
            quickSortHoare(lista, startIndex, pivo);
            quickSortHoare(lista, pivo+1, endIndex);
        }
        return lista;
    }
    // Metodo de Partição de Hoare
    static int hoarePartition(int []arr, int low, int high) { 
        int pivot = arr[low]; 
        int i = low - 1, j = high + 1; 
    
        while (true) {
            do { i++; } while (arr[i] < pivot);
            do { j--; } while (arr[j] > pivot);
            if (i >= j) return j;              
            swap(arr, i, j);                   
        } 
    }
    // Quicksort -- Lomuto
    static int[] quickSortLomuto(int[] lista, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int pivo = lomutoPartition(lista, startIndex, endIndex);
            quickSortLomuto(lista, startIndex, pivo-1);
            quickSortLomuto(lista, pivo+1, endIndex);
        }
        return lista;
    }
    // Metodo de Partição de Lomuto
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
    // Troca valores no array e aproveita para contar.
    static void swap(int[] arr, int i, int j) {
        trocas++;
        int aux = arr[i];
        arr[i] = arr[j];
        arr[j] = aux;
    }


    
}