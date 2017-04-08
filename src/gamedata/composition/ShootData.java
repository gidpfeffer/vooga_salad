package gamedata.composition;

public abstract class ShootData implements Data {
	
	//FIX DUPLICATION IN SUBCLASSES
	
	public ShootData(double range, int rate) {
		setRange(range);
		setFireRate(rate);
	}

	public abstract double getRange();

	public abstract void setRange(double range);

	public abstract int getFireRate();
	
	public abstract void setFireRate(int fireRate);
	
}
