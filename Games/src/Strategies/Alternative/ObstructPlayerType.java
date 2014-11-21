package Strategies.Alternative;

import Boards.Board;
import PlayerTypes.PlayerType;
import Strategies.Minimax.MinimaxPlayerType;
import common.Move;
import java.util.ArrayList;

/***********************************************************************************************************************************************************
 * 							ObstructionPlayerType Class creates Indexes for Row and Column to be Used in Board Classes
 ***********************************************************************************************************************************************************

 * Milestone 3, Author: Brandon Marino
 * This is our alternative AI for the project
 *
 * Essentially it attempts to the other player from making a good move whenever possible.  If it can't block a move, it fallss back on minimax
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
     *
     * I had to make a design choice to not making this function abstracted, not my preference
     * @param move some move
     *  @return its rank
     */
    protected int rankObstruction(Move move){return 0;}

    /**
     * Get board contained by this object
     * @return get the internal board
     */
    protected Board getBoard(){
        return gameBoard;
    }
    /**
     * Get board contained by this object
     * @return the current player's number
     */
    protected int getPlayerNum(){
        return playernum;
    }

}
