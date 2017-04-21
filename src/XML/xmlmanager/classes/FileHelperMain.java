package XML.xmlmanager.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import XML.xmlmanager.interfaces.GroupFileHelper;

public class FileHelperMain implements GroupFileHelper{
	
	List<String> addedDirectories;
	
	public FileHelperMain(){
		addedDirectories = new ArrayList<>();
	}

	@Override
	public boolean directoryExists(String filepathToDir) {
		File f = new File(filepathToDir);
		return f.exists() && f.isDirectory();
	}

	@Override
	public boolean addStringFileToDirectory(String filepathToDir, String fileContent, String filename) throws IOException{
		File file = new File(filepathToDir + "/" + filename);
		if(file.exists()) return false;
		FileWriter fileWriter = new FileWriter(file, false);
		fileWriter.write(fileContent);
		fileWriter.close();
		return true;
	}

	@Override
	public String getFileContent(String filepathToDir, String filename) throws IOException{
		return new String(Files.readAllBytes(Paths.get(filepathToDir + "/" + filename)));
	}

	@Override
	public boolean makeDirectory(String filepath, String name) throws SecurityException{
		String newDirPath = filepath + "/" + name;
		File theDir = new File(newDirPath);
		if (!theDir.exists()) {
			theDir.mkdir();
		    if(!addedDirectories.contains(newDirPath)) addedDirectories.add(newDirPath);
		    return true;
		}
		return false;
	}

	@Override
	public boolean deleteDirectory(String filepath) {
		if(!addedDirectories.contains(filepath)) return false;
		addedDirectories.remove(filepath);
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

	@Override
	public boolean cleanse() {
		if(addedDirectories.size() == 0) return false;
		for(String dir : addedDirectories){
			deleteDirectory(dir);
		}
		return true;
	}

}
