package factories;

import gameengine.actors.management.Actor;

/**
 * Factory for creation of actors
 * 
 * @author maddiebriere
 *
 */

public class ActorFactory extends AbstractFactory<Actor>{

	//Use ID generator class
	
	public ActorFactory(String path) {
		super(path);
	}

	@Override
	protected String generateObjectType(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Actor failResponse() {
		//TODO: More sophisticated response
		return null;
	}

}
