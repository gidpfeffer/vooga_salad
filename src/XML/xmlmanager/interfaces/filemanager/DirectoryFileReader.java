package XML.xmlmanager.interfaces.filemanager;

import java.io.IOException;
import java.util.Collection;

public interface DirectoryFileReader {

	boolean fileExists(String fileName);
	
	String getFileContent(String filename) throws IOException;
	
	Collection<String> getAllNewFiles();
	
}
