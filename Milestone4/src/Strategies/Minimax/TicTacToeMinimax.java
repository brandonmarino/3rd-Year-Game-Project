package Strategies.Minimax;

import Boards.Board;
import Boards.TicTacToeBoard;
import Games.TicTacToe;
import common.Move;

import java.util.ArrayList;

/***********************************************************************************************************************************************************
 * 							MiniMaxPlayerType Class creates Indexes for Row and Column to be Used in Board Classes
 ***********************************************************************************************************************************************************

 * Milestone 2, Author: Brandon Marino
 *  - Subclass of PlayerTypes
 * An AI implementation which looks into the future that each legal move could result in
 * and selects the best and safest legal move for the current state
 *
 * Currently this only works for Othello, We'll have to make some heavy modifications for it to work with our own game later
 */
public class TicTacToeMinimax extends MinimaxPlayerType {

    /**
     * Copy/ up-casting constructor
     * @param player the generic Player that was made in the generic Game class needed to make decisions about later int the game
     */
    public TicTacToeMinimax(MinimaxPlayerType player) {
        super(player.getBoard(),player.getPlayerNum());
    }

    /**
     * Evaluate the current state of the board to rank it later
     *
     * @param GenericBoardGame      the board that the game is being played on
     * @param availableMoves all legal moves
     * @return the evaluation of this state
     */
    @Override
    protected int evaluate(Board GenericBoardGame, ArrayList<Move> availableMoves) {
        TicTacToeBoard boardGame = (TicTacToeBoard)GenericBoardGame;
        int gameState = boardGame.getStateWorth(); //if the game has been won, and if so, how many conditions does it meet?
        // return evaluation of board
        return (100* gameState) + super.evaluate(boardGame, availableMoves); //ability to move is more important than the player's score at the next state
    }
}
