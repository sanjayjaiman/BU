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
public class Board {
    private static final int SHIP_LENGTH = 4;
    private ArrayList<ArrayList<Position>>  positions;
    private final int N;
    private final int M;
    private ArrayList<Position> shipPresent;

    public Board(int n, int m, int numShips) {
        this(n,m);
        createShips(numShips);
    }
    
    public String toString() {
        String str = "";
        for (ArrayList<Position> yArray : positions) {
            for (Iterator j = yArray.iterator(); j.hasNext();) {
                Position p = (Position)j.next();
                str += p + " ";
            }
            str += "\n";
        }
        return str;
    }
    public Board(int n, int m) {
        N = n;
        M = m;
        positions =  new ArrayList<>();
        shipPresent =  new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            ArrayList<Position> yArray = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                yArray.add(new Position(i, j, Position.positionState.WATER));
            }
            positions.add(yArray);
        }
        System.out.println("Created board of size " + Integer.toString(N) + " X " + Integer.toString(N));
    }

    private boolean occupied(int x, int y) {
        ArrayList<Position> temp = positions.get(y);
        Position p = temp.get(x);

        if (p.getState() == Position.positionState.SHIP_PRESENT) {
            return true;
        }
        return false;        
    }
    
    private boolean getEmptySquares(Position []xy, int cnt) {
        boolean right = true;
        if (cnt % 2 != 0) {
            right = false;
        }
        boolean found = false;
        while (! found) {
            Random RN = new Random();
            int randN = RN.nextInt(N-1);
            int randM = RN.nextInt(M-1);
//            System.out.println("Randon N = " + Integer.toString(randN)); 
//            System.out.println("Randon M = " + Integer.toString(randM)); 
            if (right) {
                if (randN + SHIP_LENGTH > N) {
                    continue;
                }
            } else {
                if (randM + SHIP_LENGTH > M) {
                    continue;
                }
            }
            boolean allFound = true;
            for (int i = 0; i < SHIP_LENGTH; i++) {
                if (right) {
                    xy[i].X = randN++;
                    xy[i].Y = randM;
                } else {
                    xy[i].X = randN;
                    xy[i].Y = randM++;
                }
                if (occupied(xy[i].X, xy[i].Y)) {
                    allFound = false;
                    break;
                }
            }
            if (!allFound) {
                continue;
            }
    //        cout << "Ship " << cnt << " pos = ";
    //        for (int i = 0; i < SHIP_LENGTH; i++) {
    //            cout << xy[i].print() << ", ";
    //        }
    //        cout << endl;

            found = true;
        }
        return true;
    }
    
    private void createShips(int num) {
        for (int i = 0; i < num; i++) {
            Position [] newShipPosiiton = new Position[SHIP_LENGTH];
            for (int j = 0; j < SHIP_LENGTH; j++) {
                newShipPosiiton[j] = new Position();
            }
//            System.out.println("CREATING SHIP # " + i);
//            System.out.print("\t");
            if (getEmptySquares(newShipPosiiton, i)) {
                for (int j = 0; j < SHIP_LENGTH; j++) {
                    int x = newShipPosiiton[j].X;
                    int y = newShipPosiiton[j].Y;
//                    System.out.println("Setting " + Integer.toString(x) + " " + Integer.toString(y) + " to PRESENT");
                    positions.get(y).get(x).state = Position.positionState.SHIP_PRESENT;
                    newShipPosiiton[j].state = Position.positionState.SHIP_PRESENT;
                    shipPresent.add(newShipPosiiton[j]);
//                    System.out.print("\"" + newShipPosiiton[j].printPos() + "\"" + " ");
                }
            }
//            System.out.println();
        }
//        System.out.print("Ships present at following posiitons:\n\t");
//        for (Position p : shipPresent) {
//            System.out.print("[" + p.printPos() + "],  ");
//        }
//        System.out.print("Board layout:\n" + this);
//        System.out.println();
    }
    void getDimensions(Position xy) {
        xy.X = M; xy.Y = N;
    }    
    boolean set(int x, int y, Position.positionState newState) {
        Position p = getPos(x, y);
        if (p != null) {
            p.setState(newState);
            return true;
        }
        return false;
    }
    Position getPos(int x, int y) {
//        System.out.print("Extracting posiiton at " + Integer.toString(x) + " " + Integer.toString(y));
        ArrayList<Position> temp = positions.get(y);
        Position p = temp.get(x);
        return p;
    }

    boolean areAllShipsShot(Board incoming) {
        for (Position pos : shipPresent) {
            Position in_pos = incoming.getPos(pos.X, pos.Y);
            if (in_pos.getState() != Position.positionState.SHOT_HIT) {
//                System.out.println("areAllShipsShot: returning false;\n\tShip not present at: " + Integer.toString(pos.X + 1) + " " +
//                                Integer.toString(pos.Y + 1) + " = " + in_pos);
                return false;
            }
        }
//        System.out.println("areAllShipsShot: returning TRUE");
        return true;
    }
}
