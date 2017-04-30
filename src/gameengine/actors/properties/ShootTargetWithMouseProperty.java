package gameengine.actors.properties;

import java.util.Collection;
import java.util.function.Consumer;

import gamedata.composition.ShootTargetWithMouseData;
import gameengine.actors.propertygen.IActProperty;
import gameengine.actors.propertygen.ShootTargetProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;

public class ShootTargetWithMouseProperty<G extends ReadAndSpawnGrid> extends ShootTargetLineProperty<G>{

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

	@Override
	protected Consumer<Collection<IActProperty<MasterGrid>>> projectileProperty(Double target, double range,
			double speed) {
		// TODO Auto-generated method stub
		return null;
	}

}
