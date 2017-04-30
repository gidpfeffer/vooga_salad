package builders;

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
	
	public String simplifyName(A obj){
		Class<?> clzz = obj.getClass();
		return simplifyName(clzz);
	}

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
			e.printStackTrace();
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
