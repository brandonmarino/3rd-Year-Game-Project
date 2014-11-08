package PlayerTypes;
/***********************************************************************************************************************************************************
 * 											PlayerTypes Class Generates Random Indexes for Row and Column to be Used in Board Class
 ***********************************************************************************************************************************************************

 ** Adapted from PlayerTypes source of TIC TAC TOE Authored by Lina
 *
 * Milestone 1, Adapting Author: Brandon Marino
 * Changed to pick from a list of predefined spaces, instead of getting a bunch of random values and looking for an empty space (Othello Game)
 * Milestone 2, Adapting Author: Brandon Marino
 * Changed both games to use the method Othello used, then made this a superclass of all move types
 */
import com.sun.media.jfxmedia.events.PlayerStateEvent;

import java.util.ArrayList;
public abstract class PlayerType
{

    private ArrayList<Integer[]> availableMoves;
    private String name = "Computer";

    /**
     * Set the availableMoves arraylist to something to avoid null pointer
     */
    public PlayerType(){
        availableMoves = new ArrayList<Integer[]>();
    }
    /**
     * Tell object all of the possible moves that can be made on the board
     * @param availableMoves a list of all of the moves possible on this board by this player
     */
    public void setAvailableMoves(ArrayList<Integer[]> availableMoves)
    {
        this.availableMoves = availableMoves;
    }

    /**
     * Return the name of this player
     * @return a name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Will return the moves that are available to this player at this time
     * @return the current list of available moves for this player
     */
    protected ArrayList<Integer[]> getAvailableMoves(){
        return availableMoves;
    }

    /**
     * Return the name of this player
     * @return a name
     */
    public String getName(){
        return name;
    }

    /**
     * Will clone a set of moves
     * @param originalMoves original set of moves
     * @return the cloned set
     */
    public static ArrayList<Integer[]> cloneMoves(ArrayList<Integer[]> originalMoves) {
        ArrayList<Integer[]> cloneMoves = new ArrayList<Integer[]>();
        //for(Integer[] move: originalMoves) cloneMoves.add(move.clone());
        for(Integer[] move: originalMoves) cloneMoves.add(move);
        //ArrayList<Integer[]> cloneMoves = originalMoves.clone();
        return cloneMoves;
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