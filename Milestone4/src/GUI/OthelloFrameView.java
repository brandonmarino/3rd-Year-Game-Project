package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import Boards.Board;
import Boards.Board.PLAYER;
import Boards.OthelloBoard;
import Games.*;



public class OthelloFrameView extends JFrame implements Observer
{	
	protected JPanel othelloGamePane;			//Contains the game grid

	/**
	 * Creates two panels to be further used for menu-like tasks like restarting and naming players.
	 */
	public OthelloFrameView()
	{
		super("Othello");		
		setLayout(new BorderLayout());
		JPanel westOptions = new JPanel();
		JPanel northOptions = new JPanel();
		othelloGamePane = new JPanel();
		
		add(westOptions, BorderLayout.WEST);
		add(northOptions, BorderLayout.NORTH);					
	}
	
	/**
	 * Scales the gui to the game board size (4X4 or 8X8 or any ?X?) creates the appropriate about of buttons
	 * and places them in a logical order that resembles the way the panel will see the grid.
	 * @param dimensions a size for the board
	 */
	public void setSize(int dimensions)
	{
		othelloGamePane.setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.ipady = 80;
		constraint.ipadx = 80;
		for(int x = 0; x < dimensions; x++)
			for(int y = 0; y < dimensions; y++) {
				OthelloButton button = new OthelloButton(x, y, PLAYER.EMPTY);
				button.setName(x+ " " + y);
				constraint.gridx = x;
				constraint.gridy = y;
				othelloGamePane.add(button, constraint);	
			}
		add(othelloGamePane);
	}
	
	/**
	 * Adds a controller(ActionListener) to all the buttons
	 * in order to enable multiple controllers (human players) for a single game.  
	 * @param cont
	 */
	public void addController(OthelloController cont)
	{
		Component[] buttons = othelloGamePane.getComponents();
		for(Component button : buttons)
		{
			((OthelloButton)button).addActionListener(cont);
		}
	}


	public JPanel getGamePane()
	{
		return othelloGamePane;
	}

	
	/**
	 * Sets all buttons to the appropriate player as reflected in arg
	 * @param o: Game being played
	 * @param arg: Board being played on
	 */
	@Override
	public void update(Observable o, Object arg) 
	{
		PLAYER[][] pieces = ((Board)arg).getBoard();
		Component[] buttons = othelloGamePane.getComponents();
		OthelloButton button;
		for(Component comp : buttons)
		{
			button = (OthelloButton) comp;
			if(button.getPlayer() != pieces[button.getY()][button.getX()])
				button.setPlayer(pieces[button.getY()][button.getX()]); //add a wait function here for display effects.
		}
	}
}
