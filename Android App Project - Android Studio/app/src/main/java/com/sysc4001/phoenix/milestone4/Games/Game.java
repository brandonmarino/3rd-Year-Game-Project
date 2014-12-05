package com.sysc4001.phoenix.milestone4.Games;

import android.app.Activity;

import java.io.Serializable;
import com.sysc4001.phoenix.milestone4.Boards.Board;
import com.sysc4001.phoenix.milestone4.Boards.OthelloBoard;
import com.sysc4001.phoenix.milestone4.PlayerTypes.*;
import com.sysc4001.phoenix.milestone4.common.Move;

import java.util.Observable;

/***************************************************************************************************************************************************************
 * 										Game Class Plays Tic Tac Toe by Implementing Methods from RandomPlayerType and OthelloBoard Class
 ***************************************************************************************************************************************************************
 *
 * Milestone 2: Brandon Marino: Collected general functions from the two current Games will adapt as needed in future iterations
 * By merging Tic Tac Toe and Othello, we were able to significantly decrease redundant code
 */
public abstract class Game extends Observable implements Serializable{
    // Fields that are constant across all possible games
    protected Board boardGame;
    protected PlayerType[] players = new PlayerType[2]; //each player has enough dimensions to warrant it's own object
    public boolean isGUI = false;
    String play1Name = "";
    String play2Name = "";
    /**
     * Create some non-generic Game
     * @param boardGame the board of the non generic game
     */
    public Game(Board boardGame){
        this.boardGame = boardGame;
    }

    /******************************************************************************************************************************************************************
     * 												Functions to Play a Generic Game
     * ****************************************************************************************************************************************************************/

    /**
     * Play a game
     */
    public void play(Activity activity, String pl1Name, String pl2Name){
        // Continue Playing Until You're Done
        play1Name = pl1Name;
        play2Name = pl2Name;
        while (boardGame.getCurrentState() == Board.GAME_STATE.PLAYING) {
            boolean moveMade = false;
            //allow turns of different player types
            if (boardGame.getCurrentPlayer() == Board.PLAYER.PLAYER1)
                moveMade = takeTurn(players[0]);
            else if (boardGame.getCurrentPlayer() == Board.PLAYER.PLAYER2)
                moveMade = takeTurn(players[1]);
            update(moveMade, activity);
            checkIfWon();
        }
    }

    /**
     * Take a turn
     * @return if a turn was successfully completed
     */
    protected boolean takeTurn(PlayerType move){
        boolean state = true;
        boolean turnTaken = false;
        move.setAvailableMoves(boardGame.getPossibleMoves());       //give all available moves from board
        while(state){
            Move playerMove = move.getMove();
            if (playerMove == null || (playerMove.getColumn() == -1 && playerMove.getRow() == -1) ){
                //All moves have been exhausted
                state = false;//Quit loop
            }else{
                //some moves were available
                if (boardGame.attemptMove(playerMove)){  //try to make the move
                    turnTaken = true;
                    state = false;//Quit loop
                }
            }

        }
        return turnTaken;
    }

    /**
     * Update if a winner has been found, if a turn was taken by the current player print out a board
     * @param turnTaken if a turn was successfully completed by the current player
     */
    protected void update(boolean turnTaken, Activity activity){
        //Check if the last game has been played and what is the new status (win, draw, or not done)
        boardGame.updateGame();
        //if a move was made print the resulting board
        if (turnTaken){
            ((OthelloBoard)boardGame).printBoard(activity, play1Name, play2Name);
            setChanged();				//notify view(s)
	        notifyObservers(boardGame);
        }
    }

    /**
     * Check if the game has been won by either player
     */
    protected void checkIfWon(){
        if(boardGame.getCurrentPlayer() == Board.PLAYER.PLAYER1)
            boardGame.setcurrentPlayer(Board.PLAYER.PLAYER2);
        else
            boardGame.setcurrentPlayer(Board.PLAYER.PLAYER1);
    }
    /** Clone the generic board
     *
     * @return a generic clone of the current board
     */
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    /**
     * Return the board
     * @return the board
     */
    public Board getBoard(){
        return boardGame;
    }
    /**
     * Return the board
     * @return the board
     */
    public PlayerType[] getPlayers(){
        return players;
    }
}
