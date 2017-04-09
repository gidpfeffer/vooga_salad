package gameengine.grid.classes;

import gameengine.actors.management.Actor;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.grid.interfaces.Identifiers.LocationalActor;

public class ActorLocator implements LocationalActor{
	
	private Grid2D location;
	private Actor actor;

	public ActorLocator(Grid2D location, Actor actor){
		this.actor = actor;
		this.location = location;
	}
	
	@Override
	public Grid2D getLocation() {
		return location;
	}

	@Override
	public void setLocation(double x, double y) {
		location = new Coordinates(x, y);
	}

	@Override
	public Actor getActor() {
		return actor;
	}

}
