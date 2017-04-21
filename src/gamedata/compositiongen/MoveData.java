package gamedata.compositiongen;

public abstract class MoveData implements Data {
	private double mySpeed;
	
	public MoveData(double mySpeed) {
		super();
		this.mySpeed = mySpeed;
	}
	
	public double getMySpeed() {
		return mySpeed;
	}

	public void setMySpeed(double mySpeed) {
		this.mySpeed = mySpeed;
	}
	
	
}
