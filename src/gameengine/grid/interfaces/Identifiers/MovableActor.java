package gameengine.grid.interfaces.Identifiers;

import gameengine.actors.management.Actor;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public interface MovableActor <T extends Actor<? extends ReadableGrid>> extends FinadableActor<T>{
	
	void setLocation(double x, double y);

}
