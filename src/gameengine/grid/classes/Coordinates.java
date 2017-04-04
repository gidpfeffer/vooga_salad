package gameengine.grid.classes;

import gameengine.grid.interfaces.Identifiers.Grid2D;

public class Coordinates implements Grid2D{

	private double x;
	private double y;
	
	public Coordinates(double x, double y){
		this.x = x;
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

}
