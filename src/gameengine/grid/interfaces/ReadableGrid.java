package gameengine.grid.interfaces;

import java.util.List;
import java.util.Map;

import gameengine.actors.management.Actor;

public interface ReadableGrid{
	
	List<Actor<? extends ReadableGrid>> getInRadius(double x, double y, double radius);
	Grid2D getLocationOf(Actor<? extends ReadableGrid> t);
	Map<Actor<? extends ReadableGrid>, Grid2D> getEnemyMap();
	Map<Actor<? extends ReadableGrid>, Grid2D> getTowerMap();
	Map<Actor<? extends ReadableGrid>, Grid2D> getBaseMap();
	// need to add pathmap
	boolean isValidLoc(double x, double y);
	double getMaxX();
	double getMaxY();
}
