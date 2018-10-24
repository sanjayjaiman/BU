/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bu.met.cs665.battleShipsServer;

/**
 *
 * @author sanjay
 */
public class Player {
    private int   ID;
    public Board myBoard;
    public Board opponentsBoard;
    
    public Player(int n, int m, int numShips) {
        myBoard = new Board(n, m, numShips);
        opponentsBoard = new Board(n, m);
    };
    void setId(int id) {
        ID = id;
    };
    int  getId() {
        return ID;
    }
    public String toString() {
        String str = "MY BOARD\n";
        str += myBoard;
        str += "Opponents BOARD\n";
        str += opponentsBoard;
        return str;
    }
    boolean play(int x, int y, Player opponent) {
        Position xy = new Position();
        opponentsBoard.getDimensions(xy);
        if ( x < 0 || x > xy.X || y < 0 || y > xy.Y ) {
            System.out.println("Incorrect input");
            return false;
        }
        int adj_x = x-1;
        int adj_y = y-1;
        
        opponentsBoard.set(adj_x, adj_y, Position.positionState.SHOT_FIRED);

        Position p = opponent.myBoard.getPos(adj_x, adj_y);
        if (p.getState() == Position.positionState.SHIP_PRESENT) {
            opponentsBoard.set(adj_x, adj_y, Position.positionState.SHOT_HIT);
        }
        return true;
    }

    boolean allShipsShot(Board  incoming) {
        if (myBoard.areAllShipsShot(incoming)) {
            return true;
        }
        return false;
    }        
}
