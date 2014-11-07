package PlayerTypes;
/***********************************************************************************************************************************************************
 * 											RandomPlayerType Class Generates Random Indexes for Row and Column to be Used in Board Class
 ***********************************************************************************************************************************************************

 * Milestone 1, Adapting Author: Lina + Brandon
 *  - Pick a random move for the player to do
 * Milestone 2, Author: Brandon Marino
 *  - Make subclass of PlayerTypes
 */
import java.util.Random;
public class RandomPlayerType extends PlayerType
{
    private Random randVal;

    /**Constructor
     * Set and seed the random
     */
    public RandomPlayerType(int playernum){
        setSeed();
        setName("Computer-Random " + playernum);
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