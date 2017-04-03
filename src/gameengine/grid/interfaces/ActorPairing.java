package gameengine.grid.interfaces;

import gameengine.actors.management.Actor;

public interface ActorPairing <P> {

	Actor<? extends ReadableGrid> getActor();
	P getActorPartner();
	
	
}
