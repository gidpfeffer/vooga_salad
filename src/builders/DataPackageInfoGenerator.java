package builders;

import java.util.ArrayList;
import java.util.List;

import gamedata.compositiongen.Data;

public class DataPackageInfoGenerator extends PackageInfoGenerator<Data>{
	private static final String DATA_PATH = "gamedata.composition";
	private static final String SUPER_DATA_PATH = "gamedata.compositiongen";
	
	public DataPackageInfoGenerator() {
		super(generatePackageList());
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
	public List<String> getPropertyNames(){
		List<Class<?>> properties = allClassesIn(0); //search lowest/concrete level
		if(!(properties.size() == 0)){
			return getSimplifiedNames(properties);
		}
		return new ArrayList<String>();
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
	protected String simplifyName(Class<?> className) {
		return className.getSimpleName().replace("Data", "");
	}
	
	private static List<String> generatePackageList(){
		ArrayList<String> toRet= new ArrayList<String>();
		toRet.add(DATA_PATH);
		toRet.add(SUPER_DATA_PATH);
		return toRet;
	}

}
