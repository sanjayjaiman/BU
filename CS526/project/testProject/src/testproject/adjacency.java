package testproject;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;
import net.datastructures.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sanjay
 */
public class adjacency {
    private static boolean v_print = true;
    
    private static final LinkedHashMap< String, LinkedHashMap<String, Integer>> myMatrix = new LinkedHashMap< >();
    static ArrayList<String> header = new ArrayList<String>();
        
    public static directDistanceNode []getAdjacentNodes(String x) {
        
        LinkedHashMap<String, Integer> adj = myMatrix.get(x);
        int numElements = adj.size();
        directDistanceNode []ddArray = new directDistanceNode[numElements];
        
        Set< Map.Entry< String, Integer> > set = adj.entrySet();
        int i = 0;
        for (Map.Entry< String, Integer> vals : set) {
            String key = vals.getKey();
            directDistanceNode ddn = directDistance.get(key);
            ddArray[i++] = ddn;
//            System.out.print(vals.getKey() + ":" + vals.getValue() + ";  ");
        }
        Arrays.sort(ddArray);
        return ddArray;
    }

    public static directDistanceNode []getAdjacentNodesWeighted(String x) {
        
        LinkedHashMap<String, Integer> adj = myMatrix.get(x);
        int numElements = adj.size();
        directDistanceNode []ddArray = new directDistanceNode[numElements];
        
        Set< Map.Entry< String, Integer> > set = adj.entrySet();
        int i = 0;
        for (Map.Entry< String, Integer> vals : set) {
            String key = vals.getKey();
            directDistanceNode dd = directDistance.get(key);
            Integer val = getDistance(x, key);
//            System.out.print(val + "+" + dd.getDistance() + "=");
            val += dd.getDistance();
//            System.out.println(val);
            directDistanceNode ddn = new directDistanceNode(key, val);
            ddArray[i++] = ddn;
            
//            ddn.print();
//            System.out.println("****");
//            System.out.print(vals.getKey() + ":" + vals.getValue() + ";  ");
        }
        Arrays.sort(ddArray);
        return ddArray;
    }
        
    private static void initHashMap(String [] tokens) {
        int i = 0;
        for (String token : tokens) {
            myMatrix.put(token, null);
            header.add(i++, token);
        }
    }
    
    private static void addToHashMap(String [] tokens) {
        String node = tokens[0];
        LinkedHashMap<String, Integer> hash = myMatrix.get(node);
        if (hash == null) {
            hash = new LinkedHashMap< >();
            myMatrix.put(node, hash);
        }
        for (int i = 1; i < tokens.length; i++) {
            Integer val = Integer.parseInt(tokens[i]);
            String key = header.get(i);
            if (key != null) {
                if (val != 0) {
                    hash.put(key, val);
                }
            }
        }
    }
    
    public static void print() {
        Set< Map.Entry< String, LinkedHashMap<String, Integer>> > st = myMatrix.entrySet();   

        for (Map.Entry< String, LinkedHashMap<String, Integer>> me:st)
        {
            LinkedHashMap<String, Integer> val = me.getValue();
            String key = me.getKey();
            System.out.println(key + ":");
            System.out.print("\t");
            if (val != null) {
                Set< Map.Entry< String, Integer> > set2 = val.entrySet(); 
                for (Map.Entry< String, Integer> vals : set2) {
                    System.out.print(vals.getKey() + ":" + vals.getValue() + ";  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static Integer getDistance(String a, String b) {
        LinkedHashMap<String, Integer> hash = myMatrix.get(a);
        Integer val = hash.get(b);
        if (val == null) {
            return 0;
        }
        return val;
    }

    public static String getTotalDistance(String start, LinkedHashMap< String, Integer> pathMap) {
        Set<Map.Entry< String, Integer>> st;
        st = pathMap.entrySet();
        boolean first = true;
        String str = "";
        Integer sum = 0;
        String prev = start;
        int n = 0;
        for (Map.Entry< String, Integer> me:st) {
            String k = me.getKey();
            Integer i = getDistance(prev, k);
            if (! first) {
//                System.out.println("Distance between " + prev + " and " + k + " = " + i);
                if (! str.equals("")) {
                    str += "+";
                }
                str += i;
                sum += i;
                n++;
            }
            else {
                first = false;
            }
            prev = k;
        }
        if (n > 1) {
            str += "=";
            str += sum;
        }
        return str;
    }

    /**
     *
     * @param filename
     */
    
    public static void init (String filename) {
        try {
            FileReader f = new FileReader(filename);
            BufferedReader in = new BufferedReader(f);
            Scanner scan = new Scanner(in);
            boolean lineOne = true;
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] tokens = line.split("\\s+");
                if (lineOne) {
                    initHashMap(tokens);
                    lineOne = false;
                    continue;
                }
                addToHashMap(tokens);
            }
            f.close();
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
