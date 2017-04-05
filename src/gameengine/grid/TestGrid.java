package gameengine.grid;

import java.util.Collection;

import gameengine.actors.Shot;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;

public class TestGrid implements ReadableGrid{

	@Override
	public Collection<Grid2D> getEnemiesInRadius(double x, double y, double radius) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Grid2D> getBasesInRadius(double x, double y, double radius) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Grid2D> getProjectilesInRadius(double x, double y, double radius) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Grid2D> getTowersInRadius(double x, double y, double radius) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Grid2D getLocationOf(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Grid2D> getEnemyLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Grid2D> getTowerLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Grid2D> getBaseLocations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Grid2D> getProjectileLocations() {
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

}