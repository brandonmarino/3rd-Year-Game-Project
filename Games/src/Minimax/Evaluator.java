package Minimax;

/**
 * Created by Brandon on 11/7/2014.
 */
import Boards.Board;

import java.util.ArrayList;
import PlayerTypes.PlayerType;
public class Evaluator {
    ArrayList<Integer[]> availableMoves;

    public void setAvailableMoves(ArrayList<Integer[]> availableMoves) {
        this.availableMoves = PlayerType.cloneMoves( availableMoves );
    }
    public int evaluate(Board boardGame) {
        int score = 0;
        if (boardGame.getCurrentState() != Board.GAME_STATE.PLAYING) {
            // disks difference
            score = boardGame.getPlayer1Score() - boardGame.getPlayer2Score();
        }
        else {
            // mobility
            int darkMobility = PlayerType.cloneMoves( availableMoves ).size();
            int lightMobility = PlayerType.cloneMoves(availableMoves).size();
            int mobility = darkMobility - lightMobility;
            // stability
            int player1Stability = 0;
            int player2Stability = 0;
            for (int row = 0; row < boardGame.getDimensions(); row++) {
                for (int column = 0; column < boardGame.getDimensions(); column++) {
                    if (boardGame.attemptMove(row,column)){
                        if (boardGame.getCell(row,column) == Board.PLAYER.PLAYER1) {
                            player1Stability++;
                        }
                        else {
                            player2Stability++;
                        }
                    }
                }
            }
            int stability = player1Stability - player2Stability;
            // disks difference
            int disksDiff = boardGame.getPlayer1Score() - boardGame.getPlayer2Score();
            // total score
            score = (100 * mobility) + (10 * stability) + (1 * disksDiff);
        }
        return score;
    }
}
