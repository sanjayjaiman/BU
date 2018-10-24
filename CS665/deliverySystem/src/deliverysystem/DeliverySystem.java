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
public class DeliverySystem implements Observer {
    private static void printRegisteredUsers() {
        for(int i = 0; i < registeredUsers.size(); i++) {
            System.out.println(registeredUsers.users[i]);
        }
    }
    private static void printDrivers() {
        for(int i = 0; i < Driver.size(); i++) {
            System.out.println(Driver.drivers[i]);
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        registeredUsers.init();
        printRegisteredUsers();
        printDrivers();        
    }
    public void Notify(Observee observee) {
        
    }
}
