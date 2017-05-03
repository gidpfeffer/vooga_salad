package gameengine.actors.properties;

import java.util.function.Consumer;

import gamedata.composition.ActorDamageableData;
import gamedata.composition.ActorDamageableWithExitData;
import gameengine.grid.interfaces.ActorGrid.ReadAndDamageGrid;

public class ActorDamageableWithExitProperty<G extends ReadAndDamageGrid> extends ActorDamageableProperty<G> {
	
	//private Collection<IActProperty<MasterGrid>> myExits;
	
	//public ActorDamageableWithExitProperty(ActorDamageableWithExitData myData) {
	//	myExits = myData.getExits();

	public ActorDamageableWithExitProperty(ActorDamageableWithExitData myData) {
		super(myData);
	}
	
	@Override
	protected void damageActor(G grid, Integer actorID, Consumer<Double> damage, Double health) {
		super.damageActor(grid, actorID, damage, health);
		grid.getWriteableGameStatus().addExperience(health);
		grid.getWriteableGameStatus().addMoney(health);
		//grid.
	}

}
