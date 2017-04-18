package gameengine.actors.properties;

import java.util.ArrayList;
import java.util.Collection;

import gamedata.composition.ShootMultiData;
import gamedata.compositiongen.ShootData;
import gameengine.actors.propertygen.ShootTargetProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;

public class ShootMultiProperty<G extends ReadAndSpawnGrid> extends ShootTargetProperty<G> {
	
	private Collection<Double> shotAngles;

	public ShootMultiProperty(ShootMultiData myData) {
		super(myData);
		shotAngles = new ArrayList<>();
		for(int shot = 0; shot<myData.getNumShots(); shot++) {
			shotAngles.add(shot*2*(Math.PI/myData.getNumShots()));
		}
	}

	@Override
	protected Collection<Double> getEnemyToShoot(Collection<Grid2D> points, Grid2D myPos) {
		ArrayList<Double> retCollection = new ArrayList<>();
		if(points.size()>0) {
			shotAngles.stream().forEach((angle) -> retCollection.add(angle));
		}
		return retCollection;
	}

}
