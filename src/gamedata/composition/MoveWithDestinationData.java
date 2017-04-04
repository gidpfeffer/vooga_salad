package gamedata.composition;

import gameengine.grid.classes.Coordinates;

public class MoveWithDestinationData extends MoveData {
	private double mySpeed;
	private Coordinates finalLocation;
	
	public MoveWithDestinationData(double mySpeed, double finalX, 
			double finalY){
		this.mySpeed = mySpeed;
		finalLocation = new Coordinates(finalX, finalY);
	}
	
	public double getMySpeed() {
		return mySpeed;
	}

	public void setMySpeed(double mySpeed) {
		this.mySpeed = mySpeed;
	}

	public Coordinates getFinalLocation() {
		return finalLocation;
	}

	public void setFinalLocation(Coordinates finalLocation) {
		this.finalLocation = finalLocation;
	}
	
	
	
}
