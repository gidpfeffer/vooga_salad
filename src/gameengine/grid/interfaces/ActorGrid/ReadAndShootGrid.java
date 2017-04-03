package gameengine.grid.interfaces.ActorGrid;

import gameengine.actors.Projectile;

public interface ReadAndShootGrid extends ReadableGrid{

	//Someone needs to make a projectile class
	boolean addProjectile(Projectile projectile, double startX, double startY);

}
