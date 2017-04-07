package gameengine.actors.properties;

import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;

public interface ShootableProperty <G extends ReadAndSpawnGrid>{
	
	public boolean shoot(G grid);
}
