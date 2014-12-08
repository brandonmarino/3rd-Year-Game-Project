package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import Boards.*;
import Boards.Board.PLAYER;
import Games.Othello;
import PlayerTypes.HumanPlayerType;
import PlayerTypes.PlayerType;
import common.Move;

public class OthelloController extends PlayerType implements ActionListener {

	private PLAYER myPlayer;						//This player (to keep track of who has control over the burrons
	private Move selection = new Move();			//Grid selection for a move

	
	/**
	 * Controller determines what player this was called for using playerNumber and stores the model for further calling
	 * @param player Some Human that wishes to play through a GUI
	 */
	public OthelloController(HumanPlayerType player) {
		super(player.getBoard(), player.getNumber());
		setName(player.getName());
		if(player.getNumber() == 0) myPlayer = PLAYER.PLAYER1;
		else myPlayer = PLAYER.PLAYER2;
	}


	/**
	 * Controls every button only when it's this player's turn. Gets the coordinate of the button and puts it in selection
	 * @param arg0: The pressed button.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		OthelloButton button = (OthelloButton)arg0.getSource();
		if (getBoard().getCurrentPlayer() == myPlayer) {
			selection.setColumn( button.getX() + 1 );
			selection.setRow( button.getY() + 1 );
		}
	}
	
	/**
	 * Gets a pr0per selection from the frame by waiting for a button who's coordinates match an available move 
	 * to be pressed and makes the selection
	 */
	@Override
	public Move getMove(Games.Game game) throws common.GameTerminatedException{
		ArrayList<Move> moves = getAvailableMoves();
		if (moves.isEmpty()) return null;
		boolean goodMove = false;
		Move move = null;
		do {
			move = selection;
			for(Move possibleMove : moves) {
				if (possibleMove.getRow() + 1 == move.getRow() && possibleMove.getColumn() + 1 == move.getColumn()) {
					goodMove = true;
					selection = new Move();
					move = possibleMove;
				}
			}
		}
		while(!goodMove);
		return popMove(moves.indexOf(move));
	}

	
	public static void main(String[] args)
	{
		Othello game = new Othello();								//Create game
		OthelloBoard board = (OthelloBoard)(game.getBoard());						//Get model from game
		OthelloFrameView view = new OthelloFrameView();				//Create view
		view.setSize(board.getDimensions());						//Size view to scale with model size
		for(PlayerType player : game.getPlayers())					//Create controller(s)
			if (player.getClass() == OthelloController.class)		//1 controller per player
				view.addController((OthelloController)player);
		game.addObserver(view);
		view.update(game, game.getBoard());							//init board setup on gui
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//init gui
		view.pack();
		view.setVisible(true);
		game.isGUI = true;
		game.play();												//play the game     ya    play  (Shrek)
	}
}
