package gameengine.actors.properties;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

import gamedata.compositiongen.ShootTargetLineData;
import gameengine.actors.propertygen.IActProperty;
import gameengine.actors.propertygen.ShootTargetProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;

public abstract class ShootTargetLineProperty<G extends ReadAndSpawnGrid> extends ShootTargetProperty<G>  {

	public ShootTargetLineProperty(ShootTargetLineData myData) {
		super(myData);
	}
	
	@Override
	protected Consumer<Collection<IActProperty<MasterGrid>>> projectileProperty(Double target, double range, double speed) {
		return (list) -> Arrays.asList(new MoveAlongAngleProperty<MasterGrid>(target, range, speed));
	}

}
