// This entire file is part of my masterpiece.
// Maddie Briere
package builders.infogen.masterpiece;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import gamedata.reflections.Reflections;
import voogasalad.util.reflection.ReflectionException;

/**
 * Abstract class used to garner information from a given package.
 * This generator can iterate through a package and return things like
 * --> Classes within the package
 * --> Simplified names of packages within the package
 * --> A way to "rebuild" and "simplify" names and classes
 * 
 * 
 * 
 * @author maddiebriere
 *
 */

public abstract class PackageInfoGenerator {
	List<String> packages;
	
	public PackageInfoGenerator(String ... packages){
		if(packages.length==0){
			//TODO: Error throwing?
		}
		this.packages = Arrays.asList(packages);
	}
	
	/**
	 * Modify an input class name to return a displayable
	 * String. 
	 * 
	 * Example for AuthorInfoGenerator:
	 * Input: gamedata.composition.MoveWithSetPathData
	 * Output: MoveWithSetPath
	 * 
	 * @param name className to simplify -- assuming labeling system is
	 * consistent across packages
	 * @return Simplified class name
	 */
	public abstract String simplifyName(Class<?> className);
	
	/**
	 * Return the simplified class name of the given object.
	 * Instead of returning a full class path, return 
	 * the simple name modified using the given parameters.
	 * 
	 * @param obj Object from which to collect simplified class name
	 * @return Simplified name
	 */
	public<T> String simplifyObjectName(T obj){
		Class<?> clzz = obj.getClass();
		return simplifyName(clzz);
	}
	
	/**
	 * Return the rebuilt address from the given name, for use
	 * in reflection.
	 * 
	 * @param name Simple name (e.g., MoveWithSetPath)
	 * @return String address (gamedata.composition.MoveWithSetPathData)
	 */
	protected abstract String rebuildAddress(String name);
	
	/**
	 * Method to add a package given a String path to the
	 * given package. This is abstract to allow for subclasses
	 * to either dis-allow package adding or restrict it.
	 * @param pkg String path to package
	 * @return true if the package was added, false otherwise
	 */
	public abstract boolean addPackage(String pkg);
	
	/**
	 * Method to remove a package given a String path to the
	 * given package. This is abstract to allow for subclasses
	 * to either dis-allow package removal or restrict it.
	 * @param pkg String path to package
	 * @return true if the package was removed, false otherwise
	 */
	public abstract boolean removePackage(String pkg);
	
	/**
	 * Use the simplifyName method to return a List of simplified names
	 * matching to a list of classes.
	 * 
	 * @param toSimplify List of Classes to simplify
	 * @return List of Strings representing simplified class names
	 */
	public List<String> getSimplifiedNames(List<Class<?>> toSimplify){
		return 	toSimplify
				.stream()
				.map(p -> simplifyName(p))
				.collect(Collectors.toList());
	}
	
	/**
	 * Modify an input target name to reflect
	 * an actual Class name.
	 * 
	 * Example: For DataInfoGenerator
	 * Input: "MoveWithSetPath"
	 * Output: Class<MoveWithSetPathData>
	 * 
	 * @param name String to "rebuild" into valid class
	 * @return Valid class name matching to given keyword
	 */
	public Class<?> rebuildFromName(String name) {
		try {
			return Class.forName(rebuildAddress(name));
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

	protected List<Class<?>> allClassesIn(String pkg){
		Class<?> [] properties = new Class[0]; 
		try {
			properties = Reflections.getClasses(pkg);
		} catch (ClassNotFoundException | IOException e) {
			throw new ReflectionException("Package not found", this);
		}
		return Arrays.asList(properties);
	}
	
	protected List<Class<?>> allClassesIn(int level){
		return allClassesIn(packages.get(level));
	}
}
