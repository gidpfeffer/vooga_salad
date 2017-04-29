package gameengine.grid.classes;

import gameengine.actors.management.Actor;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.grid.interfaces.frontendinfo.FrontEndInformation;

public class DisplayInfo implements FrontEndInformation{
	
	private Grid2D loc;
	private double healthRemaining;
	private int actorOption;
	
	public DisplayInfo(Grid2D loc, Actor actor){
		this.loc = loc;
		this.healthRemaining = actor.getPercentHealth();
		this.actorOption = actor.getMyOption();
	}

	@Override
	public Grid2D getActorLocation() {
		return loc;
	}

	@Override
	public double getActorPercentHealth() {
		return healthRemaining;
	}

	@Override
	public int getActorOption() {
		return actorOption;
	}

}
