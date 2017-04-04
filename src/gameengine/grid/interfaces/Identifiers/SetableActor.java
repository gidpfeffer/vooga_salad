package gameengine.grid.interfaces.Identifiers;

import gameengine.actors.management.Actor;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public interface SetableActor <T extends Actor<? extends ReadableGrid>> extends FinadableActor<T>{

	void UpgradeActor(T t);
	
}
