package ui.player.inGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TempData {
	private Map<Integer, List<String>> tempTowers = new HashMap<>();
	private Map<Integer, List<String>> tempBases = new HashMap<>();
	private Map<Integer, List<String>> tempTroops = new HashMap<>();
	private Map<Integer, List<String>> tempShots = new HashMap<>();
	
	public Map<Integer, List<String>> getTempTowers() {
		return tempTowers;
	}
	public void setTempTowers(Map<Integer, List<String>> tempTowers) {
		this.tempTowers = tempTowers;
	}
	public Map<Integer, List<String>> getTempBases() {
		return tempBases;
	}
	public void setTempBases(Map<Integer, List<String>> tempBases) {
		this.tempBases = tempBases;
	}
	public Map<Integer, List<String>> getTempTroops() {
		return tempTroops;
	}
	public void setTempTroops(Map<Integer, List<String>> tempTroops) {
		this.tempTroops = tempTroops;
	}
	public Map<Integer, List<String>> getTempShots() {
		return tempShots;
	}
	public void setTempShots(Map<Integer, List<String>> tempShots) {
		this.tempShots = tempShots;
	}
	
	
	public TempData(){
		tempTowers.put(0, new ArrayList<String>(Arrays.asList("tower0", "tower_icon.png", "Tower")));
		tempTowers.put(1, new ArrayList<String>(Arrays.asList("tower1", "tower_icon.png", "Tower")));
		tempTowers.put(2, new ArrayList<String>(Arrays.asList("tower2", "tower_icon.png", "Tower")));
		tempBases.put(3, new ArrayList<String>(Arrays.asList("base0", "base_icon.png", "Base")));
		tempBases.put(4, new ArrayList<String>(Arrays.asList("base1", "base_icon.png", "Base")));
		tempBases.put(5, new ArrayList<String>(Arrays.asList("base2", "base_icon.png", "Base")));
		tempBases.put(6, new ArrayList<String>(Arrays.asList("base3", "base_icon.png", "Base")));
		tempTroops.put(7, new ArrayList<String>(Arrays.asList("troop0", "enemy_icon.png", "Troop")));
		tempTroops.put(8, new ArrayList<String>(Arrays.asList("troop1", "enemy_icon.png", "Troop")));
		tempTroops.put(9, new ArrayList<String>(Arrays.asList("troop2", "enemy_icon.png", "Troop")));
		tempShots.put(10, new ArrayList<String>(Arrays.asList("shot0", "projectile_icon.png", "Shot")));
		tempShots.put(11, new ArrayList<String>(Arrays.asList("shot1", "projectile_icon.png", "Shot")));
		tempShots.put(12, new ArrayList<String>(Arrays.asList("shot2", "projectile_icon.png", "Shot")));
		tempShots.put(13, new ArrayList<String>(Arrays.asList("shot3", "projectile_icon.png", "Shot")));
	}
}
