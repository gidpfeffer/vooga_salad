package XML.xstream.tests;

import XML.xstream.classes.FileHelperMain;
import XML.xstream.classes.XStreamHelper;
import XML.xstream.interfaces.VoogaSerializer;
import XML.xstream.interfaces.XMLFileHelper;
import gamedata.GameData;

public class FileHelperTest {
	public static void main(String[] main){
		String filepath = "src/XML/xstream/tests";
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
	}

}
