package XML.xmlmanager.interfaces;

public interface SingleFileHelper extends FileReader{
	
	boolean addStringFileToDirectory(String filepathToDir, String fileContent, String filename);
	
	boolean makeDirectory(String filepath, String name);
	
	boolean deleteDirectory(String filepath);

}
