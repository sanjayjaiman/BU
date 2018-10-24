package edu.bu.met.cs665;

import edu.bu.met.cs665.DS.DeliverySystem;
import edu.bu.met.cs665.DS.Driver;
//
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

  /**
   * A main method to run examples. 
   * @param args not used 
   */
  public static void main(String[] args) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    LocalDate xmas = LocalDate.parse("22/12/1994", formatter);
    DeliverySystem ds = new DeliverySystem();
    DeliverySystem.init(ds);    
    try{
        while(ds.processingOrders) {
//              System.out.println("Main thread Running " );
              Thread.sleep(1000);
        }
    }
    catch (InterruptedException e) {
        System.out.println(e);
    }
   }
}
