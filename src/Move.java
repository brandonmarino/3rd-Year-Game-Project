import java.util.Random;
public class Move extends Board{
	public static final int ROWS = 3, COLUMNS = 3; 
	private int col, row;
	 /** Player with the "theMove" makes one move, with input validation.
    Update global variables "currentRow" and "currentCol". */
	public Move()
	{
		col =0;
		row = 0;
		
	}
public int playerMoveColumn() 
{
   boolean validInput = false;  // for input validation
   Random randCol;
   while(!validInput)
   { //generate Random Moves
	   
      randCol = new Random(); 
      col = randCol.nextInt(3);
      if (col >= 0 && col < COLUMNS ) 
      {
         validInput = true;  // input okay, exit loop
      }
      else
      System.out.println("The input is outside the range. Please pick from 1 to 3");
    }
    return col;
}

public int playerMoveRow() {
	   boolean validInput = false;  // for input validation
	   Random randRow;
	   while(!validInput)
	   {
	         //generate Random Moves
		  randRow = new Random();  
	      row = randRow.nextInt(3);
	      if (row >= 0 && row < ROWS) 
	      {
	    	  validInput = true;
	      }
	      else
	         System.out.println("The input is outside the range. Please pick from 1 to 3");
	      }
	   return row;
	   }  
	   
	}

