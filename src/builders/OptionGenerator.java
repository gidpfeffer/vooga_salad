package builders;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import gamedata.FieldData;
import gamedata.compositiongen.Data;
import gamedata.reflections.Reflections;
import types.BasicActorType;
import util.general.FieldGenerator;

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
	private static final String DATA_PATH = "gamedata.composition";
	
	public static List<BasicActorType> getActorTypes(){
		return Arrays.asList(BasicActorType.values());
	}
	
	//TODO: Refactor
	
	/**
	 * Get all of the property types available to the user. 
	 * Includes:
	 * - LimitedHealth
	 * - MoveWithDestination
	 * - ShotTargetFar
	 * ...
	 * @return List of String corresponding to the names of the properties
	 */
	public static List<String> getPropertyTypes(){
		Class<?> [] properties = new Class[0]; 
		List<String>toRet = new ArrayList<String>();
		try {
			properties = Reflections.getClasses(DATA_PATH);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!(properties.length == 0)){
			toRet = Arrays.asList(properties)
					.stream()
					.map(p -> p.getSimpleName())
					.map(p -> p.replaceAll("Data",""))
					.collect(Collectors.toList());
		}
		return toRet;
	}
	
	/**
	 * Get all of the general property names available to the user.
	 * Includes:
	 * - Shoot
	 * - Move
	 * - Health
	 * - Status 
	 * 
	 * @return Strings corresponding to the names of the general properties
	 */
	public static List<String> getGeneralPropertyTypes(){
		Class<?> [] properties = new Class[0]; 
		List<String>toRet = new ArrayList<String>();
		try {
			properties = Reflections.getClasses(DATA_PATH);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!(properties.length == 0)){
			Set<String> temp = Arrays.asList(properties)
					.stream()
					.map(p -> p.getSuperclass().getSimpleName())
					.map(p -> p.replaceAll("Data",""))
					.collect(Collectors.toSet());
			toRet = new ArrayList<String>(temp);
		}
		return toRet;
	}
	
	/**
	 * Get all of the subcategories under a superclass/ type. 
	 * Example:
	 * 
	 * Command: getSubcategories("Shoot")
	 * Returns: [ShootTargetFar, ShootTargetNear]
	 * @param supertype
	 * @return
	 */
	public static List<String> getSubcategories(String supertype){
		Class <?> parent = null;
		try {
			parent = Class.forName(DATA_PATH + "gen." + supertype + "Data");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		final Class<?> toCompare = parent;
		
		Class<?> [] properties = new Class[0]; 
		try {
			properties = Reflections.getClasses(DATA_PATH);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> toRet =  Arrays.asList(properties)
				.stream()
				.filter(p -> p.getSuperclass().equals(toCompare)) //if has superclass as parent
				.map(p -> p.getSimpleName())
				.map(p -> p.replaceAll("Data", ""))
				.collect(Collectors.toList());
		return toRet;
		
	}
	
	/**
	 * Get all of the Property types (as Strings) matched to a List of
	 * FieldData objects representing the fields required by that property.
	 * 
	 * Example:
	 * Command: getPropertyTypesWithArgs()
	 * Returns: 
	 * 
	 * LimitedHealth
	 * - double startHealth
	 * 
	 * ShootTargetFar
	 * - double myRange
	 * - int  fireRate
	 * 
	 * ...
	 * 
	 * @return Properties matched to arguments
	 */
	//http://stackoverflow.com/questions/15112590/
	//get-the-class-instance-variables-and-print-their-values-using-reflection
	public static Map<String, List<FieldData>> getPropertyTypesWithArgs(){
		Map<String,List<FieldData>> toRet = new LinkedHashMap<String, List<FieldData>>();
		List<String> datas = getPropertyTypes();
		for(int i=0; i<datas.size(); i++)
		{
			String property = datas.get(i)+"Data";
			Field [] fields = FieldGenerator.getFields(DATA_PATH + "." + property);
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
	
	public static Map<String, List<FieldData>> getPropertyTypeWithArgsOfType(String superclass){
		//TODO: Implement
		return null;
	}
	
	/**
	 * Get the name (representing the type) of the current data object
	 * 
	 * @param data
	 * @return
	 */
	public static String getName(Data data){
		String s = data.getClass().getSimpleName();
		return s.replace("Data", "");
	}
	
	/**
	 * Return the field names in the data objects mapped to their
	 * current values
	 * 
	 * @param data Data object to check
	 * @return Mapping of field names to values
	 */
	public static Map<String, Object>  getFields(Data data){
		Class clzz = data.getClass();
		Map<String, Object> fieldMap = new LinkedHashMap<String,Object>();
		Field[] fields = FieldGenerator.getFields(clzz);
		
		for(Field f: fields){
			try {
				fieldMap.put(f.getName(), f.get(data));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fieldMap;
	}
	
	//Use property file
	public static String getDescription(String propertyName){
		//TODO: Write
		return null;
	}
}
