/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;
import java.io.*;

/**
 *
 * @author sanjay
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            BufferedReader in = new BufferedReader(new FileReader("input.txt"));
            String x;
            while ((x = in.readLine()) != null) {;
                System.out.println(x);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found - exiting");
            System.out.println(e);
            return;
        }
        catch (IOException ioe) {
            System.out.println("Error reading file - exiting");
        }
    }
    
}
