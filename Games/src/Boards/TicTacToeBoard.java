package Boards;

import common.Move;

import java.util.ArrayList;

/**
 * *********************************************************************************************************************************************************
 * Board Class Sets Up the board, Checks Winning Conditions, And Updates Status of Othello
 * ***********************************************************************************************************************************************************
 *
 * Milestone 1:
 *  Adapting Author: Lina
 *  Original code provided by Lina
 * Milestone 2:
 *  Adapting Author: Brandon Marino
 *  -Made it a subclass of Board
 *  -removed redundant code
 *  -cleaned up some code
 */
public class TicTacToeBoard extends Board
{
    private int currentRow;
    private int currentCol;

    /**
     * Define the parameters of the Tic Tac Toe board
     */
    public TicTacToeBoard()
	{
        super(3);
        setPlayerTiles('X', 'O');
        currentRow = 0;
        currentCol = 0;
	}
	
	/******************************************************************************************************************************************************************
	 * 												Getter and Setter Methods for variables and constants defined above
	 * ****************************************************************************************************************************************************************/

    /**
     * Return all possible moves of the othello board
     * @return all empty space on the board
     */
    public ArrayList<Move> getPossibleMoves(){
        return getEmptySpaces();
    }

    private int getCurrentRow(){
        return currentRow;
    }

    private int getCurrentColumn(){
        return currentCol;
    }

    public Board getClone(){
        TicTacToeBoard clonedBoard = new TicTacToeBoard();
        clonedBoard = (TicTacToeBoard)super.getClone(clonedBoard);
        clonedBoard.setCurrentRow( this.getCurrentRow() );
        clonedBoard.setCurrentColumn( this.getCurrentColumn() );
        return clonedBoard;
    }

    protected void setCurrentRow(int currentRow){
        this.currentRow = currentRow;
    }

    protected void setCurrentColumn(int currentCol){
        this.currentCol = currentCol;
    }

	/******************************************************************************************************************************************************************
	 * 											Conditional Methods to test for Winning Conditions or Draw
	 * ****************************************************************************************************************************************************************/

    /**
     * Try a move in tic tac toe
     * @param move: the attempted move
     * @return if the move worked
     */
	public boolean attemptMove(Move move)
	{
        currentRow = move.getRow();
        currentCol = move.getColumn();
        setCell(getCurrentPlayer(), move);
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
    public boolean isVertical()
	{
		return ( getCell(0,currentCol) == getCurrentPlayer()  && getCell(1,currentCol) == getCurrentPlayer() && getCell(2, currentCol) == getCurrentPlayer() );
	}

    /**
     * Find if horizontal match can be found
     * @return if that vertical was found
     */
	public boolean isHorizontal()
	{
		return ( getCell(currentRow, 0) == getCurrentPlayer()  && getCell(currentRow,1) == getCurrentPlayer() && getCell(currentRow,2) == getCurrentPlayer() );
	}

    /**
     * Find if diagonal match can be found
     * @return if that diagonal was found
     */
	public boolean isDiagonal()
	{
		if( currentRow == currentCol  && getCell(0,0) == getCurrentPlayer() && getCell(1,1) == getCurrentPlayer() && getCell(2,2) == getCurrentPlayer())
			return true;
		else if (currentRow + currentCol == 2 && getCell(0,2) == getCurrentPlayer() && getCell(1,1) == getCurrentPlayer() && getCell(2,0) == getCurrentPlayer())
			return true;
		else
			return false;
	}

    /**
     * Allow getCell to be accessed without first creating a new move object
     * @param row: co-ordinate
     * @param column: co-ordinate
     */
    private PLAYER getCell(int row, int column){
        Move move = new Move(row, column);
        return getCell(move);
    }
    public int getState(){
        int value = 0;
        if(isVertical())
            value++;
        else if(isHorizontal())
            value++;
        else if (isDiagonal())
            value++;
        return value;
    }
    /**
     * Find if somebody has made a line of three
     * @return if a line was found
     */
    protected PLAYER hasBeenWon(){
        if(isVertical())
            return getCurrentPlayer();
        else if(isHorizontal())
            return getCurrentPlayer();
        else if (isDiagonal())
            return getCurrentPlayer();
        else if (isDraw())
            return PLAYER.EMPTY;
        return null;
    }
 }