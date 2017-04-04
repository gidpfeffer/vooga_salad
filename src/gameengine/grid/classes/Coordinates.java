package gameengine.grid.classes;

import gameengine.grid.interfaces.Identifiers.Grid2D;

public class Coordinates implements Grid2D{

	private double x;
	private double y;
	
	public Coordinates(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public Coordinates(Coordinates other){
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
	
	public String asString(){
		return x + " , " + y; 
	}

}
