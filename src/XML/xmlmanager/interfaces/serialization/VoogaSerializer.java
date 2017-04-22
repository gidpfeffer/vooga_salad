package XML.xmlmanager.interfaces.serialization;

import XML.xmlmanager.exceptions.IllegalXStreamCastException;

/**
 * 
 * @author Gideon
 * 
 *
 */
public interface VoogaSerializer{
	
	String getXMLStringFromObject(Object o);
	
	<C> C makeObjectFromXMLString(String XMLString, Class<C> clazz) throws IllegalXStreamCastException;

}
