package Strategies.Minimax;

import Boards.Board;
import Boards.TicTacToeBoard;
import common.Move;

import java.util.ArrayList;

/** Milestone 3 - Author: Brandon Marino
 * Will handle it's own evaluation w/ help from the superclass
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
