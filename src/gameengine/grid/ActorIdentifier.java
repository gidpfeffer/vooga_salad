package gameengine.grid;

import gameengine.actors.management.Actor;
import gameengine.grid.interfaces.ActorPairing;
import gameengine.grid.interfaces.ReadableGrid;

public class ActorIdentifier implements ActorPairing<Integer>{
	
	private final Integer id;
	private Actor<? extends ReadableGrid> actor;
	
	
	public ActorIdentifier(Actor<? extends ReadableGrid> actor, Integer id){
		this.id = id;
		this.actor = actor;
	}

	@Override
	public Actor<? extends ReadableGrid> getActor() {
		return this.actor;
	}

	@Override
	public Integer getActorPartner() {
		return this.id;
	}

}
