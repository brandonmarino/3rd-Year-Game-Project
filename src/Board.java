/************************************************************************************************************************************************************
 * 										Board Class Sets Up the board, Checks Winning Conditions, And Updates Status of Game
 ************************************************************************************************************************************************************/

public class Board 
{
	private final int EMPTY = 0;
	private final int CROSS = 1;
	private final int NOUGHT = 2;
	 
	   
	private final int PLAYING = 0;
	private final int DRAW = 1;
	private final int CROSS_WON = 2;
	private final int NOUGHT_WON = 3;
	 
	   
	private final int ROWS = 3, COLUMNS = 3; 
	private int[][] board = new int[ROWS][COLUMNS]; 
	private int currentState;  
	private int currentPlayer; 
	private int currentRow, currentColumn; 


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
	
	/******************************************************************************************************************************************************************
	 * 												Getter Methods for variables and constants defined above
	 * ****************************************************************************************************************************************************************/
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
	
	public int getEMPTY()
	{ 
		return EMPTY;
	}
 
	public int getCROSS()
	{
		return CROSS;
	}
 
	public int getNOUGHT()
	{ 
		return NOUGHT;
	}
 
	public int getPLAYING()
	{ 
		return PLAYING;
	}
 
	public int getDRAW()
 	{ 
		return DRAW;
 	}
 
	public int getCROSSWon()
	{ 
		return CROSS_WON;
	}
	
	public int getNOUGHTWon()
	{
		return NOUGHT_WON;
	}
	
	public int getMoveOnBoard(int r, int c)
	{
		return board[r][c];
	}
	
	/******************************************************************************************************************************************************************
	 * 												Setter Methods for variables and constants defined above
	 * ****************************************************************************************************************************************************************/
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
	
	public void setMoveOnBoard(int r, int c, int theMove)
	{
		board[r][c] = theMove;		
	}

	/******************************************************************************************************************************************************************
	 * 												Printing Methods for Board Game and Cells Within
	 * ****************************************************************************************************************************************************************/
	public void printBoard()
	{
		for (int row = 0; row < ROWS; ++row)
		{
			for (int col = 0; col < COLUMNS; ++col)
			{
				printCell(board[row][col]); // print each of the cells
				if (col != COLUMNS - 1) 
				{
					System.out.print("|");   // print vertical partition
				}
			}
			System.out.println();
			if (row != ROWS - 1) 
			{
				System.out.println("-----------"); // print horizontal partition
			}
		}
		System.out.println();
	}


	public void printCell(int content) 
	{
		if(content == EMPTY )
			System.out.print("   "); 
		else if( content == NOUGHT)
			System.out.print(" O ");
		else if(content == CROSS)
			System.out.print(" X "); 
	}




	/******************************************************************************************************************************************************************
	 * 											Conditional Methods to test for Winning Conditions or Draw
	 * ****************************************************************************************************************************************************************/

	public boolean isDraw() 
	{
		for (int row = 0; row < ROWS; ++row)
		{
			for (int col = 0; col < COLUMNS; ++col) 
			{
				if (board[row][col] == EMPTY) 
				{
					return false;  // an empty cell found, not draw, exit
				}
			}
		}
		return true;  // no empty cell, it's a draw
	}

	public boolean hasWon(int theMove, int currentRow, int currentCol) 
	{
		if(isVertical(theMove, currentRow, currentCol))
			return true;
		else if(isHorizontal(theMove, currentRow, currentCol))
			return true;
		else if (isDiagonal(theMove, currentRow, currentCol))
			return true;
		else 
			return false;
	}

	public boolean isVertical(int theMove, int currentRow, int currentCol)
	{
		if(board[0][currentCol] == theMove  && board[1][currentCol] == theMove && board[2][currentCol] == theMove)
			return true;
		else
			return false;
	}
 
	public boolean isHorizontal(int theMove, int currentRow, int currentCol) 
	{
		if( board[currentRow][0] == theMove  && board[currentRow][1] == theMove && board[currentRow][2] == theMove)
			return true;
		else
			return false;
	}
 
	public boolean isDiagonal(int theMove, int currentRow, int currentCol)
	{
		if( currentRow == currentCol  && board[0][0] == theMove && board[1][1] == theMove && board[2][2] == theMove)
			return true;
		else if (currentRow + currentCol == 2 && board[0][2] == theMove && board[1][1] == theMove && board[2][0] == theMove)
			return true;
		else
			return false;
	}
 
	/******************************************************************************************************************************************************************
	 * 												Update Method To Change State of Game to Win or Draw
	 * ****************************************************************************************************************************************************************/
	public void updateGame(int theMove, int currentRow, int currentCol)
	{
		if (hasWon(theMove, currentRow, currentCol))
		{  
			if(theMove == CROSS)
				currentState = CROSS_WON;
			else
				currentState = NOUGHT_WON;
		} 	
		else if (isDraw())
		{  
			currentState = DRAW;
		}
 
	}
 }