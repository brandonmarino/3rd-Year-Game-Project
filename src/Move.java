/***********************************************************************************************************************************************************
 * 											Move Class Generates Random Indexes for Row and Column to be Used in Board Class
 ***********************************************************************************************************************************************************/
import java.util.Random;

public class Move extends Board
{
	private final int ROWS = 3, COLUMNS = 3; 
	private int col, row;
	
	//Constructor to initialize variables
	public Move()
	{
		col =0;
		row = 0;
	}
	
	//Random Generation of column index
	public int playerMoveColumn() 
	{
		boolean validInput = false;  // for input validation
		Random randCol;
		while(!validInput)
		{ 
			randCol = new Random(); 
			col = randCol.nextInt(3);
			if (col >= 0 && col < COLUMNS ) //Check if it's within the limits of the board
			{
				validInput = true;  // if it's within the limit, exit loop
			}
			else
				System.out.println("The input is outside the range. Please pick from 1 to 3");
		}
		return col;
	}

	//Random Generation of row index
	public int playerMoveRow() 
	{
	   boolean validInput = false;  // for input validation
	   Random randRow;
	   while(!validInput)
	   {
		  randRow = new Random();  
	      row = randRow.nextInt(3);
	      if (row >= 0 && row < ROWS) //Check if it's within the limits of the board
	      {
	    	  validInput = true;	//if it's within the limit, exit loop
	      }
	      else
	         System.out.println("The input is outside the range. Please pick from 1 to 3");
	   }
	   return row;
	}  	   
}