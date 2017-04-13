package gameengine.grid.classes;

import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.grid.interfaces.frontendinfo.FrontEndInformation;

public class DisplayInfo implements FrontEndInformation{
	
	private Grid2D loc;
	private double healthRemaining;
	private int actorOption;
	
	public DisplayInfo(Grid2D loc, double healthRemaining, int actorOption){
		this.loc = loc;
		this.healthRemaining = healthRemaining;
		this.actorOption = actorOption;
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
