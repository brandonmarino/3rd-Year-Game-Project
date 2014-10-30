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
import java.util.Random;
public class RandomMove extends Move
{
    private Random randVal;

    /**Constructor
     * Set and seed the random
     */
    public RandomMove(){
        setSeed();
    }

    /**
     * Set the random so that it is as random as we can provide in this particular case
     */
    private void setSeed(){
        randVal = new Random();
        randVal.setSeed(System.currentTimeMillis());
    }

    /**
     * Get a move randomly
     * @return some random move
     */
    @Override
    public Integer[] getMove(){
        if (getAvailableMoves().isEmpty())   //player cannot move
            return null;
        int val = randVal.nextInt(getAvailableMoves().size());   //seed with the number of moves
        return popMove(val);  //remove that move from all possible moves, if another move needs to be selected, its not one of these
    }
}