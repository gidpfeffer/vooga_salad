package XML.xmlmanager.interfaces.filemanager;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Gideon
 *
 * This interface should be used for rading file contents and checking the files that have been added.
 * A concrete example is {@link  DirectoryFileWriter}. In {@link  DirectoryFileWriter} it is expected that
 * you pass in a filename without the file path because the instantiation defines the root directory.
 * This interface could be extended to allow for file reading on any system where files added is meant to be trancked. 
 */

public interface DirectoryFileReader {

	/**
	 * @param fileName the file you are searching for
	 * @return a boolean whether or not that file exists
	 */
	boolean fileExists(String fileName);
	
	/**
	 * @param filename the name of the file to extract the text from
	 * @return the String text extracted from the file
	 * @throws IOException this occurs when the filename specified is faulty
	 */
	String getFileContent(String filename) throws IOException;
	
	/**
	 * @return a collection of filenames for all of the new files added
	 */
	Collection<String> getAllNewFilenames();
	
}
