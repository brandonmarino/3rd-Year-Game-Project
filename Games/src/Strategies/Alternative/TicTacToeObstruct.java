package Strategies.Alternative;

import Boards.Board;
import Boards.TicTacToeBoard;
import common.Move;

/**
 * Created by Brandon on 11/20/14.
 */
public class TicTacToeObstruct extends ObstructPlayerType {
    public TicTacToeObstruct(ObstructPlayerType player){
        super(player.getBoard(),player.getPlayerNum());
    }

    /**
     * block enemy from getting a, or multiple triplet(s)
     * @param move some move
     * @return the obstruction's rank
     */
    protected int rankObstruction(Move move){
        int rank = super.rankObstruction(move);
        TicTacToeBoard gameBoard = ((TicTacToeBoard) getBoard().getClone() );
        gameBoard.switchcurrentPlayer();

        //find the rank of this obstruction
        rank = rank + gameBoard.isDiagonal();
        if ( gameBoard.isVertical() )
            rank++;
        if ( gameBoard.isHorizontal() )
            rank++;
        return rank;
    }
}
