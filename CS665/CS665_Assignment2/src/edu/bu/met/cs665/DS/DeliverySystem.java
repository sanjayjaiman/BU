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
public class DeliverySystem implements Observee, Runnable  {
    private static final int WARMFOOD = 0;
    private static final int FROZENFOOD = 1;
    private static final int CHOCOLATES = 2;
    private static final int FLOWERBOUET = 3;
    private static final int BIRTHDAYBOX = 4;
    private final String threadName = "DeliverySystemThread";
    private static Thread t = null;
    public  static boolean processingOrders = false;
    
    // All Orders being serviced 
    private final ArrayList<Order> orders;
    private final ArrayList<Observer> observers;
    
    public DeliverySystem() {
        t = null;
        orders = new ArrayList<>();
        observers = new ArrayList<>();
    }
    
    public void start() {
        if (t == null) {
            System.out.println("Starting thread " + threadName);
            t = new Thread(this, threadName);
            t.start();
        }
    }

    public static void init(DeliverySystem ds) {
        registeredUsers.init();
//        registeredUsers.print();
        ds.buildTestDeliveries();
        Driver.init(ds);
//        Driver.printDrivers();
        ds.start();
    }
    @Override
    public void Delivered(Observer obsrvr) {
        obsrvr.inTransit.reset();
        //Could broadcast to other drivers about this driver becoming available.
    }
    @Override
    public void startDelivery(Observer obsrvr, int storeId, Delivery del) {
        obsrvr.inTransit.Set(true, storeId);        
        obsrvr.assignDelivery(del);
    }
    @Override
    public void Register(Observer observer) {
        observers.add(observer);
    }
    
    public static Product buildNewProctOfType(int type) {
        Product p = null;
        switch(type) {
            case WARMFOOD:
                p = new WarmFood();
                break;
            case FROZENFOOD:
                p = new FrozenFood();
                break;
            case CHOCOLATES:
                p = new Chocolates();
                break;
            case FLOWERBOUET:
                p = new FlowerBoquet();
                break;
            case BIRTHDAYBOX:
                p = new BirthdayBox();
                break;
        }
        return p;
    }
    static int rushHrCnt = 0;
    private static boolean isRushHour() {
        // This method would in normal working conditions get the current time and if it is between 9-5
        // return true otherwise false;        
        return rushHrCnt++ % 2 == 0;
    }
        
    private Delivery buildDelivery(Order  order) {
        String type = "UNKNOWN";
        User user = order.user;
        boolean   rushHourDelivery = isRushHour();
        StoreChosen store = new StoreChosen();
   
        boolean refrigerationNeeded = order.requireRefrigeration() && rushHourDelivery;
        Driver dv = Driver.findDriver(refrigerationNeeded, store, user);

        if (dv == null) {
//            System.out.println("Delivery vehicle NOT found for order number: " + Integer.toString(order.getOrderNumber()));
            return null;
        }
        int storeId = store.storeId;

//        System.out.println("*** FOUND STORE" + 
//                "; DriverID = " + dv.getId() +
//                "; StoreID = " + Integer.toString(storeId) +
//                "; Total Distance = " + Integer.toString(store.minDistance) + 
//                "; User Distance to store = " + user.getDistanceToStore(storeId) + 
//                "; Driver Distance from store = " + Integer.toString(dv.getDistanceFromStore(storeId)));
        
        int totalDistance = dv.getDistanceFromStore(storeId) + user.getDistanceToStore(storeId);
        Delivery del = new Delivery(totalDistance, dv, order, rushHourDelivery);

        startDelivery(dv, storeId, del);
        return del;
    }

    public void printAllOrders() {
//        for (Order o : orders) {
        for (int i =0; i < orders.size(); i++) {
            Order o = orders.get(i);

            System.out.println(o);
        }                
    }
    
    
    
    private int dispatch() {
        int numLeft = 0;
//        System.out.println("Building deliveries from open orders");
        ArrayList<Order> ordersToDelete = new ArrayList<>();
        for (int i =0; i < orders.size(); i++) {
            Order ord = orders.get(i);
            int orderNumber = ord.getOrderNumber();
            Delivery del = buildDelivery(ord);
            if (del != null) {
//                System.out.println("Order to be dispatched : " + o);
                ordersToDelete.add(ord);
                Driver driver = del.getVehicle();
                driver.start();
            } else {
//                System.out.println("\tOrder " + Integer.toString(orderNumber) + " could not be built due to unavailability of a deliver vehicle");
                numLeft++;
            }
        }
        for (Order o: ordersToDelete) {
//            System.out.println("Removing Order number " + o.getOrderNumber());
            orders.remove(o);
        }
//        printAllOrders();        
        return numLeft;
    }

    @Override
    public void run() {
        System.out.println("Running " +  threadName );
        try{
            while(true) {
                if (dispatch() == 0) {
                    System.out.println(threadName + ":: All outstanding orders delivered");
                    processingOrders = false;
                    break;  // Comment this out if new orders are to be put in
                }
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }
   }
    
    public void buildTestDeliveries () {
        int userIndex = 0;
        int numUsers = registeredUsers.size();
        
        for (int i = 0; i < 5; i++) {
            User u = registeredUsers.getUser(i % numUsers);
            Order ord = new Order(u);
            ord.add(buildNewProctOfType(i % 5));
            orders.add(i, ord);
        }        
        for (int i = 5; i < 15; i++) {
            User u = registeredUsers.getUser(i % numUsers);
            Order ord = new Order(u);
            ord.add(buildNewProctOfType(i % 5));
            ord.add(buildNewProctOfType((i + 1) % 5));
            orders.add(i, ord);
        }        
        for (int i = 15; i < 25; i++) {
            User u = registeredUsers.getUser(i % numUsers);
            Order ord = new Order(u);
            ord.add(buildNewProctOfType(i % 5));
            ord.add(buildNewProctOfType((i + 1) % 5));
            ord.add(buildNewProctOfType((i + 2) % 5));
            orders.add(i, ord);
        }
        printAllOrders();
        processingOrders = true;
    }
}

