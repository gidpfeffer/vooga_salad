package gameengine.grid.classes;

public class Grid2D {
	
	private double myX; 
	private double myY; 
	
	public Grid2D(double x, double y){
		myX = x; 
		myY = y; 
	}
	
	public Grid2D (Grid2D other){
		myX = other.getX();
		myY = other.getY();
	}
	
	public double getX(){
		return myX; 
	}
	public double getY(){
		return myY; 
	}
	public String asString(){
		return myX + " , " + myY;
	}
}
