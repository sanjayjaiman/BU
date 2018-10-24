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
public interface connectionState {
    public enum STATE {
        GAME_NOT_CREATED,
        GAME_CREATED,
        GAME_ON,
        GAME_OVER,
    }
    public void connected();
}
