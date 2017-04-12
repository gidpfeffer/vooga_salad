package builders;

import java.util.ArrayList;
import java.util.List;

import gamedata.GameData;
import gamedata.ProjectileData;
import gamedata.ProjectileType;

public class ProjectileGenerator {

	/**
	 * Get all of the current projectiles available in the game data,
	 * indexed by Integers. 
	 * 
	 * @param data The current GameData
	 * @return a List of Integers representing possible projectiles
	 */
	public static List<Integer> getCurrentProjectiles(GameData data){
		return new ArrayList<Integer>(data.getMyProjectiles().getMyProjectiles().keySet());
	}
	
	
	/**
	 * Add a projectile to the list of available projectiles in your GameData
	 * 
	 * @param data Current GameData
	 * @param toAdd Projectile to add
	 */
	public static void addProjectile(ProjectileData data, ProjectileType toAdd){
		data.addProjectile(toAdd);
	}
	
	
	public static void getProjectileFields(){
		
	}
	
	//TODO: Other needed methods?
}
