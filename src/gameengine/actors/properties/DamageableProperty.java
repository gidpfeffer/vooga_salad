/**
 * 
 */
package gameengine.actors.properties;

import java.util.ArrayList;
import java.util.List;

import gamedata.composition.DamageableData;
import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndDamageGrid;
import types.BasicActorType;

/**
 * @author harirajan
 *
 */
public class DamageableProperty implements IActProperty<ReadAndDamageGrid> {
	
	private static final double SELF_DAMAGE_ON_HIT = 1.0;
	
	private double myPower;
	private double myHitRadius;
	private List<BasicActorType> myEnemyTypes;
	
	public DamageableProperty(DamageableData data) {
		myPower = data.getMyPower();
		myHitRadius = data.getMyHitRadius();
		myEnemyTypes = data.getMyEnemyTypes();
		
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
		grid.getMyDamageable(actorID).accept(SELF_DAMAGE_ON_HIT);
	}

	/* (non-Javadoc)
	 * @see gameengine.actors.propertygen.IActProperty#isOn()
	 */
	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return true;
	}


}
