package com.sysc4001.phoenix.milestone4.Strategies.Alternative;

import com.sysc4001.phoenix.milestone4.common.Move;

/**
 * Created by Brandon on 11/20/14.
 */
public class CheckersObstruct extends ObstructPlayerType {
    public CheckersObstruct(ObstructPlayerType player){
        super(player.getBoard(),player.getNumber() );
        setName("Computer- Obstruction "+ player.getNumber());
    }

    /**
     * Rank how well this move will block the opponent from king-ing, and jumping
     * @param move some move
     * @return it's rank
     */
    protected int rankObstruction(Move move){
        return 0;
    }
}
