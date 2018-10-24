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
public class algorithmTwo extends commonAlgorithmFunctions {
    
    @Override
    public String getAdjacentNodesString(String current, directDistanceNode [] adj) {
        String s = directDistanceNode.arrayToString(adj);
        s += "\n";      
        for(directDistanceNode y: adj) {
            s += y.printWithWeights(current)+ "\n";
        }
        return s;
    }    
}
