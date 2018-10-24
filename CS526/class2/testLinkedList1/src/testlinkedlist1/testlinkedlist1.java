/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testlinkedlist1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import net.datastructures.SinglyLinkedList;

/**
 *
 * @author sanjay
 */
public class testlinkedlist1 {

    /**
     * @param args the command line arguments
     */
    private static int getTokens(String line, String[] tokens) {
        String []split_tokens = line.split(",\\s+");
        
        if (split_tokens.length != 4) {
            return 0;
        }
        System.arraycopy(split_tokens, 0, tokens, 0, split_tokens.length);
        tokens[4] = "oil";
        tokens[5] = "canavas";
        
//        System.out.println("Num tokens = " + tokens.length);
        return tokens.length;
    }
    
    private static void printTokens(String[] tokens) {
        for(int i = 0; i < tokens.length; i++) {
            System.out.print("token#" + i + " " + tokens[i] + "; ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
    try {
            FileReader f = new FileReader("artinfo.txt");
            BufferedReader in = new BufferedReader(f);
            Scanner scan = new Scanner(in);
            int numLines = 0;
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
//                System.out.println(numLines);
                numLines++;
            }
            f.close();
            System.out.println("Num Lines = " + numLines);
            // Reset back to the beginning of the file
            f = new FileReader("artinfo.txt");
            in = new BufferedReader(f);
            scan = new Scanner(in);
            int i = 0;
            SinglyLinkedList<Painting> sl = new SinglyLinkedList<>();
            System.out.println("Adding Paintings:");
            
            while(scan.hasNextLine() && i <= numLines){
                String line = scan.nextLine();
                String[] tokens = new String[6];
                
                int numTokens = getTokens(line, tokens);
                if (numTokens != 0) {
//                    printTokens(tokens);
                    Painting p = new Painting(tokens);
                    sl.addLast(p);
                    System.out.println("\t" + p.toString());
                }
            }
            int numRecs = i;
            for (;;) {
                System.out.println("Enter an integer corresponding to one of the following");
                System.out.println("Add a painting(1)");
                System.out.println("Remove a painting (2)");
                System.out.println("Display all paintings (3)");
                System.out.println("Exit (4)");

                Scanner inS = new Scanner(System.in);
                int input = inS.nextInt();
                switch (input){
                    case 1:
                        System.out.println("Add painting not implemented");
                        break;
                    case 2:
                        System.out.println("Add painting not implemented");
                        break;
                    case 3:
                        System.out.println("Displaying all paintings");
                        SinglyLinkedList.Node<Painting> current = sl.head;
                        while (current != null) {
                            current.getElement().print();
                            current = current.getNext();
                        }
                        
                        break;
                    case 4:
                        return;
                    default:
                    System.out.println("Invalid Input");
                }
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
        catch (NumberFormatException nf) { 
            System.out.println("Bad number;" + nf + " - exiting");
        }
        catch (IllegalArgumentException ia) {
            System.out.println("Illegal argumet;" + ia + ": exiting");          
    }
    }
}
