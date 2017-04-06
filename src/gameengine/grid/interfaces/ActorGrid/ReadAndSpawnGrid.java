package gameengine.grid.interfaces.ActorGrid;

import gameengine.actors.management.Actor;

public interface ReadAndSpawnGrid extends ReadableGrid{

	//Someone needs to make a projectile class
	void spawn(Actor newActor, double startX, double startY);

}
