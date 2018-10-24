/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bu.met.cs665.battleShipsServer;

import java.net.ServerSocket;
import java.io.IOException;
import java.util.*;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 *
 * @author sanjay
 */
public class serverMain {
    public static final int PORT_NUMBER = 10001;
    private static int numConnections = 0;
    private static HashMap<Integer, connectionState> connections;
    private static boolean running = true;
    
    // One client disconnected. Disconnect the other one and exit the application
    public static void clientDisconnected(int number) {
        if (numConnections <= 0) {
            System.out.println("clientDisconnected: Error");
            return;
        }
        // Stop accepting any more connections
        connections.remove(number);
        numConnections--;
        running = false;
    }
    // id can be 1/2
    public static serverThread getOtherPlayer(int id) {
        
        if (numConnections <= 0) {
            return null;
        }
        int other = ((id + 1) % 2);
//        System.out.println("Other connection # = " + Integer.toString(other));
        return (serverThread)connections.get(other);
    }
    
    public static void runMainLoop() {
        connections = new HashMap<>();
        
//        System.out.println("ServerMain: Main loop");
        ServerSocketChannel ssc = null;
        try {
            ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            // Get the Socket connected to this channel, and bind it to the
            // listening port
            ServerSocket ss = ssc.socket();

            InetSocketAddress isa = new InetSocketAddress( PORT_NUMBER );
            System.out.println("Listening on Inet Socket Address:" + isa);
            ss.bind(isa);
            
            Selector selector= Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            while (running) {
                int num = selector.select(1000);  //  1 second
                if (num == 0) {
//                    System.out.println("1 sec timeout");
                    continue;
                }
//                System.out.println("Select poped");
                Set keys = selector.selectedKeys();
                Iterator it = keys.iterator();
                while (it.hasNext()) {
                    // Get a key representing one of bits of I/O
                    // activity.
                    SelectionKey key = (SelectionKey)it.next();
                    if ((key.readyOps() & SelectionKey.OP_ACCEPT) ==
                        SelectionKey.OP_ACCEPT) {
                        if (numConnections == 2) {
                            System.out.println("No more connections accepted");
                        }
                        else  {
                            // Accept the incoming connection.
                            SocketChannel s = ssc.accept();
//                            System.out.println("New client connected from " + s.getRemoteAddress());
                            numConnections++;
                            connectionState th = null;
                            if (numConnections == 1) {
                                th = new PlayerOneConnected(s, numConnections);
                            }
                            else {
                                th = new PlayerTwoConnected(s, numConnections);                                
                            }
                            th.connected();
                            connections.put(numConnections, th);                            
//                            System.out.println("Num connections = " + Integer.toString(numConnections));
                        }
                    }
                }
                keys.clear();
                Thread.sleep(1000);                
            }
        } catch (IOException ex) {
            System.out.println("Unable to start server.");
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }
        finally {
            try {
            if (ssc != null)
               ssc.close();
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}