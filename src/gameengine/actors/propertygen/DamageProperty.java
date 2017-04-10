package gameengine.actors.propertygen;

/**
 * specify the amount of damage of the actor to the player - this is mostly for troop 
 * when enemy gets to base, enemy location will be set to a location with negative coordinates, then Grid can 
 * filter enemy that walks off the map and call base to take damage from those enemies by calling Actor.getMyDamageToPlayer();
 * 
 */
public interface DamageProperty extends Property {

	double getDamageToPlayer(); 
}
