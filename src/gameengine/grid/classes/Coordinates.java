package gameengine.grid.classes;

import gameengine.grid.interfaces.Identifiers.Grid2D;

/**
 * 
 * @author Gideon
 *
 * This class is a concrete implementation of Grid2D.
 * It is a very basic class. 
 * It holds an x and y location with no setters only getters.
 * If a new coordinate location needs to be made, a new instance also has to be made
 */
public class Coordinates implements Grid2D {

	private double x;
	private double y;
	
	public Coordinates(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public Coordinates(Grid2D other){
		this.x = other.getX();
		this.y = other.getY();
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

}
