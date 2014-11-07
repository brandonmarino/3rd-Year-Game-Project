package PlayerTypes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import PlayerTypes.RandomPlayerType;

import org.junit.Before;
import org.junit.Test;

/**
 * *********************************************************************************************************************************************************
 * 									RandomPlayerTypeTEST 
 * ***********************************************************************************************************************************************************
 *
 * Author: Osama Buhamad 
 * -test for RandomPlayerTypeTEST Class
 * 
 */

public class RandomPlayerTypeTEST {
	private PlayerType randomPlayer; 
	
	 	@Before
	    public void setUp() {
		 randomPlayer = new RandomPlayerType();
	    }

	 	@Test
	    public void testGetMove() {
	        // test with case not enough available moves
	        ArrayList<Integer[]> availableMoves = new ArrayList<Integer[]>();
	        randomPlayer.setAvailableMoves(availableMoves);
	        assertNull(randomPlayer.getMove());
	        
	        // test with case have an available moves
	        availableMoves.add(new Integer[] { 1 });
	        randomPlayer.setAvailableMoves(availableMoves);
	        int move = randomPlayer.getMove()[0];
	        assertEquals(1, move);
	        assertTrue(randomPlayer.getAvailableMoves().isEmpty());
	        
	        // test another case
	        availableMoves.add(new Integer[] { 1 });
	        availableMoves.add(new Integer[] { 2 });
	        randomPlayer.setAvailableMoves(availableMoves);
	        randomPlayer.getMove();
	        assertEquals(1, randomPlayer.getAvailableMoves().size());
	        randomPlayer.getMove();
	        assertTrue(randomPlayer.getAvailableMoves().isEmpty());
	 }

}
