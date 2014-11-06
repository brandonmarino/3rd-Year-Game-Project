package Games;

import minimax.AlphaBetaMiniMax;
import minimax.OthelloAIPlayer;
import Boards.Board;

import Moves.Move;



public class GameNotAbs extends Game{
	
	Board board;
	
	
	
	public GameNotAbs(Board boardGame){
       super(boardGame);
       
    }
    
    
    
    public GameNotAbs(GameNotAbs g){
    	 super(g.getBoardGame());
        this.setCurrentMove(g.getCurrentMove());
        this.getBoardGame().setcurrentPlayer(g.getBoardGame().getcurrentPlayer());
        this.setDepth(g.getDepth()); 
        
    }
	
	
	
	 protected void Smartplay(){
	        // Continue Playing Until You're Done
	        while (this.getBoardGame().getCurrentState() == Board.GAME_STATE.PLAYING) {
	            boolean moveMade = false;
	            //allow turns of different player types
	            if (this.getBoardGame().getcurrentPlayer() == Board.PLAYER.PLAYER1)
	                moveMade = takeSmartTurn(this.getMove(0), this.getName(0));
	            else if (this.getBoardGame().getcurrentPlayer() == Board.PLAYER.PLAYER2)
	                moveMade = takeSmartTurn(this.getMove(1), this.getName(1));

	            update(moveMade);
	            checkIfWon();
	        }
	    }
	
	protected boolean takeSmartTurn(Move move, String name){
        boolean state = true;
        boolean turnTaken = false;
        move.setAvailableMoves(this.getBoardGame().getPossibleMoves());       //give all available moves from board
        while(state){
        	if( name.equals("Player 1") || name.equals("Player 2") )
        	{
        		AlphaBetaMiniMax ab = new AlphaBetaMiniMax();
        	OthelloAIPlayer ai = new OthelloAIPlayer(this, 3,  move);
        	 int Intmove = ai.minMaxValue(this, ab,3, Integer.MAX_VALUE);
        	turnTaken = true;
            state = false;
        	}
        	else
        	{				/**********************************************************************
        							Player becomes null after minima's turn
        					************************************************************************/
            Integer[] playerMove = move.getMove();
            if (playerMove == null){
                //All moves have been exhausted
                state = false;//Quit loop
            }else{
                //some moves were available
                if (this.getBoardGame().attemptMove(playerMove[0], playerMove[1])){  //try to make the move
                	turnTaken = true;
                    state = false;//Quit loop
                }
            }
        }
        }
        return turnTaken;
    }
	
	
}
