package Games;

import Boards.Board;
import Boards.OthelloBoard;
import Moves.RandomMove;

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
    }

    /**
     * Play a game of othello
     */
    public Othello(){
        super(new OthelloBoard(), new RandomMove());
        //Create variables, and initialize them
        player1 = "Black";
        player2 = "White";
        play();
    }

    /**
     * Take a turn
     * @return if a turn was successfully completed
     */
    protected boolean takeTurn(){
        boolean takeTurn = super.takeTurn();
        ((OthelloBoard)boardGame).setMoved(takeTurn);
        return takeTurn;
    }
}