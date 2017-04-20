package XML.xstream.tests;

import XML.xstream.classes.XStreamHelper;
import XML.xstream.interfaces.VoogaSerializer;
import gamedata.GameData;

public class CastTest {

	public static void main(String[] args){
		GameData data = new GameData();
		data.addType("test1");
		data.addType("test2");
		VoogaSerializer ser = new XStreamHelper();
		String xml1 = ser.getXMLStringFromObject(data);
		String str = ser.makeObjectFromXMLString(xml1, String.class);
		System.out.println(str);
		String xml2 = ser.getXMLStringFromObject(data);
		GameData gamedata = ser.makeObjectFromXMLString(xml2, GameData.class);
		System.out.println(gamedata.getTypes());
	}
	
}
