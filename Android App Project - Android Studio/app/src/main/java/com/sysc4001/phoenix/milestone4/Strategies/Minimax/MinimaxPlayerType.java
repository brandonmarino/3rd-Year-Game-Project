package com.sysc4001.phoenix.milestone4.Strategies.Minimax;

import com.sysc4001.phoenix.milestone4.Boards.Board;
import java.util.ArrayList;
import com.sysc4001.phoenix.milestone4.PlayerTypes.PlayerType;
import com.sysc4001.phoenix.milestone4.common.Move;

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
    public final int MAXDEPTH = 5;
    private int playernum;
    /**
     * @param boardGame needed to make decisions about later int the game
     * @param playernum needed for naming purposes
     */
    public MinimaxPlayerType(Board boardGame, int playernum) {
        super(boardGame,playernum);
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
     * get player number for subclass copy constructor
     * @return the playernum
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
     *
     * @param boardGame      the board that the game is being played on
     * @param availableMoves all legal moves
     * @return the evaluation of this state
     */
    protected int evaluate(Board boardGame, ArrayList<Move> availableMoves) {
        boardGame = boardGame.getClone();   //clone the board for safety
        //player mobilities and total owed squares
        int player1Mobility;
        int player2Mobility;
        int mobility = 0;
        int owendSpaces = 0;
        int player1Score = boardGame.countSpaces(Board.PLAYER.PLAYER1);
        int player2Score = boardGame.countSpaces(Board.PLAYER.PLAYER2);
        switch (boardGame.getCurrentPlayer()) {
            case PLAYER1:
                player1Mobility = cloneMoves(availableMoves).size();
                boardGame.setcurrentPlayer(Board.PLAYER.PLAYER2);
                player2Mobility = boardGame.getPossibleMoves().size();
                boardGame.setcurrentPlayer(Board.PLAYER.PLAYER1);
                mobility = player1Mobility - player2Mobility;
                owendSpaces = player1Score - player2Score;
                break;
            case PLAYER2:
                boardGame.setcurrentPlayer(Board.PLAYER.PLAYER1);
                player1Mobility = boardGame.getPossibleMoves().size();
                boardGame.setcurrentPlayer(Board.PLAYER.PLAYER2);
                player2Mobility = cloneMoves(availableMoves).size();
                mobility = player2Mobility - player1Mobility;
                owendSpaces = player2Score - player1Score;
                break;
        }
        // return evaluation of board
        return (10 * mobility) + owendSpaces; //ability to move is more important than the player's score at the next state
    }
}
