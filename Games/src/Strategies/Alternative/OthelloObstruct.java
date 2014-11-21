package Strategies.Alternative;

import Boards.Board;
import Boards.OthelloBoard;
import common.Move;

import java.util.ArrayList;

/**
 * Created by Brandon on 11/20/14.
 */
public class OthelloObstruct extends ObstructPlayerType {
    public OthelloObstruct(ObstructPlayerType player){
        super(player.getBoard(),player.getPlayerNum());
    }

    /**
     * Will check if the enemy can do something significantly bad to the player, if so, obstruct it
     * @param move
     * @return
     */
    protected int rankObstruction(Move move){
        int rank = super.rankObstruction(move);
        OthelloBoard gameBoard = (OthelloBoard)getBoard().getClone();
        gameBoard.switchcurrentPlayer();

        //find the rank of this obstruction
        ArrayList<Move> adjEnemies = gameBoard.findadjacentEnemies(move);
        for(Move enemy: adjEnemies){
            Move slope = new Move (enemy.getRow() - move.getRow(), enemy.getColumn() - move.getColumn());
            rank += gameBoard.canFlank(move, slope);
        }
        return rank;
    }

}
