package XML.xmlmanager.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import XML.xmlmanager.classes.FileHelperMain;
import XML.xmlmanager.classes.XStreamHelper;
import XML.xmlmanager.interfaces.VoogaSerializer;
import XML.xmlmanager.interfaces.XMLFileHelper;
import gamedata.GameData;
import types.BasicActorType;

public class FileManagerTest {
	
	private GameData myData;
	private VoogaSerializer mySerializer;
	private String filepath;
	XMLFileHelper helper;
	
    public void setUp () {
       myData = new GameData();
       mySerializer = new XStreamHelper();
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
		assertEquals(helper.directoryExists("lib/xstream"), true);
		assertEquals(helper.directoryExists("lib/xstrem"), false);
	}
	
    // tests the content reading functionality
	@Test
	public void getFileContentTest() {
		setUp();
		assertEquals(helper.getFileContent(filepath, "testing.txt"), "testing");
		assertEquals(helper.getFileContent(filepath, "testinggg.txt"), null);
		assertEquals(helper.getFileContent("src", "blah.blah"), null);
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
}
