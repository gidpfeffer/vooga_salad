package gameengine.actors.properties.shoots;

import java.util.Collection;

import gamedata.composition.ShootData;
import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndShootGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;

public abstract class ShootTargetProperty<G extends ReadAndShootGrid> implements IActProperty<G>{
	
	private double myRange;
	
	public ShootTargetProperty(ShootData myData) {
		myRange = myData.getMyRange();
	}
	
	@Override
	public void action(G grid, Integer actorID) {
		Collection<Grid2D> dirCoordinates = getEnemyToShoot(grid.getEnemiesInRadius(grid.getLocationOf(actorID).getX(), grid.getLocationOf(actorID).getX(), myRange), grid.getLocationOf(actorID));
		spawnProjectiles(dirCoordinates);
	}
	
	protected abstract Collection<Grid2D> getEnemyToShoot(Collection<Grid2D> points, Grid2D myID);
	
	protected abstract void spawnProjectiles(Collection<Grid2D> targets);
	
	protected double getAngle(Grid2D origin, Grid2D target) {
		return Math.toDegrees(Math.atan((target.getY()-origin.getY())/(target.getX()-origin.getX())));
	}
}
