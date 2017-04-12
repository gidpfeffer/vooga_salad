package builders;

import java.util.ArrayList;
import java.util.List;

import factories.ActorFactory;
import factories.PropertyFactory;
import gamedata.ActorData;
import gamedata.compositiongen.Data;
import gameengine.actors.MainActor;
import gameengine.actors.propertygen.IActProperty;
import gameengine.actors.propertygen.Property;
import util.IDGenerator;

/**
 * Builds an Actor using the ActorFactory. This should
 * be invoked by the GameController to generate new actors.
 * This class should be used AFTER the number representing the 
 * order has been used to invoke the correct ActorData from
 * the GameData object (using getOption(Integer index)) as 
 * its make function requires an IDGenerator-created ID and the
 * ActorData (like a blueprint) corresponding to the desired Actor.
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

	public static MainActor makeActor(Integer option, ActorData data){
		//Change to property factory
		
		//TODO: Add ID Generator
		
		ActorFactory actorFactory = new ActorFactory();
		ArrayList<Object> toBuild = new ArrayList<Object>();
		
		toBuild.add(data.getActor()); //add type
		toBuild.add(option);
		int index = IDGenerator.getNewID();
		toBuild.add(index); //add ID
	
		
		List<Data> properties = data.getMyData();
		PropertyFactory propFactory = new PropertyFactory();
		for(Data d: properties){
			String dataName = d.getClass().getSimpleName();
			String propertyName = dataName.replace("Data", "Property");
			Property property = propFactory.make(propertyName, d);
			toBuild.add(property);
		}
		
		MainActor toRet = actorFactory.make(toBuild.toArray());
		return toRet;
	}
}
