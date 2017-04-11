/**
 * 
 */
package gameengine.actors.properties;

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
	private List<BasicActorType> myTargetTypes;
	
	public DamageableProperty(DamageableData data) {
		myPower = data.getMyPower();
		myHitRadius = data.getMyHitRadius();
		myTargetTypes = data.getMyEnemyTypes();
		
	}
	
	/* (non-Javadoc)
	 * @see gameengine.actors.properties.IActProperty#action(gameengine.grid.interfaces.ActorGrid.ReadableGrid, java.lang.Integer)
	 */
	@Override
	public void action(ReadAndDamageGrid grid, Integer actorID) {
		for (BasicActorType t : myTargetTypes) {
			grid.getActorDamagablesInRadius(grid.getLocationOf(actorID).getX(),
					grid.getLocationOf(actorID).getY(), myHitRadius, t).forEach(a -> a.accept(myPower));
		}
		grid.getMyDamageable(actorID).accept(SELF_DAMAGE_ON_HIT);
	}

	/* (non-Javadoc)
	 * @see gameengine.actors.propertygen.IActProperty#isOn()
	 */
	@Override
	public boolean isOn() {
		return true;
	}


}
