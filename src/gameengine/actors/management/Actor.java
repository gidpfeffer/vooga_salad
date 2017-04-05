package gameengine.actors.management;

import gameengine.grid.interfaces.ActorGrid.ReadableGrid;

public interface Actor <G extends ReadableGrid>{

	public void act(G grid);
		
	public boolean isActive();
	
	public void applyDamage(double health);

}
