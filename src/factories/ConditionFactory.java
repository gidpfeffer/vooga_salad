package factories;

import gameengine.conditionsgen.Condition;

public class ConditionFactory extends AbstractFactory<Condition>{
	private static final String PATH = "gameengine.conditions.";
	
	public ConditionFactory() {
		super(PATH);
	}

	@Override
	protected String generateObjectType(String name) {
		return name;
	}

	@Override
	protected Condition failResponse() {
		// TODO Auto-generated method stub
		return null;
	}

}
