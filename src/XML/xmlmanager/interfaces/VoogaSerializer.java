package XML.xmlmanager.interfaces;
/**
 * 
 * @author Gideon
 * 
 *
 */
public interface VoogaSerializer{
	
	String getXMLStringFromObject(Object o);
	
	<C> C makeObjectFromXMLString(String XMLString, Class<C> clazz);

}
