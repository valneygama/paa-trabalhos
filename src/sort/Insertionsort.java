/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

/**
 *
 * @author valney
 */
public class Insertionsort {
    
    public static void sort(Integer[] vetor) {
        if (vetor.length > 1) {
            int i, key, j;

            for (j = 1; j < vetor.length; j++) {
                key = vetor[j];
                for (i = j - 1; (i >= 0) && (vetor[i] > key); i--) {
                    vetor[i + 1] = vetor[i];
                }
                vetor[i + 1] = key;
            }
        }
    }
}
