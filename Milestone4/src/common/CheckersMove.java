package common;

/**
 * Checkers moves have an origin and a destination. The origin will be this move object and
 * this will also contain its destination move.
 * @author Pheonix
 *
 */
public class CheckersMove extends Move {
	private Move dest;
	public CheckersMove (Move from, Move to)
	{
		setRow(from.getRow());
		setColumn(from.getColumn());
		setWorth(from.getWorth());
		dest = to;
	}
	
	/**
	 * @return the dest
	 */
	public Move getDest() {
		return dest;
	}
	/**
	 * @param dest the dest to set
	 */
	public void setDest(Move dest) {
		this.dest = dest;
	}

}
