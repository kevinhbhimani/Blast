/* 
 * This class stores information for each match longer than a given threshold.
 * 
 * Created by: Kevin H. Bhimani
 * Date: November 18, 2017
*/
public class MatchElement {

	private int matchLength;
	private Location dataLoc;
	private Location queryLoc;
	private int queryNumber;
	private String nameOfMatch;

	/*
	 * Constructor for MatchElement Class
	 * 
	 * @param matchLength Length to which the match was extended to
	 * 
	 * @databasePosition position of the database
	 * 
	 * @queryPosition position of the query
	 */
	public MatchElement(int lengthOfMatch, Location databasePosition, Location queryPosition) {
		nameOfMatch = "";
		matchLength = lengthOfMatch;
		dataLoc = databasePosition;
		queryLoc = queryPosition;
		queryNumber = queryPosition.getIndex();
	}

	/*
	 * returns the number of the query where the match was found.
	 */
	public int getQNumber() {

		return queryNumber;
	}

	/*
	 * returns the length of the match
	 */
	public int getLength() {
		return matchLength;
	}

	/*
	 * returns the location of the match in the database.
	 */
	public Location getDataLocation() {
		return dataLoc;
	}

	/*
	 * returns the location of the match in the query.
	 */
	public Location getQueryLocation() {
		return queryLoc;
	}

	/*
	 * adds the name of the description in the database.
	 * 
	 * @param name the name of the match
	 */

	public void addName(String name) {
		nameOfMatch = name;
	}

	/*
	 * returns the name of a match.
	 */
	public String getName() {
		return nameOfMatch;
	}

	/*
	 * Provides information about the match.
	 */
	public String toString() {
		String string = "\n" + "Length of the match:  " + getLength() + "," + "Name: " + getName() + "," + " In Query: "
				+ getQueryLocation().getPosition() + "," + " Database:  " + getDataLocation();
		return string;
	}
}
