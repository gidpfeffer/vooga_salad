package gameengine.actors.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import gamedata.composition.ShootTargetFarData;
import gameengine.actors.propertygen.ShootTargetProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import util.PathUtil;

public class ShootTargetFarProperty<G extends ReadAndSpawnGrid> extends ShootTargetProperty<G> {

	public ShootTargetFarProperty(ShootTargetFarData myData) {
		super(myData);
	}

	@Override
	protected Collection<Grid2D> getEnemyToShoot(Collection<Grid2D> points, Grid2D myPos) {
		ArrayList<Grid2D> retCollection = new ArrayList<>();
		if(points.size()==0) return points;
		retCollection.add(points.stream().max(Comparator.comparingDouble(point -> PathUtil.getDistance(myPos, point))).get());
		return retCollection;
	}

}
