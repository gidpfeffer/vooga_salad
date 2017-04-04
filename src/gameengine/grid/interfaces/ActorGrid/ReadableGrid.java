package gameengine.grid.interfaces.ActorGrid;

import java.util.Collection;

import gameengine.grid.interfaces.Identifiers.Grid2D;

public interface ReadableGrid{
	
	Collection<Grid2D> getEnemiesInRadius(double x, double y, double radius);
	Collection<Grid2D> getBasesInRadius(double x, double y, double radius);
	Collection<Grid2D> getProjectilesInRadius(double x, double y, double radius);
	Collection<Grid2D> getTowersInRadius(double x, double y, double radius);
	Grid2D getLocationOf(int id);
	Collection<Grid2D> getEnemyLocations();
	Collection<Grid2D> getTowerLocations();
	Collection<Grid2D> getBaseLocations();
	Collection<Grid2D> getProjectileLocations();
	// need to add pathmap
	boolean isValidLoc(double x, double y);
	double getMaxX();
	double getMaxY();
}
