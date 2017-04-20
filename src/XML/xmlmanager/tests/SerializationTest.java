package XML.xmlmanager.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import XML.xmlmanager.classes.XStreamHelper;
import XML.xmlmanager.interfaces.VoogaSerializer;
import gamedata.GameData;
import types.BasicActorType;

public class SerializationTest {
	
	private GameData myData;
	private VoogaSerializer mySerializer;
	
    public void setUp () {
       myData = new GameData();
       mySerializer = new XStreamHelper();
       myData.addType("test1");
       myData.addType("test2");
    }

    // adds data to the object, serializes it, then makes sure the data was preserved
	@Test
	public void BasicWriteAndReadTest() {
		setUp();
		String myDataString = mySerializer.getXMLStringFromObject(myData);
		myData = mySerializer.makeObjectFromXMLString(myDataString, GameData.class);
		List<BasicActorType> types = myData.getTypes();
		assertEquals(types.size(), 2);
		Assert.assertTrue(types.contains(new BasicActorType("test1")));
		Assert.assertTrue(types.contains(new BasicActorType("test2")));
	}
	
    // tries to get an invalid class from an XML String
	@Test
	public void InvalidCastTest() {
		setUp();
		String myDataString = mySerializer.getXMLStringFromObject(myData);
		String invalidString = mySerializer.makeObjectFromXMLString(myDataString, String.class);
		assertEquals(invalidString, null);
	}
	
    // sends an invalid String into the object generator
	@Test
	public void InvalidWrite() {
		setUp();
		myData = mySerializer.makeObjectFromXMLString("sdfasdf", GameData.class);
		assertEquals(myData, null);
	}
}