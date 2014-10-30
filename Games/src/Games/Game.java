package Games;

import Boards.Board;
import Moves.Move;

/**
 * Created by Brandon on 10/29/14.
 */
public class Game {


    // Fields that are constant across all possible games
    private Board boardGame;
    private Move move;
    private String player1;
    private String player2;

    public Game(Board boardGame, Move move){
        this.boardGame = boardGame;
        this.move = move;
    }

    /******************************************************************************************************************************************************************
     * 							Functions to get fields
     * ****************************************************************************************************************************************************************/

    /**
     * Return to sub-class the current in use board
     * @return this current in play board
     */
    protected Board getBoard(){
        return boardGame;
    }

    /**
     * Set the player 1's name
     */
    protected String getPlayer1(){
        return player1;
    }
    /**
     * Set the player 2's name
     */
    protected String getPlayer2(){
        return player2;
    }

    /******************************************************************************************************************************************************************
     * 							Functions to set fields
     * ****************************************************************************************************************************************************************/


    /**
     * Set the player names
     */
    protected void setPlayers(String player1, String player2){
        this.player1 = player1;
        this.player2 = player2;
    }

    /******************************************************************************************************************************************************************
     * 												Functions to Play a Generic Game
     * ****************************************************************************************************************************************************************/

    protected void play(){
        // Continue Playing Until You're Done
        while (boardGame.getCurrentState() == Board.GAME_STATE.PLAYING) {
            move.setAvailableMoves(boardGame.getPossibleMoves());       //give all available moves from board
            update(takeTurn());
            checkIfWon();
        }
    }
    private Boolean takeTurn(){
        Boolean state = true;
        Boolean turnTaken = false;
        while(state){
            Integer[] playerMove = move.getMove();
            if (playerMove == null){       //All moves have been exhausted
                state = false;//Quit loop
            }else{      //some moves were available
                if (boardGame.attemptMove(playerMove[0], playerMove[1])){  //try to make the move
                    turnTaken = true;
                    state = false;//Quit loop
                }
            }
        }
        return turnTaken;
    }
    private void update(boolean turnTaken){
        //Check if the last game has been played and what is the new status (win, draw, or not done)
        boardGame.updateGame();
        //if a move was made
        if (turnTaken)
            boardGame.printBoard();
    }
    private void checkIfWon(){
        String player1 = getPlayer1();
        String player2 = getPlayer2();
        //if the status has been changed from updateGame, check which player won, or is it a draw
        if (boardGame.getCurrentState() == Board.GAME_STATE.PLAYER1_WON)
            System.out.println( player1 + " won!");
        else if (boardGame.getCurrentState() == Board.GAME_STATE.PLAYER2_WON)
            System.out.println( player2 + " won!");
        else if (boardGame.getCurrentState() == Board.GAME_STATE.DRAW)
            System.out.println("It's a Draw!");

        //if no status update, change the player
        if(boardGame.getcurrentPlayer() == Board.PLAYER.PLAYER1)
            boardGame.setcurrentPlayer(Board.PLAYER.PLAYER2);
        else
            boardGame.setcurrentPlayer(Board.PLAYER.PLAYER1);
    }
}
