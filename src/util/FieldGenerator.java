package util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Return information about the fields from
 * a class
 * 
 * @author maddiebriere
 *
 */
public class FieldGenerator {

	public static List<Field> getFields(String className){
		Class propertyClass = null;
		try {
			propertyClass = Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getFields(propertyClass);
	}
	
	public static List<Field> getFields(Class clzz){
		ArrayList<Field> addList = new ArrayList<Field>();
		while(!clzz.equals(Object.class)){
			addList.addAll(Arrays.asList(clzz.getDeclaredFields()));
			clzz = clzz.getSuperclass();
		}
		return addList;
	}
	
}
