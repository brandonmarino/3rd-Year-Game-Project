package Strategies.Minimax;

import Boards.Board;
import Boards.CheckersBoard;
import common.Move;

import java.util.ArrayList;

 /** Milestone 3 - Author: Brandon Marino
  * Will handle it's own evaluation w/ help from the superclass
  */
public class CheckersMinimax extends MinimaxPlayerType {
    /**
     * Copy/ up-casting constructor
     * @param player the generic Player that was made in the generic Game class needed to make decisions about later int the game
     */
    public CheckersMinimax(MinimaxPlayerType player) {
        super(player.getBoard(),player.getPlayerNum());
    }

    /**
     * Evaluate the current state of the checkers board to rank it later
     *
     * @param GenericBoardGame      the board that the game is being played on
     * @param availableMoves        all legal moves
     * @return the evaluation of this state
     */
    protected int evaluate(Board GenericBoardGame, ArrayList<Move> availableMoves) {

        CheckersBoard boardGame = (CheckersBoard)GenericBoardGame;
        //checkers specific code
        int gameState = boardGame.getStateWorth();
        return gameState + super.evaluate(boardGame, availableMoves);
    }
}
