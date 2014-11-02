package Moves;
/***********************************************************************************************************************************************************
 * 											RandomMove Class Generates Random Indexes for Row and Column to be Used in Board Class
 ***********************************************************************************************************************************************************
 ** Adapted from RandomMove source of TIC TAC TOE Authored by Lina
 * Milestone 1, Adapting Author: Brandon Marino
 *
 * Much of the code is similar however i've changed the method to use implicit enum values opposed to constants which were set to an interger value
 * Also changed the intended game to Othello, not tic tac toe
 */
import java.util.ArrayList;
public abstract class Move
{
    private ArrayList<Integer []> availableMoves;
    private Integer [] previousMove, nextMove;


	/**
     * Tell object all of the possible moves that can be made on the board
     * @param availableMoves a list of all of the moves possible on this board by this player
     */
   public void setAvailableMoves(ArrayList<Integer []> availableMoves)
    {
        this.availableMoves = availableMoves;
        previousMove = null;
        nextMove = null;
    }

    /**
     * Will return the moves that are available to this player at this time
     * @return the current list of available moves for this player
     */
    public ArrayList<Integer []> getAvailableMoves(){
        return availableMoves;
    }

    /**
     * Remove and return a move at some index
     * @param index some index in the list
     * @return the item in that index
     */

    public Integer[] popMove(int index){
        return availableMoves.remove(index);
    }

    /**
     * Some method do find a move for the player to do
     * @return Use some possible method to find a possible move by this player
     */
    public abstract Integer[] getMove();
    
    
    
    public Integer[] getPreviousMove() {
		return previousMove;
	}

	public void setPreviousMove(Integer[] previousMove) {
		this.previousMove = previousMove;
	}

	public Integer[] getNextMove() {
		return nextMove;
	}

	public void setNextMove(Integer[] nextMove) {
		this.nextMove = nextMove;
	}
}
