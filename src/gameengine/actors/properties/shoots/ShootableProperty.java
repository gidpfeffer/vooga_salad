package gameengine.actors.properties.shoots;

import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndShootGrid;

public interface ShootableProperty <G extends ReadAndShootGrid> extends IActProperty<G>{
	
	public boolean shoot(G grid);
}
