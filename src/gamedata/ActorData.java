package gamedata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gamedata.composition.BasicData;
import gamedata.composition.Data;
import gamedata.composition.HealthData;
import gamedata.composition.LimitedHealthData;
import types.ActorType;
import types.BasicActorType;

/**
 * Information required to construct a certain, 
 * user-created Actor. Holds a name (e.g., "MyCoolTower")
 * and a List of Data objects that represent the composition
 * of the Actor. This is like a blueprint for the factory --
 * when the user places one of these actors on the grid, 
 * the backend can take all of the data objects and convert
 * them to properties to create an Actor equivalent to the
 * ActorData.
 * 
 * @author maddiebriere
 *
 */

public class ActorData {
	private final static double HEALTH = 100;
	
	private List<Data> myData;
	private BasicActorType actor;
	private BasicData basic;
	
	/**
	 * Easy implementation example:
	 * 
	 * Creation of a tower named Bob
	 * ActorData myActor = new ActorData 
	 * 		(ActorType.TOWER, new BasicData("Bob", images/bob));
	 * 
	 * @param data BasicData object with name and imagepath
	 */
	public ActorData(BasicActorType actor, BasicData data){
		this(actor, data, new LimitedHealthData(HEALTH));
	}
	
	public ActorData(BasicActorType actor, BasicData data, HealthData health){
		this(actor, data, health, new Data[0]);
	}
	
	public ActorData(BasicActorType actor, BasicData data, HealthData health, Data... properties){
		myData = new ArrayList<Data>();
		myData.add(data);
		myData.add(health);
		myData.addAll(Arrays.asList(properties));
		this.actor=actor;
		this.basic = data;
	}
	
	/**
	 * GamePlayer: Useful methods for accessing information about an ActorData
	 *
	 */
	public String getImagePath() {
		return basic.getImagePath();
	}

	public String getName() {
		return basic.getName();
	}

	/**
	 * Getters and setters
	 */
	
	public List<Data> getMyData() {
		return myData;
	}

	public void setMyData(List<Data> myData) {
		this.myData = myData;
	}

	public BasicActorType getActor() {
		return actor;
	}

	public void setActor(BasicActorType actor) {
		this.actor = actor;
	}

	public BasicData getBasic() {
		return basic;
	}

	public void setBasic(BasicData basic) {
		this.basic = basic;
	}
	

	
	
}
