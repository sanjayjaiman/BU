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
public class registeredUsers {
    private final String name;
    private final String address;
    private final DistanceToStores distances;

    public registeredUsers(String name, String address, DistanceToStores dst) {
        distances = dst;
        this.name = name;
        this.address = address;
    }
    public static final registeredUsers []users = new registeredUsers[4];
    
    public static void init() {
        users[0] = new registeredUsers("Fred", "20 BowString rd, Stow, MA - 01345", new DistanceToStores(1,5,8,2,9));
        users[1] = new registeredUsers("Mary", "45 Sany ln, Acton, MA - 22321", new DistanceToStores(2,7,1,9,4));
        users[2] = new registeredUsers("John", "32 Robert dr, Acton, MA - 22321", new DistanceToStores(8,2,3,1,7));
        users[3] = new registeredUsers("Bob", "55 Nutshell rd, Stow, MA - 01345", new DistanceToStores(9,1,8,6,3));
    }
    public static int size() {
        return users.length;
    }
    public String toString() {
        return "Name = " + name + "\nAddress = " + address + "\nDistanceToStores = " + distances + "\n";
    }
}
