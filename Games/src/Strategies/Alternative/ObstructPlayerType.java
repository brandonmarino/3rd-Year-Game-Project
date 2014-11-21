package Strategies.Alternative;

import Boards.Board;
import Boards.TicTacToeBoard;
import PlayerTypes.PlayerType;
import Strategies.Minimax.MinimaxPlayerType;
import common.Move;
import PlayerTypes.RandomPlayerType;
import java.util.ArrayList;

/**
 * Created by Brandon on 11/20/14.
 */
public class ObstructPlayerType extends PlayerType {
    private Board gameBoard;
    private int playernum;

    /**
     * Will define an obstruction super-class
     * @param gameBoard needed for obstruction testing
     *
     */
    public ObstructPlayerType(Board gameBoard, int playernum){
        this.gameBoard = gameBoard;
        this.playernum = playernum;
        setName("Computer- Obstruction "+ playernum);
    }
    /**
     * Return the best possible obstruction, if there is one
     * @return some move
     */
    public Move getMove(){
        ArrayList<Move> allMoves = getAvailableMoves();
        Move finalMove;
        int bestObstructionIndex = -1;
        int bestObstructionScore = 0;
        int i = 0;
        for(Move currentMove: allMoves) {
            int currentScore = rankObstruction(currentMove);
            if ( currentScore > bestObstructionScore ) {    //if 0, don't pick this move, it doesnt obstruct anything
                bestObstructionIndex = i;
                bestObstructionScore = currentScore;
            }
            i++;
        }
        if(bestObstructionScore == 0){
            // no obstructions found, just return a random of the set
            PlayerType minimax = new MinimaxPlayerType(getBoard(),0);
            minimax.setAvailableMoves(allMoves);
            finalMove = minimax.getMove();
        }else   finalMove = popMove(bestObstructionIndex);  //return the item in the set that performs the best possible obstruction
        return finalMove;
    }

    /**
     * Will check if the enemy can do something significantly bad to the player, if so, obstruct it
     * @param move
     * @return
     */
    protected int rankObstruction(Move move){
        return 0;
    }

    /**
     * Get board contained by this object
     * @return
     */
    protected Board getBoard(){
        return gameBoard;
    }
    /**
     * Get board contained by this object
     * @return
     */
    protected int getPlayerNum(){
        return playernum;
    }

}
