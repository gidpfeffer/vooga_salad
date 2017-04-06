package gameengine.grid.interfaces.ActorGrid;

import java.util.Collection;

import gameengine.grid.interfaces.Identifiers.Grid2D;
import types.BasicActorType;

public interface ReadableGrid{
	
	Collection<Grid2D> getActorLocationsInRadius(double x, double y, double radius, BasicActorType type);
	Grid2D getLocationOf(int id);
	Collection<Grid2D> getActorLocations(BasicActorType type);
	// need to add pathmap
	boolean isValidLoc(double x, double y);
	double getMaxX();
	double getMaxY();
}
