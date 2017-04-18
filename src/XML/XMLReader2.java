package XML;

import java.io.File;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import gamedata.GameData;

public class XMLReader2 {
	private File dataFile;
	private GameData myGameData;
	


	public XMLReader2(File data) {
		dataFile = data;
		myGameData = new GameData();
		generateData();
	}

	public XMLReader2(String fileName) {
		dataFile = new File(fileName);
		myGameData = new GameData();
		generateData();

}
	private void generateData(){
		XStream xStream=new XStream(new DomDriver());
		String savedData=xStream.toXML(myGameData);
		System.out.println(savedData);
	}
}