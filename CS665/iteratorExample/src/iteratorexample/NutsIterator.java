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
public class NutsIterator implements XYZ {
    
    private final ArrayList<Nuts> nuts;
    private int index;
    
    public NutsIterator(ArrayList<Nuts> in) {
        nuts = in;
    }
    
    @Override
    public Nuts next() {
        if (index < nuts.size()) {
            int i = index++;
            return nuts.get(i);
        }
        return null;
    }
    @Override
    public boolean hasNext() {
        int sz = nuts.size();
        if (index < sz) {
            return true;
        }
        return false;
    }          
}
