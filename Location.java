/*
 * This class represents a pairing of a sequence number
 * and a position within that sequence.
 * 
 * Created by: Kevin H. Bhimani
 * Date: November 11, 2017
*/

public class Location {
	// instance variables
	private int index;
	private int position;

	/**
	 * Generates a location where a substring is located.
	 * 
	 * @param ind
	 *            The number which indicates DNASequence the certain substring
	 *            appears.
	 * @param pos
	 *            the number which indicates the location of the substring that
	 *            appears in the DNASequence.
	 */
	public Location(int ind, int pos) {
		index = ind;
		position = pos;

	}

	/**
	 * Retrieves the index of a location.
	 * 
	 * @return index The number which indicates DNASequence the certain substring
	 *         appears.
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Retrieves the Position of a location.
	 * 
	 * @return position The number which indicates the location of the substring
	 *         that appears in the DNASequence.
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * Converts this object to string
	 * 
	 * @return the string form of this object
	 */
	public String toString() {
		String info = "(" + getIndex() + "," + getPosition() + ")";
		return info;
	}

}
