package XML.xstream.interfaces;

public interface VoogaSerializer {
	
	String getXMLStringFromObject(Object o);
	
	Object makeObjectFromXMLString(String XMLString);

}
