package PlayerTypes;

import Minimax.*;

import java.util.ArrayList;

import Boards.*;
import Games.*;


public class AIPlayerType extends PlayerType {

    private Integer alpha = Integer.MIN_VALUE;
    private Integer beta = Integer.MAX_VALUE;

    private int maxDepth;
    private Board gameBoard;

    private AlphaBetaMiniMax ab;

    public AIPlayerType(Board gameBoard) {
        ab = new AlphaBetaMiniMax();
        this.gameBoard = gameBoard;
        maxDepth = 3;
    }

    @Override
    public Integer[] getMove() {
        return null;
    }

    public ArrayList<Board> getSuccessorStates(Board board) {
        ArrayList<Board> result = new ArrayList<Board>();

        for (Integer[] move : getAvailableMoves()) {
            Board newState = board.getClone();
            if (newState.attemptMove(move[0],move[1]))
                result.add(newState);
        }
        return result;
    }

    public int minMaxValue(int depth, int limitVal) {
        ArrayList<Game> successorList;
        int minimumValueOfSuccessor, maximumValueOfSuccessor;
        if (depth >= maxDepth) {
            return evaluate();
        } else {
            successorList = getSuccessorStates(state);
            for (Game possibleSuccessor : successorList) {
                if (limitVal == beta) {
                    Game successor = possibleSuccessor;
                    limitVal = alpha;
                    if (ab != null)
                        minimumValueOfSuccessor = minMaxValue(successor, ab.MakeCopy(), depth++, limitVal);
                    else
                        minimumValueOfSuccessor = minMaxValue(successor, null, depth++, limitVal);

                    if (minimumValueOfSuccessor > limitVal) {
                        limitVal = minimumValueOfSuccessor;
                        state.setNextMove(successor.getNextMove());
                    }

                    if (ab != null) {
                        // use alpha-beta pruning
                        if (limitVal >= ab.getBeta()) {
                            return limitVal;
                        }
                        ab.setAlpha(Math.max(ab.getAlpha(), limitVal));
                    }
                }
                if (limitVal == alpha) {
                    Game successor = possibleSuccessor;
                    limitVal = beta;
                    if (ab != null)
                        maximumValueOfSuccessor = minMaxValue(successor, ab.MakeCopy(), depth++, limitVal);
                    else
                        maximumValueOfSuccessor = minMaxValue(successor, null, depth++, limitVal);
                    if (maximumValueOfSuccessor < limitVal) {
                        limitVal = maximumValueOfSuccessor;
                        state.setNextMove(successor.getNextMove());
                    }
                    if (ab != null) {
                        // use alpha-beta pruning
                        if (limitVal <= ab.getBeta()) {
                            return limitVal;
                        }
                        ab.setBeta(Math.min(ab.getBeta(), limitVal));
                    }
                }
            }
            return limitVal;
        }
    }

    public int evaluate() {
        int score = 0;
        int BoardDimension = gameBoard.getDimensions();
        OthelloBoard boardClone = (OthelloBoard) gameBoard.getClone();
        boardClone.updateGame();
        if (boardClone.getCurrentState() == Board.GAME_STATE.PLAYER1_WON || boardClone.getCurrentState() == Board.GAME_STATE.PLAYER2_WON) {
            // disks difference
            score = gameBoard.getPlayer1Score() - gameBoard.getPlayer2Score();
        } else {
            // mobility
            int darkMobility = getAvailableMoves().size();
            int lightMobility = getAvailableMoves().size();
            int mobility = darkMobility - lightMobility;
            // stability
            int darkStability = 0;
            int lightStability = 0;
            for (int i = 0; i < BoardDimension; i++) {
                for (int j = 0; j < BoardDimension; j++) {
                    if (game.getBoard().getCell(i, j) != Board.PLAYER.EMPTY) {

                        if (((OthelloBoard) board).)

                            if (game.getBoard().getCell(i, j) == PLAYER.PLAYER1) {
                                darkStability++;
                            } else {
                                lightStability++;
                            }
                    }
                }
            }
        }
        int stability = darkStability - lightStability;
        // disks difference
        int disksDiff = game.getBoard().getBlackDiscs() - game.getBoard().getWhiteDiscs();
        // total score
        score = (100 * mobility) + (10 * stability) + (1 * disksDiff);
        return score;
    }
}
