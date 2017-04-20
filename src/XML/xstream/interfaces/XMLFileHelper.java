package XML.xstream.interfaces;

public interface XMLFileHelper {
	
	boolean directoryExists(String filepathToDir);
	
	boolean addStringToDirectory(String filepathToDir, String fileContent, String filename);
	
	String getFileContent(String filepathToDir, String filename);
	
	boolean makeDirectory(String filepath, String name);

}
