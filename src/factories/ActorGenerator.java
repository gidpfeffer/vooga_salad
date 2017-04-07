package factories;

import java.util.ArrayList;
import java.util.List;

import gamedata.ActorData;
import gamedata.composition.Data;
import gameengine.actors.management.Actor;
import gameengine.actors.properties.IActProperty;

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
		ActorFactory actorFactory = new ActorFactory();
		ArrayList<Object> toBuild = new ArrayList<Object>();
		toBuild.add(data.getActor()); //add type
		toBuild.add(ID); //add ID
		
		List<Data> properties = data.getMyData();
		PropertyFactory propFactory = new PropertyFactory();
		for(Data d: properties){
			String dataName = d.getClass().getName();
			String propertyName = dataName.replace("Data", "Property");
			IActProperty property = propFactory.make(propertyName, properties);
			toBuild.add(property);
		}
		return actorFactory.make(data.getActor().toString(), toBuild.toArray());
	}
}