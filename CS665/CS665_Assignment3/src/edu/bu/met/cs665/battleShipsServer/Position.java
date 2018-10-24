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
public class Position {
    public enum positionState {
        WATER,
        SHIP_PRESENT,
        SHOT_FIRED,
        SHOT_HIT,        
    }
    public positionState state;
    public Integer X;
    public Integer Y;
    static String enmumStrings[] = {"W", "P", "F", "H", "U"};

    public Position() {
        this(-1, -1, positionState.WATER);
    }
    public Position(int i, int j, positionState s) {
        X = i;
        Y = j;
        state = s;
    }
    public String toString() {
        String str = ((state == positionState.WATER) ? enmumStrings[0] :
                 ((state == positionState.SHIP_PRESENT) ? enmumStrings[1] :
                  ((state == positionState.SHOT_FIRED) ? enmumStrings[2] :
                   ((state == positionState.SHOT_HIT) ? enmumStrings[3] : enmumStrings[4]))));
        return str;        
    }
    public String printPos() {
        return (Integer.toString(X) + " " + Integer.toString(Y));
    }
    public positionState getState() {
        return state;
    }
    public void setState(positionState s) {
        state = s;
    }
    public void clear() {
        state = positionState.WATER;
        X = -1;
        Y = -1;
    }
}
