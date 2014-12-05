package com.sysc4001.phoenix.milestone4.PlayerTypes;

import android.app.Activity;

import com.sysc4001.phoenix.milestone4.Boards.Board;
import com.sysc4001.phoenix.milestone4.Games.Game;
import com.sysc4001.phoenix.milestone4.OthelloActivity;
import com.sysc4001.phoenix.milestone4.common.Move;

import java.util.ArrayList;

/***********************************************************************************************************************************************************
 * 							HumanPlayerType Class creates Indexes for Row and Column to be Used in Board Classes
 ***********************************************************************************************************************************************************

 * Milestone 2, Author: Brandon Marino
 *  - Subclass of PlayerTypes
 *  This will print the available moves to the user and then prompt for the user to choose a move
 */
public class TouchPlayerType extends PlayerType {
    private Move currentMove;
    private OthelloActivity act;
   /**
     * Create a human player
     * @param playernum the name of the human..
     * @param boardGame the current gamestate
     */

    public TouchPlayerType(Board boardGame, int playernum){
        super(boardGame, playernum);
        currentMove = new Move(-1,-1);
    }
    public void setActivity(OthelloActivity act){
        this.act = act;
    }
    public void setMove(int row, int column){
        currentMove.setRow(row);
        currentMove.setColumn(column);
        act.play();
    }
    /**
     * Let user define the wanted move
     */
    public Move getMove(){
        getBoard().setCurrentState(Board.GAME_STATE.WAITING);
        Move returnMove = currentMove;
        currentMove = new Move(-1,-1);
        return returnMove;
    }

    /**
     * Make sure that the user's redoStack is flushed with each move
     * @param availableMoves a list of all of the moves possible on this board by this player
     */
    @Override
    public void setAvailableMoves(ArrayList<Move> availableMoves){
        //store the current state before performing any action
        Board.storeUndo(boardGame);
        Board.dumpRedoStack();
        super.setAvailableMoves(availableMoves);
    }
}