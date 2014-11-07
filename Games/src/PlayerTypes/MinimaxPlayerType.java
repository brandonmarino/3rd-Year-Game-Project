package PlayerTypes;

import Boards.Board;
import java.util.ArrayList;
import Boards.OthelloBoard;
import Minimax.moveWorth;

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
    private int maxDepth = 5;

    /**
     * @param boardGame needed to make decisions about later int the game
     * @param playernum needed for naming purposes
     */
    public MinimaxPlayerType(Board boardGame, int playernum) {
        this.boardGame = boardGame;
        setName("Computer-MiniMax " + playernum);
        maxDepth = boardGame.getDimensions();
    }

    /**
     * Call to make some decision and then pop the best move off the available moves
     * @return the best possible move
     */
    @Override
    public Integer[] getMove() {
        if (getAvailableMoves().isEmpty())   //player cannot move
            return null;
        moveWorth mostWorthy = decision(boardGame, getAvailableMoves(), 0);
        Integer[] bestmove = mostWorthy.getMove();
        for (int i = 0; i < getAvailableMoves().size(); i++) {
            if (getAvailableMoves().get(i) == bestmove) {
                popMove(i);
                break;
            }
        }
        return bestmove;
    }

    /**
     * decide on the best the possible move for the current state
     *
     * @param boardGame a copy of the current board state
     * @param moves all legal moves that could be made
     * @param depth the maxiumu depth of the decision tree
     * @return some move
     */
    private moveWorth decision(Board boardGame, ArrayList<Integer[]> moves, int depth) {
        moves = PlayerType.cloneMoves(moves);   //clone that set of moves so as to not edit the overall list of moves (allows branching without sharing one object of moves)
        boardGame = boardGame.getClone();  //get a full clone of the current board which you can perform operations on
        moveWorth thisMove = new moveWorth();
        if (depth >= maxDepth) {
            thisMove.setScore(evaluate(boardGame, moves));
            return thisMove;
        } else {
            if (moves.size() == 0) {
                thisMove.setScore(evaluate(boardGame, moves));
                return thisMove;
            } else {
                int maximumScore = Integer.MAX_VALUE;
                Integer[] bestMove = new Integer[2];
                //for(Integer[] currentMove: moves){
                for (int i = moves.size() - 1; i >= 0; i--) {
                    Integer[] currentMove = moves.get(i);
                    moves.remove(currentMove); //pop off list
                    boardGame.attemptMove(currentMove[0], currentMove[1]);  //attempt this move
                    moveWorth worth = decision(boardGame, moves, depth + 1);
                    if (worth.getScore() < maximumScore) {
                        maximumScore = worth.getScore();
                        bestMove = currentMove;
                    }
                }
                thisMove = new moveWorth();
                thisMove.setScore(maximumScore);
                thisMove.setMove(bestMove);
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
    public int evaluate(Board boardGame, ArrayList<Integer[]> availableMoves) {
        int score = 0;
        //player mobilities and shiptotals
        int player1Mobility = 0;
        int player2Mobility = 0;
        int mobility = 0;
        int chips = 0;
        int player1Chips = ((OthelloBoard) boardGame).countBlack();
        int player2Chips = ((OthelloBoard) boardGame).countWhite();
        switch (boardGame.getCurrentPlayer()) {
            case PLAYER1:
                player1Mobility = PlayerType.cloneMoves(availableMoves).size();
                boardGame.setcurrentPlayer(Board.PLAYER.PLAYER2);
                player2Mobility = boardGame.getPossibleMoves().size();
                boardGame.setcurrentPlayer(Board.PLAYER.PLAYER1);
                mobility = player1Mobility - player2Mobility;
                chips = player1Chips - player2Chips;
                break;
            case PLAYER2:
                boardGame.setcurrentPlayer(Board.PLAYER.PLAYER1);
                player1Mobility = boardGame.getPossibleMoves().size();
                boardGame.setcurrentPlayer(Board.PLAYER.PLAYER2);
                player2Mobility = PlayerType.cloneMoves(availableMoves).size();
                mobility = player2Mobility - player1Mobility;
                chips = player2Chips - player1Chips;
                break;
        }
        // return evaluation of board
        return (10 * mobility) + chips; //ability to move is more important than the player's score at the next state

    }
}
