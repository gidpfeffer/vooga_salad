package XML.xmlmanager.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import XML.xmlmanager.classes.FileHelperMain;
import XML.xmlmanager.exceptions.IllegalFileException;
import XML.xmlmanager.exceptions.InvalidRootDirectoryException;
import XML.xmlmanager.interfaces.GroupFileHelper;
import gamedata.GameData;

public class SingleFileHelperTests {
	
	private GameData myData;
	private GroupFileHelper helper;
	
    public void setUp (boolean test, String rootDir, String newDir) {
       myData = new GameData();
       myData.addType("test1");
       myData.addType("test2");
       try {
    	   helper = new FileHelperMain(rootDir, newDir);
    	   assertEquals(test, true);
       } catch (InvalidRootDirectoryException | IOException e) {
    	   assertEquals(test, false);
       }
    }

    // tests the initialization
	@Test
	public void setupAndCleanseTest() {
		setUp(true, "src/XML/xmlmanager/tests", "blah");
		setUp(false, "src/XML/xmlmanager/tests", "blah");
		cleanse();
		setUp(true, "src/XML/xmlmanager/tests", "blah");
		cleanse();
		setUp(true, "src/XML", "blah");
		cleanse();
		setUp(true, "src/XML/xmlmanager/tests/", "blah");
		cleanse();
		cleanse();
		setUp(false, "src/XML/xmlmanager/tests", "/blah");
		setUp(false, "", "/blah");
		setUp(false, " ", "/blah");
		setUp(false, "  ", "/blah");
		setUp(false, "/", "/blah");
		setUp(true, "/", "blah");
		cleanse();
	}
	
    // tests adding/writing to files
	@Test
	public void addStringFileTest() {
		setUp(true, "src/XML/xmlmanager/tests", "foo");
		fillDirWithFiles();
		assertEquals(addStringFile("", "/"), false);
		assertEquals(addStringFile("", ""), false);
		assertEquals(addStringFile("", "foo/asd"), false);
		cleanse();
	}
	
    // tests reading files
	@Test
	public void fileContentTest() {
		setUp(true, "src/XML/xmlmanager/tests", "foo");
		fillDirWithFiles();
		assertEquals(getFileContent("testing.txt"), "blah");
		assertEquals(getFileContent("testing2.txt"), "123");
		assertEquals(getFileContent("abc.txt"), "");
		assertEquals(getFileContent("abccc.txt"), null);
		assertEquals(getFileContent(""), null);
		cleanse();
	}
	
	private void fillDirWithFiles(){
		assertEquals(addStringFile("blah", "testing.txt"), true);
		assertEquals(addStringFile("blah again", "testing.txt"), false);
		assertEquals(addStringFile("123", "testing2.txt"), true);
		assertEquals(addStringFile("", "abc.txt"), true);
	}
	
	private boolean addStringFile(String file, String content){
		try {
			 return helper.addStringFileToDirectory(file, content);
		} catch (IOException e) {
			Assert.fail("Filewrite was deemed illegal");
			return false;
		} catch (IllegalFileException ex){
			return false;
		}
	}
	
	private void cleanse(){
		try{
			helper.cleanse();
		} catch(IOException ex){
			Assert.fail("Cleanse was illegal");
		}
	}
	
	private String getFileContent(String filename){
		try {
			return helper.getFileContent(filename);
		} catch (IOException e) {
			return null;
		}
	}
}
