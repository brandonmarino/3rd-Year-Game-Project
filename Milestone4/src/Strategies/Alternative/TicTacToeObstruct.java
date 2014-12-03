package Strategies.Alternative;

import Boards.Board;
import Boards.TicTacToeBoard;
import common.Move;

/**
 * Created by Brandon on 11/20/14.
 */
public class TicTacToeObstruct extends ObstructPlayerType {
    public TicTacToeObstruct(ObstructPlayerType player){
        super(player.getBoard(),player.getNumber());
    }

    /**
     * block enemy from getting a, or multiple triplet(s)
     * @param move some move
     * @return the obstruction's rank
     */
    protected int rankObstruction(Move move){
        TicTacToeBoard board = ((TicTacToeBoard) getBoard().getClone() );

        //swap players
        if ( board.getCurrentPlayer() == Board.PLAYER.PLAYER1 )
            board.setcurrentPlayer(Board.PLAYER.PLAYER2);
        else if ( board.getCurrentPlayer() == Board.PLAYER.PLAYER2 )
            board.setcurrentPlayer(Board.PLAYER.PLAYER1);
        board.attemptMove(move);

        //find the rank of this obstruction
        int rank = 0;
        rank = rank + board.isDiagonal();
        if ( board.isVertical() )
            rank++;
        if ( board.isHorizontal() )
            rank++;
        return rank;
    }
}
