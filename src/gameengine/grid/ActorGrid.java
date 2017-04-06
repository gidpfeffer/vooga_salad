package gameengine.grid;

import java.util.ArrayList;
import java.util.Collection;
import gameengine.actors.Base;
import gameengine.actors.Shot;
import gameengine.actors.Troop;
import gameengine.actors.management.Actor;
import gameengine.actors.towers.Tower;
import gameengine.grid.classes.Coordinates;
import gameengine.grid.interfaces.ActorGrid.MasterGrid;
import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;
import gameengine.grid.interfaces.ActorGrid.ReadAndShootGrid;
import gameengine.grid.interfaces.ActorGrid.ReadShootMoveGrid;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.grid.interfaces.controllergrid.ControllableGrid;
import gameengine.grid.interfaces.controllergrid.SteppableGrid;

public class ActorGrid implements ReadableGrid, MasterGrid,
	ReadAndMoveGrid, ReadAndShootGrid, ReadShootMoveGrid, ControllableGrid, SteppableGrid{
	
	private Coordinates limits;
	private Collection<Actor> actors;
	
	public ActorGrid(double maxX, double maxY){
		limits = new Coordinates(maxX, maxY);
		actors = new ArrayList<>();
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEnemy(Troop enemy, int ID, double startX, double startY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBase(Base base, int ID, double startX, double startY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTower(Tower tower, int ID, double startX, double startY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeActor(int ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void upgradeEnemy(Troop newEnemy, int ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void upgradeProjectile(Shot newShot, int ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void upgradeBase(Base newBase, int ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void upgradeTower(Tower newTower, int ID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProjectile(Shot shot, int ID, double startX, double startY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(int ID, double newX, double newY) {
		// TODO Auto-generated method stub
		
	}

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
