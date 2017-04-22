package XML.xmlmanager.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.io.FileUtils;

import XML.xmlmanager.exceptions.IllegalFileException;
import XML.xmlmanager.exceptions.InvalidRootDirectoryException;
import XML.xmlmanager.interfaces.GroupFileHelper;

public class FileHelperMain implements GroupFileHelper{
	
	private List<String> addedFiles;
	private String rootDir;
	
	public FileHelperMain(String baseDirectoryPath, String newRootDirectoryName) throws InvalidRootDirectoryException, IOException{
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


	private boolean directoryExists(String filepathToDir) {
		return applyAndTest(filepathToDir, fp -> new File(fp), file -> file.exists() && file.isDirectory());
	}
	
	@Override
	public boolean fileExists(String filename){
		return applyAndTest(rootDir + filename, fp -> new File(fp), file -> file.exists() && !file.isDirectory());
	}

	@Override
	public boolean addStringFileToDirectory(String fileContent, String filename) throws IOException, IllegalFileException{
		if(filename == null || filename.length() < 1 || filename.contains("/")){
			throw new IllegalFileException(new IllegalStateException("Invalid filename syntax"));
		}
		String totalPath = rootDir + "/" + filename;
		File file = new File(totalPath);
		if(file.exists()) return false;
		FileWriter fileWriter = new FileWriter(file, false);
		fileWriter.write(fileContent);
		fileWriter.close();
		addedFiles.add(totalPath);
		return true;
	}

	@Override
	public String getFileContent(String filename) throws IOException{
		return new String(Files.readAllBytes(Paths.get(rootDir + "/" + filename)));
	}

	@Override
	public void cleanse() throws IOException {
		FileUtils.deleteDirectory(new File(rootDir));
	}

	@Override
	public Collection<String> getAllNewFiles() {
		return Collections.unmodifiableCollection(addedFiles);
	}
	
	private <I, O> boolean applyAndTest(I input, Function<I, O> funct, Predicate<O> pred){
		return pred.test(funct.apply(input));
	}

}
