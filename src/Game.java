
public class Game {
	public static final int EMPTY = 0;
	public static final int CROSS = 'X';
	public static final int NOUGHT = 'O';
		 
		   // Name-constants to represent the various states of the game
	public static final int PLAYING = 0;
	public static final int DRAW = 1;
	public static final int CROSS_WON = 2;
	public static final int NOUGHT_WON = 3;
		 
		   
	public static final int ROWS = 3, COLUMNS = 3; 
	public static int[][] board = new int[ROWS][COLUMNS]; 
	public static int currentState;  
	public static int currentPlayer; 
	public static int currentRow, currentColumn;

	 /** The entry main method (the program starts here) */
	   public static void main(String[] args) {
		  Board boardGame = new Board();
		  Move move = new Move();
			int row =0;
			int col=0;
			boolean state = true;
		// Play the game once
		      while(boardGame.getCurrentState() == PLAYING){
		       while(state)
		    	{//System.out.println("the row is" + row);
		    	   row =  move.playerMoveRow(); // update currentRow and currentCol
		    	  // System.out.println("the row is" + row);
		    	  // System.out.println("the columns is" + col);
		    	   col =  move.playerMoveColumn();
		    	   //System.out.println("the columns is" + col);
		    	   if(boardGame.getMoveOnBoard(row,col)==0)
		    	   {
		    		  boardGame.setMoveOnBoard(row, col, boardGame.getcurrentPlayer() );
		    		  //state = false;
		    		  if(boardGame.isDraw())
		    			  state = false;
		    		  //System.out.println("im in the if statement");
		    	   }
		    	   //System.out.println("im outside the if statement");
		        }
		       


	        boardGame.printBoard();
	       // break;
	   }
	   
	
}
}