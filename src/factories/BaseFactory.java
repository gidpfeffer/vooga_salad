package factories;

import gameengine.actors.Base;

public class BaseFactory extends ActorFactory<Base>{
	
	public BaseFactory() {
		super();
	}

	@Override
	protected String generateObjectType(String name) {
		return "Base";
	}

}