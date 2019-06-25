/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bucketsort;

import java.util.Arrays;
import sort.Bucketsort;
import sort.SortType;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author valney
 */
public class BucketsortMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String t1, t2, t3, t4 = "";
        double tempo1, tempo2, tempo3, tempo4 = 0;
        int testLengths[] = {100, 500, 1000, 5000, 30000, 80000, 100000, 150000, 200000};
        
        // aleatoriza uma ordem
        Random rand = new Random();
        java.util.ArrayList<Integer> order = new java.util.ArrayList<Integer>();
        while (order.size() < 4) {
            int value = rand.nextInt(5);
            if (value>0 && !order.contains(value) ) order.add(value);
            
        }
        
        // para cada tamanho a ser testado
        for (int length: testLengths) {
               
            System.out.print(length+"\t");
            
            Integer[] array = generateArray(length);
            Integer arrays[][] = {array, array.clone(), array.clone(), array.clone()};
            int index = 0;
            // chama as funcoes baseada na ordem aleatoria criada anteriormente
            // isso serve pra validar as performances em todas as execucoes
            for (int typeValue: order) {
                act(arrays[index++], SortType.valueOf(typeValue));
            }
            // para 600ms a cada fim de linha
            try { Thread.sleep(600); } catch (Exception ex) {
                Logger.getLogger(BucketsortMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println();
        }
        
    }
    
    // chama cada execucao e conta seu tempo (milissegundos)
    private static void act(Integer[] array, SortType type) {
        double tempo = System.nanoTime();
        Bucketsort.sort(array, type);
        tempo = (System.nanoTime() - tempo)/1000000;
        String t = tempo + ""; t = t.replace(".",",");
        
        System.out.print("\t" + type.toString() + "\t" + t );
        
        // para 300ms a cada fim de execucao de algoritmo
        try { Thread.sleep(300); } catch (Exception ex) {
            Logger.getLogger(BucketsortMain.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    

    // gera array aleatorio de tamanho n.
    static Integer[] generateArray(int size) {
        Integer[] array = new Integer[size];
        Random rand = new Random();
        for (int j = 0; j < size; j++)
            // para criar mais heterogeneidade, multiplicar size por algum tamanho
            // de valor n. ex.: size * 3
            array[j] = rand.nextInt(size);
        return array;
    }
    
}
