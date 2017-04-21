package gamedata.compositioncon;

import gamedata.compositiongen.StatusData;
import gameengine.actors.status.StatusDuration;
import types.BasicActorType;

/**
 * @author sarahzhou
 *
 */
public class PoisonedStatusData extends StatusData{
	
	double myDamage;
	int myTimeBetweenDamage;

	public PoisonedStatusData(BasicActorType type, StatusDuration statusDuration, double damage, int timeBetweenDamage) {
		super(type, statusDuration);
		myDamage = damage;
		myTimeBetweenDamage = timeBetweenDamage;
	}
	
	public double getMyDamage() {
		return myDamage;
	}

	public void setMyDamage(double myDamage) {
		this.myDamage = myDamage;
	}
	
	public int getMyTimeBetweenDamage() {
		return myTimeBetweenDamage;
	}

	public void setMyTimeBetweenDamage(int myTimeBetweenDamage) {
		this.myTimeBetweenDamage = myTimeBetweenDamage;
	}
}
