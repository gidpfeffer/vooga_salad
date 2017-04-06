package gameengine.grid.interfaces.ActorGrid;

public interface ReadAndMoveGrid extends ReadableGrid{

	void move(int ID, double newX, double newY);
	
}
