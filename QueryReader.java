
// Class: DNADataReader
//
// Author: Pamela Cutter & Alyce Brady
//
// Created November 19, 2006

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains code to read DNA sequence queries from a file.
 * 
 * @author Alyce Brady
 * @author Pamela Cutter
 * @version Nov 19, 2006
 */
public class QueryReader extends DNASeqReader {
	private String filename;

	/**
	 * Constructs an object that reads DNA sequence information from the given file.
	 * The data is assumed to be in the following format: N a group of nucleotide
	 * blocks, spanning N lines For example, the following would specify two valid
	 * queries: 1 ctccaccaaa ctttgagagt cactacaaaa acattcacga tcgcttcact 2
	 * ctccaccaaa ctttgagagt cactacaaaa acattcacga tcgcttcact ctccaccaaa ctttgagagt
	 * cactacaaaa acattcacga tcgcttcact
	 */
	public QueryReader(String filename) {
		super(filename);
		this.filename = filename;
	}

	/**
	 * Reads queries from the given file, which must be in the format specified in
	 * the documentation for the QueryReader constructor. Precondition: the file
	 * must have been successfully opened for reading.
	 */
	public List<String> readData() {
		List<String> queries = new ArrayList<String>();

		try {
			// Read a line containing the number of subsequent lines
			// for the query, then read those line, until End-Of-File.
			String next;
			while ((next = this.readLine()) != null) {
				// Debug.println(next);

				if (!next.equals("")) {
					int numLines = Integer.parseInt(next);
					String seq = readSequenceString(numLines);
					queries.add(seq);
				}
			}
		} catch (IOException e) {
			System.err.println("Could not read line " + (this.getLineNumber() + 1) + " of file " + filename);
			return null;
		}

		return queries;
	}
}