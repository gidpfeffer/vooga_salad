package factories;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import gamedata.FieldData;
import gamedata.composition.Data;
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
	private static final String PROPERTY_PATH = "gameengine.actors.properties";
	private static final String DATA_PATH = "gamedata.composition";
	
	public static List<BasicActorType> getActorTypes(){
		return Arrays.asList(BasicActorType.values());
	}
	
	public static List<String> getPropertyTypes(){
		Class<?> [] properties = new Class[0]; 
		List<String>toRet = new ArrayList<String>();
		try {
			properties = Reflections.getClasses(PROPERTY_PATH);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!(properties.length == 0)){
			toRet = Arrays.asList(properties)
					.stream()
					.map(p -> p.getCanonicalName())
					.map(p -> p.replaceAll("Property",""))
					.map(p -> p.replaceAll(PROPERTY_PATH +".",""))
					.collect(Collectors.toList());
		}
		return toRet;
	}
	
	//http://stackoverflow.com/questions/15112590/
	//get-the-class-instance-variables-and-print-their-values-using-reflection
	public static Map<String, List<FieldData>> getPropertyTypesWithArgs(){
		//TODO: Implement
		Map<String,List<FieldData>> toRet = new HashMap<String, List<FieldData>>();
		List<String> datas = getPropertyTypes();
		for(int i=0; i<datas.size(); i++)
		{
			String property = datas.get(i)+"Data";
			Class<?> propertyClass = null;
			try {
				propertyClass = Class.forName(DATA_PATH + "." + property);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Field[] fields = propertyClass.getDeclaredFields();
			if(fields.length == 0){
				try{
					fields = propertyClass.getSuperclass().getDeclaredFields();
				}catch(Exception e){
					//TODO: Complete
				}
			}
			List<FieldData> fieldDatas = new ArrayList<FieldData>();
			for(Field f : fields){
				String name = f.getName();
				Class<?> type = f.getType();
				FieldData toAdd = new FieldData(name,type);
				fieldDatas.add(toAdd);
			}
			
			toRet.put(datas.get(i), fieldDatas);
		}
		return toRet;
	}
	
	public static Map<String, List<String>> getPropertyTypesWithArgs(Data d){
		//TODO: Implement
		return null;
	}
}
