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
public class BirthdayBox implements Product {
    private Chocolates chocolates;
    private FlowerBoquet flowers;
    public BirthdayBox() {
        chocolates = new Chocolates();
        flowers = new FlowerBoquet();
    }
    @Override
    public boolean isFrozen() {return chocolates.isFrozen() || flowers.isFrozen();} 
    @Override
    public String toString() {return "BIRTDAYBOX: (" + chocolates + " " + flowers + ")";}
}