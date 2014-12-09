package tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;



import org.junit.Before;
import org.junit.Test;

import Games.Checkers;
import PlayerTypes.RandomPlayerType;

public class CheckersGameTest {
	
	Checkers checkers;
	
	// Test for CheckersGame class 
	@Before
    public void setUp() throws UnsupportedEncodingException {
		
		// to simulate players moves 
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
	//testing the number of players and making Random palyer 
	@Test
	public void test() {
		 assertEquals(2, checkers.getPlayers().length);
		 for (int i = 0; i < checkers.getPlayers().length; i++) {
			 assertEquals(checkers.getPlayers()[0] instanceof RandomPlayerType, true);
		 }
	}

}
