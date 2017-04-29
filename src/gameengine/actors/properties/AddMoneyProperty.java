package gameengine.actors.properties;

import gameengine.actors.propertygen.CycleProperty;
import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndDamageGrid;

/**
 * TODO: make sure AddMoneyData 
 * @author Moses Wayne
 *
 * @param <G>
 */
public class AddMoneyProperty<G extends ReadAndDamageGrid> extends CycleProperty<G>{

	private Double myMoney;
	
	public AddMoneyProperty(AddMoneyData myData) {
		super(myData.getRate());
		myMoney = myData.getMoney();
	}
	
	@Override
	public void action(G grid, Integer actorID) {
		grid.getWriteableGameStatus().addMoney(myMoney);
	}

}
