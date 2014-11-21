package Boards;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import common.CheckersMove;
import common.Move;

public class CheckersBoard extends Board {

	private int forwards, deadRed, deadBlack;
	private boolean blackMoved; //pay attention to if the black player made a move on their last attempt
    	private boolean redMoved; // pay attention to if the red player made a move on their last attempt
	private ArrayList<Move> kingedPieces;
	
	public CheckersBoard() {
		super(8);
		setPlayerTiles('B', 'R'); // Black moves first
		setUpBoard(getDimensions());
		deadRed = 0;
		deadBlack = 0;	
		kingedPieces = new ArrayList<Move>();
	}

	private void setUpBoard(int dim) {
		ArrayList<PLAYER> players = new ArrayList<PLAYER>();
		players.add(PLAYER.PLAYER1);
		players.add(PLAYER.PLAYER2);
		for(PLAYER player : players)
		{
			setForwards(player);
			int rowStart = 0;
			if(player.equals(PLAYER.PLAYER2))
				rowStart = dim -1; 
			for(int row = rowStart; row != (dim-2)/2 && row != (dim-2)/2 +1 ; row += forwards)
			{
				if (row % 2 == 1){
					setCell( player, new Move(row,0));
					setCell( player, new Move(row,dim/4));
					setCell( player, new Move(row,dim/2));
					setCell( player, new Move(row,dim-2));
				}
				else{
					setCell( player, new Move(row,(dim/4) -1));
					setCell( player, new Move(row,(dim/2) -1));
					setCell( player, new Move(row,(dim/2) +1));
					setCell( player, new Move(row,dim -1));
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
     * Will scan the current board and find all of the current occupied places
     * 
     * @return a list of all occupied spaces on the board
     */
    protected ArrayList<Move> getFullSpaces() {
        ArrayList<Move> fullSpaces = new ArrayList<Move>();
        PLAYER[][] board = getBoard();
        for (int currentRow = 0; currentRow < DIMENSIONS; currentRow++) {
            for (int currentColumn = 0; currentColumn < DIMENSIONS; currentColumn++) {
                if (board[currentRow][currentColumn] == getCurrentPlayer()) {
                    Move ret = new Move(currentRow, currentColumn);
                    fullSpaces.add(ret);
                }
            }
        }
        return fullSpaces;
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
		if (isInBound(aMove) && isEmpty(aMove)){
			//System.out.println("After1: Row: " + aMove.getRow() + "    Column: " + aMove.getColumn());
			goodMoves.add(new Move(aMove.getRow(), aMove.getColumn()));}
		
		aMove = new Move (possibleMove.getRow() + forwards, possibleMove.getColumn() - 1);
		if (isInBound(aMove) && isEmpty(aMove)){
			//System.out.println("After2: Row: " + aMove.getRow() + "    Column: " + aMove.getColumn());
			goodMoves.add(new Move(aMove.getRow(), aMove.getColumn()));}
		
		if(kingedPieces.contains(possibleMove))
		{ //check all directions
			aMove = new Move(possibleMove.getRow() + (forwards * -1), possibleMove.getColumn() + 1);
			if (isInBound(aMove) && isEmpty(aMove))
				//System.out.println("BackMove: " + aMove.getRow()+ "  " + aMove.getColumn());
				goodMoves.add(new Move(aMove.getRow(), aMove.getColumn()));
			
			aMove = new Move(possibleMove.getRow() + (forwards * -1), possibleMove.getColumn() - 1);
			if (isInBound(aMove) && isEmpty(aMove))
				//System.out.println("BackMove: " + aMove.getRow()+ "  " + aMove.getColumn());
				goodMoves.add(new Move(aMove.getRow(), aMove.getColumn()));
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
			if(isInBound(jump) && isEmpty(jump)){
				//System.out.println("Here");
				goodMoves.add(new Move(jump.getRow(), jump.getColumn()));}
		}
		
		enemy = new Move(justMoved.getRow() + forwards, justMoved.getColumn() - 1);
		if (isInBound(enemy) && isEnemy(enemy))
		{
			jump = new Move (enemy.getRow() + forwards, enemy.getColumn() -1);
			if(isInBound(jump) && isEmpty(jump)){
				//System.out.println("Here1");
				goodMoves.add(new Move(jump.getRow(), jump.getColumn()));}
		}
		
		if(kingedPieces.contains(justMoved))
		{ //check all directions
			enemy = new Move (justMoved.getRow() + (forwards * -1), justMoved.getColumn() +1);
			if (isInBound(enemy) && isEnemy(enemy))
			{
				jump = new Move (enemy.getRow() + (forwards * -1), enemy.getColumn() +1);
				if(isInBound(jump) && isEmpty(jump)){
					//System.out.println("Here2");
					goodMoves.add(new Move(jump.getRow(), jump.getColumn()));}
			}
			
			enemy = new Move (justMoved.getRow() + (forwards * -1), justMoved.getColumn() -1);
			if (isInBound(enemy) && isEnemy(enemy))
			{
				jump = new Move (enemy.getRow() + (forwards * -1), enemy.getColumn() -1);
				if(isInBound(jump) && isEmpty(jump)){
					//System.out.println("Here3");
					goodMoves.add(new Move(jump.getRow(), jump.getColumn()));
				}
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
		//System.out.println("Source: Row: " + moveTo.getRow() + "    Column: " + moveTo.getColumn());
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
		//System.out.println("Origin: Row: " + moveFrom.getRow() + "    Column: " + moveFrom.getColumn());
		//System.out.println("Destination: Row: " + moveTo.getRow() + "    Column: " + moveTo.getColumn());
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
			return getEnemy();
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
	            case EMPTY:
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


    /*****************************************************************************************************************************************************************
     *             State testing functions
     * ****************************************************************************************************************************************************************/

    private boolean canBeJumped(Move move){
        if (move instanceof CheckersMove){
            Move dest = ((CheckersMove) move).getDest();
            for(Move currentEnemy: findadjacentEnemies(dest)){
                Board testBoard = this.getClone();

                ArrayList<Move> testMoves = testBoard.getPossibleMoves();
                CheckersMove testmove = new CheckersMove(new Move(), new Move());
                for (Move currentMove:testMoves){
                    if (move.equals(currentMove))
                        testmove = (CheckersMove)currentMove;
                }
                testBoard.switchcurrentPlayer();
                testBoard.attemptMove(testmove);
                if (getCurrentPlayer() == PLAYER.PLAYER1){
                    if  (((CheckersBoard)testBoard).getDeadBlack() > deadBlack)
                        return true;
                }
                else if(getCurrentPlayer() == PLAYER.PLAYER2){
                    if  (((CheckersBoard)testBoard).getDeadRed() > deadRed)
                        return true;
                }
            }
        }
        return false;
    }
    private int countJump(Move move){
        int jumpTotal = 0;
        //jumpTotal = ((CheckersMove)move).getJumpCount();
        return jumpTotal;
    }
    private boolean canKing(Move move){
        if (move instanceof CheckersMove){
            if (!kingedPieces.contains(move)){
                switch(getCurrentPlayer()){
                    case PLAYER1:
                        if(((CheckersMove) move).getDest().getRow() == DIMENSIONS-1)
                            return true;
                        break;
                    case PLAYER2:
                        if(((CheckersMove) move).getDest().getRow() == 0)
                            return true;
                        break;
                }
            }
        }
        return false;
    }
    private int LeftOrRight(Move move){
        int distance = 0;
        if (move instanceof CheckersMove){
            Move destination = ((CheckersMove) move).getDest();
                distance = move.getColumn() - destination.getColumn();
        }
        return Math.abs(distance);
    }
    protected int getDeadBlack(){ return deadBlack;}
    protected int getDeadRed(){ return deadRed;}

    public int getStateWorth(){
        Move move = lastMove;
        int jumpable = 0;
        int kingable = 0;
        if (canBeJumped(move))  jumpable = 1;
        int jumpCount = countJump(move);
        if (canKing(move))  kingable = 1;
        //only jump if you can get two or more out of the opponent
        return kingable*100 + jumpable*20 + jumpCount*10 + LeftOrRight(move);
    }
}
