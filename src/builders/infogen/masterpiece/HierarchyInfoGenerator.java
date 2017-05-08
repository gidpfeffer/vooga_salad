package builders.infogen.masterpiece;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import builders.infogen.VarArgsUtil;
import builders.util.FieldGenerator;
import gamedata.FieldData;

public abstract class HierarchyInfoGenerator extends PackageInfoGenerator{
	
	/**
	 * The user should enter a String corresponding to the lower level (most
	 * concrete) package of classes (ex. gamedata.composition) and a String
	 * corresponding to the higher level (most abstract of classes
	 * (ex. TwoLevelInfoGenerator)
	 * @param concreteLevel
	 * @param categoryLevels
	 */
	public HierarchyInfoGenerator(String concreteLevel, String ... categoryLevels) {
		super(VarArgsUtil.createVarArgs(concreteLevel, categoryLevels));
	}
	
	/**
	 * Get all of the property types available to the user. 
	 * Includes:
	 * - LimitedHealth
	 * - MoveWithDestination
	 * - ShotTargetFar
	 * ...
	 * @return List of String corresponding to the names of the properties
	 */
	public List<String> getOptionNames(){
		return packageNameSearch(0);
	}
	
	/**
	 * Get all of the property categories available to the user. 
	 * Includes:
	 * - Shoot
	 * - Move
	 * - Health
	 * ...
	 * @return List of String corresponding to the names of the properties
	 */
	public List<String> getCategoryNames(){
		return packageNameSearch(1);
	}
	
	private List<String> packageNameSearch(int level){
		List<Class<?>> types = allClassesIn(0);
		if(!(types.size() == 0)){
			return getSimplifiedNames(types);
		}
		return new ArrayList<String>();
	}
	
	/**
	 * Return a map of the Strings representing the class type and a List of
	 * FieldData objects representing the fields taken by that class.
	 * 
	 * @param info TwoLevelInfoGenerator for use in collecting concrete data classes
	 * @return Map of String names representing classes to Lists of FieldDatas
	 */
	public Map<String, List<FieldData>> getOptionsWithArgs(){
		List<Class<?>> datas = getConcreteDataClasses();
		Map<String,List<FieldData>> toRet = new LinkedHashMap<String, List<FieldData>>();
		
		for(Class<?> clzz: datas){
			List<Field> fields = FieldGenerator.getFields(clzz);
			List<FieldData> fieldDatas = FieldGenerator.getFieldDatas(fields);
			toRet.put(simplifyName(clzz), fieldDatas);
		}
		return toRet;
	}
	
	/**
	 * Get all of the data types available in the back-end 
	 * Includes:
	 * - LimitedHealthData
	 * - MoveWithDestinationData
	 * - ShotTargetFarData
	 * ...
	 * @return List of String corresponding to the names of data classes
	 */
	public List<Class<?>> getConcreteDataClasses(){
		return allClassesIn(0);
	}
	
	@Override
	public boolean addPackage(String pkg) {
		return false;
	}

	@Override
	public boolean removePackage(String pkg) {
		return false;
	}
}
