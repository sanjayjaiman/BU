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
public class State {
    private  boolean inTransit;
    private  int     storeId;
    public State() {
        inTransit = false;
        storeId = -1;
    }
    public void Set(boolean tr, int id) {
        inTransit = tr;
        storeId = id;
    }
    public boolean Get() {
        return inTransit;
    }
    public int GetStoreId() {
        return storeId;
    }
    public void reset() {
        inTransit = false;
        storeId = -1;
    }
}
