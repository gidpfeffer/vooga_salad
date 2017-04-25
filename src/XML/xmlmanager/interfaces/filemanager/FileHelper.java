package XML.xmlmanager.interfaces.filemanager;

import java.io.File;
import java.io.IOException;

import XML.xmlmanager.exceptions.IllegalFileException;

public interface FileHelper {
	
	boolean overwriteStringFile(String filepath, String fileContent) throws IllegalFileException, IOException;
	
	boolean deleteFile(File file);
	
	boolean deleteDir(File directory);
	
	String readFile(File file) throws IOException;
	
}
