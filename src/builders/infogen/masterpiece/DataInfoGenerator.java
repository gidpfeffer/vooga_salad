package builders.infogen.masterpiece;

import builders.infogen.ClassFinder;

/**
 * Generate information about Data objects, including
 * concrete subclasses, abstract super classes, etc.
 * (inherited).
 * 
 * @author maddiebriere
 *
 */

public class DataInfoGenerator extends HierarchyInfoGenerator{
	
	//Keys for accessing class path property file
	private static final String DATA = "Data";
	private static final String SUPER_DATA = "GenData";
	
	public DataInfoGenerator() {
		super(ClassFinder.getClass(DATA), ClassFinder.getClass(SUPER_DATA));
	}
	
	@Override
	public String simplifyName(Class<?> className) {
		return className.getSimpleName().replace(DATA, "");
	}
	
	@Override
	protected String rebuildAddress(String name){
		return ClassFinder.getClass(DATA) + "." + name + DATA;
	}
	

}
