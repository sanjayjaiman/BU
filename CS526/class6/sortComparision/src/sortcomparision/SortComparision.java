/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortcomparision;

import static sortcomparision.InsertionSort.insertionSort;

/**
 *
 * @author sanjay
 */
public class SortComparision {
    
    private static String [] toBeSorted = {"C", "E", "B", "D", "A", "I", "J", "L", "K", "H", "G", "F"};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testInsertionSort ();
    }
    
    private static void testInsertionSort () {
        System.out.println(java.util.Arrays.toString(toBeSorted));
//        insertionSort(toBeSorted);
        // Do it generically
        InsertionSortGeneric<String> x = InsertionSortGeneric.getinstance();
        x.insertionSort(toBeSorted);
        System.out.println(java.util.Arrays.toString(toBeSorted));
    }    
}    

