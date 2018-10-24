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
public interface Observee {
    public void Delivered(Observer obsrvr);
    public void startDelivery(Observer obsrvr, int storeId, Delivery del);
    public void Register(Observer observer);
}
