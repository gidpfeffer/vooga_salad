package gameengine.actors.properties;

import gameengine.grid.interfaces.ActorGrid.ReadAndShootGrid;

public interface Shootable <G extends ReadAndShootGrid>{
	
	public boolean shoot(G grid);
}
