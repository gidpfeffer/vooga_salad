package gamedata;

import java.util.Map;

/**
 * holds all the data that is encoded for a level
 * such as enemies, towers, and map
 * @author Maddie
 *
 */
public class LevelData {
	private PreferencesData myPreferences;
	private Map<ActorData, Integer> troops;
	private int difficulty;
	private double duration; //duration for enemy presence
	
	//Increase by level
	private double healthMultiplier;
	private double attackMultiplier;
	private double speedMultiplier;

	public LevelData(Map<ActorData, Integer> troops){
		//TODO: Implement constructors
	}
	

	
	
}
