package gamedata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author maddiebriere
 *
 */

public class GameData {
	List<LevelData> levels;
	List<PreferencesData> levelSettings;
	
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
