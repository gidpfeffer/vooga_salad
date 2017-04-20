package XML.xmlmanager.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import XML.xmlmanager.interfaces.FileHelper;

public class FileHelperMain implements FileHelper{
	
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
		if(!directoryExists(filepathToDir)) return null;
		try {
			return new String(Files.readAllBytes(Paths.get(filepathToDir + "/" + filename)));
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public boolean makeDirectory(String filepath, String name) {
		if(directoryExists(filepath + "/" + name)) return false;
		File theDir = new File(filepath + "/" + name);
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

	@Override
	public boolean deleteDirectory(String filepath) {
	    File dir = new File(filepath);
	    return deleteDirHelper(dir);
	}
	
	// recursive solution found at http://stackoverflow.com/questions/3775694/deleting-folder-from-java
	private boolean deleteDirHelper(File dir){
	    if (dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i = 0; i < children.length; i++) {
	            boolean success = deleteDirHelper(new File(dir, children[i]));
	            if (!success) {
	                return false;
	            }
	        }
	    }

	    return dir.delete();
	}

}
