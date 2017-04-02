package gameengine.grid;

import java.util.List;
import java.util.Map;

import gameengine.actors.management.Actor;
import gameengine.grid.interfaces.Grid2D;
import gameengine.grid.interfaces.ReadShootMoveGrid;
import gameengine.grid.interfaces.ReadableGrid;

public class ActorGrid implements ReadShootMoveGrid{
	
	public ActorGrid(){
		
	}
	

	@Override
	public boolean addProjectile(Actor<? extends ReadableGrid> projectile, double startX, double startY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Actor<? extends ReadableGrid>> getInRadius(double x, double y, double radius) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Grid2D getLocationOf(Actor<? extends ReadableGrid> t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Actor<? extends ReadableGrid>, Grid2D> getEnemyMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Actor<? extends ReadableGrid>, Grid2D> getTowerMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Actor<? extends ReadableGrid>, Grid2D> getBaseMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValidLoc(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getMaxX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMaxY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean move(double ID, double newX, double newY) {
		// TODO Auto-generated method stub
		return false;
	}

}
