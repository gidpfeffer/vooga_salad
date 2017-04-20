package XML.xstream.interfaces;

public interface XMLFileHelper {
	
	boolean directoryExists(String filepathToDir);
	
	boolean addXMLToDirectory(String filepathToDir, String XMLString, String filename);
	
	String getFileContent(String filepathToDir, String filename);

}
