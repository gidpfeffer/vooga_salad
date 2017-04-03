package gameengine.grid.interfaces.Identifiers;

import gameengine.actors.management.Actor;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public interface FinadableActor <T extends Actor<ReadableGrid>>{

	T getActor();
	Grid2D getLocation();
	
	
}
