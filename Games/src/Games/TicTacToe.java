package Games;

import Boards.TicTacToeBoard;
import Moves.RandomMove;

/**
 * ************************************************************************************************************************************************************
 * Game Class Plays Tic Tac Toe by Implementing Methods from Move and Board Class
 * *************************************************************************************************************************************************************
 */

public class TicTacToe extends Game{

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
    }

    public TicTacToe(){
        super(new TicTacToeBoard(), new RandomMove());
        //Create variables, and initialize them
        setPlayers("Player X", "Player O");
        play();
    }
}