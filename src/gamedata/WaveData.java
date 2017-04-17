package gamedata;

import java.util.ArrayList;
import java.util.List;

/**
 * wave of enemy attack for each level
 * @author Anh
 *
 */

/**
 * Wave ex: 
 * - Enemy Type: 1 Number: 200 PathOptions: 1, 2
 * 
 * number of troops and its types and its path options as indexes 
 *  
 * @author Anh
 *
 */
public class WaveData {
	List<EnemyInWaveData> waveEnemies; 
	
	public WaveData(){
		waveEnemies = new ArrayList<EnemyInWaveData>(); 
	}
	
	public boolean contains(ActorData actor){
		for(EnemyInWaveData enemy: waveEnemies){
			
			if(enemy.getMyActor().equals(actor)){
				return true;
			}
		}
		return false;
	}
	
	public void setQuantity(ActorData actor, int quantity){
		for(EnemyInWaveData enemy: waveEnemies){
			if(enemy.getMyActor().equals(actor)){
				enemy.setMyNumber(quantity);
			}
		}
	}
	
	public int getQuantity(ActorData actor){
		int toRet = 0;
		for(EnemyInWaveData enemy: waveEnemies){
			if(enemy.getMyActor().equals(actor)){
				toRet = enemy.getMyNumber();
			}
		}
		return toRet;
	}
	
	public void addWaveEnemy(EnemyInWaveData enemy){
		waveEnemies.add(enemy); 
	}
	
	public List<EnemyInWaveData> getWaveEnemies(){
		return waveEnemies;
	}
	
	public String toString(){
		return waveEnemies.toString();
	}
	
}
