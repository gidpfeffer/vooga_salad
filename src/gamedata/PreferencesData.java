package gamedata;

import java.util.Optional;

/**
 * Preferences set in authoring stage
 * @author maddiebriere
 *
 */

public class PreferencesData{
	static final int NUM_LIVES = 3;
	static final boolean DEFAULT = false;
	
	private Optional<Integer> numLives;
	private Optional<Boolean> enemyLoop;
	private Optional<Boolean> towersAttackable;
	private Optional<Boolean> wantMoney;
	private Optional<Boolean> expByLevel;
	private Optional<Boolean> pauseBetweenWaves;
	
	public int getNumLives(){
		return numLives.orElse(NUM_LIVES);
	}
	
	public boolean doEnemiesLoop(){
		return enemyLoop.orElse(DEFAULT);
	}
	
	public boolean areTowersAttackable(){
		return towersAttackable.orElse(DEFAULT);
	}
	
	public boolean wantMoney(){
		return wantMoney.orElse(DEFAULT);
	}
	
	public boolean expByLevel(){
		return expByLevel.orElse(DEFAULT);
	}
	
	public boolean pauseBetweenWaves(){
		return pauseBetweenWaves.orElse(DEFAULT);
	}

	public void setNumLives(Optional<Integer> numLives) {
		this.numLives = numLives;
	}

	public void setEnemyLoop(Optional<Boolean> enemyLoop) {
		this.enemyLoop = enemyLoop;
	}

	public void setTowersAttackable(Optional<Boolean> towersAttackable) {
		this.towersAttackable = towersAttackable;
	}

	public void setWantMoney(Optional<Boolean> wantMoney) {
		this.wantMoney = wantMoney;
	}

	public void setExpByLevel(Optional<Boolean> expByLevel) {
		this.expByLevel = expByLevel;
	}

	public void setPauseBetweenWaves(Optional<Boolean> pauseBetweenWaves) {
		this.pauseBetweenWaves = pauseBetweenWaves;
	}
	
	//TODO: Generate more preferences
	
	
}
