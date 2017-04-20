package XML.xstream.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import XML.xstream.interfaces.XMLFileHelper;

public class FileHelperMain implements XMLFileHelper{
	
	public FileHelperMain(){

	}

	@Override
	public boolean directoryExists(String filepathToDir) {
		File f = new File(filepathToDir);
		return f.exists() && f.isDirectory();
	}

	@Override
	public boolean addStringToDirectory(String filepathToDir, String fileContent, String filename) {
		if(!directoryExists(filepathToDir)) return false;
		File file = new File(filepathToDir + "/" + filename);
		try {
			FileWriter fileWriter = new FileWriter(file, false);
			fileWriter.write(fileContent);
			fileWriter.close();
			return true;
		} catch (IOException e) {
			// was unabl to process;
		}
		return false;
	}

	@Override
	public String getFileContent(String filepathToDir, String filename) {
		try {
			return new String(Files.readAllBytes(Paths.get(filepathToDir + "/" + filename)));
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public boolean makeDirectory(String filepath, String name) {
		File theDir = new File(filepath + name);
		
		if (!theDir.exists()) {
		    try{
		        theDir.mkdir();
		        return true;
		    } 
		    catch(SecurityException se){
		    	return false;
		    }        
		}
		return false;
	}

}
