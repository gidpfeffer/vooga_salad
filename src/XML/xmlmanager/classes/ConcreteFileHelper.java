package XML.xmlmanager.classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.function.Predicate;

import XML.xmlmanager.exceptions.IllegalFileException;
import XML.xmlmanager.interfaces.filemanager.FileHelper;

public class ConcreteFileHelper implements FileHelper{

	public ConcreteFileHelper(){
		
	}
	
	@Override
	public boolean overwriteStringFile(String filepath, String fileContent) throws IllegalFileException, IOException{
		File file = new File(filepath);
		checkForValidity(file, f -> f.isDirectory(), "filepath passed mapped to a directory");
		boolean test = checkAndConsume(file, f -> file.exists(), f -> f.delete());
		writeFile(file, fileContent);
		return test;
	}
	
	private void writeFile(File file, String fileContent) throws IOException{
		FileWriter fileWriter = new FileWriter(file, false);
		fileWriter.write(fileContent);
		fileWriter.close();
	}

	@Override
	public boolean deleteFile(File file) {
		return checkAndConsume(file, f -> f.isFile(), f-> f.delete());
	}

	@Override
	public boolean deleteDir(File directory) {
		return checkAndConsume(directory, f -> f.isDirectory(), f-> f.delete());
	}

	@Override
	public String readFile(File file) throws IOException {
		return new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
	}
	
	private <F> void checkForValidity(F f, Predicate<F> pred, String error) throws IllegalFileException{
		if(pred.test(f)){
			throw new IllegalFileException(new IllegalStateException(error));
		}
	}
	
	private <F> boolean checkAndConsume(F f, Predicate<F> pred, Consumer<F> consumer){
		boolean test = pred.test(f);
		if(test){
			consumer.accept(f);
		}
		return test;
	}

}
