package gamedata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import builders.OptionGenerator;
import gamedata.composition.LimitedHealthData;
import gamedata.compositiongen.Data;
import gamedata.compositiongen.HealthData;
import gamedata.map.LayerData;
import gamedata.map.PolygonData;
import types.BasicActorType;

/**
 * Information required to construct a certain, 
 * user-created Actor. This is effectively a "blueprint"
 * class. The authoring environment will create a blueprint and save
 * it, allowing for future game players to invoke that blueprint
 * (pass it to a factory) and create an instance of the desired Actor.
 * 
 * This is like a blueprint for the factory --
 * when the user places one of these actors on the grid, 
 * the backend can take all of the data objects and convert
 * them to properties to create an Actor equivalent to the
 * ActorData.
 * 
 * Holds:
 * 1) A name (e.g., "MyCoolTower")
 * 2) A List of Data objects that represent the composition
 * of the Actor. 
 * 
 * @author maddiebriere
 *
 */

public class ActorData {
	private final static double HEALTH = 1;
	
	private Map<String,Data> myData;
	private BasicActorType type;
	private HealthData health;
	private BasicData basic;
	
	/**
	 * This list holds the layer that this actor belong.  Layer is a list of polygons
	 */
	private LayerData layer; 
	

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
	 * 		(BasicActorType.TOWER, new BasicData("Bob", images/bob));
	 * 
	 * @param data BasicData object with name and imagepath
	 */
	public ActorData(BasicActorType actor, BasicData data){
		this(actor, data, new LimitedHealthData(HEALTH), new LayerData(new ArrayList<PolygonData>()),new Data[0]);
	}
	
	public ActorData(BasicActorType actor, BasicData data, HealthData health, LayerData layer, Data... properties){
		myData = new HashMap<String,Data>();
		for(Data d: properties){
			addData(d);
		}
		this.health=health;
		this.type=actor;
		this.basic=data;
		this.layer = layer; 
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
		String name = OptionGenerator.getName(property);
		if(myData.containsKey(name)){
			myData.remove(name);
		}
		myData.put(name, property);
	}
	
	
	public void removeData(Data property){
		String name = OptionGenerator.getName(property);
		if(myData.containsKey(name)){
			myData.remove(name);
		}
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
		return new ArrayList<Data>(myData.values());
	}
	
/*	public List<Data> getAllData(){
		List<Data> copy = new ArrayList<Data>(myData);
		copy.add(health);
		return copy;
	}*/

	public void setMyData(Map<String, Data> data) {
		this.myData = data;
	}

	public BasicActorType getType() {
		return type;
	}

	public void setType(BasicActorType actor) {
		this.type = actor;
	}

	public BasicData getBasic() {
		return basic;
	}

	public void setBasic(BasicData basic) {
		this.basic = basic;
	}


	public HealthData getHealth() {
		return health;
	}

	public void setHealth(HealthData health) {
		this.health = health;
	}
	

	public LayerData getLayer() {
		return layer;
	}

	public void setLayer(LayerData layer) {
		this.layer = layer;
	}

	
	
}
