package XML.xmlmanager.interfaces;

import java.io.IOException;

public interface FileReader {

	boolean directoryExists(String filepathToDir);
	
	String getFileContent(String filepathToDir, String filename) throws IOException;
	
}
