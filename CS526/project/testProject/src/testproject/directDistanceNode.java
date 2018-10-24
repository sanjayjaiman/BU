/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testproject;

/**
 *
 * @author sanjay
 */
public class directDistanceNode implements Comparable<directDistanceNode>{
    private final String name;
    private final Integer distance;
    
    public directDistanceNode(String name, Integer distance) {
        this.name = name;
        this.distance = distance;
    }
    public Integer getDistance() {
        return distance;
    }
    public String getName() {
        return name;
    }
    public static String arrayToString(directDistanceNode [] adj) {
        String s = "";
        for(directDistanceNode y: adj) {
            s += y.getName() + ", ";
        }
        return s;
    }
    
    @Override
    public int compareTo(directDistanceNode o) {
        return this.distance - o.distance;
    }
    
    @Override
    public String toString() {
        return "\t" + name + ":dd(" + name + ") = " + distance;
    }
    
    public String printWithWeights(String currentNode) {
        int adjDistance = adjacency.getDistance(currentNode, name);
        int newd = distance - adjDistance;  // hack
        int sum = distance;
        return "\t" + name + ":ww(" + currentNode + "," + name + ") + dd(" + name + ") = " + adjDistance + "+" + newd + "=" + sum;
    }
}
