package Boards;

import java.util.ArrayList;
import java.util.Set;

import common.CheckersMove;
import common.Move;

public class CheckersBoard extends Board {

	private int forwards, deadRed, deadBlack;
	private boolean blackMoved; //pay attention to if the black player made a move on their last attempt
    private boolean redMoved; // pay attention to if the red player made a move on their last attempt
	private Set<Move> kingedPieces;
	
	public CheckersBoard() {
		super(8);
		setPlayerTiles('B', 'R'); // Black moves first
		setUpBoard(getDimensions());
		deadRed = 0;
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
		
		if(kingedPieces.contains(possibleMove))
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
			if(isInBound(jump) && isEmpty(jump))
				goodMoves.add(jump);
		}
		
		enemy.setColumn(justMoved.getColumn() - 1);
		if (isInBound(enemy) && isEnemy(enemy))
		{
			jump = new Move (enemy.getRow() + forwards, enemy.getColumn() -1);
			if(isInBound(jump) && isEmpty(jump))
				goodMoves.add(jump);
		}
		
		if(kingedPieces.contains(justMoved))
		{ //check all directions
			enemy.setRow(justMoved.getRow() + forwards * -1);
			enemy.setColumn(justMoved.getColumn() +1);
			if (isInBound(enemy) && isEnemy(enemy))
			{
				jump = new Move (enemy.getRow() + forwards * -1, enemy.getColumn() +1);
				if(isInBound(jump) && isEmpty(jump))
					goodMoves.add(jump);
			}
			
			enemy.setRow(justMoved.getRow() + forwards * -1);
			enemy.setColumn(justMoved.getColumn() -1);
			if (isInBound(enemy) && isEnemy(enemy))
			{
				jump = new Move (enemy.getRow() + forwards * -1, enemy.getColumn() -1);
				if(isInBound(jump) && isEmpty(jump))
					goodMoves.add(jump);
			}
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
		CheckersBoard cloneBoard = new CheckersBoard();
		cloneBoard = (CheckersBoard) super.getClone(cloneBoard);
		cloneBoard.setBlackMoved(isBlackMoved());
		cloneBoard.setRedMoved(isRedMoved());
		return cloneBoard;
	}

	@Override
	public boolean attemptMove(Move move) {
		CheckersMove moveFrom = (CheckersMove) move;
		Move moveTo = moveFrom.getDest();
		PLAYER[][] board = getBoard();
		if (hasJumped(moveFrom))
		{
			board[ moveFrom.getRow() + ( (moveTo.getRow() - moveFrom.getRow()) /2) ][ moveFrom.getColumn() + ( (moveTo.getColumn() - moveFrom.getColumn()) /2) ] = PLAYER.EMPTY;
			if(getEnemy() == PLAYER.PLAYER1)
			{
				deadBlack += 1;
			}
			else
			{
				deadRed += 1;
			}
		}
		board[moveFrom.getRow()][moveFrom.getColumn()] = PLAYER.EMPTY;
		board[moveTo.getRow()][moveTo.getColumn()] = getCurrentPlayer();
		
		//check if piece is kinged
		if(moveTo.getRow() == 0 || moveTo.getRow() == getDimensions() -1)
		{
			kingedPieces.add(moveTo);
		}
		//update kinged list
		if(kingedPieces.contains(moveFrom))
		{
			kingedPieces.remove(moveFrom);
			kingedPieces.add(moveTo);
		}
		setForwards(getEnemy());
		return true;
	}

	private boolean hasJumped(CheckersMove aMove) {
		if(Math.abs(aMove.getColumn() - aMove.getDest().getColumn()) > 1)
			return true;
		return false;
	}

	@Override
	protected PLAYER hasBeenWon() {
		if(deadRed >= 12)
			return PLAYER.PLAYER1;
		else if (deadBlack >= 12)
			return PLAYER.PLAYER2;
		else if (getPossibleMoves().isEmpty())
			return PLAYER.EMPTY;////////////////////////////////////////////
		return null;
	}
	
	
	public void setMoved(Boolean set){
        switch(getCurrentPlayer()){
            case PLAYER1:
                blackMoved = set;
                break;
            case PLAYER2:
                redMoved = set;
                break;
        }
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
	
	/**
	 * @return the blackMoved
	 */
	public boolean isBlackMoved() {
		return blackMoved;
	}

	/**
	 * @param blackMoved the blackMoved to set
	 */
	public void setBlackMoved(boolean blackMoved) {
		this.blackMoved = blackMoved;
	}

	/**
	 * @return the redMoved
	 */
	public boolean isRedMoved() {
		return redMoved;
	}

	/**
	 * @param redMoved the redMoved to set
	 */
	public void setRedMoved(boolean redMoved) {
		this.redMoved = redMoved;
	}

}
