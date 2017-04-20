package XML.xmlmanager.interfaces;

public interface FileReader {

	boolean directoryExists(String filepathToDir);
	
	String getFileContent(String filepathToDir, String filename);
	
}
