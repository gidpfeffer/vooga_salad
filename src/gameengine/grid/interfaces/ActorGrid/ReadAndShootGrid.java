package gameengine.grid.interfaces.ActorGrid;

import gameengine.actors.Shot;

public interface ReadAndShootGrid extends ReadableGrid{

	//Someone needs to make a projectile class
	void addProjectile(Shot<? extends ReadableGrid> shot, int ID, double startX, double startY);

}
