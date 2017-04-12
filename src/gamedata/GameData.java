package gamedata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameengine.grid.interfaces.Identifiers.Grid2D;
import types.BasicActorType;

/**
 * 
 * GameData is the over-arching data class that holds 
 * all of the data required to launch a game.
 * It holds a Map of each actor type between
 * Integers (representing Option numbers) and ActorDatas, 
 * where the ActorData holds information about how to
 * make that Actor.
 * 
 * The LevelData objects hold preferences for each
 * Level -- these are saved and passed to the LevelController
 * constructor whenever a new Level is created.
 * 
 * USES:
 * 
 * GAME PLAYER:
 * -getOptions --> return all pieces (ActorData) mapped to order numbers (Integers)
 * -getTowerOptions --> return all Tower pieces
 * -getTroopOptions --> return all Troop pieces
 * -getBaseOptions --> return all Base pieces
 * -getShotOptions --> return all Shot pieces
 * 
 * GAME CONTROLLER:
 * -getOption(Integer index) --> return the ActorData associated with that order number
 * 
 * 
 * GAME AUTHORING ENVIRONMENT:
 * -add(ActorData a) --> Add an ActorData (blueprint for an Actor) 
 * 			to the current representation of the game
 * -addLevel(LevelData l) --> Add a LevelData to represent the preferences and 
 * 			enemies for that level
 * 
 * 
 * @author maddiebriere
 *
 */

public class GameData {
	//Level information (preferences, no & type of enemies)
	Map<Integer,LevelData> levels;
	
	//Path information
	PathData myPaths;
	
	//Possible projectiles
	ProjectileData myProjectiles;
	
	//Information about how the game is visually displayed
	DisplayData display;
	
	//Actors available for entire game
	private Map<Integer, ActorData> pieces;
	
	private int numOptions;
	
	public GameData(){
		levels=new HashMap<Integer,LevelData>();
		myPaths = new PathData();
		myProjectiles = new ProjectileData();
		display = new DisplayData();
		pieces = new HashMap<Integer, ActorData>();
		numOptions = 0;
	}
	
	/**
	 * This is for use in the GamePlayer. Returns
	 * all of possible options for creation in the 
	 */
	public Map<Integer,ActorData> getOptions(){
		return pieces;
	}
	
	public Map<Integer,ActorData> getTowerOptions(){
		return getOptionType(BasicActorType.Tower);
	}
	
	public Map<Integer,ActorData> getTroopOptions(){
		return getOptionType(BasicActorType.Troop);
	}
	
	public Map<Integer,ActorData> getBaseOptions(){
		return getOptionType(BasicActorType.Base);
	}
	
	public Map<Integer,ActorData> getShotOptions(){
		return getOptionType(BasicActorType.Shot);
	}
	
	
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
		return pieces.get(option);
	}
	
	
	
	
	
	
	
	
	/**
	 * This is implementation for use in the Authoring Environment
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
	
	public void add(ActorData data){
		pieces.put(numOptions++, data);
	}
	
	/**
	 * Easy way to add a level -- just pass in
	 * the enemies used in this level.
	 * 
	 * Integer maps to the number of enemies on the level.
	 * 
	 * @param duration representing level length
	 */
	public void addLevel(double duration,int level){
		levels.put(level,new LevelData(duration));
	}
	
	/**
	 * More sophisticated way to add a level -- pass entire
	 * level data with preferences, settings and troops
	 * encapsulated in data structure
	 * 
	 * @param data LevelData holding level information
	 */
	public void addLevel(LevelData data, int level){
		levels.put(level,data);
	}
	
	
	/**
	 * Returns Integers corresponding to a path number, matched to the 
	 * Path that is defined by that number.
	 * 
	 * @return Map of Integers mapped to Paths
	 */
	public Map<Integer, List<Grid2D>> getPathOptions(){
		return myPaths.getMyPaths();
	}
	
	/**
	 * Returns Integers corresponding to a path number, matched to the 
	 * Path that is defined by that number.
	 * 
	 * @return Map of Integers mapped to Paths
	 */
	public Map<Integer, ProjectileType> getProjectileOptions(){
		return myProjectiles.getMyProjectiles();
	}
	
	
	
	
	/**
	 * Get all option matching to a certain type (Troop, Tower, etc.) of
	 * Actor. 
	 * @param type Type to match
	 * @return All Actors available matching this type
	 */
	private Map<Integer,ActorData> getOptionType(BasicActorType type){
		Map<Integer,ActorData> toRet = new HashMap<Integer,ActorData>();
		pieces.forEach((key, value) 
				-> {if (value.getActor().equals(type)) { 
					toRet.put(key,value);
					}});
		return toRet;
	}
	
	
	//Getters and setters
	public Map<Integer,LevelData> getLevels() {
		return levels;
	}
	public LevelData getLevel(int level){
		return levels.get(level);
	}
	public void setLevel(Map<Integer,LevelData> level) {
		this.levels = level;
	}

	public int getNumOptions() {
		return numOptions;
	}

	public void setNumOptions(int numOptions) {
		this.numOptions = numOptions;
	}

	public PathData getMyPaths() {
		return myPaths;
	}

	public void setMyPaths(PathData myPaths) {
		this.myPaths = myPaths;
	}

	public ProjectileData getMyProjectiles() {
		return myProjectiles;
	}

	public void setMyProjectiles(ProjectileData myProjectiles) {
		this.myProjectiles = myProjectiles;
	}
	
	
}
