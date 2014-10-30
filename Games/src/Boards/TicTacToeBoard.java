package Boards;

import java.util.ArrayList;

/************************************************************************************************************************************************************
 * 										Board Class Sets Up the board, Checks Winning Conditions, And Updates Status of Game
 ************************************************************************************************************************************************************/

public class TicTacToeBoard extends Board
{
    private int currentRow;
    private int currentCol;
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
     * @return
     */
    public ArrayList<Integer[]> getPossibleMoves(){
        return getEmptySpaces();
    }

	/******************************************************************************************************************************************************************
	 * 											Conditional Methods to test for Winning Conditions or Draw
	 * ****************************************************************************************************************************************************************/

    /**
     * Check if the game resulted in a draw
     * @return
     */
	public boolean isDraw() 
	{
        return getEmptySpaces().isEmpty(); // no empty cells, it's a draw
	}

	public boolean attemptMove(int currentRow, int currentCol)
	{
        this.currentRow = currentRow;
        this.currentCol = currentCol;
        setCell(getcurrentPlayer(),currentRow,currentCol);
        return true;
	}

	public boolean isVertical()
	{
		return (getCell(0,currentCol) == getcurrentPlayer()  && getCell(1,currentCol) == getcurrentPlayer() && getCell(2, currentCol) == getcurrentPlayer());
	}
 
	public boolean isHorizontal()
	{
		return ( getCell(currentRow, 0) == getcurrentPlayer()  && getCell(currentRow,1) == getcurrentPlayer() && getCell(currentRow,2) == getcurrentPlayer());
	}
 
	public boolean isDiagonal()
	{
		if( currentRow == currentCol  && getCell(0,0) == getcurrentPlayer() && getCell(1,1) == getcurrentPlayer() && getCell(2,2) == getcurrentPlayer())
			return true;
		else if (currentRow + currentCol == 2 && getCell(0,2) == getcurrentPlayer() && getCell(1,1) == getcurrentPlayer() && getCell(2,0) == getcurrentPlayer())
			return true;
		else
			return false;
	}
    protected PLAYER hasBeenWon(){
        if(isVertical())
            return getcurrentPlayer();
        else if(isHorizontal())
            return getcurrentPlayer();
        else if (isDiagonal())
            return getcurrentPlayer();
        else
            return null;
    }
 
	/******************************************************************************************************************************************************************
	 * 												Update Method To Change State of Game to Win or Draw
	 * ****************************************************************************************************************************************************************/
	public void updateGame()
	{
        PLAYER winner = hasBeenWon();
		if (winner != null)
		{
			if(winner == PLAYER.PLAYER1)
				setCurrentState(GAME_STATE.PLAYER1_WON);
			else
                setCurrentState(GAME_STATE.PLAYER2_WON);
		}
		else if (isDraw())
		{
            setCurrentState(GAME_STATE.DRAW);
		}
 
	}
 }