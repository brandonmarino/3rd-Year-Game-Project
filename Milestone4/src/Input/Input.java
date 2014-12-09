package Input;

import java.util.Scanner;

/**
 * 
 * @author Lina El Sadek
 *
 */
public class Input {
	private static Scanner in = null;
	
	public static Scanner getInstance() {
		if (in == null) {
			in = new Scanner(System.in);
		}
		return in;
	}
}
