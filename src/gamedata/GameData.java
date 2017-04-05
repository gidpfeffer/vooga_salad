package gamedata;

import java.util.List;

/**
 * 
 * @author maddiebriere
 *
 */

public class GameData {
	List<LevelData> levels;
	PreferencesData myPreferences;
	
	public List<LevelData> getLevels() {
		return levels;
	}
	public LevelData getLevel(int level){
		return levels.get(level);
	}
	public void setLevel(List<LevelData> level) {
		this.levels = level;
	}
	public PreferencesData getMyPreferences() {
		return myPreferences;
	}
	public void setMyPreferences(PreferencesData myPreferences) {
		this.myPreferences = myPreferences;
	}
	
	
}
