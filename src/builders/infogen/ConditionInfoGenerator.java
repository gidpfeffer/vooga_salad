package builders.infogen;

import gameengine.conditionsgen.Condition;

/**
 * Generate information about Conditions, including
 * concrete subclasses, abstract super classes, etc.
 * (inherited).
 * 
 * @author maddiebriere
 *
 */

public class ConditionInfoGenerator extends TwoLevelInfoGenerator<Condition>{
	//keys for accessing class path property file
	private static final String CONDITION = "Condition";
	private static final String SUPER_CONDITION = "GenCondition";
	
	public ConditionInfoGenerator() {
		super(ClassFinder.getClass(CONDITION), ClassFinder.getClass(SUPER_CONDITION));
	}
	
	@Override
	protected String simplifyName(Class<?> className) {
		return className.getSimpleName().replace(CONDITION, "");
	}

	@Override
	protected Class<?> rebuildFromName(String name) {
		try {
			return Class.forName(name + CONDITION);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
	

}