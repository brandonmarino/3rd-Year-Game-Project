
public class Game {
	public static final int EMPTY = 0;
	public static final int CROSS = 1;
	public static final int NOUGHT = 2;
		 
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
		      while(boardGame.getCurrentState() == PLAYING)
		      {
		    	  state = true;
		    	  while(state)
		    	  {
		    		  row =  move.playerMoveRow();  
		    		  col =  move.playerMoveColumn();
		    	
		    		  if(boardGame.getMoveOnBoard(row,col)==0)
		    		  {
		    			  System.out.println(boardGame.getMoveOnBoard(row,col));
		    			  System.out.println(boardGame.getMoveOnBoard(row,col)==0);
		    			  boardGame.setMoveOnBoard(row, col, boardGame.getcurrentPlayer() ); 		  
		    			  //boardGame.printBoard();
		    			  //state = false;
		    			  
		    				  state= false;
		    		  }
		    	  	}
		    	  boardGame.updateGame(boardGame.getcurrentPlayer(), boardGame.getcurrentRow(), boardGame.getcurrentColumn()); // update currentState
		    	  	boardGame.printBoard();
		      
		    	  	if (boardGame.getCurrentState() == CROSS_WON) 
		    	  	{
			         System.out.println("'X' won!");
			      } 
			      else if (boardGame.getCurrentState() == NOUGHT_WON) 
			      {
			         System.out.println("'O' won!");
			      } 
			      else if (boardGame.getCurrentState() == DRAW) 
			      {
			         System.out.println("It's a Draw!");
			      }
			         
		    	  	if(boardGame.getcurrentPlayer() == CROSS)
	 		        	 boardGame.setcurrentPlayer(NOUGHT);
	 			      else
	 		        	 boardGame.setcurrentPlayer(CROSS);
			      
			 }  // repeat if not game-over
	        
	       // break;
	   
}  
	
}
