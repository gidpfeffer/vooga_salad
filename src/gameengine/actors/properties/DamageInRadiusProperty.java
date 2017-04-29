package gameengine.actors.properties;

import java.util.Collection;

import gamedata.composition.DamageInRadiusData;
import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndDamageGrid;
import types.BasicActorType;

public class DamageInRadiusProperty<G extends ReadAndDamageGrid> implements IActProperty<G> {

	private Double myRadius;
	private Double myDamage; 
	private Collection<BasicActorType> myTargets;
	
	public DamageInRadiusProperty(DamageInRadiusData myData) {
		myRadius = myData.getRadius();
		myDamage = myData.getDamage();
		myTargets = myData.getTargets();
	}
	
	@Override
	public void action(G grid, Integer actorID) {
		myTargets.stream().forEach(target -> grid.getActorDamagablesInRadius(grid.getLocationOf(actorID).getX(), grid.getLocationOf(actorID).getY(), myRadius, target).forEach((damage,health) -> damage.accept(myDamage)));
	}

	@Override
	public boolean isOn() {
		return false;
	}

}
