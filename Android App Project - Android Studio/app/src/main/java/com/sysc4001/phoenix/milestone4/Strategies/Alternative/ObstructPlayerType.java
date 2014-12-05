package com.sysc4001.phoenix.milestone4.Strategies.Alternative;

import com.sysc4001.phoenix.milestone4.Boards.Board;
import com.sysc4001.phoenix.milestone4.PlayerTypes.PlayerType;
import com.sysc4001.phoenix.milestone4.Strategies.Minimax.MinimaxPlayerType;
import com.sysc4001.phoenix.milestone4.common.Move;
import java.util.ArrayList;

/**
 * Created by Brandon on 11/20/14.
 */
public class ObstructPlayerType extends PlayerType {
    /**
     * Will define an obstruction super-class
     * @param gameBoard needed for obstruction testing
     *
     */
    public ObstructPlayerType(Board gameBoard, int playernum){
        super(gameBoard,playernum);
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
            PlayerType Minimax = new MinimaxPlayerType(boardGame,0);
            Minimax.setAvailableMoves(allMoves);
            finalMove = Minimax.getMove();
        }else   finalMove = popMove(bestObstructionIndex);  //return the item in the set that performs the best possible obstruction
        return finalMove;
    }

    /**
     * Will check if the enemy can do something significantly bad to the player, if so, obstruct it
     * @param move some move to rank
     * @return some rank
     */
    protected int rankObstruction(Move move){
        return 0;
    }

}
