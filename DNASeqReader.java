
// Class: DNASeqReader
//
// Author: Pamela Cutter & Alyce Brady
//
// Created November 19, 2006

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class contains code to read DNA sequence data in from a file. It must be
 * put into a data structure as it is read. The data structure should be chosen
 * by the modifier of this class.
 * 
 * @author Alyce Brady
 * @author Pamela Cutter
 * @version Nov 19, 2006
 */
public class DNASeqReader {
	private BufferedReader reader = null;
	private int lineCounter = 0;

	/**
	 * Constructs an object that can read a DNA sequence string from the given file.
	 */
	public DNASeqReader(String filename) {
		// Get an object that can read from the file.
		try {
			this.reader = new BufferedReader(new FileReader(filename));
		} catch (IOException e) {
			System.err.println("Cannot open file " + filename + " for reading");
		}
	}

	/**
	 * Reads in the sequence string for a single DNA sequence. The sequence is
	 * assumed to be presented as groups of space-separated nucleotide blocks,
	 * spanning 1 or more lines. For example, the following would be a valid
	 * single-line sequence: ctccaccaaa ctttgagagt cactacaaaa acattcacga tcgcttcact
	 * 
	 * @param numLines
	 *            the number of lines containing this sequence
	 * @throws IOException
	 */
	public String readSequenceString(int numLines) throws IOException {
		String seq = ""; // Will contain complete nucleotide sequence
		
		String nextLine = this.reader.readLine();
		while(nextLine!=null && !(nextLine.length()<1))
		{
			String[] items = nextLine.split(" ");

			// Concatenate all the blocks on this line.
			for (int i = 0; i < items.length; i++)
				seq += items[i];

			// Read a line and find the individual items.
			nextLine = this.reader.readLine();
		}
		//System.out.println(seq);
		return seq;
	}

	/**
	 * Reads a line of data from the file specified in the constructor.
	 * 
	 * @return the line read in from the file
	 * @throws IOException
	 */
	protected String readLine() throws IOException {
		String nextLine = this.reader.readLine();
		lineCounter++;
		return nextLine;
	}

	/**
	 * Returns the line number of the most recently read-in line. The first line to
	 * be read in is line number 1.
	 */
	public int getLineNumber() {
		return lineCounter;
	}
}
