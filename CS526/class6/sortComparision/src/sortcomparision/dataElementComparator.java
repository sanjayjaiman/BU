/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortcomparision;

import java.util.Comparator;

/**
 *
 * @author sanjay
 */
public class dataElementComparator <E extends Comparable<E>>implements Comparator<E> {
    
    private static dataElementComparator Instance = new dataElementComparator();
    
    public static <E extends Comparable<E>> Comparator<E> getInstance() {
        @SuppressWarnings("unchecked")
        Comparator<E> result = (Comparator<E>)Instance;
        return result;
    }
    @Override
    public int compare(E obj1, E obj2) {
        if (obj1 == null) {
            return -1;
        }
        if (obj2 == null) {
            return 1;
        }
        if (obj1.equals( obj2 )) {
            return 0;
        }
        return obj1.compareTo(obj2);
    }    
}
