package Games;

import Boards.Board;
import PlayerTypes.*;
import Strategies.Alternative.ObstructPlayerType;
import Strategies.Minimax.MinimaxPlayerType;
import common.GameInputException;
import common.GameTerminatedException;
import common.Move;

import java.util.Observable;
import java.util.Scanner;

import java.util.List;

/***************************************************************************************************************************************************************
 * 										Game Class Plays Tic Tac Toe by Implementing Methods from RandomPlayerType and OthelloBoard Class
 ***************************************************************************************************************************************************************
 *
 * Milestone 2: Brandon Marino: Collected general functions from the two current Games will adapt as needed in future iterations
 * By merging Tic Tac Toe and Othello, we were able to significantly decrease redundant code
 */
public abstract class Game extends Observable implements java.io.Serializable {
    // Fields that are constant across all possible games
    protected Board boardGame;
    protected PlayerType[] players = new PlayerType[2]; //each player has enough dimensions to warrant it's own object
    public boolean isGUI = false;
    
    /**
     * Create some non-generic Game
     * @param boardGame the board of the non generic game
     */
    public Game(Board boardGame){
        this.boardGame = boardGame;
        displayInfo();
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
            try {
                boolean moveMade = false;
                //allow turns of different player types
                if (boardGame.getCurrentPlayer() == Board.PLAYER.PLAYER1)
                    moveMade = takeTurn(players[0]);
                else if (boardGame.getCurrentPlayer() == Board.PLAYER.PLAYER2)
                    moveMade = takeTurn(players[1]);
                update(moveMade);
                checkIfWon();
            }catch(common.GameTerminatedException g){
                System.out.println("Game is terminated.");
                System.exit(0);
            }
            
        }
    }
    /**
     * Prompt user for the player info, names and the player type be-it human or computer
     */
    protected void getPlayerInfo(){
        int choice;
      
        for(int playernum = 0; playernum < players.length; playernum++){
            System.out.println("\nPlayers types:\n1: Human\n2: Computer- Random\n3: Computer- MiniMax\n4: Computer- Obstruction");
            for(int i = 0; i < 100; i++){
                try{
                    Scanner user_input  = new Scanner(System.in);
                    System.out.println("Please choose a player type from the options for Player "+ (playernum+1) + " by number:");
                    choice = user_input.nextInt();
                    if (choice == 1)
                        players[playernum] = new HumanPlayerType(boardGame,playernum+1);
                    else if (choice == 2)
                        players[playernum] = new RandomPlayerType(boardGame,playernum+1);
                    else if ( choice == 3 )
                        players[playernum] = new MinimaxPlayerType(boardGame,playernum+1);  //change this to MinMaxMove!
                    else if ( choice == 4 )
                        players[playernum] = new ObstructPlayerType(boardGame,playernum+1);  //change this to MinMaxMove!
                    else
                        throw new GameInputException("Sorry, that is not an option!"); //This should be changed to a custom Exception!
                    break;
                }catch(GameInputException e){
                    System.out.println(e.getMessage());
                }
            }
            if(players[0] instanceof HumanPlayerType && players[1] instanceof HumanPlayerType) {
                ((HumanPlayerType)players[0]).canUndo(false);
                ((HumanPlayerType)players[1]).canUndo(false);
            }
        }
    }
    /**
     * Take a turn
     * @return if a turn was successfully completed
     */
    protected boolean takeTurn(PlayerType move) throws common.GameTerminatedException{
        boolean state = true;
        boolean turnTaken = false;
        move.setAvailableMoves(boardGame.getPossibleMoves());       //give all available moves from board
        while(state){
            Move playerMove = move.getMove(this);
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
         
    private boolean deserializeFromFile(Scanner userInput){
        System.out.println("Please enter the file path:");
        String path = userInput.next();
        try{
            Game g = (Game) common.Util.deserializeFromFile(path);
            isGUI = g.isGUI;
            boardGame = g.boardGame;
            players = g.players;
            
            boardGame.printBoard();
            for(int i = 0; i<players.length; i++)
                System.out.println(players[i].getName());
            
        }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("You have given the wrong path.");
            return false;
        }
        return true;
    }
    
    private void displayInfo(){
        try{
        for(int i = 0; i<100; i++){
            boolean success = true;
            Scanner sc = new Scanner(System.in);
            System.out.println("\n1: New game.\n2: Load from file.");
            System.out.println("Please enter your choice:");
            int choice = sc.nextInt();
            if(choice==1){
                getPlayerInfo();
            }else if(choice==2){
                success = deserializeFromFile(sc);
            }else {
                throw new Exception("Invalid choice.");
            }
            if(success) break;
        }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
}
