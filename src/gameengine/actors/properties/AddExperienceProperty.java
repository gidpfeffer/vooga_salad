package gameengine.actors.properties;

import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndDamageGrid;

/**
 * TODO: make sure that AddExperienceData is a thing
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
