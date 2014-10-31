package Games;

import Boards.Board;
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
    protected void checkIfWon(){
        String player1 = getPlayer1();
        String player2 = getPlayer2();

        //if no status update, change the player
        if(boardGame.getcurrentPlayer() == Board.PLAYER.PLAYER1)
            boardGame.setcurrentPlayer(Board.PLAYER.PLAYER2);
        else
            boardGame.setcurrentPlayer(Board.PLAYER.PLAYER1);

        //if the status has been changed from updateGame, check which player won, or is it a draw
        if(boardGame.getCurrentState() != Board.GAME_STATE.PLAYING){
            if(boardGame.getcurrentPlayer() == Board.PLAYER.PLAYER2){
                if (boardGame.getCurrentState() == Board.GAME_STATE.PLAYER1_WON)
                    System.out.println( player1 + " won!");
                else if (boardGame.getCurrentState() == Board.GAME_STATE.PLAYER2_WON)
                    System.out.println( player2 + " won!");
                else if (boardGame.getCurrentState() == Board.GAME_STATE.DRAW)
                    System.out.println("It's a Draw!");
            }else{
                update(takeTurn());
                checkIfWon(); //call self again to see if the tables turned after this one move
            }
        }
    }
}