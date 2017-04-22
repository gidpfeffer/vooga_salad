package gamedata;

import java.util.ArrayList;
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
	
	//Preferences for game
	PreferencesData preferences;
	
	//Path information
	PathData myPaths;
	
	//Layer information
	MapLayersData myLayers; 

	//Categories available (e.g. Troop)
	List<BasicActorType> types;
	
	//Information about how the game is visually displayed
	DisplayData display;
	
	//Actors available for entire game
	private Map<Integer, LineageData> pieces;
	
	private int numOptions;


	public GameData(){
		levels=new HashMap<Integer,LevelData>();
		myPaths = new PathData();
		preferences = new PreferencesData();
		display = new DisplayData();
		pieces = new HashMap<Integer, LineageData>();
		types = new ArrayList<BasicActorType>();
		numOptions = 0;
		myLayers = new MapLayersData();
	}
	
	/**
	 * This is for use in the GamePlayer. Returns
	 * all of possible options for creation in the 
	 * game.
	 */
	public Map<Integer, ActorData> getOptions(){
		HashMap<Integer, ActorData> toRet = new HashMap<Integer, ActorData>();
		for(Integer lineage: pieces.keySet()){
			toRet.put(lineage, pieces.get(lineage).getCurrent());
		}
		return toRet;
	}
	
	/**
	 * Get all option matching to a certain type (Troop, Tower, etc.) of
	 * Actor. 
	 * @param type Type to match
	 * @return All Actors available matching this type
	 */
	public Map<Integer,ActorData> getAllOfType(BasicActorType type){
		Map<Integer,ActorData> toRet = new HashMap<Integer,ActorData>();
		getOptions().forEach((key, value) 
				-> {if (value.getType().equals(type)) { 
					toRet.put(key,value);
					}});
		return toRet;
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
		return getOptions().get(option);
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
	public LineageData add(ActorData data){
		LineageData lin = new LineageData(data);
		pieces.put(numOptions++, lin);
		return lin;
	}
	
	public void addUpgrade(LineageData data, ActorData toAdd){
		data.addGeneration(toAdd);
	}
	
	/**
	 * Upgrade the given lineage data
	 * @param LineageData data to update
	 */
	public void upgrade(Integer option){
		pieces.get(option).upgrade();
	}
	
	/**
	 * Add another category type -- defined by user.
	 * Example: addType("Monster")
	 * @param newCategory New category
	 * @return BasicActorType Created category
	 */
	public BasicActorType addType(String newCategory){
		BasicActorType toRet = new BasicActorType(newCategory);
		types.add(toRet);
		return toRet;
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

	public List<BasicActorType> getTypes() {
		return types;
	}

	public void setTypes(List<BasicActorType> types) {
		this.types = types;
	}

	public PreferencesData getPreferences() {
		return preferences;
	}

	public void setPreferences(PreferencesData preferences) {
		this.preferences = preferences;
	}
	
	public void setLayers(MapLayersData layers){
		myLayers = layers;
	}
	public MapLayersData getLayers(){
		return myLayers;
	}

	

	
}
