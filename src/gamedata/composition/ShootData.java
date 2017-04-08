package gamedata.composition;

public class ShootData implements Data {
	
	private double myRange;
	private int fireRate;
	
	public ShootData(double range, int rate) {
		myRange = range;
		fireRate = rate;
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
	
}
