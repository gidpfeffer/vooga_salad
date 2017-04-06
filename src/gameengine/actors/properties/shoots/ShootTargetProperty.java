package gameengine.actors.properties.shoots;

import gameengine.actors.Projectile;
import gameengine.grid.interfaces.Identifiers.Grid2D;

public abstract class ShootTargetProperty extends ShootableProperty{
	@Override
	public void action(G grid, Integer actorID) {
		Grid2D dirCoordinates = getEnemyToShoot(grid.getEnemiesInRadius(grid.getLocationOf(actorID).getX(), grid.getLocationOf(actorID).getX(), myRange));
		grid.addProjectile(new Projectile(dirCoordinates.getX(), dirCoordinates.getY()), drid., startY);
	}
}
