package XML.xmlmanager.interfaces;

import java.io.IOException;

public interface SingleFileHelper extends FileReader{
	
	boolean addStringFileToDirectory(String filepathToDir, String fileContent, String filename) throws IOException;
	
	boolean makeDirectory(String filepath, String name) throws SecurityException;
	
	boolean deleteDirectory(String filepath);

}
