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
public enum SortType {
    BUCKETSORT(1), INSERTIONSORT(2), HEAPSORT(3), QUICKSORT(4);
    
    private final int valor;
    
    SortType(int valor) {
        this.valor = valor;
    }
    public int getValor() { return this.valor; }
    
    public static SortType valueOf(int valor) {
    for (SortType e : values()) {
        if (e.valor==valor) {
            return e;
        }
    }
    return null;
}
}
