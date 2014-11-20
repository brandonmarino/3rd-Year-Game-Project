package Strategies.Minimax;

import Boards.Board;
import PlayerTypes.PlayerType;
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
public class CheckersMinimaxPlayerType extends MinimaxPlayerType {
    /**
     * Copy/ up-casting constructor
     * @param player the generic Player that was made in the generic Game class needed to make decisions about later int the game
     */
    public CheckersMinimaxPlayerType(MinimaxPlayerType player) {
        super(player.getBoard(),player.getPlayerNum());
    }

    /**
     * Evaluate the current state of the board to rank it later
     *
     * @param boardGame      the board that the game is being played on
     * @param availableMoves all legal moves
     * @return the evaluation of this state
     */
    protected int evaluate(Board boardGame, ArrayList<Move> availableMoves) {
        //player mobilities and shiptotals
        int player1Mobility = 0;
        int player2Mobility = 0;
        int mobility = 0;
        int chips = 0;
        //int player1Chips = boardGame.countSpaces(Board.PLAYER.PLAYER1);
        //int player2Chips = boardGame.countSpaces(Board.PLAYER.PLAYER2);
        switch (boardGame.getCurrentPlayer()) {
            case PLAYER1:
                player1Mobility = PlayerType.cloneMoves(availableMoves).size();
                boardGame.setcurrentPlayer(Board.PLAYER.PLAYER2);
                player2Mobility = boardGame.getPossibleMoves().size();
                boardGame.setcurrentPlayer(Board.PLAYER.PLAYER1);
                mobility = player1Mobility - player2Mobility;
                //chips = player1Chips - player2Chips;
                break;
            case PLAYER2:
                boardGame.setcurrentPlayer(Board.PLAYER.PLAYER1);
                player1Mobility = boardGame.getPossibleMoves().size();
                boardGame.setcurrentPlayer(Board.PLAYER.PLAYER2);
                player2Mobility = PlayerType.cloneMoves(availableMoves).size();
                mobility = player2Mobility - player1Mobility;
                //chips = player2Chips - player1Chips;
                break;
        }
        // return evaluation of board
        return (10 * mobility) + chips; //ability to move is more important than the player's score at the next state

    }
}
