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
	private double myStartX;
	private double myStartY;
	private double myFinalX;
	private double myFinalY;
	
	public MoveWithDestinationData(double mySpeed, double startX,
			double startY, double finalX, 
			double finalY){
		this.mySpeed = mySpeed;
		myStartX = startX;
		myStartY = startY;
		myFinalX = finalX;
		myFinalY = finalY;
	}
	
	public List<Grid2D> getStraightPath(){
		return PathUtil.getIncrementPoints(new Coordinates(myStartX, myStartY), 
				new Coordinates(myFinalX, myFinalY), 
				mySpeed);
	}
	
	public double getMySpeed() {
		return mySpeed;
	}

	public void setMySpeed(double mySpeed) {
		this.mySpeed = mySpeed;
	}

	public Coordinates getInitialLocation() {
		return new Coordinates(myStartX, myStartY);
	}

	public void setInitialLocation(Coordinates initialLocation) {
		myStartX = initialLocation.getX();
		myStartY = initialLocation.getY();
	}

	public Coordinates getFinalLocation() {
		return new Coordinates(myFinalX, myFinalY);
	}

	public void setFinalLocation(Coordinates finalLocation) {
		myFinalX = finalLocation.getX();
		myFinalY = finalLocation.getY();
	}
	
	
	
}
