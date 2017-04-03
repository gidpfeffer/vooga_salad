package gameengine.grid.interfaces;

import java.util.Map;

import gameengine.actors.management.Actor;
import gameengine.grid.ActorType;

public interface ActorMap <P>{

	Map<P, Actor<? extends ReadableGrid>> getMap();
	ActorType getType();
	
}
