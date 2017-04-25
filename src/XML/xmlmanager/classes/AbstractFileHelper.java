package XML.xmlmanager.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import XML.xmlmanager.exceptions.IllegalFileException;
import XML.xmlmanager.interfaces.filemanager.DirectoryFileManager;

public abstract class AbstractFileHelper implements DirectoryFileManager{

	public AbstractFileHelper(){
		
	}
	
	private boolean addFileToDirectory(String fileContent, String filepath) throws IOException{
		File file = new File(filepath);
		if(file.exists()) return false;
		writeFile(file, fileContent);
		return true;
	}
	
	private void writeFile(File file, String fileContent) throws IOException{
		FileWriter fileWriter = new FileWriter(file, false);
		fileWriter.write(fileContent);
		fileWriter.close();
	}
	
	protected boolean directoryExists(String filepath) {
		return applyAndTest(filepath, fp -> new File(fp), file -> file.exists() && file.isDirectory());
	}
	
	protected boolean fileCheck(String filepath){
		return applyAndTest(filepath, fp -> new File(fp), file -> file.exists() && !file.isDirectory());
	}

	private <I, O> boolean applyAndTest(I input, Function<I, O> funct, Predicate<O> pred){
		return pred.test(funct.apply(input));
	}
	
	private String getFileContentHelper(String filepath) throws IOException, IllegalFileException{
		return new String(Files.readAllBytes(Paths.get(filepath)));
	}
	
//	private void deleteDirectory(String rootDirPath) throws IOException{
//		FileUtils.deleteDirectory(new File(rootDirPath));
//	}
	
	private void deleteFiles(List<String> filepaths){
		for(String filepath: filepaths){
			deleteFile(filepath);
		}
	}
	
	protected boolean fileIsClean(String filename){
		return !(filename == null || filename.length() < 1 || filename.contains("/"));
	}
	
	private void deleteFile(String filepath){
		File file = new File(filepath);
		file.delete();
	}
	
	protected String getFileContentHelper(String dirPath, String filename) throws IOException, IllegalFileException{
		if(filename.contains("/")) throw new IllegalFileException(new IllegalStateException("tried reading a file with a / in it"));
		return getFileContentHelper((dirPath + "/" + filename));
	}
	
	protected Collection<String> getFilesInDir(String dirPath){
		return map(filter(allFilesInDir(dirPath), f -> f.isFile()), f -> f.getAbsolutePath());
	}
	
	protected Collection<String> getDirsInDir(String dirPath){
		return map(filter(allFilesInDir(dirPath), f -> f.isDirectory()), f -> f.getAbsolutePath());
	}
	
	private Collection<File> allFilesInDir(String dirPath){
		File folder = new File(dirPath);
		return Arrays.asList(folder.listFiles());
	}
	
	private <I, O> Collection<O> map(Collection<I> col, Function<I, O> function){
		return col.stream().map(i -> function.apply(i)).collect(Collectors.toList());
	}
	
	private <I> Collection<I> filter(Collection<I> col, Predicate<I> pred){
		return col.stream().filter(i -> pred.test(i)).collect(Collectors.toList());
	}
	
	protected boolean addStringFileToDirectoryHelper(String fileContent, 
			String filename, String dirPath, Collection<String> files) throws IOException, IllegalFileException{
		if(generateFile(fileContent, filename, dirPath)){
			files.add(filename);
			return true;
		}
		return false;
	}
	
	protected void deleteFilesHelper(Collection<String> filenames, String rootDir){
		deleteFiles(filenames.stream()
				.map(s -> rootDir + "/" + s)
				.collect(Collectors.toList()));		
	}
	
	private void checkCleanliness(String filename) throws IllegalFileException{
		if(!fileIsClean(filename)) throw new IllegalFileException(new IllegalStateException("Invalid filename syntax"));
	}
	
	private boolean generateFile(String fileContent, String filename, String dirPath) throws IOException, IllegalFileException{
		checkCleanliness(filename);
		String totalPath = dirPath + "/" + filename;
		return addFileToDirectory(fileContent, totalPath);
	}
}
