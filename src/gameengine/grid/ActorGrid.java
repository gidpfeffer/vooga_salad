package gameengine.grid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import gameengine.actors.Base;
import gameengine.actors.Shot;
import gameengine.actors.Troop;
import gameengine.actors.management.Actor;
import gameengine.actors.towers.ATower;
import gameengine.grid.classes.ActorLocator;
import gameengine.grid.classes.Coordinates;
import gameengine.grid.interfaces.ActorGrid.ReadAndMoveGrid;
import gameengine.grid.interfaces.ActorGrid.ReadAndShootGrid;
import gameengine.grid.interfaces.ActorGrid.ReadShootMoveGrid;
import gameengine.grid.interfaces.ActorGrid.ReadableGrid;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import gameengine.grid.interfaces.controllergrid.ControllableGrid;
import util.PathUtil;

public class ActorGrid implements ReadableGrid, ReadAndMoveGrid, ReadAndShootGrid, ReadShootMoveGrid, ControllableGrid{
	
	private Map<Integer, ActorLocator<Shot<? extends ReadableGrid>>> projectileMap;
	private Map<Integer, ActorLocator<Troop<? extends ReadableGrid>>> enemyMap;
	private Map<Integer, ActorLocator<Base<? extends ReadableGrid>>> baseMap;
	private Map<Integer, ActorLocator<ATower<? extends ReadableGrid>>> towerMap;
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
	public Grid2D getLocationOf(int ID) {
		Map<Integer, ? extends ActorLocator<? extends Actor<? extends ReadableGrid>>> map = findRelevantMap(ID);
		return map.get(ID).getLocation();
	}
	
	private Map<Integer, ? extends ActorLocator<? extends Actor<? extends ReadableGrid>>> findRelevantMap(int ID){
		
		for(Map<Integer, ? extends ActorLocator<? extends Actor<? extends ReadableGrid>>> map : actorList){
			if(map.keySet().contains(ID)){
				return map;
			}
		}
		throw new IllegalArgumentException("Invalid id was called, see ActorGrid 84");
	}

	@Override
	public boolean isValidLoc(double x, double y) {
		if(x < 0 || y < 0){
			return false;
		}
		return x <= limits.getX() && y < limits.getY();
	}

	@Override
	public double getMaxX() {
		return limits.getX();
	}

	@Override
	public double getMaxY() {
		return limits.getY();
	}

	@Override
	public void move(int ID, double newX, double newY) {
		Map<Integer, ? extends ActorLocator<? extends Actor<? extends ReadableGrid>>> map = findRelevantMap(ID);
		map.get(ID).setLocation(newX, newY);
	}

	private Collection<Grid2D> getLocationsFromMap(Map<Integer, 
			? extends ActorLocator<? extends Actor<? extends ReadableGrid>>> map){
		
		List<Grid2D> locations = map.values().stream()
				.map(a -> a.getLocation())
				.collect(Collectors.toList());
		return Collections.unmodifiableCollection(locations);
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

	@Override
	public void addEnemy(Troop<? extends ReadableGrid> enemy, int ID, double startX, double startY) {
		Coordinates location = new Coordinates(startX, startY);
		ActorLocator<Troop<? extends ReadableGrid>> troop = new ActorLocator<>(location, enemy);
		enemyMap.put(ID, troop);
	}

	@Override
	public void addProjectile(Shot<? extends ReadableGrid> shot, int ID, double startX, double startY) {
		addActorToMap(shot, ID, startX, startY, projectileMap);
	}

	@Override
	public void addBase(Base<? extends ReadableGrid> base, int ID, double startX, double startY) {
		addActorToMap(base, ID, startX, startY, baseMap);
	}

	@Override
	public void addTower(ATower<? extends ReadableGrid> tower, int ID, double startX, double startY) {
		addActorToMap(tower, ID, startX, startY, towerMap);
	}
	
	private <T extends Actor<? extends ReadableGrid>> void addActorToMap(T actor, 
			int ID, double startX, double startY, Map<Integer, ActorLocator<T>> map){
		
		Coordinates location = new Coordinates(startX, startY);
		ActorLocator<T> actorLocator = new ActorLocator<>(location, actor);
		map.put(ID, actorLocator);
	}

	@Override
	public void removeActor(int ID) {
		Map<Integer, ? extends ActorLocator<? extends Actor<? extends ReadableGrid>>> map = findRelevantMap(ID);
		map.remove(ID);
	}

	@Override
	public void upgradeEnemy(Troop<? extends ReadableGrid> newEnemy, int ID) {
		assertIDExists(enemyMap, ID);
		upgradeActor(enemyMap.get(ID).getActor(), newEnemy, enemyMap);
	}

	@Override
	public void upgradeProjectile(Shot<? extends ReadableGrid> newShot, int ID) {
		assertIDExists(projectileMap, ID);
		upgradeActor(projectileMap.get(ID).getActor(), newShot, projectileMap);
	}

	@Override
	public void upgradeBase(Base<? extends ReadableGrid> newBase, int ID) {
		assertIDExists(baseMap, ID);
		upgradeActor(baseMap.get(ID).getActor(), newBase, baseMap);
	}

	@Override
	public void upgradeTower(ATower<? extends ReadableGrid> newTower, int ID) {
		assertIDExists(towerMap, ID);
		upgradeActor(towerMap.get(ID).getActor(), newTower, towerMap);
	}
	
	private <T extends Actor<? extends ReadableGrid>> void assertIDExists(Map<Integer, ActorLocator<T>> map, int ID){
		if(!map.containsKey(ID)){
			throw new IllegalArgumentException("Attempted to upgrade an Actor that doesn't exist. "
				+ "See ActorGrid line ~ 210");
		}
	}
	
	private <T extends Actor<? extends ReadableGrid>>
		void upgradeActor(T oldActor, T newActor, Map<Integer, ActorLocator<T>> map){
		for(Entry<Integer, ActorLocator<T>> entry : map.entrySet()){
			if(entry.getValue().getActor().equals(oldActor)){
				entry.getValue().UpgradeActor(oldActor);
			}
		}
	}

	@Override
	public Collection<Grid2D> getBasesInRadius(double x, double y, double radius) {
		return Collections.unmodifiableCollection(
				filterLocations(getLocationsFromMap(baseMap), x, y, radius));
	}

	@Override
	public Collection<Grid2D> getProjectilesInRadius(double x, double y, double radius) {
		return Collections.unmodifiableCollection(
				filterLocations(getLocationsFromMap(projectileMap), x, y, radius));
	}

	@Override
	public Collection<Grid2D> getTowersInRadius(double x, double y, double radius) {
		return Collections.unmodifiableCollection(
				filterLocations(getLocationsFromMap(towerMap), x, y, radius));
	}
	
	@Override
	public Collection<Grid2D> getEnemiesInRadius(double x, double y, double radius) {
		return Collections.unmodifiableCollection(
				filterLocations(getLocationsFromMap(enemyMap), x, y, radius));
	}
	
	private Collection<Grid2D> filterLocations(Collection<Grid2D> allLocations,
			double x, double y, double radius){
		return filter(allLocations, 
				g -> PathUtil.getDistance(new Coordinates(g.getX(), g.getY()), 
						new Coordinates(x, y)) <= radius);
	}
	
	private <T> Collection<T> filter(Collection<T> items, Predicate<T> predicate){
		return items.stream()
				.filter(t -> predicate.test(t))
				.collect(Collectors.toList());
	}
}
