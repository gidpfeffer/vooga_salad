package gamedata.composition;

import types.BasicActorType;

public class ShootData implements Data{
	
	private double myRange;
	private int fireRate;
	private BasicActorType myTarget;
	
	public ShootData(double range, int rate, BasicActorType type) {
		myRange = range;
		fireRate = rate;
		myTarget = type;
	}

	public double getRange() {
		return myRange;
	}

	public void setRange(double range) {
		this.myRange = range;
	}

	public int getFireRate() {
		return fireRate;
	}

	public BasicActorType getTarget() {
		return myTarget;
	}
	
}
