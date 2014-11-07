package PlayerTypes;

import java.util.ArrayList;
import PlayerTypes.HumanPlayerType;
import org.junit.Before;
import org.junit.Test;

/**
 * *********************************************************************************************************************************************************
 * 									HumanPlayerTypeTEST 
 * ***********************************************************************************************************************************************************
 *
 * Author: Osama Buhamad 
 * -test for HumanPlayerTypeTEST Class
 * 
 */

public class HumanPlayerTypeTEST {
	 private PlayerType humanPlayerMove;

	    @Before
	    public void setUp() {
	        humanPlayerMove = new HumanPlayerType("Dummy Player");
	    }

	    @Test
	    public void testGetMove() {
	        // test with case not enough available moves
	        ArrayList<Integer[]> availableMoves = new ArrayList<Integer[]>();
	        humanPlayerMove.setAvailableMoves(availableMoves);
	    }

}
