package Games;

import Boards.CheckersBoard;
import Boards.OthelloBoard;
import PlayerTypes.PlayerType;

public class Checkers extends Game {
	
	public static void main(String[] args) {
        Checkers game = new Checkers();
        game.play();
    }
	
	public Checkers()
	{
		super(new CheckersBoard());
	}
	
	/**
     * Take a turn
     * @return if a turn was successfully completed
     */
    protected boolean takeTurn(PlayerType move){
        boolean takeTurn = super.takeTurn(move);
        ((CheckersBoard)boardGame).setMoved(takeTurn);
        return takeTurn;
    }
	
}
