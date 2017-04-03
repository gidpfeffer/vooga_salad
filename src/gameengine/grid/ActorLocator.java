package gameengine.grid;

import gameengine.actors.management.Actor;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.grid.interfaces.Identifiers.MovableActor;

public class ActorLocator implements MovableActor{
	
	private Grid2D location;
	Actor<? extends ReadableGrid> actor;

	public ActorLocator(Grid2D location, Actor<? extends ReadableGrid> actor){
		this.actor = actor;
		this.location = location;
	}
	
	@Override
	public Actor<? extends ReadableGrid> getActor() {
		return actor;
	}

	@Override
	public Grid2D getLocation() {
		return location;
	}

	@Override
	public void setLocation(double x, double y) {
		location = new Dimensions(x, y);
	}

}
