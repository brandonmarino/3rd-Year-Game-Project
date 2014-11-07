package PlayerTypes;

import Boards.Board;

import java.util.ArrayList;

import Minimax.Evaluator;
import Minimax.minimaxWorth;

/**
 * Created by Brandon on 11/7/2014.
 */
public class MinimaxPlayerType extends PlayerType {
    private Board boardGame;
    private int maxDepth = 4;
    private Evaluator evaluator = new Evaluator();

    public MinimaxPlayerType(Board boardGame) {
        this.boardGame = boardGame;
        setName("Computer-MiniMax");
    }

    @Override
    public Integer[] getMove() {
        return getDecision(boardGame);
    }

    @Override
    public void setAvailableMoves(ArrayList<Integer[]> availableMoves) {
        super.setAvailableMoves(availableMoves);
        evaluator.setAvailableMoves(availableMoves);
    }

    public Integer[] getDecision(Board boardGame) {
        Integer[] finalMove = new Integer[]{-1, -1};
        Integer finalScore = 0;
        minimaxWorth mostWorthy = new minimaxWorth();
        if (boardGame.getCurrentPlayer() == Board.PLAYER.PLAYER1) {
            mostWorthy = maxDecision(boardGame, getAvailableMoves(), 0);
        } else {
            mostWorthy = minDecision(boardGame, getAvailableMoves(), 0);
        }
        return mostWorthy.getMove();
    }

    private minimaxWorth maxDecision(Board boardGame, ArrayList<Integer[]> moves, int depth) {
        moves = PlayerType.cloneMoves(moves);   //clone that set of moves so as to not edit the overall list of moves (allows branching without sharing one object of moves)
        minimaxWorth thisMove = new minimaxWorth();
        if (depth >= maxDepth) {
            thisMove.setScore(evaluator.evaluate(boardGame));
            return thisMove;
        } else {
            if (moves.size() == 0) {
                thisMove.setScore(evaluator.evaluate(boardGame));
                return thisMove;
            } else {
                int maxScore = Integer.MAX_VALUE;
                Integer[] bestMove = new Integer[2];
                //for(Integer[] currentMove: moves){
                for (int i = moves.size() - 1; i >= 0; i--) {
                    Integer[] currentMove = moves.get(i);
                    moves.remove(currentMove); //pop off list
                    Board newBoardGame = boardGame.getClone();  //get a full clone of the current board which you can perform operations on
                    newBoardGame.attemptMove(currentMove[0], currentMove[1]);  //attempt this move
                    minimaxWorth worth = maxDecision(newBoardGame, moves, depth + 1);
                    if (worth.getScore() < maxScore) {
                        maxScore = worth.getScore();
                        bestMove = currentMove;
                    }
                }                thisMove = new minimaxWorth();
                thisMove.setScore(maxScore);
                thisMove.setMove(bestMove);
                return thisMove;
            }
        }
    }

    private minimaxWorth minDecision(Board boardGame, ArrayList<Integer[]> moves, int depth) {
        moves = PlayerType.cloneMoves(moves);   //clone that set of moves so as to not edit the overall list of moves (allows branching without sharing one object of moves)
        minimaxWorth thisMove = new minimaxWorth();
        if (depth >= maxDepth) {
            thisMove.setScore(evaluator.evaluate(boardGame));
            return thisMove;
        } else {
            if (moves.size() == 0) {
                thisMove.setScore(evaluator.evaluate(boardGame));
                return thisMove;
            } else {
                int minScore = Integer.MIN_VALUE;
                Integer[] bestMove = new Integer[2];
                //for(Integer[] currentMove: moves){
                for (int i = moves.size() - 1; i >= 0; i--) {
                    Integer[] currentMove = moves.get(i);
                    Board newBoardGame = boardGame.getClone();
                    newBoardGame.attemptMove(currentMove[0], currentMove[1]);  //attempt this move
                    moves.remove(currentMove);
                    minimaxWorth worth = minDecision(newBoardGame, moves, depth + 1);
                    if (worth.getScore() > minScore) {
                        minScore = worth.getScore();
                        bestMove = currentMove;
                    }
                }
                thisMove.setScore(minScore);
                thisMove.setMove(bestMove);
                return thisMove;
            }
        }
    }
}
