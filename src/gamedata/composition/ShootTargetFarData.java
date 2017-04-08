package gamedata.composition;

public class ShootTargetFarData extends ShootData{

	private double myRange;
	private int fireRate;
	
	public ShootTargetFarData (double myRange, int fireRate){
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
