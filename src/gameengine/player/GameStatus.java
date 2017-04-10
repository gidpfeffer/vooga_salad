package gameengine.player;

import java.util.Optional;

/**
 * 
 * Holds basic information about current status
 * of the game, including experience (if none, just set equal
 * to the levels won), money, and current level.
 * 
 * @author maddiebriere
 *
 */

public class GameStatus {
	//NOTE: Don't need to have experience or money
	public Optional<Double> myExperience;
	public Optional<Double> myMoney;
	
	//TODO: Replace with Predicate
	public void addExperience(double exp){
		if(myExperience.isPresent()){
			myExperience = Optional.of(myExperience.get()+exp);
		}
	}
	
	public void addMoney(double mon){
		if(myMoney.isPresent()){
			myMoney = Optional.of(myMoney.get()+mon);
		}
	}
	
	public void spendMoney(double mon){
		addMoney(-mon);
	}
	
	public double getMyExperience() {
		return myExperience.get();
	}
	
	//TODO: Is the check necessary?
	public void setMyExperience(double myExperience) {
		if(this.myExperience.isPresent()) //if we are considering experience
			this.myExperience = Optional.of(myExperience);
	}
	
	public double getMyMoney() {
		return myMoney.get();
	}
	
	public void setMyMoney(double myMoney) {
		if(this.myMoney.isPresent()) //if we are considering experience
			this.myMoney = Optional.of(myMoney);
	}
	
	
	
}
