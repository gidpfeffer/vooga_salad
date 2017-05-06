package gameengine.actors.properties;

import gamedata.composition.AddMoneyCycleData;
import gameengine.actors.propertygen.CycleProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndDamageGrid;

/**
 * TODO: make sure AddMoneyData 
 * @author Moses Wayne
 *
 * @param <G>
 */

public class AddMoneyCycleProperty<G extends ReadAndDamageGrid> extends CycleProperty<G>{

	private Double myMoney;
	
	public AddMoneyCycleProperty(AddMoneyCycleData myData) {
		super(myData.getFrequency());
		myMoney = myData.getMoney();
	}
	
	@Override
	public void action(G grid, Integer actorID) {
		grid.getWriteableGameStatus().addMoney(myMoney.intValue());
	}

}
