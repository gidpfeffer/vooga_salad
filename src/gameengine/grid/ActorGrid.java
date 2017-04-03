package gameengine.grid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import gameengine.actors.AbstractActor;
import gameengine.actors.Base;
import gameengine.actors.Projectile;
import gameengine.actors.Shot;
import gameengine.actors.Troop;
import gameengine.actors.management.Actor;
import gameengine.actors.towers.ATower;
import gameengine.grid.interfaces.ActorGrid.ReadShootMoveGrid;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.grid.interfaces.Identifiers.MovableActor;

public class ActorGrid implements ReadShootMoveGrid{
	
	Map<Integer, MovableActor<Shot<ReadableGrid>>> projectileMap;
	Map<Integer, MovableActor<Troop<ReadableGrid>>> enemyMap;
	Map<Integer, MovableActor<Base<ReadableGrid>>> baseMap;
	Map<Integer, MovableActor<ATower<ReadableGrid>>> towerMap;
	List<Map<Integer, MovableActor<Actor<ReadableGrid>>>> actorList;
	
	public ActorGrid(){
		initializeInstances();
	}
	
	private void initializeInstances(){
		projectileMap = new HashMap<>();
		enemyMap = new HashMap<>();
		baseMap = new HashMap<>();
		towerMap = new HashMap<>();
		actorList = new ArrayList<>();
		actorList.add(enemyMap);
	}

	@Override
	public boolean addProjectile(Projectile projectile, double startX, double startY) {
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
	
	private Map<>

	
	private <T extends Actor<? extends ReadableGrid>> Collection<Grid2D> 
		getLocationsFromMap(Map<Integer, MovableActor<T>> map){
		
		return map.values().stream()
				.map(a -> a.getLocation())
				.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public Collection<Grid2D> getEnemyLocations() {
		return Collections.unmodifiableCollection(getLocationsFromMap(enemyMap));
	}

	@Override
	public Collection<Grid2D> getTowerLocations() {
		return Collections.unmodifiableCollection(getLocationsFromMap(towerMap));
	}

	@Override
	public Collection<Grid2D> getBaseLocations() {
		return Collections.unmodifiableCollection(getLocationsFromMap(baseMap));
	}

	@Override
	public Collection<Grid2D> getProjectileLocations() {
		return Collections.unmodifiableCollection(getLocationsFromMap(projectileMap));
	}

}
