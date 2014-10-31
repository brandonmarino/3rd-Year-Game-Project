package Boards;

import java.util.ArrayList;

/************************************************************************************************************************************************************
 * 										Board Class Sets Up the board, Checks Winning Conditions, And Updates Status of Game
 ************************************************************************************************************************************************************/

public class TicTacToeBoard extends Board
{
    private int currentRow;
    private int currentCol;

    /**
     * Define the parameters of the Tic Tac Toe board
     */
    public TicTacToeBoard()
	{
        super(3,3);
        setPlayerTiles('X', 'O');
        currentRow = 0;
        currentCol = 0;
	}
	
	/******************************************************************************************************************************************************************
	 * 												Getter Methods for variables and constants defined above
	 * ****************************************************************************************************************************************************************/

    /**
     * Return all possible moves of the othello board
     * @return all empty space on the board
     */
    public ArrayList<Integer[]> getPossibleMoves(){
        return getEmptySpaces();
    }

	/******************************************************************************************************************************************************************
	 * 											Conditional Methods to test for Winning Conditions or Draw
	 * ****************************************************************************************************************************************************************/

    /**
     * Try a move in tic tac toe
     * @param currentRow some row
     * @param currentCol some column
     * @return if the move worked
     */
	public boolean attemptMove(int currentRow, int currentCol)
	{
        this.currentRow = currentRow;
        this.currentCol = currentCol;
        setCell(getcurrentPlayer(),currentRow,currentCol);
        return true;
	}

    /**
     * Check if the game resulted in a draw
     * @return if the game ended in a draw
     */
    private boolean isDraw()
    {
        return getEmptySpaces().isEmpty(); // no empty cells, it's a draw
    }

    /**
     * Find if vertical match can be found
     * @return if that vertical was found
     */
	private boolean isVertical()
	{
		return (getCell(0,currentCol) == getcurrentPlayer()  && getCell(1,currentCol) == getcurrentPlayer() && getCell(2, currentCol) == getcurrentPlayer());
	}

    /**
     * Find if horizontal match can be found
     * @return if that vertical was found
     */
	private boolean isHorizontal()
	{
		return ( getCell(currentRow, 0) == getcurrentPlayer()  && getCell(currentRow,1) == getcurrentPlayer() && getCell(currentRow,2) == getcurrentPlayer());
	}

    /**
     * Find if diagonal match can be found
     * @return if that diagonal was found
     */
	private boolean isDiagonal()
	{
		if( currentRow == currentCol  && getCell(0,0) == getcurrentPlayer() && getCell(1,1) == getcurrentPlayer() && getCell(2,2) == getcurrentPlayer())
			return true;
		else if (currentRow + currentCol == 2 && getCell(0,2) == getcurrentPlayer() && getCell(1,1) == getcurrentPlayer() && getCell(2,0) == getcurrentPlayer())
			return true;
		else
			return false;
	}

    /**
     * Find if somebody has made a line of three
     * @return if a line was found
     */
    protected PLAYER hasBeenWon(){
        if(isVertical())
            return getcurrentPlayer();
        else if(isHorizontal())
            return getcurrentPlayer();
        else if (isDiagonal())
            return getcurrentPlayer();
        else if (isDraw())
            return PLAYER.EMPTY;
        return null;
    }
 }