package com.sysc4001.phoenix.milestone4.Games;

import android.app.Activity;

import com.sysc4001.phoenix.milestone4.Boards.OthelloBoard;
import com.sysc4001.phoenix.milestone4.PlayerTypes.HumanPlayerType;
import com.sysc4001.phoenix.milestone4.PlayerTypes.PlayerType;
import com.sysc4001.phoenix.milestone4.Strategies.Alternative.ObstructPlayerType;
import com.sysc4001.phoenix.milestone4.Strategies.Alternative.OthelloObstruct;
import com.sysc4001.phoenix.milestone4.Strategies.Minimax.MinimaxPlayerType;
import com.sysc4001.phoenix.milestone4.Strategies.Minimax.OthelloMinimax;

/***************************************************************************************************************************************************************
 * 										Game Class Plays Tic Tac Toe by Implementing Methods from RandomPlayerType and OthelloBoard Class
 ***************************************************************************************************************************************************************
 *
 * Milestone 1: A Game of Othello, adapted from code for tic tac toe provided by Lina
 *
 * Milestone 2:
 * Adapting Author: Brandon Marino
 * -Made it a subclass of Games
 * -removed redundant code
 * -fixed glitches
 * -cleaned up some code
 */
public class Othello extends Game
{
    /**
     * Play a game of othello
     */
    public Othello(){
        super(new OthelloBoard());
    }

    /**
     * Take a turn
     * @return if a turn was successfully completed
     */
    protected boolean takeTurn(PlayerType move){
        boolean takeTurn = super.takeTurn(move);
        ((OthelloBoard)boardGame).setMoved(takeTurn);
        return takeTurn;
    }

    public void setPlayer1Info(PlayerType player1){
        PlayerType[] players = getPlayers();
        players[0] = player1;
    }

    public void setPlayer2Info(PlayerType player2){
        PlayerType[] players = getPlayers();
        players[1] = player2;
    }
}