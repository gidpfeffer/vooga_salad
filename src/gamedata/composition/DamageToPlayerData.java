package gamedata.composition;

import gamedata.compositiongen.DamageData;

public class DamageToPlayerData implements DamageData {
	
	private double damage; 
	DamageToPlayerData(double dam){
		damage = dam; 
	}
	
	public double getDamageToPlayer(){
		return damage; 
	}
	
	public void setDamageToPlayer(double newdam){
		damage = newdam; 
	}
	
}
