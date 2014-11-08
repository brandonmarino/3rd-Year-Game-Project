package GUI;

import java.awt.*;

import javax.swing.Icon;
import javax.swing.JButton;

import Boards.Board.PLAYER;

/**
 * 
 * @author Pheonix
 * Extension of JButton that stores it's location on a grid
 */


public class OthelloButton extends JButton//I would like to extend JButton here but when I did, a bug showed up where instances of OthelloButton would only be visible on mouseover. No visible online fix.
{
	private int xOnGrid, yOnGrid;
	private Icon image;
	private PLAYER player;
	
	
	/**
	 * Constructor
	 * @param x stores the x coordinate of the button
	 * @param y stores the y coordinate of the button
	 * @param aPlayer is starting PLAYER value
	 */
	public OthelloButton(int x, int y, PLAYER aPlayer)
	{		
		super();
		xOnGrid = x;
		yOnGrid = y;
		setPlayer(aPlayer);		
	}
		
	/**Will be useful later if we want to have fancier looking buttons.
	 * It will change the icon on the button with the appropriate file
	 * @param aPlayer PLAYER who's icon will be on this button.
	 */
	public void setIcon(PLAYER aPlayer)
	{
		player = aPlayer;
		validate();
		repaint();
	}
	
	public PLAYER getPlayer()
	{
		return player;
	}
	
	public void setPlayer(PLAYER aPlayer)
	{
		player = aPlayer;
		setIcon(aPlayer);
	}
	
	/**
	 * Will be useful later
	 * See setIcon()
	 */
	public void flipIcon()
	{
		if (player == PLAYER.PLAYER1)
        {
            setPlayer(PLAYER.PLAYER2);
        }     
        else if (player == PLAYER.PLAYER2)
        {
        	setPlayer(PLAYER.PLAYER1);
        }
		validate();
		repaint();
	}
	
	public int getX(){return xOnGrid;}
	public int getY(){return yOnGrid;}
	
	
	
	@Override
	public String toString()
	{
		return "X: "+ getX() + "  Y: " + getY() + "   Player: " + getPlayer();
	}
	
	
	/**
	 * Draws the correct collour of ellipse if a player is selected
	 * 
	 * @param g graphics for button
	 */	
	@Override
	public void paintComponent(Graphics g)
    {
        if(player == PLAYER.EMPTY)
            super.paintComponent(g);
        else if (player == PLAYER.PLAYER1) //player1 is always black in othello
        {
             g.setColor(Color.BLACK);
             g.fillOval(getHorizontalAlignment(), getVerticalAlignment(), getWidth(), getHeight());
        }     
        else if (player == PLAYER.PLAYER2)
        {
             g.setColor(Color.WHITE);
             g.fillOval(getHorizontalAlignment(), getVerticalAlignment(), getWidth(), getHeight());
        }   
    }
	
}
