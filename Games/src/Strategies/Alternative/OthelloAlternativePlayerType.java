package Strategies.Alternative;

import PlayerTypes.PlayerType;
import common.Move;

/**
 * Created by Brandon on 11/20/14.
 */
public class OthelloAlternativePlayerType extends AlternativePlayerType {
    public OthelloAlternativePlayerType(AlternativePlayerType player){
        //super();
    }
    public Move getMove(){
        return new Move();
    }
}
