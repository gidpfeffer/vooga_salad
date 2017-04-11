package gamedata.compositiongen;

import types.BasicActorType;

public class ShootData implements Data {

	private double myRange;
	private int fireRate;
	private BasicActorType myTarget;
	private Integer myProjectile;

	public ShootData(double range, int rate, BasicActorType type, Integer projectile) {
		myRange = range;
		fireRate = rate;
		myTarget = type;
		myProjectile = projectile;
	}

	public double getRange() {
		return myRange;
	}

	public void setRange(double myRange) {
		this.myRange = myRange;
	}

	public int getFireRate() {
		return fireRate;
	}

	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}

	public BasicActorType getTarget() {
		return myTarget;
	}
	
	public Integer getProjectile() {
		return myProjectile;
	}

}
