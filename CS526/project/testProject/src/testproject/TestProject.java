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
public class TestProject {
    
    private static void findPath(String start, String end, int algorithm, boolean print) {
        System.out.println("Algorithm " + algorithm + ": Start Node(" + start + ") ; End Node(" + end + ")");
        String traversedPath = start;
        algorithmInterface alg;
        
        if (algorithm == 1) {
            alg = new algorithmOne();
        }
        else {
            alg = new algorithmTwo();
        }
        
        alg.findPath(start, end, print);
    }
    
    
    public static void init() {
//        adjacency.init("graph_input.txt");
//        directDistance.init("direct_distance.txt");
        adjacency.init("graph_input_V.txt");
        directDistance.init("direct_distance_V.txt");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       String[] nodesToTest = {"C", "G", "R"};
//        String[] nodesToTest = {"C"};  
        boolean print = true;
        init();

        if (print) {
            directDistance.print();
            adjacency.print();
        }

        String endNode = "Z";
        for (String x: nodesToTest) {
            findPath(x, endNode, 1, print);
            findPath(x, endNode, 2, print);
        }        
    }
}
