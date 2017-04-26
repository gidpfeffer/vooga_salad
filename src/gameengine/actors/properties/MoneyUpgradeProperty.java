package gameengine.actors.properties;

import gamedata.composition.MoneyUpgradeData;
import gameengine.actors.propertygen.UpgradeProperty;
import gameengine.grid.interfaces.ActorGrid.ReadAndSpawnGrid;

/**
 * Upgrade property triggered by purchase
 * 
 * @author maddiebriere
 *
 */

public class MoneyUpgradeProperty <G extends ReadAndSpawnGrid>extends UpgradeProperty<G>{
	private double cost;
	
	public MoneyUpgradeProperty(MoneyUpgradeData data){
		cost = data.getCost();
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
}
