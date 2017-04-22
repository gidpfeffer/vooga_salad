package XML.xmlmanager.interfaces;

import java.io.IOException;

import XML.xmlmanager.exceptions.IllegalFileException;

public interface SingleFileHelper extends FileReader{
	
	boolean addStringFileToDirectory(String fileContent, String filename) throws IOException, IllegalFileException;

}
