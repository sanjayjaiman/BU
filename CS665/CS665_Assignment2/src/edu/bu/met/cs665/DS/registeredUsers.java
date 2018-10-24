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
public class registeredUsers {

    private HashMap< Integer,User> users = new HashMap< Integer,User>();
    public registeredUsers() {}
    private static final registeredUsers regUsers = new registeredUsers();
    
    public static void init() {
        int i = 0;
        regUsers.users.put(i++, new User("Fred", "20 BowString rd, Stow, MA - 01345", new DistanceToStores(1,5,8,2,9)));
        regUsers.users.put(i++, new User("Mary", "45 Sany ln, Acton, MA - 22321", new DistanceToStores(2,7,1,9,4)));
        regUsers.users.put(i++, new User("John", "32 Robert dr, Acton, MA - 22321", new DistanceToStores(8,2,3,1,7)));
        regUsers.users.put(i++, new User("Bob", "55 Nutshell rd, Stow, MA - 01345", new DistanceToStores(9,1,8,6,3)));
    }
    public static int size() {
        return regUsers.users.size();
    }
   
    public String toString() {
        String retValue = "";

        Set< Map.Entry< Integer,User> > st = users.entrySet();   
        for (Map.Entry< Integer,User> me:st) {
            User u = me.getValue();
            retValue += me.getValue();  // calls "toString" on "User"
        }
        return retValue;
    }
    public static void print() {
        String st = regUsers.toString();
        System.out.println(st);        
    }
    public static User getUser(int i) {
        return regUsers.users.get(i);
    }
}
