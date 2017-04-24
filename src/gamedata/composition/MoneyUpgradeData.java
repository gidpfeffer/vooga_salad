package gamedata.composition;

import gamedata.compositiongen.UpgradeData;

/**
 * Assumption: Upgrades will happen only by
 * cost/ when user actively tries to upgrade
 * 
 * @author maddiebriere
 *
 */
public class MoneyUpgradeData extends UpgradeData {
	private Double cost;
	
	public MoneyUpgradeData(){
		this(0.0);
	}
	
	public MoneyUpgradeData(Double cost){
		this.cost = cost;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
}
