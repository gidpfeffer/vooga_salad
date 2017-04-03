package gameengine.grid;

import java.util.Map;

import gameengine.actors.management.Actor;
import gameengine.grid.interfaces.ActorMap;
import gameengine.grid.interfaces.ReadableGrid;

public class ActorMapIdentifier implements ActorMap<ActorIdentifier>{
	
	private ActorType type;
	private Map<ActorIdentifier, Actor<? extends ReadableGrid>> map;
	
	public ActorMapIdentifier(Map<ActorIdentifier, Actor<? extends ReadableGrid>> map, ActorType type){
		this.map = map;
		this.type = type;
	}

	@Override
	public Map<ActorIdentifier, Actor<? extends ReadableGrid>> getMap() {
		return this.map;
	}

	@Override
	public ActorType getType() {
		return this.type;
	}

}
