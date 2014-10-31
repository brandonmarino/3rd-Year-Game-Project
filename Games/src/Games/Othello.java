package Games;

import Boards.Board;
import Boards.OthelloBoard;
import Moves.RandomMove;

/**
 * ************************************************************************************************************************************************************
 * Game Class Plays Othello by Implementing Methods from Move and Board Class
 * *************************************************************************************************************************************************************
 */
//setMoved(boolean)
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

    /**
     * Othello specific win check
     */
    protected void checkIfWon(){

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