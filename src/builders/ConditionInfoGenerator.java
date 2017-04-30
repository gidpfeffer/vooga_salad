package builders;

import gamedata.compositiongen.Data;
import gameengine.conditionsgen.Condition;

public class ConditionInfoGenerator extends TwoLevelInfoGenerator<Condition>{
	private static final String DATA_PATH = "gameengine.actors.conditions";
	private static final String SUPER_DATA_PATH = "gameengine.actors.conditionsgen";
	
	public ConditionInfoGenerator() {
		super(DATA_PATH, SUPER_DATA_PATH);
	}
	
	@Override
	protected String simplifyName(Class<?> className) {
		return className.getSimpleName().replace("Condition", "");
	}
	

}