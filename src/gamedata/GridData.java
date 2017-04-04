package gamedata;

import java.util.HashMap;
import java.util.Map;

import gameengine.grid.classes.Coordinates;

public class GridData {
	Map<ActorData, Coordinates> actorMap;

	public GridData(){
		actorMap = new HashMap<ActorData, Coordinates>();
	}
	
	public void addActor(ActorData actor, Coordinates loc){
		actorMap.put(actor,loc);
	}
	
	public void removeActor(ActorData actor){
		actorMap.remove(actor);
	}
	
	public Map<ActorData, Coordinates> getActorMap() {
		return actorMap;
	}

	public void setActorMap(Map<ActorData, Coordinates> actorMap) {
		this.actorMap = actorMap;
	}
	
}
