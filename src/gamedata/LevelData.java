package gamedata;

import java.util.List;

/**
 * holds all the data that is encoded for a level
 * such as enemies, towers, and map
 * @author TNK
 *
 */
public class LevelData {
	private MapData mapData;
	private List<ActorData>actors;
	
	/* TODO
	 * 
	 * EnemyData // hp, speed, immunity, money-drop,attack
	 * TowerData //pow, range, freq, anti-immunity, movability, (health v static)
	 * power-up data
	 * BaseData (optional)
	 * PreferencesData (game type, optional functionality)
	 	* Rules
	 	* Are towers attackable
	 	* Do enemies loop around when they get to the exit
	 	* Is there going to be a base
	 	* How many lives does the player have
	 	* Interest on money?
	 	* pause between waves?
	 */
}
