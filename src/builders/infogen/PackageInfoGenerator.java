package builders.infogen;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import gamedata.reflections.Reflections;

public abstract class PackageInfoGenerator <A> {
	/**
	 * Note: An assumption is made that the user has 
	 * separated their classes into a package
	 * of concrete classes, which extend more general super
	 * classes, which then may extend more general super classes...
	 * and so on
	 */
	List<String> packages;
	
	/**
	 * Expected input when String at 0 represents more
	 * concrete classes, while String at length-1 represents
	 * highest categories.
	 * 
	 * @param packages
	 */
	public PackageInfoGenerator(List<String> packages){
		if(packages.size()==0){
			//TODO: Error throwing?
		}
		this.packages = packages;
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
	protected abstract String simplifyName(Class<?> className);
	
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
	protected abstract Class<?> rebuildFromName(String name);
	
	
	/**
	 * Return the simplified class name of the given object.
	 * Instead of returning a full class path, return 
	 * the simple name modified using the given parameters.
	 * 
	 * @param obj Object from which to collect simplified class name
	 * @return Simplified name
	 */
	public String simplifyName(A obj){
		Class<?> clzz = obj.getClass();
		return simplifyName(clzz);
	}

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

	protected List<Class<?>> allClassesIn(String pkg){
		Class<?> [] properties = new Class[0]; 
		try {
			properties = Reflections.getClasses(pkg);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
		}
		return Arrays.asList(properties);
	}
	
	protected List<Class<?>> allClassesIn(int level){
		return allClassesIn(packages.get(level));
	}
	
	public List<String> getPackages() {
		return packages;
	}

	public void setPackages(List<String> packages) {
		this.packages = packages;
	}
}
