/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testlinkedstack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import net.datastructures.LinkedStack;

/**
 *
 * @author sanjay
 */
public class TestLinkedStack {

    private static String [] getTokens(String line) {
        String []tokens = line.split("\\s+");
        return tokens;
    }

    private static void printTokens(String[] tokens) {
        for(String token: tokens) {
            System.out.print(token + " ");
        }
        System.out.println();
    }
    
    private static String evaluate(String operand1, String operator, String operand2) {
        Integer op1 = Integer.parseInt(operand1);
        Integer op2 = Integer.parseInt(operand2);
        Integer res = 0;
        
        switch(operator) {
            case "+":
                res = op1 + op2;
                break;
            case "-":
                res = op1 - op2;
                break;
            case "*":
                res = op1 * op2;
                break;
            case "/":
                res = op1 / op2;
                break;
                
        }
        
        System.out.println("Evaluate " + operand1 + " " + operator + " " + operand2 + " = " + res);
        return res.toString();
    }
    
    public static void main(String[] args) {
        try {
            FileReader f = new FileReader("expressions.txt");
            BufferedReader in = new BufferedReader(f);
            Scanner scan = new Scanner(in);
            int numLines = 0;
            while(scan.hasNextLine()) {
                String line = scan.nextLine();
                LinkedStack<String> in_stack = new LinkedStack<>();
                LinkedStack<String> pr_stack = new LinkedStack<>();
                String[] tokens = getTokens(line);
                
                if (tokens.length == 0) {
                    continue;
                }
                for (String element : tokens) {
                    in_stack.push(element);
                }
                while (! in_stack.isEmpty()) {
                    String element = in_stack.pop();
                    if (! element.equals("(")) {
                        pr_stack.push(element);
                    }
                    else {
                        String operand1 = pr_stack.pop();
                        String operator = pr_stack.pop();
                        String operand2 = pr_stack.pop();
                        String clp = pr_stack.pop();
                        if (! clp.equals(")")) {
                            System.out.println("ERROR");
                            return;
                        }
                        String res = evaluate(operand1, operator, operand2);
                        pr_stack.push(res);
                    }
                }
                String res = pr_stack.pop();
                System.out.println("Result = " + res);
                System.out.println();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found - exiting");
            System.out.println(e);
        }
        catch (IOException ioe) {
            System.out.println("Error reading file - exiting");
        }
    }    
}
