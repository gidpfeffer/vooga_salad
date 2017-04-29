package gameengine.actors.propertygen;

import java.util.Collection;
import java.util.function.Consumer;

import gamedata.compositiongen.ShootData;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import types.BasicActorType;
import util.Delay;

public abstract class ShootTargetProperty<G extends ReadAndSpawnGrid> implements IActProperty<G>{
	
	private double myRange;
	private Delay myDelay;
	private BasicActorType myTarget;
	private Integer myProjectile;
	private double mySpeed;
	
	public ShootTargetProperty(ShootData myData) {
		myRange = myData.getRange();
		myDelay = new Delay(myData.getFireRate());
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
			//IActProperty<MasterGrid> newProperty = new MoveAlongAngleProperty<MasterGrid>(target, myRange, mySpeed);
			//IActProperty<MasterGrid> newProperty = new MoveWithHeatSeekProperty<MasterGrid>(myRange, mySpeed, myTarget);
			grid.actorSpawnActor(myProjectile, myLoc.getX(), myLoc.getY(), projectileProperty(target,myRange,mySpeed));
		});
	}
	
	protected abstract Consumer<Collection<IActProperty<MasterGrid>>> projectileProperty(Double target, double range, double speed);
	
	protected BasicActorType getMyTarget() {
		return myTarget;
	}
	
	@Override
	public boolean isOn() {
		return myDelay.delayAction();
	}
}
