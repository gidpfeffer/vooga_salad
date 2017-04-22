package XML.xmlmanager.interfaces;

import java.io.IOException;
import java.util.Collection;

public interface FileReader {

	boolean fileExists(String fileName);
	
	String getFileContent(String filename) throws IOException;
	
	Collection<String> getAllNewFiles();
	
}
