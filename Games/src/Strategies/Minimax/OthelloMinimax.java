package Strategies.Minimax;

/** Milestone 3 - Author: Brandon Marino
 * Will handle it's own evaluation w/ help from the superclass
 */
public class OthelloMinimax extends MinimaxPlayerType {
    /**
     * Copy/ up-casting constructor
     * @param player the generic Player that was made in the generic Game class needed to make decisions about later int the game
     */
    public OthelloMinimax(MinimaxPlayerType player) {
        super(player.getBoard(), player.getPlayerNum());
    }
}
