import java.util.Random;
public class Move extends Board{
	public static final int ROWS = 3, COLUMNS = 3; 
	 /** Player with the "theMove" makes one move, with input validation.
    Update global variables "currentRow" and "currentCol". */
public int playerMoveColumn() 
{
   boolean validInput = false;  // for input validation
   int col=0;
   while(!validInput)
   { //generate Random Moves
	   
      Random randCol = new Random(); 
      col = randCol.nextInt(3);
      if (col >= 0 && col < COLUMNS ) 
      {
         validInput = true;  // input okay, exit loop
      }
      System.out.println("The input is outside the range. Please pick from 1 to 3");
    }
    return col;
}

public int playerMoveRow() {
	   boolean validInput = false;  // for input validation
	   int row = 0;
	   while(!validInput)
	   {
	         //generate Random Moves
	     Random randRow = new Random();  
	      row = randRow.nextInt(3);
	      if (row >= 0 && row < ROWS) 
	    	  validInput = true;
	     
	         System.out.println("The input is outside the range. Please pick from 1 to 3");
	      }
	   return row;
	   }  
	   
	}

