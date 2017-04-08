package gameengine.grid.interfaces.ActorGrid;

import java.util.Collection;
import java.util.function.Consumer;

import types.BasicActorType;

public interface ReadAndDamageGrid extends ReadableGrid{
	
	Collection<Consumer<Double>>getActorDamagablesInRadius(double x, double y, 
			double radius, BasicActorType type);

}
