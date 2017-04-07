package gameengine.actors.management;

import java.util.function.Consumer;

import gameengine.actors.properties.IActProperty;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import types.BasicActorType;

public interface Actor {

	public void act(MasterGrid grid);
		
	public boolean isActive();
	
	public Consumer<Double> applyDamage();

	public Consumer<IActProperty<MasterGrid>> addProperty();
	
	public Integer getID();
	
	public BasicActorType getType();
}
