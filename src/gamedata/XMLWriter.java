package gamedata;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import gamedata.compositiongen.Data;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import javafx.util.Pair;
import util.Tuple;



public class XMLWriter {
private GameData myGameData;
public static final String TYPE_ATTRIBUTE = "type"; 
private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	public XMLWriter(GameData gameData){
	myGameData=gameData;
	writeXML();		
}
private void writeXML(){
	Document doc=DOCUMENT_BUILDER.newDocument();
	Element root=doc.createElement(TYPE_ATTRIBUTE); 
	doc.appendChild(root);
	setAttributes(root,doc);
	setPathData(root,doc);
}
private void setPathData(Element root, Document doc){
	Element pathData=doc.createElement("PathData");
	for(Integer i:myGameData.getPathOptions().keySet()){
		Element path=doc.createElement("Path");
		myGameData.getPathOptions().get(i);
		Tuple<List<Double>,List<Double>> coorLists=extractCoordinates(myGameData.getPathOptions().get(i));
		Element x=doc.createElement("X");
		Element y=doc.createElement("Y");
		x.appendChild(doc.createTextNode(listToString(coorLists.x)));
		y.appendChild(doc.createTextNode(listToString(coorLists.y)));
		path.appendChild(x);
		path.appendChild(y);
		pathData.appendChild(path);
		
	}
root.appendChild(pathData);
}
private void setActorData(Element root, Document doc){
	Element actorData=doc.createElement("ActorData");
	for(int i=0;i<myGameData.getNumOptions();i++){
		ActorData current=myGameData.getOption(i);
		Element actor=doc.createElement("Actor");
		Element type=doc.createElement("type");
		type.appendChild(doc.createTextNode(current.getActor().name()));
		BasicData currentBasic=current.getBasic();
		Element name=doc.createElement("Name");
		name.appendChild(doc.createTextNode(currentBasic.getName()));
		Element imagePath=doc.createElement("imagePath");
		imagePath.appendChild(doc.createTextNode(currentBasic.getImagePath()));
		actor.appendChild(name);
		actor.appendChild(imagePath);
		for(Data d:current.getMyData()){
			Element e=doc.createElement(d.getClass().toString().split("\\.")[d.getClass().toString().split("\\.").length-1]);
			
		}
		
	}
}
private Tuple<List<Double>,List<Double>> extractCoordinates(List<Grid2D>list){
	List<Double>xs=new ArrayList<Double>();
	List<Double>ys=new ArrayList<Double>();
	for(Grid2D coor:list){
		xs.add(coor.getX());
		ys.add(coor.getY());
	}
	return new Tuple<List<Double>,List<Double>>(xs,ys);
}

private String listToString(List<?> list){
	List<String>stringList=new ArrayList<String>();
	for(int i=0;i<list.size();i++){
		stringList.add(list.get(i).toString());
	}
	return String.join(" ",stringList);
}
private void setAttributes(Element root, Document doc){
	 
	Attr type=doc.createAttribute(TYPE_ATTRIBUTE);
	type.setValue("GameData");
	root.setAttributeNode(type);
}

private static DocumentBuilder getDocumentBuilder () {
    try {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }
    catch (ParserConfigurationException e) {
        throw new XMLException(e);
    }
}
}
