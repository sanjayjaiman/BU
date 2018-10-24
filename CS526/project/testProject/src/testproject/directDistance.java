/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testproject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author sanjay
 */
public class directDistance {
    private static boolean v_print = false;
    
    public directDistance () {        
    }
    //    private static final LinkedHashMap< String, Integer> dd = new LinkedHashMap< >();
    private static final LinkedHashMap<String, directDistanceNode> dd = new LinkedHashMap <> ();
    
    public static void print() {
        Set< Map.Entry< String, directDistanceNode>> st = dd.entrySet();         	
        for (Map.Entry< String, directDistanceNode> me:st) {
            directDistanceNode d = me.getValue();
            System.out.println(d);
        }
    }
    
    /**
     *
     * @param in
     * @return
     */
    public static directDistanceNode get(String in) {
        return dd.get(in);
    }
    
    public static void init(String file) {
        try {
            FileReader f = new FileReader(file);
            BufferedReader in = new BufferedReader(f);
            Scanner scan = new Scanner(in);
            
            in = new BufferedReader(f);
            scan = new Scanner(in);
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] tokens = line.split("\\s+");
                String k = tokens[0];
                Integer i = Integer.parseInt(tokens[1]);
                directDistanceNode d = new directDistanceNode(k, i);
                dd.put(k,d);
//                System.out.println(k + " = " + dd.get(k));                
            }
            f.close();
            if (v_print) {
                print();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found - exiting");
            System.out.println(e);
        }
        catch (IOException ioe) {
            System.out.println("Error reading file - exiting");
        }
        catch (NumberFormatException nf) { 
            System.out.println("Bad number;" + nf + " - exiting");
        }
        catch (IllegalArgumentException ia) {
            System.out.println("Illegal argumet;" + ia + ": exiting");          
        }
    }    
}
