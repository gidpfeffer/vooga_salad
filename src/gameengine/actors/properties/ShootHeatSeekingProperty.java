package gameengine.actors.properties;

import java.util.ArrayList;
import java.util.Collection;

import gamedata.composition.ShootTargetFarData;
import gamedata.compositiongen.ShootData;
import gameengine.actors.propertygen.IActProperty;
import gameengine.actors.propertygen.ShootTargetProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import util.PathUtil;

public class ShootHeatSeekingProperty<G extends ReadAndSpawnGrid> extends ShootTargetFarProperty<G> {

	public ShootHeatSeekingProperty(ShootTargetFarData myData) {
		super(myData);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void spawnProjectiles(G grid, Collection<Double> targets, Grid2D myLoc) {
		if(targets.size()>0) {
			IActProperty<MasterGrid> newProp = new MoveWithHeatSeek()
			grid.actorSpawnActor(getMyProjectile(), myLoc.getX(), myLoc.getY()).accept(null);
		}
	}

}
