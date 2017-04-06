package gamedata;

import java.util.ArrayList;

import factories.ActorFactory;
import gameengine.actors.management.Actor;

/**
 * Builds an Actor using the ActorFactory
 * 
 * @author maddiebriere
 */

public class ActorGenerator{

	/**
	 * Generate an Actor from an ID (generated from IdGenerator, 
	 * represents Grid placement) and ActorData object.
	 * 
	 * @param ID Represents grid placement/ ActorGrid index
	 * @param data ActorData holding information aobut how to make Actor
	 * @return Actor
	 */
	public static Actor makeActor(Integer ID, ActorData data){
		//Change to property factory
		ActorFactory factory = new ActorFactory();
		ArrayList<Object> toBuild = new ArrayList<Object>();
		toBuild.add(ID);
		toBuild.addAll(data.getMyData());
		return factory.make(data.getActor().toString(), toBuild.toArray());
	}
}
