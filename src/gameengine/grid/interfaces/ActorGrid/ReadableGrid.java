package gameengine.grid.interfaces.ActorGrid;

import java.util.Collection;
import java.util.List;

import gameengine.actors.management.Actor;
import gameengine.grid.interfaces.Identifiers.Grid2D;

public interface ReadableGrid{
	
	List<Actor<? extends ReadableGrid>> getInRadius(double x, double y, double radius);
	Grid2D getLocationOf(Actor<? extends ReadableGrid> t);
	Collection<Grid2D> getEnemyLocations();
	Collection<Grid2D> getTowerLocations();
	Collection<Grid2D> getBaseLocations();
	Collection<Grid2D> getProjectileLocations();
	// need to add pathmap
	boolean isValidLoc(double x, double y);
	double getMaxX();
	double getMaxY();
}
