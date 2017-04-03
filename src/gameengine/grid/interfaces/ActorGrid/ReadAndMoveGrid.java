package gameengine.grid.interfaces.ActorGrid;

public interface ReadAndMoveGrid extends ReadableGrid{

	void move(double ID, double newX, double newY);
	
}
