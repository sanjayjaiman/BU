/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverysystem;

/**
 *
 * @author sanjay
 */
public class DistanceToStores {
    private final int []storeDistances= new int[5];
    
    public DistanceToStores(int s1, int s2, int s3, int s4, int s5) {
        storeDistances[0] = s1;
        storeDistances[1] = s2;
        storeDistances[2] = s3;
        storeDistances[3] = s4;
        storeDistances[4] = s5;
    }
    public String toString() {
        String str = Integer.toString(storeDistances[0]) + " " +
            Integer.toString(storeDistances[1]) + " " +
            Integer.toString(storeDistances[2]) + " " +
            Integer.toString(storeDistances[3]) + " " +
            Integer.toString(storeDistances[4]);
        return str;
    }
}
