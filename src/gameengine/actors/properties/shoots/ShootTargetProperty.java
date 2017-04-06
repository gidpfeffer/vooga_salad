package gameengine.actors.properties.shoots;

import java.util.Collection;

import gamedata.composition.ShootData;
import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import types.BasicActorType;
import util.Delay;

public abstract class ShootTargetProperty<G extends ReadAndSpawnGrid> implements IActProperty<G>{
	
	private final BasicActorType target = BasicActorType.Troop;
	
	private double myRange;
	private Delay myDelay;
	
	public ShootTargetProperty(ShootData myData) {
		myRange = myData.getMyRange();
		myDelay = new Delay(myData.getFireRate());
	}
	
	@Override
	public void action(G grid, Integer actorID) {
		Collection<Grid2D> dirCoordinates = getEnemyToShoot(grid.getActorLocationsInRadius(grid.getLocationOf(actorID).getX(), grid.getLocationOf(actorID).getX(), myRange, target), grid.getLocationOf(actorID));
		spawnProjectiles(dirCoordinates);
	}
	
	protected abstract Collection<Grid2D> getEnemyToShoot(Collection<Grid2D> points, Grid2D myID);
	
	protected abstract void spawnProjectiles(Collection<Grid2D> targets);
	
	protected double getAngle(Grid2D origin, Grid2D target) {
		return Math.toDegrees(Math.atan((target.getY()-origin.getY())/(target.getX()-origin.getX())));
	}
	
	public boolean isOn() {
		return myDelay.delayAction();
	}
}
