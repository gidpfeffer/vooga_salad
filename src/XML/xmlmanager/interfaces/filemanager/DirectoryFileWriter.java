package XML.xmlmanager.interfaces.filemanager;

import java.io.IOException;

import XML.xmlmanager.exceptions.IllegalFileException;

public interface DirectoryFileWriter extends DirectoryFileReader{
	
	boolean addStringFileToDirectory(String fileContent, String filename) throws IOException, IllegalFileException;

}
