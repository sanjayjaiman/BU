/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bu.met.cs665.battleShipsServer;

import java.nio.channels.SocketChannel;

/**
 *
 * @author sanjay
 */
public class PlayerOneConnected extends serverThread implements connectionState {

    public PlayerOneConnected(SocketChannel socket, int number) {
        super(socket, number);
    }

    @Override
    public void connected() {
        state = STATE.GAME_CREATED;
        createGame();
    }
}
