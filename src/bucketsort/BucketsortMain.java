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
        //int testLengths[] = {100};
        
        for (int length: testLengths) {
               
            System.out.print(length+"\t");
            
            Integer[] array = generateArray(length);
            Integer[] arrayHeap   = array.clone();
            Integer[] arrayInsert = array.clone();
            Integer[] arrayBucket = array.clone();
            Integer[] arrayQuick  = array.clone();
            
            act(arrayBucket, SortType.BUCKETSORT);
            act(arrayQuick,  SortType.QUICKSORT);
            act(arrayHeap,   SortType.HEAPSORT);
            act(arrayInsert, SortType.INSERTIONSORT);
            
            try { Thread.sleep(600); } catch (Exception ex) {
                Logger.getLogger(BucketsortMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println();
        }
        
    }
    
    private static void act(Integer[] array, SortType type) {
        double tempo = System.nanoTime();
        Bucketsort.sort(array, type);
        tempo = (System.nanoTime() - tempo)/1000000;
        String t = tempo + ""; t = t.replace(".",",");
        
        System.out.print("\t" + type.toString() + "\t" + t );
        
        try { Thread.sleep(300); } catch (Exception ex) {
            Logger.getLogger(BucketsortMain.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    

    static Integer[] generateArray(int size) {
        Integer[] array = new Integer[size];
        Random rand = new Random();
        for (int j = 0; j < size; j++)
            array[j] = rand.nextInt(size);
        return array;
    }
    
}
