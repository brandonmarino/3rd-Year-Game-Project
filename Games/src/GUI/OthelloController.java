package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import Boards.*;
import Boards.Board.PLAYER;
import Games.Othello;
import PlayerTypes.PlayerType;
import common.Move;

public class OthelloController extends PlayerType  implements ActionListener {
	
	private String playerName;						//Name of player
	private OthelloBoard model;						//Model for MVC
	private PLAYER myPlayer;						//This player (to keep track of who has control over the burrons
	private Move selection = new Move();			//Grid selection for a move

	
	/**
	 * Constructor with only a name. Can be removed. I don't think anybody is calling it.
	 * @param name: Player name
	 */
	public OthelloController(String name) {
		playerName = name;
		model = null;		
	}
	
	/**
	 * Controller determines what player this was called for using playerNumber and stores the model for further calling
	 * @param name: Player name
	 * @param boardGame: Model that is being controlled
	 * @param playerNumber: used for myPlayer
	 */
	public OthelloController(String name, Board boardGame, int playerNumber) {
		playerName = name;
		if(playerNumber == 0) myPlayer = PLAYER.PLAYER1;
		else myPlayer = PLAYER.PLAYER2;
		model = (OthelloBoard) boardGame;		
	}
	
	/**
	 * only useful if single param controller is called
	 * @param othello
	 */
	public void setModel(Board othello)
	{
		model = (OthelloBoard) othello;
	}
	
	/**
	 * I don't think anything uses this function
	 * @return
	 */
	public Board getModel()
	{
		return model;
	}
	
	/**
	 * Used by Game for winning clause printout.
	 */
	public String getName()
	{
		return playerName;
	}
	

	/**
	 * Controls every button only when it's this player's turn. Gets the coordinate of the button and puts it in selection
	 * @param arg0: The pressed button.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		OthelloButton button = (OthelloButton)arg0.getSource();
		if (model.getCurrentPlayer() == myPlayer)
		{
			selection.setColumn( button.getX() + 1 );
			selection.setRow( button.getY() + 1 );
		}
	}
	
	/**
	 * Gets a pr0per selection from the frame by waiting for a button who's coordinates match an available move 
	 * to be pressed and makes the selection
	 */
	@Override
	public Move getMove() {
		ArrayList<Move> moves = getAvailableMoves();
		if (moves.isEmpty()) return null;
		boolean goodMove = false;
		Move move = null;
		do
		{
				move = selection;
				for(Move possibleMove : moves)
				{
					if(possibleMove.getRow() + 1 == move.getRow() && possibleMove.getColumn() + 1 == move.getColumn())
					{
						goodMove = true;
						selection = new Move();
						move = possibleMove;
					}
				}
				
		}
		while(goodMove == false); 
		return popMove(moves.indexOf(move));
	}

	
	public static void main(String[] args)
	{
		Othello game = new Othello();								//Create game
		OthelloBoard model = (OthelloBoard)(game.getBoard());						//Get model from game
		OthelloFrameView view = new OthelloFrameView();				//Create view
		view.setSize(model.getDimensions());						//Size view to scale with model size
		for(PlayerType player : game.getPlayers())					//Create controller(s)
		{
			if (player.getClass() == OthelloController.class)		//1 controller per player
			{
				view.addController((OthelloController)player);
			}
		}
		game.addObserver(view);
		view.update(game, game.getBoard());							//init board setup on gui
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//init gui
		view.pack();
		view.setVisible(true);
		game.play();												//play the game     ya    play  (Shrek)
	}
}
