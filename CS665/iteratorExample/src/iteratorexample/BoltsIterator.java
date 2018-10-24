/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iteratorexample;

import java.util.*;

/**
 *
 * @author sanjay
 */
public class BoltsIterator implements XYZ {
    
    private final Bolts []boltsArray;
    int index;

    public BoltsIterator(Bolts []boltsArray) {
        this.boltsArray = boltsArray;
        index = 0;
    }
    @Override
    public Bolts next() {
        if (index < boltsArray.length) {
            int i = index++;
            return boltsArray[i];
        }
        return null;
    }
    @Override
    public boolean hasNext() {
        if (index < boltsArray.length) {
            return true;
        }
        return false;
    }          
}
