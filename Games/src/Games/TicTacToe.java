package Games;

import Boards.TicTacToeBoard;
import Moves.RandomMove;

/***************************************************************************************************************************************************************
 * 										Game Class Plays Tic Tac Toe by Implementing Methods from RandomMove and OthelloBoard Class
 ***************************************************************************************************************************************************************
 *
 * Milestone 1: Authored by Lina
 *
 * Milestone 2:
 * Adapting Author: Brandon Marino
 * -Made it a subclass of Games
 * -removed redundant code
 * -cleaned up some code
 */

public class TicTacToe extends Game{

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
    }

    public TicTacToe(){
        super(new TicTacToeBoard(), new RandomMove());
        //Create variables, and initialize them
        player1 = "Player X";
        player2 = "Player O";
        play();
    }
}