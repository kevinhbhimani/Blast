import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/*
 *  This class contains the DNA sequence data, it contains the HashMap
 *  that holds all possible nn-letter words contained in this DNA data,
 *  it has a method to find matches of substrings of a query sequence, 
 *  a method to extend those matches, and possibly a method to display the matches.
 *  
 *  Created by: Kevin H. Bhimani
 *  With Assistance from: Tim Rutledge, Tanush Samson
 *  Date: November 19, 2017
*/
public class Database {
	// instance variables
	private ArrayList<DNASequence> seqList;
	private HashMap<String, ArrayList<Location>> hashMap;
	private int subLength;

	/*
	 * The constructor for the database class.
	 * 
	 * @param list ArrayList of DNASequences that is stored in the Database
	 * 
	 * @param sublength the length of the substrings that are passed in by the user
	 */

	public Database(ArrayList<DNASequence> list, int sublength1) {
		hashMap = new HashMap<String, ArrayList<Location>>();
		seqList = list;
		subLength = sublength1;
	}

	/*
	 * Adds the data to a hash map.
	 */
	public void addDataToHashMap() {
		// goes through the sequence.
		for (int seqIndex = 0; seqIndex < seqList.size(); seqIndex++) {
			{
				// Debug.println(seqList.size()+"");
				String rawSequence = seqList.get(seqIndex).getseq();

				for (int letterIndex = 0; letterIndex <= rawSequence.length() - subLength; letterIndex++) {
					ArrayList<Location> locList = new ArrayList<Location>();
					Location newLoc = new Location(seqIndex, letterIndex);
					String subSeq = rawSequence.substring(letterIndex, letterIndex + subLength);
					if (!hashMap.containsKey(subSeq)) // if the Hash Map does not contain the subSeq
					{
						// Debug.println("Subseq "+subSeq);

						locList.add(newLoc); // then add the location to the list of locations
						hashMap.put(subSeq, locList); // and also add the sequence to the hash map
					} else // if the Hash Map contains the subSeq
					{
						hashMap.get(subSeq).add(newLoc);
					}
				}
			}
		}
	}

	/*
	 * extendMatch method extends the matches to their full potential until the
	 * query ends.
	 * 
	 * @param matchLoc location where the match was found.
	 * 
	 * @param queryString The string which is being compared.
	 * 
	 * @param queryPosition position in the Query where the match has to be extended
	 * 
	 * @return matchLength length of the match found.
	 */
	private int extendMatch(Location matchLoc, String queryString, int queryPosition) {
		Location location = matchLoc;
		String possibleMatchString = seqList.get(location.getIndex()).getseq();

		int matchLength = 0;
		int startPosition = location.getPosition();
		// This loop extends the matches to their full potential
		while (queryPosition + matchLength < queryString.length()
				&& startPosition + matchLength < possibleMatchString.length()
				&& possibleMatchString.substring(startPosition + matchLength, startPosition + matchLength + 1)
						.equals(queryString.substring(queryPosition + matchLength, queryPosition + matchLength + 1))) {
			matchLength++; // increments the matchLength to it's full potential
		}

		return matchLength; // return the length of the found match
	}

	/*
	 * alreadyContainObj method determines if a given object is already contained in
	 * the MatchElement list. This is needed to avoid the collisions in the Hash
	 * Map.
	 * 
	 * @param elemnt the element being looked at.
	 * 
	 * @param matchList ArrayList of MatchElement
	 * 
	 * @return boolean true if the list already contains the same match, false
	 * otherwise
	 */
	private boolean alreadyContainObj(MatchElement elemnt, ArrayList<MatchElement> matchList) {

		ArrayList<MatchElement> matchElementList = matchList;
		MatchElement possMatch = elemnt;

		// goes through the list of given objects
		for (int i = 0; i < matchElementList.size(); i++) {
			// the next lines represent the index, position, length of match already in the
			// list
			int posList = matchElementList.get(i).getDataLocation().getPosition();
			int indList = matchElementList.get(i).getDataLocation().getIndex();
			int lengthList = matchElementList.get(i).getLength();
			// the next lines represent the index, position, length of a potential
			// match in question
			int possPos = possMatch.getDataLocation().getPosition();
			int possInd = possMatch.getDataLocation().getIndex();
			int possLength = possMatch.getLength();

			if (indList == possInd && (posList + lengthList) == (possPos + possLength)) {
				return true;
			}
		}
		return false; // if the condition is not met for any of the index values, false is returned.
	}

	/*
	 * findMatchElements method finds the matches from the given query and datbase.
	 * The matches are then added to the list.
	 * 
	 * @param queryList The result form the matches.
	 * 
	 * @ return matchList The list with all the matches.
	 */
	public ArrayList<MatchElement> findMatchElements(List<String> queryList, int trshvalue) {

		ArrayList<MatchElement> matchList = new ArrayList<MatchElement>();

		for (int queryNumber = 0; queryNumber < queryList.size(); queryNumber++) {
			String queryString = queryList.get(queryNumber);// gets the query form the substring
			for (int subSNum = 0; subSNum < queryString.length() - subLength; subSNum++) {
				String subQString = queryString.substring(subSNum, subSNum + subLength);
				// System.out.println(subQString.length());
				if (hashMap.containsKey(subQString))// look through Hash Map for query string
				{

					ArrayList<Location> possibleMatches = hashMap.get(subQString);
					for (int possibleMatchNum = 0; possibleMatchNum < possibleMatches.size(); possibleMatchNum++) {

						Location location = possibleMatches.get(possibleMatchNum);

						int matchLength = extendMatch(location, queryString, subSNum);

						if (matchLength >= trshvalue) {
							MatchElement newMatch = new MatchElement(matchLength, location,
									new Location(queryNumber, subSNum));

							// if there is a new location, then it gets the name and stores it.
							if (!alreadyContainObj(newMatch, matchList)) {
								String matchName = seqList.get(location.getIndex()).getdesc();
								newMatch.addName(matchName);
								matchList.add(newMatch);
							}
						}
					}
				}
			}
		}
		return matchList;
	}

}
