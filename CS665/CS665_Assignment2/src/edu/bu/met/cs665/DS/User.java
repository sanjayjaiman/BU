/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bu.met.cs665.DS;

/**
 *
 * @author sanjay
 */
public class User {
    public final String name;
    public final String address;
    public final DistanceToStores distances;
    public User(String name, String address, DistanceToStores dst) {
        distances = dst;
        this.name = name;
        this.address = address;        
    }
    public String toString(String tab) {
        return tab +"Name = " + name + "\n" + tab + "Address = " + address + "\n" + tab + "DistanceToStores = " + distances + "\n";
    }
    public String toString() {
        return toString("");
    }
    public int getDistanceToStore(int storeid) {
        return distances.getDistanceToStore(storeid);
    }
}
