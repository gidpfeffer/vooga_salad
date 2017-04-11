package gamedata;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import factories.DataGenerator;
import factories.OptionGenerator;
import gamedata.compositiongen.Data;
import gamedata.compositiongen.HealthData;
import gamedata.reflections.Reflections;
import gameengine.grid.classes.Coordinates;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import types.BasicActorType;

public class XMLReader {
	private GameData myGameData;
	private File dataFile;
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();

	public XMLReader(File data) {
		dataFile = data;
		myGameData = new GameData();
		getData();
	}

	public XMLReader(String fileName) {
		dataFile = new File(fileName);
		myGameData = new GameData();
		getData();

	}

	private String getTextValue(Element e, String tagName) {
		NodeList nodeList = e.getElementsByTagName(tagName);
		if (nodeList != null && nodeList.getLength() > 0) {
			return nodeList.item(0).getTextContent();
		} else {
			// FIXME: empty string or null, is it an error to not find the text
			// value?
			return "";
		}
	}

	private void getData() {
		getPathData();
		try {
			getActorData();
		} catch (Exception e) {
			throw new XMLException(e);
		
		
		}
		
	}

	private void getPathData()  {
		Element root = getRootElement(dataFile);
		Element allPaths = (Element) root.getElementsByTagName("PathData").item(0);
		NodeList paths = allPaths.getElementsByTagName("Path");
		Map<Integer, List<Grid2D>> pathData = new HashMap<Integer, List<Grid2D>>();
		for (int i = 0; i < paths.getLength(); i++) {
			String[] xCoors = getTextValue((Element) paths.item(i), "X").split(" ");
			String[] yCoors = getTextValue((Element) paths.item(i), "Y").split(" ");
			if (xCoors.length != yCoors.length) {
				// throw new XMLException();
			}
			List<Grid2D> path = new ArrayList<Grid2D>();
			for (int j = 0; j < xCoors.length; j++) {
				Coordinates coor = new Coordinates(Double.parseDouble(xCoors[j]), Double.parseDouble(yCoors[j]));
				path.add(coor);
			}
			pathData.put(i, path);
		}
		
		myGameData.setMyPaths(new PathData(pathData));

	}
	private void getActorData() throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<Data>[] dataTypes=Reflections.getClasses("gamedata.composition");
		Element root=getRootElement(dataFile);
		Element allActors=(Element)root.getElementsByTagName("ActorData").item(0);
		NodeList actors=allActors.getElementsByTagName("Actor");
		for(int i=0;i<actors.getLength();i++){
			Element actor=(Element) actors.item(i);
			String type=getTextValue(actor,"type");
			BasicActorType basicType=BasicActorType.valueOf(type);
			String imagePath=getTextValue(actor,"imagePath");
			String name=getTextValue(actor,"name");
			BasicData basicData=new BasicData(name,imagePath);
			HealthData healthData=null;
			List<Data>data=new ArrayList<Data>();
			for(Class c:dataTypes){
				
				String field=c.getName().split("\\.")[c.getName().split("\\.").length-1];
				Optional<String> fieldData=Optional.ofNullable(getTextValue(actor,field));
				if(fieldData.isPresent()&&fieldData.get().length()>0){
					String[]fields=fieldData.get().split(" ");
					
					Constructor<?> constr=c.getConstructors()[0];
					Class[] neededFields=constr.getParameterTypes();
				
					Object[] convertedFields=new Object[neededFields.length];
					for(int j=0;j<neededFields.length;j++){
						convertedFields[j]=StringToFieldFactory.getObject(fields[j], neededFields[j]);
						
					}
					
					Object o=DataGenerator.makeData(field.substring(0, field.length()-4), convertedFields);
					if (o instanceof HealthData){
						healthData=(HealthData)o;
					}
					else{
						data.add((Data)o);
					}
					
				}
			}
			Data[]dataArray=new Data[data.size()];
			for(int k=0;k<data.size();k++){
				dataArray[k]=data.get(k);
			}
		ActorData newActor=new ActorData(basicType,basicData,healthData,dataArray );
		myGameData.add(newActor);
		}
		System.out.println(myGameData.getOptions());
	}
	private Element getRootElement(File file) {
		try {
			DOCUMENT_BUILDER.reset();
			Document xmlDocument = DOCUMENT_BUILDER.parse(file);
			return xmlDocument.getDocumentElement();
		} catch (SAXException | IOException e) {
			throw new XMLException(e);
		}
	}

	private String getAttribute(Element e, String attributeName) {
		return e.getAttribute(attributeName);
	}

	private static DocumentBuilder getDocumentBuilder() {
		try {
			return DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new XMLException(e);
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		XMLReader x = new XMLReader("data/voogatest.xml");
		
	}
}
