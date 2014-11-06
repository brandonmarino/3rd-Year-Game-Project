package Minimax;

import Boards.Board;
import Games.Game;

public class BoardEvaluation 
{

	
	public int evaluate (Board board) {
		int score = 0;
		int BoardDimension = board.getDimensions();
		if (game.getBoard().hasBeenWon() == Board.PLAYER.PLAYER1 || board.getBoard().hasBeenWon() == Board.PLAYER.PLAYER2) {
			// disks difference
			score = game.getBoard().getBlackDiscs() - game.getBoard().getWhiteDiscs();
		}
					
		else {
			// mobility
			int darkMobility = game.getOAIM().getLegalMoves(game.getBoard().getBlackDiscs(), game).size();
			int lightMobility = game.getOAIM().getLegalMoves(game.getBoard().getWhiteDiscs(), game).size();
			int mobility = darkMobility - lightMobility;
			// stability
			int darkStability = 0;
			int lightStability = 0;
			for (int i = 0; i < BoardDimension; i++) {
				for (int j = 0; j < BoardDimension; j++) {
					if (game.getBoard().getCell(i, j) != PLAYER.EMPTY) {
						if ( (stableDirection(game, i, j, 1, 0) || stableDirection(game, i, j, -1, 0) ) &&
							 (stableDirection(game, i, j, 0, 1) || stableDirection(game, i, j, 0, -1) ) &&
							 (stableDirection(game, i, j, 1, 1) || stableDirection(game, i, j, -1, -1) ) &&
							 (stableDirection(game, i, j, 1, -1) || stableDirection(game, i, j, -1, 1) ) ) {
						
							if (game.getBoard().getCell(i, j) == PLAYER.PLAYER1) {
								darkStability++;
							}
							else {
								lightStability++;
							}
						}
					}
				}
			}
			int stability = darkStability - lightStability;
			// disks difference
			int disksDiff = game.getBoard().getBlackDiscs() - game.getBoard().getWhiteDiscs();
			// total score
			score = (100 * mobility) + (10 * stability) + (1 * disksDiff);
		}
		return score;
	}
	
	private boolean stableDirection(Game game, int row, int column, int v, int h) {
		
		int BoardDimensions =  game.getBoardDim();
		OthelloBoard player = new  OthelloBoard();
		player.setcurrentPlayer(game.getBoard().getCell(row, column));
		boolean stable = true;
		
		while (row+v < BoardDimensions && column+h < BoardDimensions &&
			   row+v >= 0 && column+h >= 0 &&
			   stable) {
			
			if (game.getBoardGame().getCell(row+v, column+h) != player.getcurrentPlayer()) {
				stable = false;
			}
			row = row + v;
			column = column + h;
			game.getBoard().attemptMove(row,column);
			
				
		}
		return stable;
	
}
}
