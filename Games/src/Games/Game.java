package Games;

import Boards.Board;
import Moves.Move;

/***************************************************************************************************************************************************************
 * 										Game Class Plays Tic Tac Toe by Implementing Methods from RandomMove and OthelloBoard Class
 ***************************************************************************************************************************************************************

 **
 **
 * Milestone 1: Authored by Lina
 * Milestone 2: Brandon Marino: Collected general functions from the two current Games will adapt as needed in future iterations
 * By merging Tic Tac Toe and Othello, we were able to significantly decrease redundant code
 */
public abstract class Game {

    // Fields that are constant across all possible games
    protected Board boardGame;
    private Move move;
    protected String player1 = "Player 1";
    protected String player2 = "Player 2";

    public Game(Board boardGame, Move move){
        this.boardGame = boardGame;
        this.move = move;
    }

    /******************************************************************************************************************************************************************
     * 												Functions to Play a Generic Game
     * ****************************************************************************************************************************************************************/

    protected void play(){
        // Continue Playing Until You're Done
        while (boardGame.getCurrentState() == Board.GAME_STATE.PLAYING) {
            update(takeTurn());
            checkIfWon();
        }
    }

    /**
     * Take a turn
     * @return if a turn was successfully completed
     */
    protected boolean takeTurn(){
        boolean state = true;
        boolean turnTaken = false;
        move.setAvailableMoves(boardGame.getPossibleMoves());       //give all available moves from board
        while(state){
            Integer[] playerMove = move.getMove();
            if (playerMove == null){
                //All moves have been exhausted
                state = false;//Quit loop
            }else{
                //some moves were available
                if (boardGame.attemptMove(playerMove[0], playerMove[1])){  //try to make the move
                    turnTaken = true;
                    state = false;//Quit loop
                }
            }
        }
        return turnTaken;
    }

    /**
     * Update if a winner has been found, if a turn was taken by the current player print out a board
     * @param turnTaken if a turn was successfully completed by the current player
     */
    protected void update(boolean turnTaken){
        //Check if the last game has been played and what is the new status (win, draw, or not done)
        boardGame.updateGame();
        //if a move was made print the resulting board
        if (turnTaken){
            boardGame.printBoard();
        }else{
            if (boardGame.getcurrentPlayer() == Board.PLAYER.PLAYER1)
                System.out.println(player1 + " couldn't make a move!\n");
            else
                System.out.println(player2 + " couldn't make a move!\n");
        }
    }
    protected void checkIfWon(){
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
