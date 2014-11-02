package minimax;

import java.util.ArrayList;
import java.util.List;

import Boards.BoardEvaluation;
import Games.Othello;
import Moves.Move;
import Moves.OthelloAIMove;
import Moves.PlayerMove;


public class OthelloAIPlayer {
		private Othello presentState;
		private int maxDepth;
		private Move currentMove;
		private Integer alpha = new Integer(Integer.MIN_VALUE);
		private Integer beta = new Integer(Integer.MAX_VALUE);
		
		private BoardEvaluation BE = new BoardEvaluation();

	    public OthelloAIPlayer(Othello initialState, int maxDepth, Move newMove)
	    {
	    	presentState = initialState;
	    	this.maxDepth = maxDepth;
	    	currentMove = newMove;
	    }
	    
	    public Othello getPresentState() {
			return presentState;
		}



		public void setPresentState(Othello presentState) {
			this.presentState = presentState;
		}



		public int getMaxDepth() {
			return maxDepth;
		}



		public void setMaxDepth(int maxDepth) {
			this.maxDepth = maxDepth;
		}
		
		public int minMaxValue(Othello state, AlphaBetaMiniMax ab, int depth, int limitVal)
		{
			int v = limitVal;
			List<Othello> successorList;
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
	            		Othello successor = (Othello) successorList.get(i);
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
	            		Othello successor = (Othello) successorList.get(i);
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

		
	 
	 public int computeUtility(Othello state) {
		return BE.evaluate (state);
		
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
    
    private boolean terminalTest(Othello currentPlay) {
     	if(currentPlay.getBoardGame().getcurrentPlayer() == null)
     		return true;
     	else
     		return false;
             
     }
       
}
