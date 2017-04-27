package gameengine.actors.properties.shoot;

import java.util.Collection;

import gamedata.composition.ShootTargetWithMouseData;
import gamedata.compositiongen.ShootData;
import gameengine.actors.propertygen.ShootTargetProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;

public class ShootTargetWithMouseProperty<G extends ReadAndSpawnGrid> extends ShootTargetProperty<G>{

	public ShootTargetWithMouseProperty(ShootTargetWithMouseData myData) {
		super(myData);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Collection<Double> getEnemyToShoot(Collection<Grid2D> points, Grid2D myPos) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isOn() {
		return true;
	}

}
