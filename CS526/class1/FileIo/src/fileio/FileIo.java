/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileio;
import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author sanjay
 */
public class FileIo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            BufferedReader in = new BufferedReader(new FileReader("artinfo.txt"));
            String x;
            
            Scanner scan = new Scanner(in);
            //Pattern p = Pattern.compile(",");
            //scan.useDelimiter(p);
            scan.useDelimiter(",");
            while(scan.hasNext()){
                System.out.print(scan.next());
            }
            /*
            while(scan.hasNextLine()){
		System.out.println(scan.nextLine());
            }
            */
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
