/*
 * This class represents a DNA sequence - it contains the GI, the description, and the (partial) DNA sequence.
 * 
 * Created by: Kevin H. Bhimani
 * Date: November 10, 2017
*/
public class DNASequence {
	// instance variables.
	private String gi;
	private String desc;
	private String seq;

	/**
	 * Constructs the DNASequence.
	 * 
	 * @param gI
	 *            The GI indicator
	 * @param desc2
	 *            The description of the Data
	 * @param seq2
	 *            The character sequence of the actual Data
	 */
	public DNASequence(String gi2, String desc2, String seq2) {
		gi = gi2;
		desc = desc2;
		seq = seq2;
	}

	/**
	 * This method returns the GI label of the sequence data.
	 * 
	 * @return gI The GI label of the sequence data.
	 */
	public String getGI() {
		return gi;
	}

	/**
	 * This method returns the description of the sequence.
	 * 
	 * @return desc The description of the sequence.
	 */
	public String getdesc() {
		return desc;

	}

	/**
	 * This method returns the character sequence of the sequence.
	 * 
	 * @return seq the sequence of character which needs to be analyzed
	 */
	public String getseq() {
		return seq;
	}

	/**
	 * This method returns GI information from the Data in a well organized String.
	 * 
	 * @return String containing GI information.
	 */
	public String toString() {
		String info = "GI: " + getGI() + "\n" + "desc: " + getdesc() + "\n" + "seq: " + getseq();
		// returns that string.
		return info;
	}

}
