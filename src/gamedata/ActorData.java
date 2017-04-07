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
	 * These constructors/methods can be used in the Game Authoring Environment to build
	 * the possible Troop, Tower, etc. options for the GamePlayer
	 * 
	 * This is the most basic constructor for the ActorData class. To add the "blueprints"
	 * for any Actor to the backend, you must build an ActorData. 
	 * 
	 * The minimal ActorData requires:
	 * 1) A basic actor type (e.g., Troop, Tower) which are used in the backend to
	 * filter (e.g., to make sure that all Troops shoot only Towers.
	 * 2) A BasicData object which represents the basic information about this
	 * actor. This includes the name and imagepath of the game piece. 
	 * 
	 * This constructor defaults to a LimitedHealth HealthData (all Actors must
	 * have some sort of Health, even if it is infinite). It also passes an empty array
	 * as the properties of this Actor.
	 * 
	 * Easy implementation example:
	 * 
	 * Creation of a tower named Bob
	 * ActorData myActor = new ActorData 
	 * 		(ActorType.TOWER, new BasicData("Bob", images/bob));
	 * 
	 * @param data BasicData object with name and imagepath
	 */
	public ActorData(BasicActorType actor, BasicData data){
		this(actor, data, new LimitedHealthData(HEALTH), new Data[0]);
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
	 * For use in Game Authoring Environment.
	 * 
	 * Add a descriptor to this Actor blueprint
	 * 
	 * Examples:
	 * PoisionData --> information about Poisoning
	 * ShootData --> Information about how to shoot
	 * 
	 * @param property Data object to add to the ActorData's list
	 */
	public void addData(Data property){
		myData.add(property);
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
