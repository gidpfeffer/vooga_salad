package gamedata;

/**
 * Preferences set in authoring stage
 * @author maddiebriere
 *
 */

public interface PreferencesData {
	static final int NUM_LIVES = 3;
	
	default int getNumLives(){
		return NUM_LIVES;
	}
	
	default boolean doEnemiesLoop(){
		return false;
	}
	
	default boolean isBasePresent(){
		return true;
	}
	
	default boolean areTowersAttackable(){
		return true;
	}
	
	default boolean wantMoney(){
		return true;
	}
	
	default boolean pauseBetweenWaves(){
		return false;
	}
	
	//TODO: Generate more preferences
}
