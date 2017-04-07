package gamedata.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sun.istack.internal.NotNull;

import gamedata.reflections.Reflections;
import types.BasicActorType;

/**
 * 
 * Option generator for use in the Game Authoring Environment. 
 * Generates information about what types of properties and
 * actors can be built (for use in displaying options
 * to user via buttons or drop-down menus).
 * 
 * @author maddiebriere
 *
 */

public class OptionGenerator {
	
	public static List<BasicActorType> getActorTypes(){
		return Arrays.asList(BasicActorType.values());
	}
	
	public static List<String> getPropertyTypes(){
		Class [] properties = new Class[0]; 
		List<String>toRet = new ArrayList<String>();
		try {
			properties = Reflections.getClasses("gameengine.actors.properties");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!(properties.length == 0)){
			toRet = Arrays.asList(properties)
					.stream()
					.map(p -> p.getCanonicalName())
					.map(p -> p.replaceAll("Property",""))
					.map(p -> p.replaceAll("gameengine.actors.properties.",""))
					.collect(Collectors.toList());
		}
		return toRet;
	}
	
	//http://stackoverflow.com/questions/15112590/
	//get-the-class-instance-variables-and-print-their-values-using-reflection
	public static Map<String, List<String>> getPropertyTypesWithArgs(){
		//TODO: Implement
		List<String> datas = getPropertyTypes();
		/*//for(int i=0; i<)
		{
			property = property+"Data";
		}
		Field[] fields = objClass.getDeclaredFields();
		for (Field field : fields) {
		    NotNull notNull = field.getAnnotation(NotNull.class);
		    field.setAccessible(true);
		}
		*/
		return null;
	}
}
