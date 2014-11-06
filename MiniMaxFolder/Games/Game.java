package Games;



import Boards.Board;
import Moves.Move;

import Moves.RandomMove;
import Moves.PlayerMove;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Created by Brandon on 10/29/14.
 */
public abstract class Game {

    // Fields that are constant across all possible games
	protected Board boardGame;
    protected String[] names = new String[]{"Player 1", "Player 2"};
    protected Move[] moves = new Move[2];
 
    private Move currentMove, previousMove, nextMove;
    private int depth;

    /**
     * Create some non-generic Game
     * @param boardGame the board of the non generic game
     */
    public Game(Board boardGame){
        this.boardGame = boardGame;
        getPlayerInfo();
        boardGame.printBoard(); //print initial board
    }
    
    
    
    public Game(Game g){
        this.boardGame = g.getBoardGame().clone();
        this.currentMove = g.currentMove;
        this.boardGame.setcurrentPlayer(g.getBoardGame().getcurrentPlayer());
        this.depth = g.getDepth();
        
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

   
    public Board getBoardGame() {
		return boardGame;
	}

    /**
     * Will return the currentMove class that is currently being implemented
     * @return some currentMove class
     */
    public Move getCurrentMove() {
		return currentMove;
	}

    public Move getPreviousMove() {
		return previousMove;
	}

	

	public Move getNextMove() {
		return nextMove;
	}

	

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	

	public String[] getNames() {
		return names;
	}
	
	public String getName(int index) {
		return names[index];
	}

	public void setName(int index, String param) {
		 names[index] = param;
	}


	public void setNames(String[] names) {
		this.names = names;
	}



	public Move[] getMoves() {
		return moves;
	}
	
	public Move getMove(int index) {
		return moves[index];
	}

	public void setMove(int index, Move param) {
		moves[index] = param;
	}


	public void setMoves(Move[] moves) {
		this.moves = moves;
	}



	public void setCurrentMove(Move currentMove) {
		this.currentMove = currentMove;
	}



	public void setPreviousMove(Move previousMove) {
		this.previousMove = previousMove;
	}



	/******************************************************************************************************************************************************************
     * 							Functions to set fields
     * ****************************************************************************************************************************************************************/


    
	public void setBoardGame(Board boardGame) {
		this.boardGame = boardGame;
	}
	
	public void setPreviousMove(RandomMove previousMove) {
		this.previousMove = previousMove;
	}
	
	public void setNextMove(Move nextMove) {
		this.nextMove = nextMove;
	}
	
	public void setMove(Move move) {
		previousMove = this.currentMove;
		this.currentMove = move;
	}

	

    /******************************************************************************************************************************************************************
     * 												Functions to Play a Generic Game
     * ****************************************************************************************************************************************************************/

    /**
     * Play a game
     */
    protected void play(){
        // Continue Playing Until You're Done
        while (boardGame.getCurrentState() == Board.GAME_STATE.PLAYING) {
            boolean moveMade = false;
            //allow turns of different player types
            if (boardGame.getcurrentPlayer() == Board.PLAYER.PLAYER1)
                moveMade = takeTurn(moves[0]);
            else if (boardGame.getcurrentPlayer() == Board.PLAYER.PLAYER2)
                moveMade = takeTurn(moves[1]);

            update(moveMade);
            checkIfWon();
        }
    }
    

    
    /**
     * Prompt user for the player info, names and the player type be-it human or computer
     */
    protected void getPlayerInfo(){
        int choice;
        for(int playernum = 0; playernum < moves.length; playernum++){
            System.out.println("\nPlayer types:\n1: Human\n2: Computer- Random\n3: Computer- MiniMax");
            while(true){
                try{
                    Scanner user_input  = new Scanner(System.in);
                    System.out.println("Please choose a player type from the options for "+ names[playernum] + " by number:");
                    choice = user_input.nextInt();
                    if (choice == 1){
                        System.out.println("Please enter Player "+(playernum+1)+"'s name:");
                        user_input = new Scanner(System.in);
                        names[playernum] = user_input.nextLine();
                        moves[playernum] = new PlayerMove(names[playernum]);
                    }
                    else if (choice == 2)
                    
                        moves[playernum] = new RandomMove();
                    
                    else if (choice == 3)
                    {
                    	
                        moves[playernum] = new PlayerMove("Alpha Beta MiniMax");  //change this to MinMaxMove!
                    }
                    else
                        throw new InputMismatchException(); //Alert user that THAT IS NOT VALID
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
    protected boolean takeTurn(Move move){
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
        }
    }

    /**
     * Check if the game has been won by either player
     */
    protected void checkIfWon(){
        //if the status has been changed from updateGame, check which player won, or is it a draw
        if (boardGame.getCurrentState() == Board.GAME_STATE.PLAYER1_WON)
            System.out.println( names[0] + " won!");
        else if (boardGame.getCurrentState() == Board.GAME_STATE.PLAYER2_WON)
            System.out.println( names[1] + " won!");
        else if (boardGame.getCurrentState() == Board.GAME_STATE.DRAW)
            System.out.println("It's a Draw!");

        //if no status update, change the player
        if(boardGame.getcurrentPlayer() == Board.PLAYER.PLAYER1)
            boardGame.setcurrentPlayer(Board.PLAYER.PLAYER2);
        else
            boardGame.setcurrentPlayer(Board.PLAYER.PLAYER1);
    }
}
    
    /******************************************************************************************************************************
     * 
     * *****************************************************************************************************************************/
    


	
	
     
