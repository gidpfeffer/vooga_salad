package gameengine.actors.properties;

import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndDamageGrid;

/**
 * STUB: need to implement when grid has access to modification of game status
 * @author Moses Wayne
 *
 * @param <G>
 */
public class AddExperienceProperty<G extends ReadAndDamageGrid> implements IActProperty<G> {
	
	private Double myExperience;
	
	public AddExperienceProperty() {
		
	}

	@Override
	public void action(G grid, Integer actorID) {
		grid.getWriteableGameStatus().addExperience(myExperience);
	}

	@Override
	public boolean isOn() {
		return false;
	}

}
