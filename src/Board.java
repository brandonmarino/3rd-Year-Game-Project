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
public void printBoard() {
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
public void printCell(int content) {
   if(content == EMPTY )
     System.out.print("   "); 
   else if( content == NOUGHT)
      System.out.print(" O ");
   else if(content == CROSS)
      System.out.print(" X "); 
   }




/** Return true if it is a draw (no more empty cell) */
// TODO: Shall declare draw if no player can "possibly" win
public boolean isDraw() {
   for (int row = 0; row < ROWS; ++row) {
      for (int col = 0; col < COLUMNS; ++col) {
         if (board[row][col] == EMPTY) {
            return false;  // an empty cell found, not draw, exit
         }
      }
   }
   return true;  // no empty cell, it's a draw
}

/** Return true if the player with "theMove" has won after placing at
    (currentRow, currentCol) */
public static boolean hasWon(int theMove, int currentRow, int currentCol) {
  if(isVertical(theMove, currentRow, currentCol))
      return true;
  else if(isHorizontal(theMove, currentRow, currentCol))
      return true;
  else if (isDiagonal(theMove, currentRow, currentCol))
      return true;
  else 
      return false;
}

public static boolean isVertical(int theMove, int currentRow, int currentCol) {
    if(board[0][currentCol] == theMove  && board[1][currentCol] == theMove && board[2][currentCol] == theMove)
     return true;
     else
     return false;
 }
 
  public static boolean isHorizontal(int theMove, int currentRow, int currentCol) {
   if( board[currentRow][0] == theMove  && board[currentRow][1] == theMove && board[currentRow][2] == theMove)
     return true;
   else
     return false;
 }
 
 public static boolean isDiagonal(int theMove, int currentRow, int currentCol) {
   if( currentRow == currentCol  && board[0][0] == theMove && board[1][1] == theMove && board[2][2] == theMove)
     return true;
   else if (currentRow + currentCol == 2 && board[0][2] == theMove && board[1][1] == theMove && board[2][0] == theMove)
     return true;
   else
     return false;
 }
 
 /** Update the "currentState" after the player with "theMove" has placed on
 (currentRow, currentCol). */
 public void updateGame(int theMove, int currentRow, int currentCol) {
 if (hasWon(theMove, currentRow, currentCol)) {  // check if winning move
    if(theMove == CROSS)
    currentState = CROSS_WON;
    else
     currentState = NOUGHT_WON;
 } else if (isDraw()) {  // check for draw
   currentState = DRAW;
 }
 // Otherwise, no change to currentState (still PLAYING).
}
 
 public int getMoveOnBoard(int r, int c)
 {
	 return board[r][c];

 }
 
 public void setMoveOnBoard(int r, int c, int theMove)
 {
	 
	 board[r][c] = theMove;
	 
	 
 }
 

 }



