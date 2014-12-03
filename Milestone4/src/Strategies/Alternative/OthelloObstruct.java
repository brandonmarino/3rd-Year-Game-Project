package Strategies.Alternative;

import common.Move;

/**
 * Created by Brandon on 11/20/14.
 */
public class OthelloObstruct extends ObstructPlayerType {
    public OthelloObstruct(ObstructPlayerType player){
        super(player.getBoard(),player.getNumber());
    }

    /**
     * Decrease the size of the flanking chane the opponent can impose on you
     * @param move some move
     * @return the rank (the size of the flank, so lower is better here)
     */
    protected int rankObstruction(Move move){
        return 0;
    }
}
