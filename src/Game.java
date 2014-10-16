/***************************************************************************************************************************************************************
 * 										Game Class Plays Tic Tac Toe by Implementing Methods from Move and Board Class
 ***************************************************************************************************************************************************************/
public class Game 
{
	   public static void main(String[] args) 
	   {
		   /*Create variables, and initialize them*/
		  int row =0;
		  int col=0;
		  boolean state = true;
		  Board boardGame = new Board();
		  Move move = new Move();
		  
		  /* Continue Playing Until You're Done*/
		  while(boardGame.getCurrentState() == boardGame.getPLAYING())
		  {
			  state = true;
			  while(state)
			  {
				  row =  move.playerMoveRow();  //get a random index for row
				  col =  move.playerMoveColumn(); //get a random index for column
		    	
				  if(boardGame.getMoveOnBoard(row,col)==0)//Check if the random index generated has not been used
				  {
					  boardGame.setMoveOnBoard(row, col, boardGame.getcurrentPlayer() ); //Mark the random index obtained with current player's mark		  
					  state= false;//Quit loop 
				  }
			  }
			  //Check if the last game has been played and what is the new status (win, draw, or not done)
			  boardGame.updateGame(boardGame.getcurrentPlayer(), boardGame.getcurrentRow(), boardGame.getcurrentColumn()); 
			  boardGame.printBoard();
		      
			  //if the status has been changed from updateGame, check which player won, or is it a draw
			  if (boardGame.getCurrentState() == boardGame.getCROSSWon()) 
				  System.out.println("'X' won!");
			  
			  else if (boardGame.getCurrentState() == boardGame.getNOUGHTWon()) 
			      System.out.println("'O' won!");
			    
			  else if (boardGame.getCurrentState() == boardGame.getDRAW()) 
			      System.out.println("It's a Draw!");
			  
			  //if no status update, change the player       
			  if(boardGame.getcurrentPlayer() == boardGame.getCROSS())
		    	  boardGame.setcurrentPlayer(boardGame.getNOUGHT());
			  else
	 			  boardGame.setcurrentPlayer(boardGame.getCROSS());		      
		  }     
	   }  
}