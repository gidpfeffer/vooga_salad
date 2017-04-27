package builders;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import gamedata.FieldData;
import gamedata.GameData;
import gamedata.compositiongen.Data;
import util.PropertyUtil;

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

public class AuthorInfoGenerator{
	
	/**
	 * REFACTORED! Now returns currently defined Strings for
	 * categories (e.g., Monster, Troop)
	 * 
	 * @param data Current GameData
	 * @return Current available categories
	 */
	public static List<String> getActorTypes(GameData data){
		return data.getTypes()
				.stream()
				.map(p-> p.getType())
				.collect(Collectors.toList());
	}
	
	public static String getName(Data data){
		return (new DataPackageInfoGenerator()).simplifyName(data);
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
	public static Map<String, List<FieldData>> getPropertyTypesWithArgs(){
		DataPackageInfoGenerator d = new DataPackageInfoGenerator();
		List<Class<?>> datas = d.getConcreteDataClasses();
		
		Map<String,List<FieldData>> toRet = new LinkedHashMap<String, List<FieldData>>();
		
		for(Class<?> clzz: datas){
			List<Field> fields = FieldGenerator.getFields(clzz);
			List<FieldData> fieldDatas = FieldGenerator.getFieldDatas(fields);
			toRet.put(d.simplifyName(clzz), fieldDatas);
		}
		return toRet;
	}
	
	public static Map<String, Object>  getFields(Data data){
		return FieldGenerator.getFields(data);
	}
	
	/**
	 * Returns a String defining the property. If there is no
	 * String for the specific property, if will look for a description
	 * for supertypes.
	 * 
	 * Pass this a propertyName, WITHOUT THE WORD "DATA"
	 * appended to the end.
	 * @param propertyName Property name (e.g., ShootMulti)
	 * @return description A String defining the property
	 */
	public static String getDescription(String propertyName){
		String toRet = PropertyUtil.getTerm("resources/property_descriptions", propertyName);
		Class<?> superclass = null;
		int counter = 0; //to stop time-outs
		while(toRet.equals("") && !Object.class.equals(superclass) && counter<5){
			try{
				counter++;
				Class<?> clzz = Class.forName(propertyName + "Data");
				superclass = clzz.getSuperclass();
				String name = superclass.getSimpleName().replace("Data", "");
				toRet = PropertyUtil.getTerm("resources/property_descriptions", name);

			}catch(Exception e){
				//TODO: Error catching
			}
		}
		return toRet;
	}
}
