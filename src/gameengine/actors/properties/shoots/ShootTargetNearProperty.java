package gameengine.actors.properties.shoots;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import gamedata.composition.ShootData;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;

public class ShootTargetNearProperty<G extends ReadAndSpawnGrid> extends ShootTargetProperty<G> {

	public ShootTargetNearProperty(ShootData myData) {
		super(myData);
	}
	
	@Override
	protected Collection<Grid2D> getEnemyToShoot(Collection<Grid2D> points, Grid2D myLocation) {
		// WHY DOES THIS RETURN A COLLECTION OF POINTS?
		double shortestDistance = Double.POSITIVE_INFINITY;
		Grid2D closestTarget = null;
		for (Grid2D loc : points) {
			double distanceToActor = Math.sqrt(Math.pow(loc.getX() - myLocation.getX(), 2) 
					+ Math.pow(loc.getY() - myLocation.getY(), 2));
			if (distanceToActor < shortestDistance) {
				shortestDistance = distanceToActor;
				closestTarget = loc;
			}
		}
		return new ArrayList<>(Arrays.asList(closestTarget));
	}

	@Override
	protected void spawnProjectiles(Collection<Grid2D> targets) {
		// TODO Auto-generated method stub
		
	}
	
	

}
