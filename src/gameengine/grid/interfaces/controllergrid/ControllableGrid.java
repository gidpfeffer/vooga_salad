package gameengine.grid.interfaces.controllergrid;

import gameengine.actors.management.Actor;

public interface ControllableGrid extends SteppableGrid{
	
	void move(int ID, double newX, double newY);
	void removeActor(int ID);
	void controllerSpawnActor(Actor actor, int startX, int startY);
	
}
