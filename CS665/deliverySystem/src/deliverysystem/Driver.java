/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverysystem;

/**
 *
 * @author sanjay
 */
public class Driver implements Observee {
    private String driver_ID;
    private DistanceToStores dst;
    private static int numDrivers = 10;
    private boolean refrigerated = false;
    public  String currentAddress = "BASE";
    
    public String getId() {return driver_ID;};
    public void NotifyAllObservers() {
        
    }
    Driver (String id, DistanceToStores dst) {
        this(id, dst, false);
    }

    Driver (String id, DistanceToStores dst, boolean refrigerated) {
        driver_ID = id;
        this.dst = dst;
        this.refrigerated = refrigerated;
    }
    public static Driver[] drivers = {
        new Driver("DRIVER_1", new DistanceToStores(1,3,5,7,10), true),
        new Driver("DRIVER_2", new DistanceToStores(3,2,4,1,9), true),
        new Driver("DRIVER_3", new DistanceToStores(2,4,6,8,1)),
        new Driver("DRIVER_4", new DistanceToStores(8,9,7,6,5)),
        new Driver("DRIVER_5", new DistanceToStores(2,5,1,2,6)),
        new Driver("DRIVER_6", new DistanceToStores(7,6,2,9,2)),
        new Driver("DRIVER_7", new DistanceToStores(10,9,8,7,6)),
        new Driver("DRIVER_8", new DistanceToStores(9,8,7,6,4)),
        new Driver("DRIVER_9", new DistanceToStores(8,1,10,3,2)),
        new Driver("DRIVER_10", new DistanceToStores(11,9,3,10,4)),
    };
    public String toString() {
        return "Driver_ID = " + driver_ID + "\n\tAddress = " + currentAddress + "\n\tDistance from stores = " + dst +
                "\n\tRefrigerated = " + refrigerated + "\n";
    }
    public static int size() {return drivers.length;};
}
