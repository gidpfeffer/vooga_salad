package gamedata.compositiongen;

import types.BasicActorType;

public class ShootData implements Data {

	private double myRange;
	private int fireRate;
	private BasicActorType myTarget;
	private Integer myProjectile;
	private double mySpeed;

	public ShootData(){
		this(0.0, 0, new BasicActorType("Troop"), 0, 0.0);
	}
	
	public ShootData(Double range, Integer rate, BasicActorType type, Integer projectile, Double speed) {
		myRange = range;
		fireRate = rate;
		myTarget = type;
		myProjectile = projectile;
		mySpeed = speed;
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
	
	public double getSpeed() {
		return mySpeed;
	}

}
