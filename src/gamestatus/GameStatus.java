package gamestatus;

import java.util.HashMap;

import ui.player.users.InitialGameStatus;
import ui.player.users.WriteableUser;
import util.observerobservable.VoogaObservableMap;

/**
 * An Observable map of Game Status indicators
 * @author sarahzhou
 *
 */
public class GameStatus extends VoogaObservableMap<String,String> implements WriteableGameStatus{

	private final String EXPERIENCE = "Experience";
	private final String MONEY = "Money";
	private final String LEVEL = "Level";
	private final String LIVES = "Lives";
	
	private WriteableUser myWriteableUser;
	private InitialGameStatus myInitialGameStatus;
	
	public GameStatus(WriteableUser writeableUser,InitialGameStatus initialGameStatus) {
		myWriteableUser =writeableUser;
		myInitialGameStatus = initialGameStatus;
		setInitialValues();
	}
	
	private void setInitialValues() {
		setMyMoney(myInitialGameStatus.getInitMoney());
		setMyLevel(myInitialGameStatus.getInitLevel());
		setMyExperience(myInitialGameStatus.getInitExp());
	}

	public void addExperience(double exp){
		double newExp = Double.parseDouble(myMap.get(EXPERIENCE))+exp;
		setMyExperience(newExp);
	}

	public void addMoney(double mon){
		double newMon = Double.parseDouble(myMap.get(MONEY))+mon;
		setMyMoney(newMon);
	}

	public void spendMoney(double mon){
		double newMon = Double.parseDouble(myMap.get(MONEY))-mon;
		setMyMoney(newMon);
	}

	public void levelUp() {
		int newLevel = Integer.parseInt(myMap.get(LEVEL))+1;
		setMyLevel(newLevel);
	}

	public void setMyExperience(double myExperience) {
		myWriteableUser.setExperience(myExperience);
		myMap.put(EXPERIENCE, Double.toString(myExperience));
		notifyObservers();
	}

	public void setMyMoney(double myMoney) {
		myWriteableUser.setMoney(myMoney);
		myMap.put(MONEY, Double.toString(myMoney));
		notifyObservers();
	}

	public void setMyLevel(int myLevel) {
		myWriteableUser.setLevel(myLevel);
		myMap.put(LEVEL, Double.toString(myLevel));
		notifyObservers();
	}

	public void clear() {
		myMap = new HashMap<String,String>();
		notifyObservers();
	}

	@Override
	public void loseLife() {
		myMap.put(LIVES, Double.toString(Double.parseDouble(myMap.get(LIVES))-1));
		notifyObservers();
	}

	@Override
	public void gainLife() {
		myMap.put(LIVES, Double.toString(Double.parseDouble(myMap.get(LIVES))+1));
		notifyObservers();
	}
	
}
