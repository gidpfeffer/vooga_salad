/**
 * 
 */
package gameengine.actors.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import gameengine.grid.classes.Coordinates;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import types.BasicActorType;
import util.PathUtil;

/**
 * @author harirajan
 *
 */
public class DamageableProperty implements IActProperty<ReadAndDamageGrid> {
	
	private double myPower;
	private double myHitRadius;
	private List<BasicActorType> myEnemyTypes;
	
	public DamageableProperty(double power, double radius, BasicActorType... enemyTypes) {
		myPower = power;
		myHitRadius = radius;
		myEnemyTypes = new ArrayList<>();
		for (BasicActorType e : enemyTypes) {
			myEnemyTypes.add(e);
		}
		
	}

	/* (non-Javadoc)
	 * @see gameengine.actors.properties.IActProperty#action(gameengine.grid.interfaces.ActorGrid.ReadableGrid, java.lang.Integer)
	 */
	@Override
	public void action(ReadAndDamageGrid grid, Integer actorID) {
		// TODO Auto-generated method stub
		Collection<Grid2D> enemyLocations = new ArrayList<>();
		for (BasicActorType e : myEnemyTypes) {
			enemyLocations.addAll(grid.getActorLocationsInRadius(grid.getLocationOf(actorID).getX(),
															grid.getLocationOf(actorID).getY(), myHitRadius, e));
		}
		grid.
		enemyLocations.stream().min(Comparator.comparingDouble(loc -> PathUtil.getDistance(grid.getLocationOf(actorID), (Grid2D) loc)).get());
	}

	/* (non-Javadoc)
	 * @see gameengine.actors.properties.IActProperty#isOn()
	 */
	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void help() {
		Grid2D masterPoint = new Coordinates(10, 10);
		Collection<Grid2D> enemyLocations = new ArrayList<>();
		enemyLocations.add(new Coordinates(0, 1));
		enemyLocations.add(new Coordinates(5, 2));
		enemyLocations.add(new Coordinates(5, 1));
		System.out.println(enemyLocations.stream().min(Comparator.comparingDouble(loc -> PathUtil.getDistance(masterPoint, (Grid2D) loc))).get());
	}
	
	public static void main(String[] args) {
		DamageableProperty test = new DamageableProperty(0, 0);
		test.help();
		
	}

}
