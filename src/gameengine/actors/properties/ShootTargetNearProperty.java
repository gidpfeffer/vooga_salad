package gameengine.actors.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import gamedata.composition.ShootData;
import gameengine.actors.propertygen.ShootTargetProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import util.PathUtil;

public class ShootTargetNearProperty<G extends ReadAndSpawnGrid> extends ShootTargetProperty<G> {

	public ShootTargetNearProperty(ShootData myData) {
		super(myData);
	}

	@Override
	protected Collection<Grid2D> getEnemyToShoot(Collection<Grid2D> points, Grid2D myPos) {
		ArrayList<Grid2D> retCollection = new ArrayList<>();
		retCollection.add(points.stream().min(Comparator.comparingDouble(point -> PathUtil.getDistance(myPos, point))).get());
		return retCollection;
	}

	@Override
	protected void spawnProjectiles(G grid, Collection<Grid2D> targets, double startX, double startY) {
		grid.actorSpawnActor(getMyProjectile(), grid.getLocationOf(), startY)
		//targets.stream().forEach(point -> grid.spawn(newActor, startX, startY););
	}

}
