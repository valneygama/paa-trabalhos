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
public class MergeSort {
    public static void sort(Integer[]a ) {
        mergeSort(a, a.length);
    }
    public static void mergeSort(Integer[] a, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Integer[] l = new Integer[mid];
        Integer[] r = new Integer[n - mid];

        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        for (int i = mid; i < n; i++) {
            r[i - mid] = a[i];
        }
        mergeSort(l, mid);
        mergeSort(r, n - mid);

        merge(a, l, r, mid, n - mid);
    }
    
    private static void merge(
        Integer[] a, Integer[] l, Integer[] r, int left, int right) {

          int i = 0, j = 0, k = 0;
          while (i < left && j < right) {
              if (l[i] <= r[j]) {
                  a[k++] = l[i++];
              }
              else {
                  a[k++] = r[j++];
              }
          }
          while (i < left) {
              a[k++] = l[i++];
          }
          while (j < right) {
              a[k++] = r[j++];
          }
      }
}
