package Boards;

import Boards.Board.PLAYER;
import Games.Othello;
public class BoardEvaluation 
{

	
	public int evaluate (Othello game) {
		int score = 0;
		int BoardDimension = game.getBoardDim();
		if (game.getBoard().hasBeenWon() == PLAYER.PLAYER1 || game.getBoard().hasBeenWon() == PLAYER.PLAYER2) {
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
	
	private boolean stableDirection(Othello game, int i, int j, int v, int h) {
		
		int BoardDimensions =  game.getBoardDim();
		OthelloBoard player = new  OthelloBoard();
		player.setcurrentPlayer(game.getBoard().getCell(i, j));
		boolean stable = true;
		
		while (i+v < BoardDimensions && j+h < BoardDimensions &&
			   i+v >= 0 && j+h >= 0 &&
			   stable) {
			
			if (game.getBoardGame().getCell(i+v, j+h) != player.getcurrentPlayer()) {
				stable = false;
			}
			i = i + v;
			j = j + h;
			game.getBoard().attemptMove(i,j);
			
				
		}
		return stable;
	
}
}