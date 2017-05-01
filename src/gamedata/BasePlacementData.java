package gamedata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameengine.grid.classes.Coordinates;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import util.Tuple;

public class BasePlacementData {
	
	private List<Tuple<ActorData, Grid2D>> myActorLocationTuples;
	
	public BasePlacementData(){
		myActorLocationTuples = new ArrayList<>();
	}
	
	public BasePlacementData(List<Tuple<ActorData, Grid2D>> actorAndLocation){
		myActorLocationTuples = actorAndLocation;
	}

	public List<Tuple<ActorData, Grid2D>> getMyActorToLocation() {
		return myActorLocationTuples;
	}
	
	public void addBase(Tuple<ActorData, Grid2D> t){
		this.myActorLocationTuples.add(t);
	}
	
	public void removeBase(Tuple<ActorData, Grid2D> t){
		this.myActorLocationTuples.remove(t);
	}
	
	
}
