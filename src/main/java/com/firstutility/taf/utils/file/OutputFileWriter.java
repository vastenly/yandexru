package com.firstutility.taf.utils.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFileWriter {

	/**
	 * Change the contents of text file in its entirety, overwriting any
	 * existing text.
	 *
	 * This style of implementation throws all exceptions to the caller.
	 *
	 * @param fileName is an existing file which can be written to.
	 * @throws IllegalArgumentException if param does not comply.
	 * @throws FileNotFoundException if the file does not exist.
	 * @throws IOException if problem encountered during the file writing.
	 */
	public void write(String fileName, String content) throws FileNotFoundException, IOException {
		
		File file = new File(fileName);
		
		if (!file.exists()) {
	      throw new FileNotFoundException( "File [" + fileName + "] does NOT exist!" );
	    }
	    if (!file.isFile()) {
	      throw new IllegalArgumentException( "File name should NOT be a directory or path: " + fileName );
	    }
	    if (!file.canWrite()) {
	      throw new IllegalArgumentException( "File [" + fileName + "] can NOT be written!" );
	    }

	    BufferedWriter output = new BufferedWriter(new FileWriter(fileName));
	    
	    //FileWriter always assumes default encoding
	    try {
	      output.write( content );
	    }
	    finally {
	      output.close();
	    }
	}
	 
}