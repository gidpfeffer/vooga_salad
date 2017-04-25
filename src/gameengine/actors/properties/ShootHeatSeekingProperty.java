package gameengine.actors.properties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import gamedata.composition.ShootHeatSeekingData;
import gameengine.actors.propertygen.IActProperty;
import gameengine.actors.propertygen.ShootTargetProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;

public class ShootHeatSeekingProperty<G extends ReadAndSpawnGrid> extends ShootTargetProperty<G> {

	public ShootHeatSeekingProperty(ShootHeatSeekingData myData) {
		super(myData);
	}
	
	@Override
	protected IActProperty<MasterGrid> projectileProperty(Double target, double range, double speed) {
		return new MoveWithHeatSeekProperty<MasterGrid>(range, speed, getMyTarget());
	}

	@Override
	protected Collection<Double> getEnemyToShoot(Collection<Grid2D> points, Grid2D myPos) {
		if(points.size()>0) {
			return new ArrayList<>(Arrays.asList(myPos.getX()));
		}
		return new ArrayList<>();
	}

}
