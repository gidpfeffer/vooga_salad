package builders;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Return information about the fields from
 * a class
 * 
 * @author maddiebriere
 *
 */
public class FieldGenerator {

	public static List<Field> getFields(String className)
			throws ClassNotFoundException {
		return getFields(Class.forName(className));
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
