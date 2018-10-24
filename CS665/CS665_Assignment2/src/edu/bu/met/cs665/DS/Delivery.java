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
public class Delivery {

    private final int totalDistance;
    private int distanceCompleted;
    private final Driver  vehicle;
    private final Order   order;
    private final boolean rushHourDelivery;
    
    public Delivery(int distance, Driver driver, Order order, boolean rushHour) {
        totalDistance = distance;
        distanceCompleted = 0;
        rushHourDelivery = rushHour;
        this.order = order;
        vehicle = driver;
    }
    void    print() {
        System.out.println(this);
    }
    @Override
    public String toString() {
        String str = "";
        str += "Delivery: Vehicle ID = " + vehicle.getId() + "\n\t";
        str += (rushHourDelivery ? "Rush hour delivery : " : "Normal delivery : ");
        str += (vehicle.refrigerated() ? "Refrigerated" : "Not referigeraated") + "\n\t";
        str += "Total Distance = " + totalDistance + "\n\t";
        str += "Distance Completed = " + distanceCompleted + "\n";
//        str += "Order::\n";
        str += order.toString("\t");
        return str;
    }
    public int     getDistanceCompleted() {return distanceCompleted;};
    public void    incrementDistanceCompleted() {distanceCompleted++;};
    public boolean hasReachedDstination() {return (distanceCompleted == totalDistance);};
    public Driver  getVehicle() {return vehicle;};
    public String  getDeliveryAddress() {
        return order.user.address;
    }
    public int getTravelDistance() {return totalDistance;};
    public DistanceToStores getUserDistances() {
        return order.user.distances;
    }
}
