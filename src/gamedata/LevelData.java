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
	private List<List<Data>> pieces;
	//TODO: add other variables

	public LevelData(double maxX, double maxY){
		myMaxX = maxX;
		myMaxY = maxY;
	}
	
	//TODO: Limit input based on types of parameters required for type
	//TODO: Get rid of dependnecy on enum actor type to match method header 
	public void addShot(ActorData actor, 
			MoveWithDestinationData move, HealthData data, 
			Data...datas){
		
	}
	
	public void addTower(HealthData data, 
			Data...datas){
		
	}
	
	public void addTroop(HealthData data, 
			Data...datas){
		
	}
	
	public void addBase(HealthData data, 
			Data...datas){
		
	}
	
	public void add(Data...datas){
		pieces.add(Arrays.asList(datas));
	}
	
	
}
