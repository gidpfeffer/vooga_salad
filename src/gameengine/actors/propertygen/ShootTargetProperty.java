package gameengine.actors.propertygen;

import java.util.Collection;

import gamedata.compositiongen.ShootData;
import gameengine.actors.properties.MoveAlongAngleProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import types.BasicActorType;
import util.Delay;
import util.PathUtil;

public abstract class ShootTargetProperty<G extends ReadAndSpawnGrid> implements IActProperty<G>{
	
	private double myRange;
	private Delay myDelay;
	private BasicActorType myTarget;
	private Integer myProjectile;
	
	public ShootTargetProperty(ShootData myData) {
		myRange = myData.getRange();
		myDelay = new Delay(myData.getFireRate());
		myTarget = myData.getTarget();
		myProjectile = myData.getProjectile();
	}
	
	@Override
	public void action(G grid, Integer actorID) {
		Collection<Grid2D> dirCoordinates = getEnemyToShoot(grid.getActorLocationsInRadius(grid.getLocationOf(actorID).getX(), grid.getLocationOf(actorID).getY(), myRange, myTarget), grid.getLocationOf(actorID));
		spawnProjectiles(grid, dirCoordinates,grid.getLocationOf(actorID));
	}
	
	protected abstract Collection<Grid2D> getEnemyToShoot(Collection<Grid2D> points, Grid2D myPos);
	
	protected void spawnProjectiles(G grid, Collection<Grid2D> targets, Grid2D myLoc) {
		targets.stream().forEach(target -> {
			IActProperty<MasterGrid> newProperty = new MoveAlongAngleProperty<MasterGrid>(myRange, PathUtil.getAngle(myLoc, target));
			grid.actorSpawnActor(myProjectile, myLoc.getX(), myLoc.getY()).accept(newProperty);
		});
	}
	
	protected Integer getMyProjectile() {
		return myProjectile;
	}
	
	@Override
	public boolean isOn() {
		return myDelay.delayAction();
	}
}
