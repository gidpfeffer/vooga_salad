package XML.xmlmanager.interfaces.filemanager;

import java.io.IOException;

public interface DirectoryFileManager extends DirectoryFileWriter{

	void cleanse() throws IOException;
	
}
