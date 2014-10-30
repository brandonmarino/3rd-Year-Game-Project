package Games;

import Boards.OthelloBoard;
import Moves.RandomMove;

/***************************************************************************************************************************************************************
 * 										Othello Class Plays Tic Tac Toe by Implementing Methods from RandomMove and OthelloBoard Class
 ***************************************************************************************************************************************************************

 ** Adapted from Othello source of TIC TAC TOE Authored by Lina
 * Milestone 1, Adapting Author: Brandon Marino
 *
 * Much of the code is similar however i've changed the method to use implicit enum values opposed to constants which were set to an interger value
 * Also changed the intended game to Othello, not tic tac toe
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
        setPlayers("Black", "White");
        play();
    }
}