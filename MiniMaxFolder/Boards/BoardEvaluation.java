package Boards;

import Boards.Board.PLAYER;
import Games.GameNotAbs;
import Games.Othello;
public class BoardEvaluation
{
	private int BoardDimension;
	private Board cloneBoard;
	public int evaluate (Othello game) {
		int score = 0;
		BoardDimension = game.getBoardDim();
		cloneBoard = game.getBoardGame().clone();
		if (cloneBoard.hasBeenWon() == PLAYER.PLAYER1 || cloneBoard.hasBeenWon() == PLAYER.PLAYER2) {
			// disks difference
			score = game.getBoard().getBlackDiscs() - game.getBoard().getWhiteDiscs();
		}
					
		else {
			// Find the total numbers of moves takes so far by each player
			int darkMobility = game.getOAIM().getLegalMoves(game.getBoard().getBlackDiscs(), game).size();
			int lightMobility = game.getOAIM().getLegalMoves(game.getBoard().getWhiteDiscs(), game).size();
			//Find the difference between them
			int mobility = darkMobility - lightMobility;
			// stability
			int darkStability = 0;
			int lightStability = 0;
			for (int i = 0; i < BoardDimension; i++) {
				for (int j = 0; j < BoardDimension; j++) {
					if (cloneBoard.getCell(i, j) != PLAYER.EMPTY) {
						if ( (stableDirection(cloneBoard, i, j, 1, 0) || stableDirection(cloneBoard, i, j, -1, 0) ) &&
							 (stableDirection(cloneBoard, i, j, 0, 1) || stableDirection(cloneBoard, i, j, 0, -1) ) &&
							 (stableDirection(cloneBoard, i, j, 1, 1) || stableDirection(cloneBoard, i, j, -1, -1) ) &&
							 (stableDirection(cloneBoard, i, j, 1, -1) || stableDirection(cloneBoard, i, j, -1, 1) ) ) {
						
							//Find how many possible moves left for player 1
							if (cloneBoard.getCell(i, j) == PLAYER.PLAYER1) {
								darkStability++;
								cloneBoard.setCell(PLAYER.PLAYER1, i, j);
								//game.getBoard().attemptMove(i, j);
								//game.getBoard().printBoard();
								//game.setBoardGame(cloneBoard);
								//game.getBoard().setCell(PLAYER.PLAYER1, i, j);
								
								
							}//Find how many possible moves left for player 2
							else {
								lightStability++;
								//cloneBoard.attemptMove(i,j);
								//game.getBoard().setCell(PLAYER.PLAYER2, i, j);
								//game.setBoardGame(cloneBoard);
								cloneBoard.setCell(PLAYER.PLAYER2, i, j);
								//game.setBoardGame(cloneBoard);
								//game.getBoard().attemptMove(i, j);
								//game.getBoard().printBoard();
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
		game.setBoardGame(cloneBoard);
		return score;
	}
	
	
	//Legal moves checking
	private boolean stableDirection(Board game, int i, int j, int v, int h) {
		
		
		OthelloBoard player = new  OthelloBoard();
		player.setcurrentPlayer(game.getCell(i, j));
		boolean stable = true;
		
				//if i+v is within range and j+h is within range
		while (i+v < BoardDimension && j+h < BoardDimension &&
			   i+v >= 0 && j+h >= 0 &&	
			   stable) {
				
			//and the new position does not have the current player
			if (game.getCell(i+v, j+h) != player.getcurrentPlayer()) {
				stable = false;
			}
			i += v;	//find another location
			j += h;				
		}
		
		return stable;
	
	}
}
