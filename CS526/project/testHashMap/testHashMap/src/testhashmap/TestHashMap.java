/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testhashmap;

import java.util.*;
/**
 *
 * @author sanjay
 */
public class TestHashMap {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        HashMap< String,Integer> hm = new HashMap< >();
        hm.put("Hello", 100);
        hm.put("a", 100);
        hm.put("b", 200);
        hm.put("c", 300);
        hm.put("d", 400);
        hm.put("World", 100);
        hm.put("e", 100);
        hm.put("f", 100);

        // Returns Set view     
        Set< Map.Entry< String,Integer> > st = hm.entrySet();   

        for (Map.Entry< String,Integer> me:st)
        {
            System.out.print(me.getKey()+":");
            System.out.println(me.getValue());
        }
    }   
}
