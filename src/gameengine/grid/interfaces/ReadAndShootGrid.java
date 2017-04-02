package gameengine.grid.interfaces;

import gameengine.actors.management.Actor;

public interface ReadAndShootGrid extends ReadableGrid{

	//Someone needs to make a projectile class
	boolean addProjectile(Actor<? extends ReadableGrid> projectile, double startX, double startY);

}
