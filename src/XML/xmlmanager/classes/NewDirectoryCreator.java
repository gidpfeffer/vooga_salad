package XML.xmlmanager.classes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import XML.xmlmanager.exceptions.IllegalFileException;
import XML.xmlmanager.exceptions.InvalidRootDirectoryException;

public class NewDirectoryCreator extends AbstractFileHelper{
	
	private List<String> addedFiles;
	private String rootDir;
	
	public NewDirectoryCreator(String baseDirectoryPath, String newRootDirectoryName) throws InvalidRootDirectoryException, IOException{
		rootDir = baseDirectoryPath + "/" + newRootDirectoryName;
		makeIfPossible(baseDirectoryPath, newRootDirectoryName);
		addedFiles = new ArrayList<>();
	}
	
	private void makeIfPossible(String baseDirectoryPath, String newRootDirectoryName) 
			throws InvalidRootDirectoryException, IOException{
		rootDir = baseDirectoryPath + "/" + newRootDirectoryName;
		if(!directoryExists(baseDirectoryPath) 
				|| newRootDirectoryName.contains("/")
				|| directoryExists(rootDir)
				|| baseDirectoryPath.length() < 1){
			throw new InvalidRootDirectoryException(new IllegalStateException("Invalid root"));
		}
		new File(rootDir).mkdirs();
	}
	
	@Override
	public boolean fileExists(String filename){
		return fileIsClean(filename) && fileCheck(rootDir + "/" + filename);
	}

	@Override
	public boolean addStringFileToDirectory(String fileContent, String filename) throws IOException, IllegalFileException{
		return addStringFileToDirectoryHelper(fileContent, filename, rootDir, addedFiles);
	}

	@Override
	public String getFileContent(String filename) throws IOException, IllegalFileException{
		return getFileContentHelper(rootDir, filename);
	}

	@Override
	public void cleanse() throws IOException {
		deleteFilesHelper(addedFiles, rootDir);
		addedFiles.clear();
	}

	@Override
	public Collection<String> getAllNewFilenames() {
		return Collections.unmodifiableCollection(addedFiles);
	}

	@Override
	public Collection<String> getAllFilesInDirectory() {
		return Collections.unmodifiableCollection(getFilesInDir(rootDir));
	}
}
