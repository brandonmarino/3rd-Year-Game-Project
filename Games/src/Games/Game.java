package Games;

import Boards.Board;
import GUI.OthelloController;
import PlayerTypes.*;
import Strategies.Alternative.ObstructPlayerType;
import Strategies.Minimax.MinimaxPlayerType;
import common.Move;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Scanner;

/***************************************************************************************************************************************************************
 * 										Game Class Plays Tic Tac Toe by Implementing Methods from RandomPlayerType and OthelloBoard Class
 ***************************************************************************************************************************************************************
 *
 * Milestone 2: Brandon Marino: Collected general functions from the two current Games will adapt as needed in future iterations
 * By merging Tic Tac Toe and Othello, we were able to significantly decrease redundant code
 */
public abstract class Game extends Observable {
    // Fields that are constant across all possible games
    protected Board boardGame;
    protected PlayerType[] players = new PlayerType[2]; //each player has enough dimensions to warrant it's own object

    /**
     * Create some non-generic Game
     * @param boardGame the board of the non generic game
     */
    public Game(Board boardGame){
        this.boardGame = boardGame;
        getPlayerInfo();
        boardGame.printBoard(); //print initial board
    }

    /******************************************************************************************************************************************************************
     * 												Functions to Play a Generic Game
     * ****************************************************************************************************************************************************************/

    /**
     * Play a game
     */
    public void play(){
        // Continue Playing Until You're Done
        while (boardGame.getCurrentState() == Board.GAME_STATE.PLAYING) {
            boolean moveMade = false;
            //allow turns of different player types
            if (boardGame.getCurrentPlayer() == Board.PLAYER.PLAYER1)
                moveMade = takeTurn(players[0]);
            else if (boardGame.getCurrentPlayer() == Board.PLAYER.PLAYER2)
                moveMade = takeTurn(players[1]);
            update(moveMade);
            checkIfWon();
        }
    }
    /**
     * Prompt user for the player info, names and the player type be-it human or computer
     */
    protected void getPlayerInfo(){
        int choice;
        for(int playernum = 0; playernum < players.length; playernum++){
            System.out.println("\nPlayers types:\n1: Human\n2: Computer- Random\n3: Computer- MiniMax\n4: Computer- Obstruction");
            while(true){
                try{
                    Scanner user_input  = new Scanner(System.in);
                    System.out.println("Please choose a player type from the options for Player "+ (playernum+1) + " by number:");
                    choice = user_input.nextInt();
                    if (choice == 1){
                        System.out.println("Please enter Players "+(playernum+1)+"'s name:");
                        user_input = new Scanner(System.in);
                        if (this instanceof Othello){
                            players[playernum] = new OthelloController(user_input.next(), boardGame, playernum);
                        }
                        else players[playernum] = new HumanPlayerType(user_input.next());
                    }
                    else if (choice == 2)
                        players[playernum] = new RandomPlayerType(playernum+1);
                    else if ( choice == 3 )
                        players[playernum] = new MinimaxPlayerType(boardGame,playernum+1);  //change this to MinMaxMove!
                    else if ( choice == 4 )
                        players[playernum] = new ObstructPlayerType(boardGame,playernum+1);  //change this to MinMaxMove!
                    else
                        throw new InputMismatchException(); //This should be changed to a custom Exception!
                    break;
                }catch(InputMismatchException e){
                    System.out.println("Not a valid option, try again!");
                }
            }
        }
    }
    /**
     * Take a turn
     * @return if a turn was successfully completed
     */
    protected boolean takeTurn(PlayerType move){
        boolean state = true;
        boolean turnTaken = false;
        move.setAvailableMoves(boardGame.getPossibleMoves());       //give all available moves from board
        while(state){
            Move playerMove = move.getMove();
            if (playerMove == null){
                //All moves have been exhausted
                state = false;//Quit loop
            }else{
                //some moves were available
                if (boardGame.attemptMove(playerMove)){  //try to make the move
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
            setChanged();				//notify view(s)
	        notifyObservers(boardGame);
        }
    }

    /**
     * Check if the game has been won by either player
     */
    protected void checkIfWon(){
        //if the status has been changed from updateGame, check which player won, or is it a draw
        if (boardGame.getCurrentState() == Board.GAME_STATE.PLAYER1_WON)
            System.out.println( players[0].getName() + " won!");
        else if (boardGame.getCurrentState() == Board.GAME_STATE.PLAYER2_WON)
            System.out.println( players[1].getName() + " won!");
        else if (boardGame.getCurrentState() == Board.GAME_STATE.DRAW)
            System.out.println("It's a Draw!");

        //if no status update, change the player
        if(boardGame.getCurrentPlayer() == Board.PLAYER.PLAYER1)
            boardGame.setcurrentPlayer(Board.PLAYER.PLAYER2);
        else
            boardGame.setcurrentPlayer(Board.PLAYER.PLAYER1);
    }
    /** Clone the generic board
     *
     * @return a generic clone of the current board
     */
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    /**
     * Return the board
     * @return the board
     */
    public Board getBoard(){
        return boardGame;
    }
    /**
     * Return the board
     * @return the board
     */
    public PlayerType[] getPlayers(){
        return players;
    }
}
