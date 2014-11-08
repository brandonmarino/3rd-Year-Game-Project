package Minimax;

/**
 * Object that will contain a move and will rank that move
 */
public class moveWorth {

    private int score;
    private Integer[] move;

    /*********************************************************************************
     *         Getter Methods for variables and constants defined above
     *********************************************************************************/

    /**
     * Get the internal score
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Get the move
     * @return the internal move
     */
    public Integer[] getMove() {
        return move;
    }

    /*********************************************************************************
     *          Setter Methods for variables and constants defined above
     *********************************************************************************/

    /**
     * Set the internal score
     * @param score some score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Set the internal move
     * @param move some move
     */
    public void setMove(Integer[] move) {
        this.move = move;
    }




}
