package XML.xmlmanager.tests;

import XML.xmlmanager.classes.FileHelperMain;
import XML.xmlmanager.classes.XStreamHelper;
import XML.xmlmanager.interfaces.VoogaSerializer;
import XML.xmlmanager.interfaces.XMLFileHelper;
import gamedata.GameData;

public class FileHelperTest {
	public static void main(String[] main){
		String filepath = "src/XML/xmlmanager/tests";
		XMLFileHelper helper = new FileHelperMain();
		GameData gd = new GameData();
		gd.addType("test");
		gd.addType("test2");
		VoogaSerializer mySerializer = new XStreamHelper();
		String xmltest = mySerializer.getXMLStringFromObject(gd);
		helper.addStringToDirectory(filepath, xmltest, "test.xml");
//		System.out.println(helper.getFileContent(filepath, "test.xml"));
		GameData newGD = mySerializer.makeObjectFromXMLString(helper.getFileContent(filepath, "test.xml"), GameData.class);
		System.out.println(newGD.getTypes());
		helper.makeDirectory(filepath, "testDir");
	}

}
