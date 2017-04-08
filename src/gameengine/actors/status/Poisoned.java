package gameengine.actors.status;

import gameengine.grid.interfaces.ActorGrid.ReadAndDamageGrid;

/**
 * 
 * 
 * @author sarahzhou
 */
public class Poisoned<G extends ReadAndDamageGrid> extends IStatus<G>{

	
	
	@Override
	public void action(G grid, Integer actorID) {
		grid.getActorDamagablesInRadius(x, y, radius, grid.);
		
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
