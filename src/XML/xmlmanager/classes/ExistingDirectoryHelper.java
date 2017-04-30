package XML.xmlmanager.classes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import XML.xmlmanager.exceptions.IllegalFileException;
import XML.xmlmanager.exceptions.InvalidRootDirectoryException;

public class ExistingDirectoryHelper extends AbstractFileHelper{
	
	private String directoryPath;
	private List<String> newFiles;
	
	public ExistingDirectoryHelper(String directoryPath) throws InvalidRootDirectoryException{
		if(!directoryExists(directoryPath)){
			throw new InvalidRootDirectoryException(new IllegalStateException("Directory didn't exist"));
		}
		this.directoryPath = directoryPath;
		newFiles = new ArrayList<>();
	}

	@Override
	public void cleanse() throws IOException {
		deleteFilesHelper(newFiles, directoryPath);
		newFiles.clear();
	}

	@Override
	public boolean addStringFileToDirectory(String fileContent, String filename) throws IOException, IllegalFileException{
		return addStringFileToDirectoryHelper(fileContent, filename, directoryPath, newFiles);
	}

	@Override
	public boolean fileExists(String filename) {
		return fileIsClean(filename) && fileCheck(directoryPath + "/" + filename);
	}

	@Override
	public String getFileContent(String filename) throws IOException, IllegalFileException {
		return getFileContentHelper(directoryPath, filename);
	}

	@Override
	public Collection<String> getAllNewFilenames() {
		return Collections.unmodifiableCollection(newFiles);
	}

	@Override
	public Collection<String> getAllFilesInDirectory() {
		return Collections.unmodifiableCollection(getFilesInDir(directoryPath));
	}
}
