package XML.xmlmanager.interfaces;

public interface FileHelper extends FileReader{
	
	boolean addStringToDirectory(String filepathToDir, String fileContent, String filename);
	
	boolean makeDirectory(String filepath, String name);
	
	boolean deleteDirectory(String filepath);

}
