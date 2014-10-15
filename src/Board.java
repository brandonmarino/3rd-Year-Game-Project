public class Board {
	
	
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

/** Initialize the game-board contents and the current states */
public Board()
{
	for (int row = 0; row < ROWS; row++) 
	{
		for (int col = 0; col < COLUMNS; col++) 
		{
			board[row][col] = EMPTY;  // all cells empty
	     }
	 }
	 currentState = PLAYING; // ready to play
	 currentPlayer = CROSS;  // cross plays first
}

public int getCurrentState()
{
	return currentState;
}

public int getcurrentPlayer()
{
	return currentPlayer;
}

public int getcurrentRow()
{
	return currentRow;
}

public int getcurrentColumn()
{
	return currentColumn;
}


public void setCurrentState(int newState)
{
	currentState = newState;
}

public void setcurrentPlayer(int newPlayer)
{
	currentPlayer = newPlayer;
}

public void setcurrentRow(int newRow)
{
	currentRow = newRow;
}

public void setcurrentColumn(int newColumn)
{
	currentColumn = newColumn;
}

/** Print the game board */
public static void printBoard() {
   for (int row = 0; row < ROWS; ++row) {
      for (int col = 0; col < COLUMNS; ++col) {
         printCell(board[row][col]); // print each of the cells
         if (col != COLUMNS - 1) {
            System.out.print("|");   // print vertical partition
         }
      }
      System.out.println();
      if (row != ROWS - 1) {
         System.out.println("-----------"); // print horizontal partition
      }
   }
   System.out.println();
}

/** Print a cell with the specified "content" */
public static void printCell(int content) {
   if(content == EMPTY )
     System.out.print("   "); 
   else if( content == NOUGHT)
      System.out.print(" O ");
   else if(content == CROSS)
      System.out.print(" X "); 
   }

}
