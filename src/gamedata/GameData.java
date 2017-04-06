package gamedata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * GameData is the over-arching data class that holds 
 * all of the data requires to launch a game.
 * It holds a Map of each actor type between
 * Integers (representing Option numbers) and ActorDatas, 
 * where the ActorData holds information about how to
 * make that Actor.
 * 
 * The LevelData objects hold preferences for each
 * Level -- these are saved and passed to the LevelController
 * constructor whenever
 * 
 * @author maddiebriere
 *
 */

public class GameData {
	//Level information (preferences, no & type of enemies)
	List<LevelData> levels;
	
	//Actors available for entire game
	private Map<Integer, ActorData> shots;
	private Map<Integer, ActorData> towers;
	private Map<Integer, ActorData> troops;
	private Map<Integer, ActorData> bases;
	
	private int numOptions;
	
	public GameData(){
		shots = new HashMap<Integer, ActorData>();
		towers = new HashMap<Integer, ActorData>();
		troops = new HashMap<Integer, ActorData>();
		bases = new HashMap<Integer, ActorData>();
		numOptions = 0;
	}
	
	/**
	 * This is for use in the GamePlayer
	 */
	
	
	
	/**
	 * This is for use in the GameController.
	 * 
	 * This returns you the ActorData matching to 
	 * the requested option. This ActorData can then be
	 * passed to ActorGenerator in order to create the Actor.
	 * 
	 * @param option Integer representing the option
	 * @return ActorData mapping to that option
	 */
	public ActorData getOption(Integer option){
		//TODO: Clean-up implementation
		if(shots.containsKey(option)){
			return shots.get(option);
		}
		if(towers.containsKey(option)){
			return towers.get(option);
		}
		if(troops.containsKey(option)){
			return troops.get(option);
		}
		if(bases.containsKey(option)){
			return bases.get(option);
		}
		return null;//Non-existent request
	}
	
	
	/**
	 * This is implementation for use in the front-end.
	 * (Authoring Environment).
	 * 
	 * It allows the front-end to add another List of
	 * Data objects representing a possible object
	 * 
	 * Frontend must make an ActorData object and pass it in.
	 * 
	 * See ActorData for an example of how to
	 * create and ActorData object
	 * 
	 */
	
	public void addShot(ActorData data){
		add(shots, data);
	}
	
	public void addTower(ActorData data){
		add(towers, data);
	}
	
	public void addTroop(ActorData data){
		add(troops,data);
	}
	
	public void addBase(ActorData data){
		add(bases,data);
	}
	
	public void add(Map<Integer,ActorData> map, ActorData data){
		map.put(numOptions++, data);
	}
	
	/**
	 * Easy way to add a level -- just pass in
	 * the enemies used in this level
	 * 
	 * @param troops Enemies for the level
	 */
	public void addLevel(Map<ActorData, Integer> troops){
		levels.add(new LevelData(troops));
	}
	
	/**
	 * More sophisticated way to add a level -- pass entire
	 * level data with preferences, settings and troops
	 * encapsulated in data structure
	 * 
	 * @param data LevelData holding level information
	 */
	public void addLevel(LevelData data){
		levels.add(data);
	}
	
	//Getters and setters
	public List<LevelData> getLevels() {
		return levels;
	}
	public LevelData getLevel(int level){
		return levels.get(level);
	}
	public void setLevel(List<LevelData> level) {
		this.levels = level;
	}
	

	public Map<Integer, ActorData> getShots() {
		return shots;
	}

	public void setShots(Map<Integer, ActorData> shots) {
		this.shots = shots;
	}

	public Map<Integer, ActorData> getTowers() {
		return towers;
	}

	public void setTowers(Map<Integer, ActorData> towers) {
		this.towers = towers;
	}

	public Map<Integer, ActorData> getTroops() {
		return troops;
	}

	public void setTroops(Map<Integer, ActorData> troops) {
		this.troops = troops;
	}

	public Map<Integer, ActorData> getBases() {
		return bases;
	}

	public void setBases(Map<Integer, ActorData> bases) {
		this.bases = bases;
	}

	public int getNumOptions() {
		return numOptions;
	}

	public void setNumOptions(int numOptions) {
		this.numOptions = numOptions;
	}
	
	
	
}
