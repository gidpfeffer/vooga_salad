package gamedata.composition;

import java.util.List;

import gameengine.grid.classes.Coordinates;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import util.PathUtil;

/**
 * Data class for MoveWithDestination Property, which
 * is used in Projectiles to define a limited type of movement
 * 
 * @author maddiebriere
 *
 */

public class MoveWithDestinationData implements Data{
	private double mySpeed;
	private Coordinates initialLocation;
	private Coordinates finalLocation;
	
	public MoveWithDestinationData(double mySpeed, double startX,
			double startY, double finalX, 
			double finalY){
		this.mySpeed = mySpeed;
		initialLocation = new Coordinates(startX, startY);
		finalLocation = new Coordinates(finalX, finalY);
	}
	
	public List<Grid2D> getStraightPath(){
		return PathUtil.getIncrementPoints(initialLocation, finalLocation, 
				mySpeed);
	}
	
	public double getMySpeed() {
		return mySpeed;
	}

	public void setMySpeed(double mySpeed) {
		this.mySpeed = mySpeed;
	}

	public Coordinates getInitialLocation() {
		return initialLocation;
	}

	public void setInitialLocation(Coordinates initialLocation) {
		this.initialLocation = initialLocation;
	}

	public Coordinates getFinalLocation() {
		return finalLocation;
	}

	public void setFinalLocation(Coordinates finalLocation) {
		this.finalLocation = finalLocation;
	}
	
	
	
}
