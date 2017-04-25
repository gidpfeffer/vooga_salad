/**
 * 
 */
package gameengine.actors.properties;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import gamedata.compositiongen.DamageableData;
import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndDamageGrid;
import types.BasicActorType;

/**
 * @author harirajan
 *
 */
public abstract class DamageableProperty<G extends ReadAndDamageGrid> implements IActProperty<G> {
	
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
	public void action(G grid, Integer actorID) {
		getEnemiesToHit(myTargetTypes,grid,actorID,myHitRadius).stream().forEach(damage -> {
			damage.accept(myPower);
			grid.getMyDamageable(actorID).accept(Double.POSITIVE_INFINITY);
		});
	}

	/* (non-Javadoc)
	 * @see gameengine.actors.propertygen.IActProperty#isOn()
	 */
	@Override
	public boolean isOn() {
		return true;
	}
	
	protected abstract Collection<Consumer<Double>> getEnemiesToHit(Collection<BasicActorType> myTypes, G grid, Integer actorID, Double radius);


}
