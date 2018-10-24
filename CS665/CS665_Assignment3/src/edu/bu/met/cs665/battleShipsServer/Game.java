/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bu.met.cs665.battleShipsServer;

import java.util.*;

/**
 *
 * @author sanjay
 */
public class Game {
    private int     N;
    private int     M;
    private static  HashMap<Integer, Player>  player = new HashMap<>();
    private static  int     defaultGameSize = 8;
    private static  int     defaultNumShips = 3;

    public Game() {
        this(defaultGameSize, defaultGameSize, defaultNumShips);
    }
    
    public Game(int n, int m, int numShips) {
        N = n;
        M = m;        
        player.put(1, new Player(n, m, numShips));
        player.put(2, new Player(n, m, numShips));
    };
    
    public String toString() {
        String str = printPlayer(1);
        str += printPlayer(2);
        return str;
    }
    
    public String printPlayer(int idx) {
        String str = "";
        if (idx == 1) {
            str = "PLAYER 1:\n";
        }
        else {
            str = "PLAYER 2:\n";
        }
        str += player.get(idx);
        str += "\n";
        return str;
    }
    
    public Player getOtherPlayer(int idx) {
        idx = (idx % 2) + 1;  // Since incoming value is 1/2
        return player.get(idx);
    }
    
    public boolean play(int connectionNumber, Integer []xy) {
//        System.out.println("Game:Play: connection number = " + connectionNumber);
        Player p1 = player.get(connectionNumber);
        Player p2 = getOtherPlayer(connectionNumber);
        p1.play(xy[0], xy[1], p2);
        
        if (p2.allShipsShot(p1.opponentsBoard)) {
            return true;
        }
        return false;
    }
}
