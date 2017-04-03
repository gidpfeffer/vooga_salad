package gameengine.player;

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
	public double myExperience;
	public double myMoney;
	public int myLevel;
	
	public void addExperience(double exp){
		myExperience +=exp;
	}
	
	public void addMoney(double mon){
		myMoney+= mon;
	}
	
	public void spendMoney(double mon){
		addMoney(-mon);
	}
	
	public void levelUp(){
		myLevel++;
	}
	
	public double getMyExperience() {
		return myExperience;
	}
	
	public void setMyExperience(double myExperience) {
		this.myExperience = myExperience;
	}
	
	public double getMyMoney() {
		return myMoney;
	}
	
	public void setMyMoney(double myMoney) {
		this.myMoney = myMoney;
	}
	
	public int getMyLevel() {
		return myLevel;
	}
	
	public void setMyLevel(int myLevel) {
		this.myLevel = myLevel;
	}
	
	
	
}
