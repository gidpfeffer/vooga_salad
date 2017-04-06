package gameengine.actors.properties.shoots;

import java.util.Collection;

import gamedata.composition.ShootData;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;

public class ShootTargetNearProperty<G extends ReadAndSpawnGrid> extends ShootTargetProperty<G> {

	public ShootTargetNearProperty(ShootData myData) {
		super(myData);
	}
	
	@Override
	protected Collection<Grid2D> getEnemyToShoot(Collection<Grid2D> points, Grid2D myID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void spawnProjectiles(Collection<Grid2D> targets) {
		// TODO Auto-generated method stub
		
	}
	
	

}
