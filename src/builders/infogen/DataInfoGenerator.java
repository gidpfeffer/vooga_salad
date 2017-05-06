package builders.infogen;

import gamedata.compositiongen.Data;

/**
 * Generate information about Data objects, including
 * concrete subclasses, abstract super classes, etc.
 * (inherited).
 * 
 * @author maddiebriere
 *
 */

public class DataInfoGenerator extends TwoLevelInfoGenerator<Data>{
	//Keys for accessing class path property file
	private static final String DATA = "Data";
	private static final String SUPER_DATA = "GenData";
	
	public DataInfoGenerator() {
		super(ClassFinder.getClass(DATA), ClassFinder.getClass(SUPER_DATA));
	}
	
	@Override
	protected String simplifyName(Class<?> className) {
		return className.getSimpleName().replace(DATA, "");
	}

	@Override
	protected Class<?> rebuildFromName(String name) {
		try {
			return Class.forName(name + DATA);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	

}
