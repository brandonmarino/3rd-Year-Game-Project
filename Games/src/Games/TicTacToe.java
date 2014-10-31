package Games;

import Boards.TicTacToeBoard;

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
        game.play();
    }

    /**
     * Play a game of Tic Tac Toe
     */
    public TicTacToe(){
        super(new TicTacToeBoard());
    }
}