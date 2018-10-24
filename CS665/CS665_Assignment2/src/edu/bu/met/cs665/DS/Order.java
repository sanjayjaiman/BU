/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bu.met.cs665.DS;

import java.util.*;

/**
 *
 * @author sanjay
 */
public class Order {
    public ArrayList<Product> listOfItems;
    public final User user;
    private static int count = 0;
    private int orderNumber;
    
    public Order(User u) {
        listOfItems = new ArrayList<Product>();
        user = u;
        orderNumber = ++count;
    }
    
    public boolean requireRefrigeration() {
        for (Product p : listOfItems) {
            if (p.isFrozen()) {
                return true;
            }
        }
        return false;
    }
    public String toString(String tab) {
        String str = tab + "Order number: " + Integer.toString(orderNumber) + "\n";
        str += user.toString(tab);
        str += tab + "List of items: ";
        for (Product p : listOfItems) {
            //            if (p != null) {
            str += p.toString() + ", ";
        }
        str += "\n";
        return str;
    }
    
    @Override
    public String toString() {
        return toString("");
    }
    public void add(Product p) {
        listOfItems.add(p);
    }
    public int getOrderNumber() {
        return orderNumber;
    }
}
