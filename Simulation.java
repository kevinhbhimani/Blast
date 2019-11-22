import java.util.ArrayList;
import java.util.List;

/*
 * This class gets the DNA data and the query data from the data readers. 
 * It has a run method that iterates through each query and displays any 
 * matches with substrings of the DNA data that are longer than a given threshold.
 * 
 * Created by: Kevin H. Bhimani
 * Date: November 11, 2017
*/
public class Simulation {

	// instance variables
	private String file1;
	private String file2;
	private int substringLength;
	private int threshold;
	private QueryReader queryReader;
	private DNADataReader dnaReader;
	private Database dataStorage;
	private ArrayList<MatchElement> matchElementList;
	private List<String> stringList;

	/*
	 * The constructor for the Simulation Class.
	 */
	public Simulation() {

		ValidatedInputReader reader = new ValidatedInputReader();
		file1 = reader.getString("Name of file", "Viruses.txt");// gets the name of the file
		System.out.println("The imput file was: " + file1);
		// Create a DNADataReader object where fileName is passed as the parameter
		dnaReader = new DNADataReader(file1);
		ArrayList<DNASequence> dNASeqList = dnaReader.readData();

		ValidatedInputReader reader2 = new ValidatedInputReader();
		file2 = reader2.getString("Filename for queries:", "Queries.txt");
		System.out.println("The imput file for queries was: " + file2);
		substringLength = reader2.getInteger("Length of the substring:", 4, 50, 40, "Number must be between 4 and 50.");
		System.out.println("Length of the substring was: " + substringLength);
		threshold = reader2.getInteger("Length of threshold:", 9, 40, 15, "Number must be between 9 and 40");
		System.out.println("Length of the threshold was: " + threshold);

		dataStorage = new Database(dNASeqList, substringLength);
		dataStorage.addDataToHashMap();
		queryReader = new QueryReader(file2);
		stringList = queryReader.readData();

	}

	/*
	 * Runs the simulation.
	 */
	public void run() {

		matchElementList = dataStorage.findMatchElements(stringList, threshold);

	}

	/*
	 * Prints the results of the simulation.
	 */
	public void showResult() {
		// if the list is empty
		if (matchElementList.isEmpty()) {
			System.out.println("No Matches found for a threshold of " + threshold + ".");
		}
		// if the list is not empty
		else {

			System.out.println("A MATCH WAS FOUND!");
			System.out.println(matchElementList.size() + " matches found for a Theshold of " + threshold);
			// goes through the elements
			for (int i = 0; i < matchElementList.size(); i++) {
				System.out.println(matchElementList.get(i));
			}
			System.out.println("Simulation is done.");
		}
	}

}