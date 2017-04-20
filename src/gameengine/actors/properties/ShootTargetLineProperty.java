package gameengine.actors.properties;

import gamedata.compositiongen.ShootData;
import gameengine.actors.propertygen.IActProperty;
import gameengine.actors.propertygen.ShootTargetProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;

public abstract class ShootTargetLineProperty<G extends ReadAndSpawnGrid> extends ShootTargetProperty<G>  {

	public ShootTargetLineProperty(ShootTargetLineData myData) {
		super(myData);
	}
	
	@Override
	protected IActProperty<MasterGrid> projectileProperty(Double target, double range, double speed) {
		return new MoveAlongAngleProperty<MasterGrid>(target, range, speed);
	}

}
