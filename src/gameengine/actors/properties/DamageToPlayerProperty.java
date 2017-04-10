package gameengine.actors.properties;

import gamedata.composition.DamageToPlayerData;
import gameengine.actors.propertygen.DamageProperty;

 
/**
 * specify the amount of damage of the actor to the player - this is mostly for troop
 * @author Anh
 *
 */
public class DamageToPlayerProperty implements DamageProperty {
	
	private double myDamageToPlayer; 
	public DamageToPlayerProperty(DamageToPlayerData data){
		myDamageToPlayer = data.getDamageToPlayer();
	}
	@Override
	public double getDamageToPlayer() {
		// TODO Auto-generated method stub
		return myDamageToPlayer;
	}
	
}
