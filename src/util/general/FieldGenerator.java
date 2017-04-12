package util.general;

import java.lang.reflect.Field;

/**
 * Return information about the fields from
 * a class
 * 
 * @author maddiebriere
 *
 */
public class FieldGenerator {

	public static Field [] getFields(String className){
		Class propertyClass = null;
		try {
			propertyClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getFields(propertyClass);
	}
	
	public static Field[] getFields(Class clzz){
		Field[] fields = clzz.getDeclaredFields();
		if(fields.length == 0){
			try{
				fields = clzz.getSuperclass().getDeclaredFields();
			}catch(Exception e){
				//TODO: Complete
			}
		}
		return fields;
	}
	
}
