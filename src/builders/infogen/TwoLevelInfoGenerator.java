package builders.infogen;

import java.util.ArrayList;
import java.util.List;

public abstract class TwoLevelInfoGenerator<A> extends PackageInfoGenerator<A>{
	
	public TwoLevelInfoGenerator(String lowerLevel, String upperLevel) {
		super(generatePackageList(lowerLevel, upperLevel));
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
	public List<String> getTypeNames(){
		List<Class<?>> types = allClassesIn(0); //search lowest/concrete level
		if(!(types.size() == 0)){
			return getSimplifiedNames(types);
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
	
	private static List<String> generatePackageList(String lowerLevel, String upperLevel){
		ArrayList<String> toRet= new ArrayList<String>();
		toRet.add(lowerLevel);
		toRet.add(upperLevel);
		return toRet;
	}

}
