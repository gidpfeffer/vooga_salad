package gameengine.grid.interfaces.controllergrid;

import gameengine.actors.management.Actor;
import gameengine.grid.interfaces.Identifiers.Grid2D;

public interface ControllableGrid extends SteppableGrid{
	
	void move(int ID, double newX, double newY);
	void removeActor(int ID);
	void controllerSpawnActor(Actor actor, int startX, int startY);
	Grid2D getLocationOf(int ID);
	boolean isValidLoc(double x, double y);
	
}
