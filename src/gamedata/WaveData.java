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
	
	public void addWaveEnemy(EnemyInWaveData enemy){
		waveEnemies.add(enemy); 
	}
	
	public List<EnemyInWaveData> getWaveEnemies(){
		return waveEnemies;
	}
	
	
	
}
