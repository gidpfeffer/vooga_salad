package gamedata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameengine.grid.interfaces.Identifiers.Grid2D;

// like Path Data, index ProjectileType
/**
 * contain a map of option index to projectile type . projectileType holds front end info on the type of 
 * projectile such as image path, speed, radius, radius, damage....
 * @author Anh
 *
 */
public class ProjectileData {
	
	private int numOptions;
	private Map<Integer,ProjectileType> allProjectileTypes;
	
	public ProjectileData(){
		allProjectileTypes = new HashMap<Integer, ProjectileType>();
		numOptions = 0;
	}
	
	public ProjectileData(Map <Integer, ProjectileType> projectiles){
		allProjectileTypes = projectiles;
		numOptions = allProjectileTypes.size();
		
	}
	
	public ProjectileType getProjectileByIndex(Integer index){
		return allProjectileTypes.get(index);
	}
	
	public void addProjectile(ProjectileType projectile){
		allProjectileTypes.put(numOptions++, projectile);
	}
	public Map<Integer, ProjectileType> getMyProjectiles() {
		return allProjectileTypes;
	}
	public void setMyProjectiles(Map<Integer, ProjectileType> projectiletypes) {
		allProjectileTypes = projectiletypes;
	}
}

