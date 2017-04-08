/**
 * 
 */
package gameengine.actors.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.classes.Coordinates;
import gameengine.grid.interfaces.ActorGrid.ReadAndDamageGrid;
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
		for (BasicActorType e : myEnemyTypes) {
			grid.getActorDamagablesInRadius(grid.getLocationOf(actorID).getX(),
					grid.getLocationOf(actorID).getY(), myHitRadius, e).forEach(a -> a.accept(myPower));
		}
	}

	/* (non-Javadoc)
	 * @see gameengine.actors.propertygen.IActProperty#isOn()
	 */
	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}


}
