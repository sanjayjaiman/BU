/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprocessscheduling;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import net.datastructures.ArrayList;

/**
 *
 * @author sanjay
 */
public class TestProcessScheduling {

    private static final ArrayList<Process> processesList = new ArrayList<>();
//    private static final HeapAdaptablePriorityQueue heapAadapPQ = new HeapAdaptablePriorityQueue();

    
    public static void printprocessesList() {
        for (Process p:processesList) {
            System.out.println(p);
        }
    }
    private static Process findProcess(int pid) {
        for (Process p:processesList) {
            if (pid == p.pid()) {
                return p;
            }
        }
        return null;
    }
    public static void addToList(Process p) {
        processesList.add(processesList.size(), p);
    }
    public static int numProcesses() {
        return processesList.size();
    }
    private static void printTokens(String[] tokens) {
        int i = 0;
        for(String token: tokens) {
            System.out.print("token#" + ++i + " " + token + "; ");
        }
        System.out.println();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            FileReader f = new FileReader("process_scheduling_input_1.txt");
            BufferedReader in = new BufferedReader(f);
            Scanner scan = new Scanner(in);
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] tokens = line.split("\\s+");
                if (tokens.length == 4) {
                    Process p = new Process(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
//                    printTokens(tokens);
                    addToList(p);
                }
            }
//            printprocessesList();
            int numProcesses = numProcesses();
            Processes.mainExecutionLoop(processesList);
            f.close();
//            System.out.println("Num processes left = " + numProcesses());
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found - exiting");
            System.out.println(e);
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
