package gameengine.actors.properties;

import gamedata.compositiongen.AddMoneyData;
import gameengine.actors.propertygen.IActProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndDamageGrid;

/**
 * TODO: make sure AddMoneyData 
 * @author Moses Wayne
 *
 * @param <G>
 */
public class AddMoneyProperty<G extends ReadAndDamageGrid> implements IActProperty<G>{
	private Double myMoney;
	
	public AddMoneyProperty(AddMoneyData myData) {
		myMoney = myData.getMoney();
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
