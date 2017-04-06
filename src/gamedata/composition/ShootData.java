package gamedata.composition;

public class ShootData implements Data{
	private double myRange;
	private int fireRate;

	public double getMyRange() {
		return myRange;
	}

	public void setMyRange(double myRange) {
		this.myRange = myRange;
	}

	public int getFireRate() {
		return fireRate;
	}
	
}
