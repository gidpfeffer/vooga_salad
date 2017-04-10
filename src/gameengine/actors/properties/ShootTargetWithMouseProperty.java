package gameengine.actors.properties;

import java.util.Collection;

import gamedata.compositiongen.ShootData;
import gameengine.actors.propertygen.ShootTargetProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;

public class ShootTargetWithMouseProperty<G extends ReadAndSpawnGrid> extends ShootTargetProperty<G>{

	public ShootTargetWithMouseProperty(ShootData myData) {
		super(myData);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Collection<Grid2D> getEnemyToShoot(Collection<Grid2D> points, Grid2D myPos) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isOn() {
		return true;
	}

}
