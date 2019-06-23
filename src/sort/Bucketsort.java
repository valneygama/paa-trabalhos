/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author valney
 */
public class Bucketsort {
    

    public static void sort(Integer a[], SortType type) {
        if (a.length<2) return;
        if ((a.length==2)&&(a[0]<a[1])) return;
        int numBuckets = (int) Math.ceil( Math.sqrt(a.length) ); // ~ 30 items/bucket
        if (numBuckets<2) numBuckets = 2;
        Integer listMin = a[0], listMax = a[0];
        for (int i = 0; i < a.length; i++) {
            if (a[i]>listMax) listMax = a[i];
            if (a[i]<listMin) listMin = a[i];
        }
        if (listMax==listMin) return;
        
        // initialize the buckets
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<ArrayList<Integer>>(numBuckets);
        for (int i = 0; i < numBuckets; i++) {
            buckets.add(new ArrayList<Integer>());
        }
        
        // go through the list and put each item in the correct bucket
        for (int i = 0; i < a.length; i++) {
            int bucket = bucketForNumber(a[i], listMin, listMax, numBuckets);
            buckets.get(bucket).add(a[i]);
        }
        
        // sort each of the buckets using the sort i want
        ArrayList<Integer> list = new ArrayList<Integer>();
        int listIndex = 0;
        for (ArrayList<Integer> bucket : buckets) {
            int indexbucket = 0;
            Integer[] bucketArray = new Integer[bucket.size()];
            for (Integer item: bucket) bucketArray[indexbucket++] = item;
            if (bucket.size() > 1) {
                switch (type) {
                    case INSERTIONSORT: Insertionsort.sort(bucketArray); break;
                    case HEAPSORT: Heapsort.sort(bucketArray); break;
                    case BUCKETSORT: Bucketsort.sort(bucketArray, SortType.BUCKETSORT); break;
                    case QUICKSORT: Quicksort.sort(bucketArray);
                        break;
                }
            }
            for (int j = 0; j < bucketArray.length; j++) {
                    a[listIndex++] = bucketArray[j];
            }
        }
        listIndex=0;
    }
    
    private static int bucketForNumber(Integer number, Integer listMin, Integer listMax, Integer numBuckets) {
        Long difference = (long)listMax - listMin;
        if (Objects.equals(number, listMin)) return 0;
        if (Objects.equals(number, listMax)) return numBuckets-1;
        int increment = (int)Math.ceil(difference / numBuckets);
        increment = increment <= 0 ? 1 : increment;
        int bucket = number / increment;
        if (bucket >= numBuckets) {
                bucket = numBuckets - 1;
        } else if (bucket < 0) {
                bucket = 0;
        }
        return bucket;
    }
    
}