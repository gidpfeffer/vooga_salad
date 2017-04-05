package gamedata;

import java.util.ArrayList;
import java.util.List;

import gamedata.composition.BasicData;
import gamedata.composition.Data;
import gamedata.composition.HealthData;
import gamedata.composition.LimitedHealthData;

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
	private String name;
	
	/**
	 * Easy implementation example:
	 * 
	 * ActorData myActor = new ActorData 
	 * 		(new BasicData("Bob", images/bob));
	 * 
	 * @param data BasicData object with name and imagepath
	 */
	public ActorData(BasicData data){
		this(data, new LimitedHealthData(HEALTH));
	}
	
	public ActorData(BasicData data, HealthData health){
		myData = new ArrayList<Data>();
		myData.add(data);
		myData.add(health);
		name = data.getName();
	}

	public List<Data> getMyData() {
		return myData;
	}

	public void setMyData(List<Data> myData) {
		this.myData = myData;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
