package gameengine.grid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import gameengine.actors.Base;
import gameengine.actors.Shot;
import gameengine.actors.Troop;
import gameengine.actors.management.Actor;
import gameengine.actors.towers.Tower;
import gameengine.grid.classes.ActorLocator;
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
	
	private Map<Integer, ActorLocator<Shot<? extends ReadableGrid>>> projectileMap;
	private Map<Integer, ActorLocator<Troop<? extends ReadableGrid>>> enemyMap;
	private Map<Integer, ActorLocator<Base<? extends ReadableGrid>>> baseMap;
	private Map<Integer, ActorLocator<Tower<? extends ReadableGrid>>> towerMap;
	private List<Map<Integer, ? extends ActorLocator<? extends Actor<? extends ReadableGrid>>>> actorList;
	private Coordinates limits;
	
	public ActorGrid(double maxX, double maxY){
		limits = new Coordinates(maxX, maxY);
		initializeInstances();
	}
	
	private void initializeInstances(){
		projectileMap = new HashMap<>();
		enemyMap = new HashMap<>();
		baseMap = new HashMap<>();
		towerMap = new HashMap<>();
		initializeActorList();
	}
	
	private void initializeActorList(){
		actorList = new ArrayList<>();
		actorList.add(projectileMap);
		actorList.add(enemyMap);
		actorList.add(baseMap);
		actorList.add(towerMap);
	}

	@Override
	public void step() {
		for(Map<Integer, ? extends ActorLocator<? extends Actor<? extends ReadableGrid>>> map: actorList){
			map = map.entrySet().stream()
					.filter(e -> e.getValue().getActor().isActive())
					.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
			
			map.values().forEach(al -> al.getActor().act(this));
		}
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
		return findMapThatContainsID(actorList, id).get(id).getLocation();
	}

	@Override
	public Collection<Grid2D> getEnemyLocations() {
		return Collections.unmodifiableCollection(getActorLocatorMap(enemyMap).values());
	}

	@Override
	public Collection<Grid2D> getTowerLocations() {
		return Collections.unmodifiableCollection(getActorLocatorMap(towerMap).values());
	}

	@Override
	public Collection<Grid2D> getBaseLocations() {
		return Collections.unmodifiableCollection(getActorLocatorMap(baseMap).values());
	}

	@Override
	public Collection<Grid2D> getProjectileLocations() {
		return Collections.unmodifiableCollection(getActorLocatorMap(projectileMap).values());
	}

	@Override
	public boolean isValidLoc(double x, double y) {
		return x >= 0 && y >= 0 && x <= getMaxX() && y <= getMaxY();
	}

	@Override
	public double getMaxX() {
		return limits.getX();
	}

	@Override
	public double getMaxY() {
		return limits.getY();
	}
	
	private <T extends Actor<? extends ReadableGrid>> Map<T, Grid2D> getActorLocatorMap(
			Map<Integer, ActorLocator<T>> map){
		
		return map.values().stream()
				.collect(Collectors.toMap(ActorLocator<T>::getActor, ActorLocator<T>::getLocation));	
	}

	private Map<Integer, ? extends ActorLocator<? extends Actor<? extends ReadableGrid>>> findMapThatContainsID(
			Collection<Map<Integer, ? extends ActorLocator<? extends Actor<? extends ReadableGrid>>>> actorCollection, int ID){
		
		Collection<Map<Integer,? extends ActorLocator<? extends Actor<? extends ReadableGrid>>>> mapCol = 
				filter(actorCollection, a -> a.keySet().contains(ID));
		if(mapCol.size() != 1){
			throw new IllegalStateException("Illegal ID, see ActorGrid ~215");
		}
		
		return mapCol.iterator().next();
	}
	
	private <T> Collection<T> filter(Collection<T> collection, Predicate<T> predicate){
		return collection.stream()
				.filter(t -> predicate.test(t))
				.collect(Collectors.toList());
	}

	
}
