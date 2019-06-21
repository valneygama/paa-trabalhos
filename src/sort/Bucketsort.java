/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author valney
 */
public class Bucketsort {
    
    public static void sort(int[] vetor, SortType type) {
	long maiorValor = Integer.MIN_VALUE;
        long tamanhoVetor = vetor.length;
        long numBaldes = tamanhoVetor;
        
        for (int i: vetor)
            if (i > maiorValor) 
                maiorValor = i;
        
        maiorValor = maiorValor + 1;

	LinkedList[] B = new LinkedList[(int) numBaldes];

	for (int i = 0; i < numBaldes; i++)
            B[i] = new LinkedList<Integer>();

        // distribute data into buckets: O(n)
        for (int i = 0; i < tamanhoVetor; i++) {
            int currentBucket = (int) Math.floor( vetor[i] * numBaldes / maiorValor );
            B[currentBucket].add(vetor[i]);
        }
        /*
	for (int i = 0; i < vetor.length; i++) {
            int j = numBaldes-1;
            
            while (true) {
		if (j<0) break;
		if (vetor[i] >= (j*5)) {
		    B[j].add(vetor[i]);
                    break;
                }
                j--;
            }
	}*/

	//Ordena e atualiza o vetor:
	int indice = 0;
	for (int i = 0; i < numBaldes; i++){
            int[] aux = new int[B[i].size()];
            //Coloca cada balde num vetor:
            for (int j = 0; j < aux.length; j++) {
                aux[j] = (Integer)B[i].get(j);
            }
            if (type != null)
                /// insertionSort(aux); //algoritmo escolhido para ordenação.
                switch (type) {
                    case INSERTIONSORT: Insertionsort.sort(aux);
                        break;
                    case HEAPSORT: Heapsort.sort(aux);
                        break;
                }
            
            // Devolve os valores ao vetor de entrada:
            for (int j = 0; j < aux.length; j++, indice++)
                vetor[indice] = aux[j];

	}
    }
    
    
    /*public static void sort(int[] input) {
        
        if (input.length > 1) {
            int maior = Integer.MIN_VALUE;
            ArrayList buckets = new ArrayList<List>();

            // create and initialize buckets to ArrayList: O(n)
            for (int i: input) {
                if (i > maior) maior = i;
                buckets.add(new ArrayList());
            }
            maior = maior + 1;

            // distribute data into buckets: O(n)
            for (int i : input) {
                int currentBucket = (int) Math.floor( i * input.length / maior );
                ArrayList lista = (ArrayList) buckets.get(currentBucket);
                lista.add(i);
            }

            // sort each bucket O(n)
            for (int i = 0; i < buckets.size(); i++) {
                ArrayList lista = (ArrayList) buckets.get(i);
                //Collections.sort(bucket);
                //System.out.println("Bucket "+(i+1)+": " + lista.toString());
            }
            
            // combine
            int j = 0;
            for (int i = 0; i < buckets.size(); i++) {
                ArrayList lista = (ArrayList) buckets.get(i);
                for (Object p : lista) {
                    int valor = (int) p;
                    input[j++] = valor;
                }
            }   
        }

    }*/
    
}
