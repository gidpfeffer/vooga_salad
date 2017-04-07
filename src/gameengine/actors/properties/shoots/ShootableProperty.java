package gameengine.actors.properties.shoots;

import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;

public abstract class ShootableProperty <G extends ReadAndSpawnGrid> implements IActProperty<G>{
	
	public boolean shoot(G grid);
}
