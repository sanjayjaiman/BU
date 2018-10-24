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
public class IteratorExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ArrayList<Nuts> nuts = new ArrayList<Nuts>();
        nuts.add(new Nuts("Pistachews"));
        nuts.add(new Nuts("Cashews"));
        nuts.add(new Nuts("Almonds"));
        
        Bolts []bolts = new Bolts[3];
        bolts[0] = new Bolts("1 mm");
        bolts[1] = new Bolts("2 mm");
        bolts[2] = new Bolts("3 mm");
        
        XYZ boltsIt = new BoltsIterator(bolts);
        XYZ nutsIt = new NutsIterator(nuts);
        print(boltsIt);
        print(nutsIt);
    }
    
    private static void print(XYZ it) {
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }    
}
