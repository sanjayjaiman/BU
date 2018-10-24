/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprocessscheduling;

import java.util.Iterator;
import net.datastructures.ArrayList;
import net.datastructures.HeapAdaptablePriorityQueue;
import net.datastructures.LinkedStack;
import net.datastructures.Entry;
/**
 *
 * @author sanjay
 */
public class Processes extends HeapAdaptablePriorityQueue<Integer, Process>{
    
    private static final Processes prcs = new Processes();
    private static boolean running = false;
    /**
     *
     * @param processArray
     */
    public static void printProcesses(ArrayList<Process> processArray) {
        
        System.out.println("Printing all processes :");
        for (Process p:processArray) {
            p.print();
        }            
    }

    public static void mainExecutionLoop(ArrayList<Process> processArray) {
        int current_time = 0;
        int numProcessesInHeap = 0;
        int numProcessesComplete = 0;
        int numProcesses = processArray.size();
        Entry<Integer, Process> running_process = null;
        boolean msgPrinted = false;
        
//        printProcesses(processArray);
        
        while (numProcesses != numProcessesComplete) {
            if (processArray.isEmpty() && !msgPrinted) {
                System.out.println("*** ALL PROCESSES HAVE BEEN MOVED TO WAITING QUEUE ***");
                msgPrinted = true;
            }
            numProcessesInHeap += prcs.putProcessInHeapArrivalTime(processArray, current_time);
            if (! running && ! prcs.isEmpty()) {
                running_process = prcs.removeMin();
                running = true;
                Process p = running_process.getValue();
                p.run(current_time);
                int wait_time = current_time - p.arrivalTime();
//                int wait_time2 = p.waitTime(current_time);
                System.out.print("\nProcess removed from queue is : " + p.pid() + ", at time = " + current_time + ", arrival time = " +
                        p.arrivalTime() + ", wait time = " + wait_time);
                System.out.println("; Current time = " + current_time);
                System.out.println(p);
            }
            if (running) {
                Process p = running_process.getValue();
                if (p.runComplete(current_time)) {
                    System.out.println("Process " + p.pid() + " finished at time = " + current_time);
                    running = false;
                    numProcessesComplete++;
                }
            }
            current_time++;
            prcs.adjustPri(current_time);
        }
        if (prcs.isEmpty()) {
            System.out.println("All processes have been run");
        }
 //       prcs.printHeap();
    }
    
    private Processes() {
        super();
    }

    private void adjustPri(int curr_time) {
        Iterator<Entry<Integer, Process>> iter = heap.iterator();
        while (iter.hasNext()) {
            Entry<Integer, Process> entry = iter.next();
            Process p = entry.getValue();
            if (p.setPri(curr_time))  {
                replaceKey(entry, p.getPriority());  // SOme more experimentation needed - seems to work
            }
        }
    }
    private void printHeap() {
        System.out.println("Printing heap:");
        Iterator<Entry<Integer, Process>> iter = heap.iterator();
        while (iter.hasNext()) {
            Entry<Integer, Process> entry = iter.next();
            System.out.println("\tPriority = " + entry.getKey().toString() + ":: " + entry.getValue().toString());
        }
    }
    
    public int putProcessInHeapArrivalTime(ArrayList<Process> processArray, int current_time) {
        int returnVal = 0;
        LinkedStack<Process> arrived = new LinkedStack<>();
        
        for (Process p:processArray) {
            Integer arrivalTime = p.arrivalTime();
            if (arrivalTime == current_time) {
                Integer pri = p.getPriority();
                System.out.print("ARRIVED process: ");
                p.print();
                System.out.println("; Current time = " + current_time);
                AdaptablePQEntry<Integer, Process> entry;
                entry = (AdaptablePQEntry<Integer, Process>) insert(pri, p);
                arrived.push(p);
                returnVal++;
            }
        }
        while (! arrived.isEmpty()) {
            Process p = arrived.pop();
            processArray.remove(p);
        }
        return returnVal;
    }
}
