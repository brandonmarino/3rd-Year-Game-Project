package Boards;

import java.util.ArrayList;

import common.CheckersMove;
import common.Move;

public class CheckersBoard extends Board {

	private int forwards, deadWhite, deadBlack;
	private ArrayList<Move> queenedPieces;
	
	public CheckersBoard() {
		super(8);
		setPlayerTiles('B', 'R'); // Black moves first
		setUpBoard(getDimensions());
		deadWhite = 0;
		deadBlack = 0;		
	}

	private void setUpBoard(int dim) {
		ArrayList<PLAYER> players = new ArrayList<PLAYER>();
		players.add(PLAYER.PLAYER1);
		players.add(PLAYER.PLAYER2);
		for(PLAYER player : players)
		{
			setForwards(player);
			int row = 0;
			if(player.equals(PLAYER.PLAYER2))
				row = dim;
			for(row--; row != (dim-2)/2 && row != (dim-2)/2 +1 ; row += forwards)
			{
				if (row % 2 == 0){
					setCell( player, new Move(row,0));
					setCell( player, new Move(row,dim/4));
					setCell( player, new Move(row,dim/2));
					setCell( player, new Move(row,dim-2));
				}
				else{
					setCell( player, new Move(row,dim/4 -1));
					setCell( player, new Move(row,dim/2 -1));
					setCell( player, new Move(row,dim/2 +1));
					setCell( player, new Move(row,dim));
				}
			}
		}	
	}

	@Override
	public ArrayList<Move> getPossibleMoves() {
		ArrayList<Move> legalMoves = new ArrayList<Move>();
        for (Move possibleMove: getFullSpaces()){
            ArrayList<Move> diagMove = findOpenDiagonals(possibleMove);
            diagMove.addAll(findHopDiagonals(possibleMove));
            for (Move ret : diagMove){
            	legalMoves.add(new CheckersMove(possibleMove, ret));
            }                
        }
        return legalMoves;
	}
	
	public ArrayList<Move> getPossibleMovesSingle(Move justMoved)
	{
		ArrayList<Move> legalMoves = new ArrayList<Move>();
		ArrayList<Move> diagOpen = findHopDiagonals(justMoved);
        for (Move ret : diagOpen){
        	legalMoves.add(new CheckersMove(justMoved, ret));
        }		
		return legalMoves;		
	}

	/**
	 * finds the possible places (Moves) a specific piece can go to.
	 * @param possibleMove the piece you want to know more about
	 * @return all the possible non-hop moves
	 */
	private ArrayList<Move> findOpenDiagonals(Move possibleMove) {
		ArrayList<Move> goodMoves = new ArrayList<Move>();
		PLAYER[][] board = getBoard();
		setForwards(board[possibleMove.getRow()][possibleMove.getColumn()]);
		
		Move aMove = new Move(possibleMove.getRow() + forwards, possibleMove.getColumn() +1);
		if (isInBound(aMove) && isEmpty(aMove))
			goodMoves.add(aMove);
		
		aMove.setColumn(possibleMove.getColumn() - 1);
		if (isInBound(aMove) && isEmpty(aMove))
			goodMoves.add(aMove);
		
		if(queenedPieces.contains(possibleMove))
		{ //check all directions
			aMove.setRow(possibleMove.getRow() + forwards * -1);
			aMove.setColumn(possibleMove.getColumn() + 1);
			if (isInBound(aMove) && isEmpty(aMove))
				goodMoves.add(aMove);
			
			aMove.setColumn(possibleMove.getColumn() - 1);
			if (isInBound(aMove) && isEmpty(aMove))
				goodMoves.add(aMove);
		}
		return goodMoves;
	}

	private ArrayList<Move> findHopDiagonals(Move justMoved) {
		ArrayList<Move> goodMoves = new ArrayList<Move>();
		PLAYER[][] board = getBoard();
		setForwards(board[justMoved.getRow()][justMoved.getColumn()]);
		
		Move enemy = new Move(justMoved.getRow() + forwards, justMoved.getColumn() +1);
		Move jump;
		if (isInBound(enemy) && isEnemy(enemy))
		{
			jump = new Move (enemy.getRow() + forwards, enemy.getColumn() +1);
			if(isInBound(jump) && isEnemy(jump))
				goodMoves.add(jump);
		}
		
		enemy.setColumn(justMoved.getColumn() - 1);
		if (isInBound(enemy) && isEmpty(enemy))
			goodMoves.add(enemy);
		
		if(queenedPieces.contains(justMoved))
		{ //check all directions
			enemy.setRow(justMoved.getRow() + forwards * -1);
			enemy.setColumn(justMoved.getColumn() + 1);
			if (isInBound(enemy) && isEmpty(enemy))
				goodMoves.add(enemy);
			
			enemy.setColumn(justMoved.getColumn() - 1);
			if (isInBound(enemy) && isEmpty(enemy))
				goodMoves.add(enemy);
		}
		return goodMoves;
	}
	
	private boolean isEnemy(Move enemy) {
		if(getCell(enemy).equals(this.getEnemy()))
			return true;
		return false;
	}

	private boolean isEmpty(Move aMove) {
		if(getCell(aMove) == PLAYER.EMPTY)
			return true;
		return false;
	}	

	private boolean isInBound(Move aMove) {
		int x = aMove.getColumn();
		int y = aMove.getRow();
		if(x >= 0 && x < getDimensions())
			if(y >= 0 && y < getDimensions())
				return true;
		return false;
		//The reason for this nesting style is that the column bounds are more likely to be violated in this game
		//if that is the case, the program only has to execute 2 comparisons instead of 4, speeding up this subroutine.
	}

	@Override
	public Board getClone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean attemptMove(Move move) {


		//check if piece is queened
		//update queened list
		return false;
	}

	@Override
	protected PLAYER hasBeenWon() {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	public void setForwards(PLAYER player) {
		if(player.equals(PLAYER.PLAYER1))
		{
			forwards = 1;
		}
		else if(player.equals(PLAYER.PLAYER2))
		{
			forwards = -1;
		}
	}

}
