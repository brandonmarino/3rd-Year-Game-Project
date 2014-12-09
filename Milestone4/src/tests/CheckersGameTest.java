package tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;





import org.junit.Before;
import org.junit.Test;

import Games.Checkers;
import PlayerTypes.HumanPlayerType;
import PlayerTypes.PlayerType;
import PlayerTypes.RandomPlayerType;

/**
 * 
 * @Edited by Lina El Sadek
 *
 */
public class CheckersGameTest {
	
	Checkers checkers;
	
	@Before
    public void setUp() throws UnsupportedEncodingException {
		
		
		InputStream old = System.in;
		try {
			String data = "2\r\n2";
			InputStream testInput = new ByteArrayInputStream(data.getBytes("UTF-8"));
		
			System.setIn(testInput);
			
			checkers = new Checkers();
			
		
		} finally {
			System.setIn(old);
		}
		
    }
	

	@Test
	public void test() {
		 assertEquals(2, checkers.getPlayers().length);
		 PlayerType[] p = checkers.getPlayers();
		 for (int i = 0; i < p.length; i++) {
			 assertEquals(checkers.getPlayers()[i] instanceof RandomPlayerType, false);
		 }
	}

}
