package gameengine.actors.propertygen;

import java.util.Collection;
import java.util.function.Consumer;

import gamedata.compositiongen.ShootData;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import types.BasicActorType;

public abstract class ShootTargetProperty<G extends ReadAndSpawnGrid> extends CycleProperty<G>{
	
	private double myRange;
	private BasicActorType myTarget;
	private Integer myProjectile;
	private double mySpeed;
	
	public ShootTargetProperty(ShootData myData) {
		super(myData.getFireRate());
		myRange = myData.getRange();
		myTarget = myData.getTarget();
		myProjectile = myData.getProjectile();
		mySpeed = myData.getSpeed();
	}
	
	@Override
	public void action(G grid, Integer actorID) {
		Collection<Double> dirCoordinates = getEnemyToShoot(grid.getActorLocationsInRadius(grid.getLocationOf(actorID).getX(), grid.getLocationOf(actorID).getY(), myRange, myTarget), grid.getLocationOf(actorID));
		spawnProjectiles(grid, dirCoordinates,grid.getLocationOf(actorID));
	}
	
	protected abstract Collection<Double> getEnemyToShoot(Collection<Grid2D> points, Grid2D myPos);
	
	protected void spawnProjectiles(G grid, Collection<Double> targets, Grid2D myLoc) {
		targets.stream().forEach(target -> {
			grid.actorSpawnActor(myProjectile, myLoc.getX(), myLoc.getY(), projectileProperty(target,myRange,mySpeed));
		});
	}
	
	protected abstract Consumer<Collection<IActProperty<MasterGrid>>> projectileProperty(Double target, double range, double speed);
	
	protected BasicActorType getMyTarget() {
		return myTarget;
	}
}
