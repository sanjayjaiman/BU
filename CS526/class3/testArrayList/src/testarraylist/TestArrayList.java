package testarraylist;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this t1late file, choose Tools | Templates
 * and open the t1late in the editor.
 */

import net.datastructures.ArrayList;

/**
 *
 * @author sanjay
 */
public class TestArrayList {

    public static void populate(ArrayList<Integer> lst, Integer[] iArray) {
        for (Integer x:iArray) {
            lst.add(lst.size(), x);
        }        
    }
    public static void printList(String name, ArrayList<Integer> tmp) {
        System.out.println("Contents of " + name);
//        for (Integer i: tmp) {
//            System.out.print("\t" + i);
//        }
//        System.out.println();
        System.out.println("\t" + tmp.toString());
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Integer[] t1 = {7,1,8,2,9,3};
        Integer[] t2 = {5,6,7,8};
        
        ArrayList<Integer> lst1 = new ArrayList<>(t1.length);
        ArrayList<Integer> lst2 = new ArrayList<>(t2.length);
        
        populate(lst1, t1);
        populate(lst2, t2);
        
//        printList("List1", lst1);
//        printList("List2", lst2);
        
        String str1 = lst1.toString();
        String str2 = lst2.toString();
        
        System.out.println("List1 toString = " + str1);
        System.out.println("List2 toString = " + str2);
        System.out.println();
        
        lst1.addAll(lst2);
//        printList("List1 + List2", lst1);
        System.out.println("List1 + List2 = " + lst1.toString());
        Integer x = 8;
        Integer I = lst1.remove(x);
        
        if (I != null) {
            System.out.println("Removed element " + I);
        }
//        printList("List after removal ", lst1);
        System.out.println("List after removal = " + lst1.toString());

        x = 7;
        I = lst1.remove(x);
        
        if (I != null) {
            System.out.println("Removed element " + I);
        }
//        printList("List after removal ", lst1);
        System.out.println("List after removal = " + lst1.toString());
    }
}
