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
public class Observer {
    protected Observee observee;
    protected String driver_ID;
    public State inTransit;
    protected Delivery delivery;

    public void Notify() {
        observee.Delivered(this);
    }    
    public String getId() {
        return driver_ID;
    };
    public void assignDelivery(Delivery del) {
        delivery = del;
    }
}
