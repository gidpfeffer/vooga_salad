package factories;

import gameengine.actors.Troop;

public class TroopFactory extends ActorFactory<Troop> {
	
	public TroopFactory() {
		super();
	}

	@Override
	protected String generateObjectType(String name) {
		return "Troop";
	}

}
