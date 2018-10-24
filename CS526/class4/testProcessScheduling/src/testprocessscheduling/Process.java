/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testprocessscheduling;

/**
 *
 * @author sanjay
 */
public class Process {
    private int pri;
    private final int processId;
    private final int arrivalTime;
    private final int duration;
    private  int startRunTime = 0;
    private static final int maxWaitTime = 80;
    private int startWaitTime;
    
    public Process(int processId, int pri, int duration, int arrivalTime) {
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.pri = pri;
        this.processId = processId;
        startWaitTime = arrivalTime;
    }
    
    public void print() {
        System.out.print("Process Id = " + Integer.toString(processId) + ", " + "priority = " + Integer.toString(pri) + ", " + 
                "duration = " + Integer.toString(duration) + ", " + "arrival time = " + Integer.toString(arrivalTime));
    }

    public String toString() {
        return "Process Id = " + Integer.toString(processId) + "\n\t" + "priority = " + Integer.toString(pri) + "\n\t" + 
                "duration = " + Integer.toString(duration) + "\n\t" + "arrival time = " + Integer.toString(arrivalTime);
    }
    
    public boolean runComplete(int current_time) {
        boolean ret = ((current_time - startRunTime) >= duration);
//        System.out.println(toString() + "; startRunTime = " + startRunTime + " current_time = " + current_time + "; Returning " + ret);
        
        return ret;
    }
    /**
     *
     * @return
     */
    public int pid() {
        return processId;
    }
    public int arrivalTime() {
        return arrivalTime;
    }
    public int waitTime(int current_time) {
        return current_time - startWaitTime;
    }
    public int getPriority() {
        return pri;
    }
    public void run(int time) {
        startRunTime = time;
    }
    public boolean setPri(int current_time) {
        if (current_time - startWaitTime > maxWaitTime) {
            if (pri > 0) {
                pri--;
                System.out.println("Priority of process " + pid() + " decremented, New priority is " + pri + "; Current time = " + current_time);
            }
            startWaitTime = current_time;
            return true;
        }
        return false;
    }
}
