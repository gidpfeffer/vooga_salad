package XML.xstream.interfaces;

public interface VoogaSerializer{
	
	String getXMLStringFromObject(Object o);
	
	<C> C makeObjectFromXMLString(String XMLString, Class<C> clazz);

}
