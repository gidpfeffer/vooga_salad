package gamedata.composition;

public class ShootData implements Data{
	
	private double mySpeed;
	private double myRange;
	private int fireRate;

	public double getRange() {
		return myRange;
	}

	public void setRange(double range) {
		this.myRange = range;
	}
	
	public double getSpeed() {
		return myRange;
	}

	public void setSpeed(double speed) {
		this.myRange = speed;
	}

	public int getFireRate() {
		return fireRate;
	}
	
}
