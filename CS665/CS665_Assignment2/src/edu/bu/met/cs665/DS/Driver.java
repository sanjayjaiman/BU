/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bu.met.cs665.DS;
import java.util.*;
import java.util.concurrent.Semaphore;

/**
 *
 * @author sanjay
 */
public class Driver extends Observer implements Runnable {
    private DistanceToStores distanceFromStore;
    private static int numDrivers = 10;
    private boolean refrigerated = false;
    public  String currentAddress = "BASE";
    private Thread t;
    private static Semaphore semaphore = new Semaphore(1, true);
    
    private static ArrayList<Driver> drivers;
    
    
    private Driver (Observee observee, String id, DistanceToStores dst) {
        this(observee, id, dst, false);
    }

    private Driver (Observee observee, String id, DistanceToStores dst, boolean refrigerated) {
        driver_ID = id;
        this.distanceFromStore = dst;
        this.refrigerated = refrigerated;
        delivery = null;
        t = null;
        this.observee = observee;
        inTransit = new State();
    }
    public void start() {
        if (t == null) {
            t = new Thread(this, driver_ID);
            t.start();
        }
    }
    
    public static void init(Observee observee) {   
        drivers = new ArrayList<>();
        drivers.add(new Driver(observee, "DRIVER_1", new DistanceToStores(1,3,5,7,10), true));
        drivers.add(new Driver(observee, "DRIVER_2", new DistanceToStores(3,2,4,1,9), true));
        drivers.add(new Driver(observee, "DRIVER_3", new DistanceToStores(2,4,6,8,1)));
        drivers.add(new Driver(observee, "DRIVER_4", new DistanceToStores(8,9,7,6,5)));
        drivers.add(new Driver(observee, "DRIVER_5", new DistanceToStores(2,5,1,2,6)));
        drivers.add(new Driver(observee, "DRIVER_6", new DistanceToStores(7,6,2,9,2)));
        drivers.add(new Driver(observee, "DRIVER_7", new DistanceToStores(10,9,8,7,6)));
        drivers.add(new Driver(observee, "DRIVER_8", new DistanceToStores(9,8,7,6,4)));
        drivers.add(new Driver(observee, "DRIVER_9", new DistanceToStores(8,1,10,3,2)));
        drivers.add(new Driver(observee, "DRIVER_10", new DistanceToStores(11,9,3,10,4)));
    }
    
    @Override
    public String toString() {
        String str = "Driver_ID = " + driver_ID + "\n\tAddress = " + currentAddress + "\n\tDistance from stores = " + distanceFromStore;
        str += "\n\tRefrigerated = " + refrigerated + "\n";
        return str;
    }
    public static int size() {return drivers.size();};
    public boolean refrigerated() {
        return this.refrigerated;
    }
    public int getDistanceFromStore(int n) {
        return distanceFromStore.getDistanceToStore(n);
    };
    // Takes minDistance computed so far (initialized to a big value) and for all the stores for the given user
    // find any store closer to this user and set the "minDistance" accordingly.
    private boolean findStore(boolean refrigerationNeeded, StoreChosen store, User user) {        
        if (inTransit.Get()) {
            return false;
        }
        if (refrigerationNeeded && !refrigerated()) {
            return false;
        }
        boolean found = false;
        for (int i = 0; i < DistanceToStores.NUMSTORES; i++) {
            int totalD = getDistanceFromStore(i) + user.getDistanceToStore(i);
            if (totalD < store.minDistance) {
                store.minDistance = totalD;
                store.storeId = i;
                found = true;
            }
        }
//        System.out.println("*** getMinDistanceStoreForUser: Storeid = " + Integer.toString(store.storeId) + " Distance = " +
//                    Integer.toString(store.minDistance) + " ***\n");
        return found;
    }
    
    public static Driver findDriver(boolean refrigerationNeeded, StoreChosen store, User user) {
        Driver dv = null;
        for(int i = 0; i < Driver.size(); i++) {
            Driver temp = Driver.drivers.get(i);
            if (temp.findStore(refrigerationNeeded, store, user)) {
                dv = temp;   // Can't return here as in the for loop we may get a different driver
            }
        }
        return dv;
    }

    void setNewAddress(String address) {
        currentAddress = address;
    };
    void setNewDistances(DistanceToStores newDistances) {
        distanceFromStore = newDistances;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.print("Running Driver::\n\t" +  this);
            System.out.println("\tStore ID for order pickup: " + inTransit.GetStoreId());
            System.out.println("\tDelivery address: " + delivery.getDeliveryAddress());
            System.out.println("\tTravel distance: " + delivery.getTravelDistance() + "\n");
            semaphore.release();
            while(true) {
                delivery.incrementDistanceCompleted();
                if (delivery.hasReachedDstination()) {
                    semaphore.acquire();
                    try {
                        System.out.println("DELIVERED::");
                        System.out.println(delivery);
                        setNewAddress(delivery.getDeliveryAddress());
                        setNewDistances(delivery.getUserDistances());
                        Notify();
                        delivery = null;  // up for garbage collection
                    }
                    catch (Exception e) {
                        System.out.println(e);
                    }
                    finally {
                        semaphore.release();
                    }
                    break;
                }
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }
        t = null;  // Garbage collect
   }
    public static void printDrivers() {
        for(int i = 0; i < Driver.size(); i++) {
            System.out.println(drivers.get(i));
        }
    }    
}

