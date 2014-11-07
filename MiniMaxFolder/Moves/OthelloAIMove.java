package Moves;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;







import Boards.Board.PLAYER;
import Boards.OthelloBoard;
import Games.Othello;

public class OthelloAIMove extends Move {
	
	
	
	
	private int i = 0;
	private int j = 0;
	
	public OthelloAIMove(int i, int j)
	{
		
		this.i = i;
		this.j = j;
	}
	
	private void switchSingleDirection(Othello game, int i, int j, int h, int v, boolean draw) {
		while (game.getBoard().getCell(i+v, j+h) == game.getBoard().getcurrentPlayer()) {
			game.getBoard().attemptMove(i+v, j+h);
			i = i + v;
			j = j + h;
		}
	}
	
	private String name = "";

    /**
     * Create a human player
     * @param name the name of the human..
     */
    public OthelloAIMove(String name){
        this.name = name;
    }
	
		
		public Integer[] getMove(){
	        ArrayList<Integer[]> moves = getAvailableMoves();

	        if (moves.isEmpty())   //player cannot move
	            return null;

	        System.out.println(name.toUpperCase()+"'S TURN");
	        printMoves(moves);
	        int choice = getChoice(moves.size());
	        return popMove(choice);
	    
	}
		private void printMoves(ArrayList<Integer[]> moves){
	        System.out.println("Currently Available Moves: ");
	        int choice = 0;
	        for(Integer[] move: moves){
	            choice++;
	        System.out.println(choice + ": Row " + (move[0]+1) +", Column "+ (move[1]+1) );
	    }
		}
		
	    private int getChoice(int options){
	        int choice = 0;
	        while(true){
	            try{
	                System.out.println("Please choose one of the options by number:");
	                Scanner user_choice = new Scanner(System.in);
	                String input = user_choice.next();
	                choice = Integer.parseInt(input);
	                if (choice > options)
	                    throw new NumberFormatException();
	                break;
	            }catch(NumberFormatException e){
	                System.out.println("Not a valid option, try again!");
	            }
	        }
	        return choice -1;
	    }
	
	public Vector<OthelloAIMove> getLegalMoves(int player, Othello game) {
		Vector<OthelloAIMove> legalMoves = new Vector<OthelloAIMove>();
		for (int i = 0; i < game.getBoardDim(); i++) {
			for (int j = 0; j < game.getBoardDim(); j++) {
				if (testLegal(i, j, player, game)) {
					legalMoves.addElement(new OthelloAIMove(i, j));
				}
			}
		}
		return legalMoves;
	}
	
	private boolean testLegal(int i, int j, int player, Othello game) {
		// valid coordinates
		if (i < 0 || i >= game.getBoardDim() || j < 0 || j >= game.getBoardDim()) {
			return false;
		}
		// square is free
		if (game.getBoard().getCell(i, j) != PLAYER.EMPTY) {
			return false;
		}
		// it produce at least a switch
		if (testSingleDirection(i, j, -1, -1,game, game.getBoard().getcurrentPlayer())) {
			return true;
		}
		if (testSingleDirection(i, j, -1, 0,game, game.getBoard().getcurrentPlayer())) {
			return true;
		}
		if (testSingleDirection(i, j, -1, +1,game, game.getBoard().getcurrentPlayer())) {
			return true;
		}
		if (testSingleDirection(i, j, 0, -1,game, game.getBoard().getcurrentPlayer())) {
			return true;
		}
		if (testSingleDirection(i, j, 0, +1,game,  game.getBoard().getcurrentPlayer())) {
			return true;
		}
		if (testSingleDirection(i, j, +1, -1, game, game.getBoard().getcurrentPlayer())) {
			return true;
		}
		if (testSingleDirection(i, j, +1, 0, game, game.getBoard().getcurrentPlayer())) {
			return true;
		}
		if (testSingleDirection(i, j, +1, +1,game, game.getBoard().getcurrentPlayer())) {
			return true;
		}
		return false;
	}
	
	private boolean testSingleDirection(int i, int j, int h, int v,Othello game,  PLAYER p) {
		
		PLAYER PTemp = null;
		if (p == PTemp)
			PTemp = p.PLAYER2;
		else
			PTemp = p.PLAYER1;
		
		boolean atLeastOne = false;
		while (i+v < game.getBoardDim()&& j+h < game.getBoardDim() &&
			   i+v >= 0 && j+h >= 0 &&
			   game.getBoard().getCell(i+v, j+h) == PTemp) {
			
			i = i + v;
			j = j + h;
			atLeastOne = true;
		}
		if (i+v < game.getBoardDim() && j+h < game.getBoardDim() &&
			i+v >= 0 && j+h >= 0 &&
			atLeastOne &&
			game.getBoard().getCell(i+v, j+h) == PTemp) {
			
			return true;
		}
		return false;
	}

	
	
	
}
