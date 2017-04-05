package gameengine.actors.management;

import gameengine.grid.interfaces.ActorGrid.MasterGrid;

public interface Actor {

	public void act(MasterGrid grid);
		
	public boolean isActive();
	
	public void applyDamage(double health);

}
