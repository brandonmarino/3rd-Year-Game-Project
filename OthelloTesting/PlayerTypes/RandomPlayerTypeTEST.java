package Testing;


import static org.junit.Assert.*;

import java.util.ArrayList;

import PlayerTypes.PlayerType;
import PlayerTypes.RandomPlayerType;

import org.junit.Before;
import org.junit.Test;

import common.Move;

/**
 * *********************************************************************************************************************************************************
 * 									RandomPlayerTypeTEST 
 * ***********************************************************************************************************************************************************
 *
 * Author: Osama Buhamad 
 * -test for RandomPlayerTypeTEST Class
 * 
 * Edited by: Lina El Sadek
 * for Milestone 3 
 */

public class RandomPlayerTypeTest {
	private PlayerType randomPlayer; 
	
	 	@Before
	    public void setUp() {
		 randomPlayer = new RandomPlayerType(0);
	    }

	 	@Test
	    public void testGetMove() {
	        // test with case not enough available moves
	        ArrayList<Move> availableMoves = new ArrayList<Move>();
	        randomPlayer.setAvailableMoves(availableMoves);
	        assertNull(randomPlayer.getMove());
	        
	        // test with case have an available moves
	        availableMoves.add(new Move( 1,2 ));
	        randomPlayer.setAvailableMoves(availableMoves);
	        Move move = randomPlayer.getMove();
	        Move temp = new Move(1,2);
	        assertEquals(temp, move);
	        //assertTrue(randomPlayer.getAvailableMoves().isEmpty());
	        
	        // test another case
	        availableMoves.add(new Move (1,2));
	        availableMoves.add(new Move(2,1));
	        randomPlayer.setAvailableMoves(availableMoves);
	        randomPlayer.getMove();
	       // assertEquals(1, randomPlayer.getAvailableMoves().size());
	        randomPlayer.getMove();
	       // assertTrue(randomPlayer.getAvailableMoves().isEmpty());
	        
	        availableMoves.add(new Move(2,3));
	        randomPlayer.setAvailableMoves(availableMoves);
	       Move move1 = randomPlayer.getMove();
	        assertNotEquals(temp, move1);	//testing for an invalid value
	        temp = null;
	        assertNull(temp);
	        assertNotEquals(temp, move1);
	        temp = move1;
	        assertEquals(temp, move1);
	        assertNotNull(temp);
	        
	       Move anotherMove = new Move();
	       temp.setRow(0);
	       temp.setColumn(0);
	       assertNotEquals(temp, anotherMove);
	       temp.setRow(-1);
	       temp.setColumn(-1);
	       assertEquals(temp, anotherMove);
	 }

}
