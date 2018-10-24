/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bu.met.cs665.battleShipsServer;

import java.io.IOException;
import static java.lang.System.in;
import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.util.*;
import java.nio.*;

/**
 *
 * @author sanjay
 */
abstract public class serverThread extends Thread {
    private boolean running;
    private SocketChannel socket;
    protected int connectionNumber;  // or player number 1/2
    
    static Game game = null;
    
    public static connectionState.STATE state = connectionState.STATE.GAME_NOT_CREATED;
    
    public serverThread(SocketChannel socket, int number) {
        this.socket = socket;
        connectionNumber = number;  // connectionNumber = 1/2
        try {
            socket.configureBlocking(false);
            startThread();
        } catch (IOException ex) {
            System.out.println("serverThread for " + connectionNumber + "exiting");
            System.out.println(ex);
        }
    }

    public void Send(String str) {
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        try {
        socket.write(buffer);
        }
        catch (IOException ex) {
            System.out.println("Unable to write back to client" + ex);
        }
    }
    
    private int BUFF_SIZE = 100;
    
    public void stopThread() {
        System.out.println("Stopping thread");
        running = false;
    }
    
    public void startThread() {
        running = true;
        start();
    }
    public boolean readTwoInts(ByteBuffer bb, int bytesRead, Integer[]twoInts) {
        if (state != connectionState.STATE.GAME_ON) {
            Send("Other player has not connected please wait!\n");
            return false;            
        }
        try {
            String str = new String(bb.array(), 0, bytesRead);
            StringTokenizer st = new StringTokenizer(str);
    //        System.out.println("num  bytes = " + bytesRead);
            Integer []tokens = new Integer[2];
            int cnt = 0;
            while (st.hasMoreTokens()) {
                str = st.nextToken();
                tokens[cnt] = Integer.parseInt(str);
    //            System.out.print(tokens[cnt] + "\t");
                cnt++;
            }
    //        System.out.println();
            twoInts[0] = tokens[0];
            twoInts[1] = tokens[1];            
        }
        catch (NumberFormatException e) {
            Send("Invalid Input; Enter 2 integers only\n>");
            return false;            
        }
        return true;
    }

    public void process(Integer[]twoInts) {
//        System.out.println("Connection number: " + connectionNumber + "; User entered " + twoInts[0] + " " + twoInts[1]);
        boolean didIWin = game.play(connectionNumber, twoInts);
        
        String str = game.printPlayer(connectionNumber);
        Send(str + "\n>");
        if (didIWin) {
            Send("!!!!! You WIN !!!!!\n");
            sendToOther("!!!!! You Loose !!!!!\n");
            state = connectionState.STATE.GAME_OVER;
        }
    }
    
    @Override
    public void run() {
//        System.out.println("ServerThread:: " + connectionNumber + " Running; ");
        try {
            Selector selector = Selector.open();
            socket.register(selector, SelectionKey.OP_READ);
//            socket.register(selector, SelectionKey.OP_WRITE);

            while (running && state != connectionState.STATE.GAME_OVER) {
//                System.out.println("ServerThread::Entering select");
                int num = selector.select(1000);  //  1 second
                if (num == 0) {
                    continue;
                }
//                System.out.println("ServerThread::select popped");
                Set keys = selector.selectedKeys();
                Iterator it = keys.iterator();
                while (it.hasNext()) {
                    // Get a key representing one of bits of I/O
                    // activity.
                    SelectionKey key = (SelectionKey)it.next();
                    SocketChannel sc = (SocketChannel)key.channel();
//                    System.out.println("\tHas data");

                    byte[] bytes = new byte [BUFF_SIZE];
                    ByteBuffer bb = ByteBuffer.wrap(bytes);
                    bb.clear();
                    int bytesRead = sc.read(bb);
                    if (bytesRead == -1) {
                        running = false;
                    }
                    else {
                        Integer []twoInts = new Integer[2];
                        if (readTwoInts(bb, bytesRead, twoInts)) {
                            process(twoInts);
                        }
                    }
                    if (key.isReadable()) {
//                        System.out.println("Socket readable:");
                    }
                }
                keys.clear();
            }
        } catch (IOException ex) {
            System.out.println("Unable to get streams from client");
        } finally {
            try {
                System.out.println("Client has disconnected " + socket.getRemoteAddress());
                in.close();
                socket.close();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        System.out.println("Exiting thread for connection " + Integer.toString(connectionNumber));
        serverMain.clientDisconnected(connectionNumber);
        state = connectionState.STATE.GAME_OVER;
    }
    void  createGame() {
        game  = new Game();
        System.out.println(game);
        String str = "Game Created\n";
        System.out.println(str);
        str += game.printPlayer(connectionNumber);
        Send(str);
        str = "Wait for the second player to connect\n";
        Send(str);           
    }

    void  gameOn() {
        String str = "Game on";
        System.out.println(str );
        str += game.printPlayer(connectionNumber);
        Send(str);
        str = "You are the second player: Lets play\nEnter two digits between 1 and 8\n> ";
        Send(str);
        str = "Second player has connected: Lets play\nEnter two digits between 1 and 8\n> ";
        sendToOther(str);
    }
    
    private void sendToOther(String str) {
        serverThread other = serverMain.getOtherPlayer(connectionNumber);
        other.Send(str);        
    }
}
