package gameengine.actors.properties;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

import gamedata.composition.ShootTargetWithMouseData;
import gameengine.actors.propertygen.IActProperty;
import gameengine.actors.propertygen.ShootTargetProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;

public class ShootTargetWithMouseProperty<G extends ReadAndSpawnGrid> extends ShootTargetFarProperty<G>{
	
	public ShootTargetWithMouseProperty(ShootTargetWithMouseData myData) {
		super(myData);
	}

	@Override
	public void action(G grid, Integer actorID) {
		Collection<Double> dirCoordinates = getEnemyToShoot(Arrays.asList(grid.getEventQueue().getLocation()), grid.getLocationOf(actorID));
		spawnProjectiles(grid,dirCoordinates,grid.getLocationOf(actorID));
	}

}
