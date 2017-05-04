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
	
	public boolean isValid(){
		return x <= 1.0 && x >= 0.0 && y<=1.0 & y>=0.0;	
	}
	
	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}
	@Override
	public String toString(){
		return(String.format("(%f,%f)", x,y));
	}
}
