package minimax;

import java.util.ArrayList;
import java.util.List;

import Boards.BoardEvaluation;
import Games.Game;
import Games.GameNotAbs;
import Games.Othello;
import Moves.Move;
import Moves.OthelloAIMove;
import Moves.PlayerMove;


public class OthelloAIPlayer {
		private GameNotAbs presentState;
		private int maxDepth;
		private Move currentMove;
		private Integer alpha = new Integer(Integer.MIN_VALUE);
		private Integer beta = new Integer(Integer.MAX_VALUE);
		
		private BoardEvaluation BE = new BoardEvaluation();

	    public OthelloAIPlayer(GameNotAbs initialState, int maxDepth, Move newMove)
	    {
	    	presentState = initialState;
	    	this.maxDepth = maxDepth;
	    	currentMove = newMove;
	    }
	    
	    public GameNotAbs getPresentState() {
			return presentState;
		}



		public void setPresentState(GameNotAbs presentState) {
			this.presentState = presentState;
		}



		public int getMaxDepth() {
			return maxDepth;
		}



		public void setMaxDepth(int maxDepth) {
			this.maxDepth = maxDepth;
		}
		
		public int minMaxValue(GameNotAbs state, AlphaBetaMiniMax ab, int depth, int limitVal)
		{
			int v = limitVal;
			List<GameNotAbs> successorList;
			int minimumValueOfSuccessor, maximumValueOfSuccessor;
			if(terminalTest(state) || depth >= maxDepth)
			{
				return computeUtility(state);
			}
			else
			{
				successorList = getSuccessorStates(state);
	            for(int i = 0; i < successorList.size(); i++) 
	            {
	            	if(v == beta)
	            	{
	            		GameNotAbs successor =  successorList.get(i);
	            		v= alpha;
	            		if(ab!= null)
	            			minimumValueOfSuccessor = minMaxValue(successor, ab.MakeCopy(), depth ++, v);
	            		else
	            			minimumValueOfSuccessor = minMaxValue(successor, null, depth ++, v);
	            		
	            		if(minimumValueOfSuccessor > v) {
	                            v = minimumValueOfSuccessor;
	                            state.setNextMove(successor.getNextMove());
	                    }
	            		
	                    if(ab != null) {
	                            // use alpha-beta pruning
	                            if(v >= ab.getBeta()) {
	                                    return v;
	                            }
	                            ab.setAlpha(Math.max(ab.getAlpha(), v));
	                    }
	            	}
	            	
	            	if(v == alpha)
	            	{
	            		GameNotAbs successor = successorList.get(i);
	            		v= beta;
	            		if(ab!= null)
	                		maximumValueOfSuccessor = minMaxValue(successor, ab.MakeCopy(), depth ++, v);
	                	else
	                		maximumValueOfSuccessor = minMaxValue(successor, null, depth ++, v);
	            		if(maximumValueOfSuccessor < v) {
	                            v = maximumValueOfSuccessor;
	                            state.setNextMove(successor.getNextMove());
	                    }
	                    if(ab != null) {
	                            // use alpha-beta pruning
	                            if(v <= ab.getBeta()) {
	                                    return v;
	                            }
	                            ab.setBeta(Math.min(ab.getBeta(), v));
	                    }
	            	}
	            }
	            return v;
	    }	
			
			
		}

		
	 
	 public int computeUtility(GameNotAbs state) {
		return BE.evaluate ((Othello)state);
		
}
	 
	
	 
    private List<GameNotAbs> getSuccessorStates(GameNotAbs state) {
		 List<GameNotAbs> result = new ArrayList<GameNotAbs>();
        
       ArrayList<Integer []> moves = state.getCurrentMove().getAvailableMoves();
        
        for(Integer[] move : moves) {
        	GameNotAbs newState = new GameNotAbs(state);
                newState.getCurrentMove().setNextMove(move);
                result.add(newState);
        }
        
        return result;
}
    
    private boolean terminalTest(GameNotAbs currentPlay) {
     	if(currentPlay.getBoardGame().getcurrentPlayer() == null)
     		return true;
     	else
     		return false;
             
     }
       
}
