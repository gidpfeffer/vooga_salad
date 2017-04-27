package gameengine.actors.properties;

import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndDamageGrid;

/**
 * STUB: implement as soon as grid has ability to access game status
 * @author Moses Wayne
 *
 * @param <G>
 */
public class AddMoneyProperty<G extends ReadAndDamageGrid> implements IActProperty<G>{

	private Double myMoney;
	
	public AddMoneyProperty() {
		
	}
	
	@Override
	public void action(G grid, Integer actorID) {
		grid.getWriteableGameStatus().addMoney(myMoney);
	}

	@Override
	public boolean isOn() {
		return false;
	}

}
