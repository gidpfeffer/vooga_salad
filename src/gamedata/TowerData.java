package gamedata;

import types.TowerType;

public class TowerData {
	private String imagePath;
	private TowerType towerType;
	
	/**
	 * TODO: Add other parameters
	 * @return
	 */
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public TowerType getTowerType() {
		return towerType;
	}
	public void setTowerType(TowerType towerType) {
		this.towerType = towerType;
	}
	
	
}
