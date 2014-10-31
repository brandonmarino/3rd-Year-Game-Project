
import java.util.ArrayList;
import java.util.List;

import Boards.Board;
import Games.Game;
import Games.Othello;
import Moves.Move;


public class OthelloAIPlayer extends AIPlayer{

	 public OthelloAIPlayer(Controller controller) {
         super( controller.getGameState(), controller.getSearchDepth());
                
        }
	 
    private List<Othello> getSuccessorStates(Othello state) {
		 List<Othello> result = new ArrayList<Othello>();
        
       ArrayList<Integer []> moves = state.getCurrentMove().getAvailableMoves();
        
        for(Integer[] move : moves) {
                Othello newState = new Othello(state);
                newState.getCurrentMove().setNextMove(move);
                result.add(newState);
        }
        
        return result;
}
       
}
