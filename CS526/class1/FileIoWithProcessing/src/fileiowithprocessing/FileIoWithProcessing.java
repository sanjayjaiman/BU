/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileiowithprocessing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
//import org.apache.commons.lang3.StringEscapeUtils;
//import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author sanjay
 */
public class FileIoWithProcessing {

    /**
     * @param args the command line arguments
     */
    private static int getTokens(String line, String[] tokens) {
        String []split_tokens = line.split(",\\s+");
        
        /*  StringTokenizer does not take regex
        StringTokenizer st = new StringTokenizer(line, ",");
        int i =0;
        while (st.hasMoreTokens()) {
            //tokens[i++] = StringUtils.trim(st.nextToken());
            String token = st.nextToken();
            System.out.println(token);
            tokens[i++] = token;
        }
        */
//        System.out.println("Num split tokens = " + split_tokens.length);
        if (split_tokens.length != 4) {
            return 0;
        }
        for (int i = 0; i < split_tokens.length; i++) {
            tokens[i] = split_tokens[i];
        }
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
            ArtWork[] artRec = new ArtWork[numLines];
            String x;
            // Reset back to the beginning of the file
            f = new FileReader("artinfo.txt");
            in = new BufferedReader(f);
            scan = new Scanner(in);
            int i = 0;
            while(scan.hasNextLine() && i <= numLines){
                String line = scan.nextLine();
                String[] tokens = new String[4];
                
//                System.out.println("Line = " + "\"" + line + "\"");
                int numTokens = getTokens(line, tokens);
                if (numTokens != 0) {
//                    printTokens(tokens);
                    artRec[i++] = new ArtWork(tokens);
                }
            }
            for (;;) {
                System.out.println("Enter an integer corresponding to one of the following");
                System.out.println("Artwork info (1)");
                System.out.println("Update Location (2)");
                System.out.println("Exit (3)");
                Scanner inS = new Scanner(System.in);
                int input = inS.nextInt();
                switch (input){
                    case 1:
                        System.out.println("Enter ID of the artwork");
                        int inInt = inS.nextInt();
                        artRec[inInt].print();
                        break;
                    case 2:
                    System.out.println(input);
                    break;
                    case 3:
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
