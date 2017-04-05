package gamedata;

import java.util.Arrays;
import java.util.List;

import gamedata.composition.ActorData;
import gamedata.composition.Data;
import gamedata.composition.HealthData;
import gamedata.composition.MoveWithDestinationData;

/**
 * holds all the data that is encoded for a level
 * such as enemies, towers, and map
 * @author Maddie
 *
 */
public class LevelData {
	private double myMaxX;
	private double myMaxY;
	private List<List<Data>> shots;
	private List<List<Data>> towers;
	private List<List<Data>> troops;
	private List<List<Data>> bases;

	public LevelData(double maxX, double maxY){
		myMaxX = maxX;
		myMaxY = maxY;
	}
	
	public Map<Integer, ActorData> getOptions(){
		
	}
	
	public List<String> getNames(){
		//TODO: Implement
		return null;
	}
	
	/**
	 * This is implementation for use in the front-end.
	 * (Authoring Environment).
	 * 
	 * It allows the front-end to add another List of
	 * Data objects representing a possible object
	 * 
	 */
	
	//TODO: Implement
	//TODO: Limit input based on types of parameters required for type
	//TODO: Get rid of dependency on enum actor type to match method header 
	public void addShot(ActorData actor, HealthData data, 
			Data...datas){
		
	}
	
	public void addTower(ActorData actor, HealthData data, 
			Data...datas){
		
	}
	
	public void addTroop(ActorData actor, HealthData data, 
			Data...datas){
		
	}
	
	public void addBase(ActorData actor,HealthData data, 
			Data...datas){
		
	}
	
	public void add(List<List<Data>> pieces, Data...datas){
		pieces.add(Arrays.asList(datas));
	}

	public double getMyMaxX() {
		return myMaxX;
	}

	public void setMyMaxX(double myMaxX) {
		this.myMaxX = myMaxX;
	}

	public double getMyMaxY() {
		return myMaxY;
	}

	public void setMyMaxY(double myMaxY) {
		this.myMaxY = myMaxY;
	}

	public List<List<Data>> getShots() {
		return shots;
	}

	public List<List<Data>> getTowers() {
		return towers;
	}

	public List<List<Data>> getTroops() {
		return troops;
	}

	public List<List<Data>> getBases() {
		return bases;
	}
	
	

	
	
	
}
