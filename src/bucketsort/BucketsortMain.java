/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bucketsort;

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
        String t1, t2 = "";
        double tempo1, tempo2 = 0;
        int testLengths[] = {100, 500, 1000, 5000, 30000, 80000, 100000, 150000, 200000};
        for (int length: testLengths) {
               
            System.out.print(length+"\t");
            
            int[] array = generateArray(length);
            tempo1 = System.nanoTime();
            Bucketsort.sort(array, SortType.HEAPSORT);
            tempo1 = (System.nanoTime() - tempo1)/100000;
            
            try {
                Thread.sleep(200);
            } catch (Exception ex) {
                Logger.getLogger(BucketsortMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            tempo2 = System.nanoTime();
            Bucketsort.sort(array, SortType.INSERTIONSORT);
            tempo2 = (System.nanoTime() - tempo2)/100000;
            
            t1 = tempo1 + ""; t1 = t1.replace(".",",");
            t2 = tempo2 + ""; t2 = t2.replace(".",",");
            
            System.out.print("HEAP\t" + t1 + "\tINSERTION\t" + t2 + "\n");
            
            try {
                Thread.sleep(600);
            } catch (Exception ex) {
                Logger.getLogger(BucketsortMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    

    static int[] generateArray(int size) {
        int[] array = new int[size];
        Random rand = new Random();
        for (int j = 0; j < size; j++)
            array[j] = rand.nextInt(size * 3);
        return array;
    }
    
}
