package Moves;
/***********************************************************************************************************************************************************
 * 											Move Class Generates Random Indexes for Row and Column to be Used in Board Class
 ***********************************************************************************************************************************************************

 ** Adapted from Move source of TIC TAC TOE Authored by Lina
 *
 * Milestone 1, Adapting Author: Brandon Marino
 * Changed to pick from a list of predefined spaces, instead of getting a bunch of random values and looking for an empty space (Othello Game)
 * Milestone 2, Adapting Author: Brandon Marino
 * Changed both games to use the method Othello used, then made this a superclass of all move types
 */
import java.util.ArrayList;
public abstract class Move
{
    private ArrayList<Integer[]> availableMoves;

    /**
     * Tell object all of the possible moves that can be made on the board
     * @param availableMoves a list of all of the moves possible on this board by this player
     */
    public void setAvailableMoves(ArrayList<Integer[]> availableMoves)
    {
        this.availableMoves = availableMoves;
    }

    /**
     * Will return the moves that are available to this player at this time
     * @return the current list of available moves for this player
     */
    protected ArrayList<Integer[]> getAvailableMoves(){
        return availableMoves;
    }

    /**
     * Remove and return a move at some index
     * @param index some index in the list
     * @return the item in that index
     */

    protected Integer[] popMove(int index){
        return availableMoves.remove(index);
    }

    /**
     * Some method do find a move for the player to do
     * @return Use some possible method to find a possible move by this player
     */
    public abstract Integer[] getMove();
}