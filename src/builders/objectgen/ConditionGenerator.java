package builders.objectgen;

import factories.ConditionFactory;
import gameengine.conditionsgen.Condition;

public class ConditionGenerator {

	public static Condition makeCondition(String data, Object ... args){
		ConditionFactory factory = new ConditionFactory();
		Condition toRet = factory.make(data+"Condition", args);
		return toRet;
	}
	
}
