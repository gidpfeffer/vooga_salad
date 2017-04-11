package gameengine.grid.classes;

import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.grid.interfaces.frontendinfo.FrontEndInformation;

public class DisplayInfo implements FrontEndInformation{
	
	private Grid2D loc;
	private double healthRemaining;
	
	public DisplayInfo(Grid2D loc, double healthRemaining){
		this.loc = loc;
		this.healthRemaining = healthRemaining;
	}

	@Override
	public Grid2D getActorLocation() {
		return loc;
	}

	@Override
	public double getActorPercentHealth() {
		return healthRemaining;
	}

}
