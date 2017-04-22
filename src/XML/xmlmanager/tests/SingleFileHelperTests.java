package XML.xmlmanager.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import XML.xmlmanager.classes.FileHelperMain;
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
       } catch (InvalidRootDirectoryException | IOException e) {
    	   assertEquals(test, false);
       }
    }

    // tests the initialization
	@Test
	public void setupTest() {
		setUp(true, "src/XML/xmlmanager/tests", "blah");
		setUp(false, "src/XML/xmlmanager/tests", "blah");
		assertEquals(cleanse(), true);
		setUp(true, "src/XML/xmlmanager/tests", "blah");
		assertEquals(cleanse(), true);
		setUp(true, "src/XML", "blah");
		assertEquals(cleanse(), true);
		setUp(false, "src/XML/xmlmanager/tests/", "blah");
		setUp(false, "src/XML/xmlmanager/tests", "/blah");
	}
	
	private boolean cleanse(){
		if(helper != null) return helper.cleanse();
		return false;
	}
}
