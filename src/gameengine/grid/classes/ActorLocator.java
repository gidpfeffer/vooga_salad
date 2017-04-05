package gameengine.grid.classes;

import gameengine.actors.management.Actor;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.grid.interfaces.Identifiers.MovableActor;
import gameengine.grid.interfaces.Identifiers.SetableActor;

public class ActorLocator <T extends Actor<? extends ReadableGrid>> implements MovableActor<T>, SetableActor<T>{
	
	private Grid2D location;
	private T actor;

	public ActorLocator(Grid2D location, T actor){
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
	public T getActor() {
		return actor;
	}


	@Override
	public void UpgradeActor(T t) {
		this.actor = t;
	}

}
