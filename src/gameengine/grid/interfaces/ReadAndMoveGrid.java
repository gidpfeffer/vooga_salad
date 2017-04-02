package gameengine.grid.interfaces;

public interface ReadAndMoveGrid extends ReadableGrid{

	boolean move(double ID, double newX, double newY);
	
}
