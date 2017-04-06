package gameengine.actors.management;

import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import types.BasicActorType;

public interface Actor {

	public void act(MasterGrid grid);
		
	public boolean isActive();
	
	public void applyDamage(double health);

	public void addProperty(IActProperty<MasterGrid> newProperty);
	
	public Integer getID();
	
	public BasicActorType getType();
}
