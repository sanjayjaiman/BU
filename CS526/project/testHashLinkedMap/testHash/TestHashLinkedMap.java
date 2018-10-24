/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testhash;

import java.util.*;

/**
 *
 * @author sanjay
 */
public class TestHashLinkedMap {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedHashMap< String,Integer> hm = new LinkedHashMap< >();
        hm.put("Hello", 100);
        hm.put("a", 100);
        hm.put("b", 200);
        hm.put("c", 300);
        hm.put("d", 400);
        hm.put("World", 500);
        hm.put("e", 600);
        hm.put("f", 700);

        // Returns Set view     
        Set< Map.Entry< String,Integer> > st = hm.entrySet();   

        for (Map.Entry< String,Integer> me:st)
        {
            System.out.print(me.getKey()+":");
            System.out.println(me.getValue());
        }
        Set<String> keys = hm.keySet();
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.print("Key = " + key);
            System.out.println("; Value = " + hm.get(key));            
        }
    }
}
