package gameengine.actors.properties.shoots;

import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndShootGrid;

public abstract class ShootableProperty <G extends ReadAndShootGrid> implements IActProperty<G>{
	
	public boolean shoot(G grid);
}
