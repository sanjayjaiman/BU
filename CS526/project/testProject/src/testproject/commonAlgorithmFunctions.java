/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testproject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author sanjay
 */
abstract public class commonAlgorithmFunctions implements algorithmInterface {
    
    abstract public String getAdjacentNodesString(String current, directDistanceNode [] adjacent);
    
    public directDistanceNode []getAdjacentNodes(String node) {
        return adjacency.getAdjacentNodes(node);
    }

    /**
     *
     * @param pathMap
     * @param shortestPathStr
     * @param x
     * @param end
     * @param adjacent
     * @param index
     * @param print
     * @return
     */
    @Override
    public boolean getNext(LinkedHashMap< String, Integer> pathMap, String shortestPathStr, String x,
                String end, directDistanceNode []adjacent, int index, boolean print){
//        System.out.println("Put in path - " + x);
//        printPathMap(pathMap);
        
        if (x.equals(end)) {
            return true;
        }
        boolean first = true;
        if (shortestPathStr.equals("")) {
            shortestPathStr = "Shortest path: ";
        }
        else {
            shortestPathStr += "-->";
            first = false;
        }
        String nextNode = adjacent[index].getName();
        shortestPathStr += nextNode;
//        System.out.println("ADDING " + nextNode);
        pathMap.put(nextNode, 0);   // put node in path map - value is not important
     
        if (! first) {
            if (print) {
                printBlock(x, nextNode, adjacent, shortestPathStr, pathMap);
            }
            else if (nextNode.equals(end)){
                printBlockMin(x, nextNode, adjacent, shortestPathStr, pathMap);
            }
        }
        
        directDistanceNode [] adj = getAdjacentNodes(nextNode);
        
        if (adj.length != 0) {
            for (int i = 0; i < adj.length; i++) {
                String k = adj[i].getName();
                if (pathMap.containsKey(k)) {
//                    System.out.println("\tBACKTRACK to - " + k);
                    continue;
                }
                if (getNext(pathMap, shortestPathStr, nextNode, end, adj, i, print)) {
                    return true;
                }
            }
        }
        pathMap.remove(nextNode);  // Backtrack
        if (print) {
            System.out.println("\tDEAD END - removing " + nextNode);
            System.out.println("\tBACKTRACK to - " + x + "\n");
        }
        return false;
    }

    protected static void printPathMap(LinkedHashMap< String, Integer> pathMap) {
        System.out.print("Pathmap = ");
        Set< Map.Entry< String, Integer>> st = pathMap.entrySet();         	
        for (Map.Entry< String, Integer> me:st) {
            String k = me.getKey();
            Integer d = me.getValue();
            System.out.print(k + " ");
        }
        System.out.println();
    }

    protected static void printHashMap() {
        adjacency.print();
        directDistance.print();
    }
    
    protected void printBlockMin(String current, String next, directDistanceNode []adjacent,
                              String shortestPathStr, LinkedHashMap< String, Integer> pathMap) {
        System.out.println("\t" + shortestPathStr);
        System.out.println("\tShortest path length: " + adjacency.getTotalDistance(current, pathMap));
        System.out.println();
    }

    protected void printBlock(String current, String next, directDistanceNode []adjacent,
                              String shortestPathStr, LinkedHashMap< String, Integer> pathMap) {
        System.out.println("\tCurrent node = " + current);
        System.out.println("\tAdjacent Nodes = " + getAdjacentNodesString(current, adjacent));
        System.out.println("\tNext node (" + next + ") is selected");
        System.out.println("\t" + shortestPathStr);
        System.out.println("\tShortest path length: " + adjacency.getTotalDistance(current, pathMap));
        System.out.println();
    }

    @Override
    public void findPath(String start, String end , boolean print) {
        LinkedHashMap< String, Integer> pathMap = new LinkedHashMap< >();
//        LinkedHashMap< String, Integer> visitedMap = new LinkedHashMap< >();
        String shortestPathStr = "";
        
        directDistanceNode ddn = directDistance.get(start);
        directDistanceNode [] adj = {ddn};
        pathMap.put(start, 0);   // put node in path map - value is not important
        
        if (getNext(pathMap, shortestPathStr, start, end, adj, 0, print)) {
//            System.out.println("Path to " + end + " FOUND");
        }
        else {
            System.out.println("Path to " + end + " not found - exiting");
        }
    }
}
