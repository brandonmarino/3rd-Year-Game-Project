package Strategies.Alternative;

import Boards.Board;
import Boards.CheckersBoard;
import PlayerTypes.PlayerType;
import common.Move;

 /** Milestone 3, Author: Brandon Marino
 * will control it's own ranking system
 */
public class CheckersObstruct extends ObstructPlayerType {
    public CheckersObstruct(ObstructPlayerType player){
        super(player.getBoard(),player.getPlayerNum());
        setName("Computer- Obstruction "+ player.getPlayerNum());
    }

    /**
     * Rank how well this move will block the opponent from king-ing, and jumping, this code was already taken care by getStateWorth back when working with minimax
     * @param move some move
     * @return it's rank
     */
    protected int rankObstruction(Move move){
        int rank = super.rankObstruction(move);
        CheckersBoard gameBoard = (CheckersBoard)getBoard().getClone();
        gameBoard.switchcurrentPlayer();

        //find the rank of this obstruction
        gameBoard.attemptMove(move);
        rank += gameBoard.getStateWorth();
        return rank;

    }
}
