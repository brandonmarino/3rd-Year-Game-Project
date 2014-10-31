package Games;

import Boards.OthelloBoard;
import Moves.Move;
/***************************************************************************************************************************************************************
 * 										Game Class Plays Tic Tac Toe by Implementing Methods from RandomMove and OthelloBoard Class
 ***************************************************************************************************************************************************************
 *
 * Milestone 1: A Game of Othello, adapted from code for tic tac toe provided by Lina
 *
 * Milestone 2:
 * Adapting Author: Brandon Marino
 * -Made it a subclass of Games
 * -removed redundant code
 * -fixed glitches
 * -cleaned up some code
 */
public class Othello extends Game
{
    /**
     * Main run function
     */
    public static void main(String[] args) {
        Othello game = new Othello();
        game.play();
    }

    /**
     * Play a game of othello
     */
    public Othello(){
        super(new OthelloBoard());
    }

    /**
     * Take a turn
     * @return if a turn was successfully completed
     */
    protected boolean takeTurn(Move move){
        boolean takeTurn = super.takeTurn(move);
        ((OthelloBoard)boardGame).setMoved(takeTurn);
        return takeTurn;
    }
}