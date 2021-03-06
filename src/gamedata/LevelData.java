package gamedata;

import java.util.ArrayList;
import java.util.List;

import gameengine.conditions.EnduranceCondition;
import gameengine.conditionsgen.Condition;

/**
 * Holds all the data that is encoded for a level
 * such as:
 * 1) Enemies
 * 2) Preferences (e.g., waves of attack)
 * 3) Duration of enemy attack
 * 4) Health, attack and speed multipliers
 * (more of each with higher levels)
 * 
 * Generated by the Game Authoring Environment and
 * invoked in the GameController when 
 * new LevelControllers are created.
 * 
 * @author maddiebriere
 *
 */
public class LevelData {
	private List<WaveData> myWaves;
	//private double difficulty; // 0 - 1.0
	private double duration; 
	
	private double healthMultiplier;
	private double speedMultiplier;
	private double attackMultiplier;
	
	private Condition condition;

	public LevelData(){
		this(100); 
	}
	
	public LevelData(double duration){
		this.duration = duration;
		myWaves= new ArrayList<WaveData>();
		attackMultiplier=-1;
		speedMultiplier=-1;
		healthMultiplier=-1;
		condition = new EnduranceCondition(100);
	}
	
	public int getNumWaves(){
		return myWaves.size();
	}
	
	public void addWave(WaveData data){
		myWaves.add(data);
	}

	public double getDuration() {
		return duration;
	}

	private double fractionCheck(double toCheck){
		if(toCheck<=0){
			toCheck = .01;
		}
		if(toCheck>1){
			toCheck = 1;
		}
		return toCheck;
	}
	
	public void setDuration(double duration) {
		this.duration = duration;
	}

	//Health, from 0 - 1.0
	// 0 difficulty -> 1 health
	public double getHealthMultiplier() {
		return healthMultiplier;
	}
	
	//Attack abililities, from 0 - 1.0
	//0 difficulty -> 1 attack
	public double getAttackMultiplier() {
		return attackMultiplier;
	}

	//How fast everything happens
	//0 difficulty -> 0 speed
	public double getSpeedMultiplier() {
		return speedMultiplier;
	}
	
	public void setHealthMultiplier(double healthMultiplier) {
		this.healthMultiplier = fractionCheck(healthMultiplier);
	}

	public void setSpeedMultiplier(double speedMultiplier) {
		this.speedMultiplier = fractionCheck(speedMultiplier);
	}

	public void setAttackMultiplier(double attackMultiplier) {
		this.attackMultiplier = fractionCheck(attackMultiplier);
	}

	public List<WaveData> getMyWaves() {
		return myWaves;
	}

	public void setMyWaves(List<WaveData> myWaves) {
		this.myWaves = myWaves;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	
	
	
}
