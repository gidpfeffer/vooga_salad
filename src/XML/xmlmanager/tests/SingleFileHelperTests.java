package XML.xmlmanager.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import XML.xmlmanager.classes.FileHelperMain;
import XML.xmlmanager.interfaces.SingleFileHelper;
import gamedata.GameData;

public class SingleFileHelperTests {
	
	private GameData myData;
	private String filepath;
	SingleFileHelper helper;
	
    public void setUp () {
       myData = new GameData();
       myData.addType("test1");
       myData.addType("test2");
       filepath = "src/XML/xmlmanager/tests";
       helper = new FileHelperMain();
    }

    // basic directory exists functionality
	@Test
	public void basicDirectoryExistsTest() {
		setUp();
		assertEquals(helper.directoryExists(filepath), true);
		assertEquals(helper.directoryExists(filepath + "/invalidDir"), false);
		assertEquals(helper.directoryExists("src"), true);
		assertEquals(helper.directoryExists("doc"), true);
		assertEquals(helper.directoryExists("lib/xstream"), true);
		assertEquals(helper.directoryExists("lib/xstrem"), false);
	}
	
    // tests the content reading functionality
	@Test
	public void getFileContentTest() {
		setUp();
		try{
			assertEquals(helper.getFileContent(filepath, "testing.txt"), "testing");
		}catch(IOException ex){
			Assert.fail("Test failed : " + ex.getMessage());
		}
		try{
			helper.getFileContent(filepath, "testinggg.txt");
			Assert.fail("Test failed : no exception was thrown during illegal file access");
		}catch(IOException ex){}
		try{
			helper.getFileContent("src", "blah.blah");
			Assert.fail("Test failed : no exception was thrown during illegal file access");
		}catch(IOException ex){}
		try{
			assertEquals(helper.getFileContent(filepath, "testing2.txt"), "line one\nline two");
		}catch(IOException ex){
			Assert.fail("Test failed : " + ex.getMessage());
		}
	}
	
    // tests the mkdir functionality
	@Test
	public void makeDirectoryTest() {
		setUp();
		assertEquals(helper.directoryExists(filepath + "/newDir"), false);
		assertEquals(helper.makeDirectory(filepath, "newDir"), true);
		assertEquals(helper.directoryExists(filepath + "/newDir"), true);
		assertEquals(helper.makeDirectory("src/XML/xmlmanager", "tests"), false);
		helper.deleteDirectory(filepath + "/newDir");
	}
	
    // tests the delete directory functionality
	@Test
	public void deleteDirectoryTest() {
		setUp();
		assertEquals(helper.makeDirectory(filepath, "newDir"), true);
		assertEquals(helper.directoryExists(filepath + "/newDir"), true);
		try{
			assertEquals(helper.addStringFileToDirectory(filepath + "/newDir", "foo", "foo.text"), true);
		}catch(IOException ex){
			Assert.fail("Exception was thrown when not applicable");
		}
		assertEquals(helper.deleteDirectory(filepath + "/newDir"), true);
		assertEquals(helper.deleteDirectory(filepath + "/newDir"), false);
	}
}
