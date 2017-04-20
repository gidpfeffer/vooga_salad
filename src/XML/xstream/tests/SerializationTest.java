package XML.xstream.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import XML.xstream.classes.XStreamHelper;
import XML.xstream.interfaces.VoogaSerializer;
import gamedata.GameData;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public class SerializationTest {
	
	private GameData myData;
	private VoogaSerializer mySerializer;
	
    public void setUp () {
       myData = new GameData();
       mySerializer = new XStreamHelper();
    }

    // tests the locational ability of finding actors on the grid
	@Test
	public void BasicWriteAndReadTest() {
		setUp();
		myData.addType("test1");
		myData.addType("test2");
		String myDataString = mySerializer.getXMLStringFromObject(myData);
		myData = mySerializer.makeObjectFromXMLString(myDataString, GameData.class);
		assertEquals(myData.getTypes().size(), 2);
	}
	

}
