package XML.xmlmanager.interfaces;

import java.io.IOException;

public interface SingleFileHelper extends FileReader{
	
	boolean addStringFileToDirectory(String fileContent, String filename) throws IOException;

}
