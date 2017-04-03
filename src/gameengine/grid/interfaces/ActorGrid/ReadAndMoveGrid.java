package gameengine.grid.interfaces.ActorGrid;

public interface ReadAndMoveGrid extends ReadableGrid{

	boolean move(double ID, double newX, double newY);
	
}
