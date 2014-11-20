package Strategies.Minimax;

import Boards.Board;
import java.util.ArrayList;
import Boards.OthelloBoard;
import PlayerTypes.PlayerType;
import common.Move;

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
public class MinimaxPlayerType extends PlayerType {
    private Board boardGame;
    public final int MAXDEPTH = 5;
    private int playernum;
    /**
     * @param boardGame needed to make decisions about later int the game
     * @param playernum needed for naming purposes
     */
    public MinimaxPlayerType(Board boardGame, int playernum) {
        this.boardGame = boardGame;
        setName("Computer-MiniMax " + playernum);
        this.playernum = playernum;
    }

    /**
     * Call to make some decision and then pop the best move off the available moves
     * @return the best possible move
     */
    @Override
    public Move getMove() {
        if (getAvailableMoves().isEmpty())   //player cannot move
            return null;
        Move mostWorthy = decision(boardGame.getClone(), getAvailableMoves(), 0);
        for (int i = 0; i < getAvailableMoves().size(); i++) {
            if (getAvailableMoves().get(i) == mostWorthy) {
                popMove(i);
                break;
            }
        }
        return mostWorthy;
    }

    /**
     * Pass board to sub-class
     */
    protected Board getBoard(){
        return boardGame;
    }

    /**
     * get player number for subclass copy constructor
     * @return
     */
     protected int getPlayerNum(){
         return playernum;
     }
    /**
     * GENERIC
     * decide on the best the possible move for the current state
     *
     * @param moves all legal moves that could be made
     * @param depth the maximum depth of the decision tree
     * @return some move
     */
    protected Move decision(Board boardGame, ArrayList<Move> moves, int depth) {
        moves = PlayerType.cloneMoves(moves);   //clone that set of moves so as to not edit the overall list of moves (allows branching without sharing one object of moves)
        boardGame = boardGame.getClone();  //get a full clone of the current board which you can perform operations on
        Move thisMove = new Move();
        if (depth >= MAXDEPTH) {
            thisMove.setWorth(evaluate(boardGame, moves));
            return thisMove;
        } else {
            if (moves.size() == 0) {
                thisMove.setWorth(evaluate(boardGame, moves));
                return thisMove;
            } else {
                int maximumScore = Integer.MAX_VALUE;
                for (int i = moves.size() - 1; i >= 0; i--) {
                    Move currentMove = moves.get(i);
                    moves.remove(currentMove); //pop off list
                    boardGame.attemptMove(currentMove);  //attempt this move
                    Move worth = decision(boardGame, moves, depth + 1);
                    if (worth.getWorth() < maximumScore) {
                        maximumScore = worth.getWorth();
                        thisMove = currentMove;
                    }
                }
                thisMove.setWorth(maximumScore);
                return thisMove;
            }
        }
    }

    /**
     * Evaluate the current state of the board to rank it later
     * The versions in the sub classes are the important ones
     * @param boardGame      the board that the game is being played on
     * @param availableMoves all legal moves
     * @return the evaluation of this state
     */
    protected int evaluate(Board boardGame, ArrayList<Move> availableMoves){ return 0;}
}
