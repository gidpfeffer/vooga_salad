package gamedata.util;

import java.io.IOException;
import java.util.Arrays;

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
	
	public static BasicActorType[] getActorTypes(){
		return BasicActorType.values();
	}
	
	public static String[] getPropertyTypes(){
		Class [] properties = new Class[0]; 
		String [] toRet = new String[0];
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
					.toArray(String[]::new);
		}
		return toRet;
	}
}
