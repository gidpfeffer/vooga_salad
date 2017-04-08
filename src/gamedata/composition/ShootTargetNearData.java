package gamedata.composition;

public class ShootTargetNearData extends ShootData{

	private double myRange;
	private int fireRate;
	
	public ShootTargetNearData(double myRange, int fireRate){
		super(myRange, fireRate);
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
