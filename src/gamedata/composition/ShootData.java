package gamedata.composition;

public class ShootData implements Data {
	
	private double myRange;
	private int fireRate;
	
	public ShootData(double range, int rate) {
		setRange(range);
		setFireRate(rate);
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
	
}
