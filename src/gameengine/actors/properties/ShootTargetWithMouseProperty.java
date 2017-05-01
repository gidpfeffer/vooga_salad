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

	private String fireKey;
	
	public ShootTargetWithMouseProperty(ShootTargetWithMouseData myData) {
		super(myData);
		fireKey = myData.getButton();
	}

	@Override
	public void action(G grid, Integer actorID) {
		Collection<Double> dirCoordinates = getEnemyToShoot(grid.getEventQueue().queryKey(fireKey) ? Arrays.asList(grid.getEventQueue().getLocation()):Arrays.asList(new Grid2D[] {}), grid.getLocationOf(actorID));
		spawnProjectiles(grid,dirCoordinates,grid.getLocationOf(actorID));
	}
	
	@Override
	public boolean isOn() {
		return true;
	}

}
