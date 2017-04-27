package gameengine.actors.properties.shoot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import gamedata.composition.ShootTargetFarData;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import util.PathUtil;

public class ShootTargetFarProperty<G extends ReadAndSpawnGrid> extends ShootTargetLineProperty<G> {

	public ShootTargetFarProperty(ShootTargetFarData myData) {
		super(myData);
	}

	@Override
	protected Collection<Double> getEnemyToShoot(Collection<Grid2D> points, Grid2D myPos) {
		ArrayList<Double> retCollection = new ArrayList<>();
		points.stream().max(Comparator.comparingDouble(point -> PathUtil.getDistance(myPos, point))).ifPresent((point) -> retCollection.add(PathUtil.getAngle(myPos, point)));
		return retCollection;
	}

}
