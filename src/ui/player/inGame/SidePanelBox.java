package ui.player.inGame;

import gamedata.GameData;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import ui.player.InGameModel;

public class SidePanelBox {
	
	private GameData gameData;
	private InGameController shots;
	private InGameController towers;
	private InGameController troops;
	private InGameController base;
	
	public VBox getShotsMenu() {
		return shots.getMenu();
	}
	
	public VBox getTowersMenu() {
		return towers.getMenu();
	}
	
	public VBox getTroopsMenu() {
		return troops.getMenu();
	}
	
	public VBox getBaseMenu() {
		return troops.getMenu();
	}
	
	public SidePanelBox(GameData gameData) {
		this.gameData = gameData;
		setup();
	}
	
	public void setup() {
		setupControllers();
		setupMaps();
		createVBoxes();
	}

	private void setupControllers() {
		shots = new InGameController(new InGameModel(), new Menu());
		towers = new InGameController(new InGameModel(), new Menu());
		troops = new InGameController(new InGameModel(), new Menu());
		base = new InGameController(new InGameModel(), new Menu());
		
	}
	
	private void setupMaps() {
		shots.setMap(gameData.getShotsOptions());
		towers.setMap(gameData.getTowersOptions());
		troops.setMap(gameData.getTroopsOptions());
		base.setMap(gameData.getBasesOptions());
	}
	
	private void createVBoxes() {
		shots.addAllToVBox(shots.getMap(), "clear_icon", addShot());
		towers.addAllToVBox(towers.getMap(), "tower_icon", addTower());
		troops.addAllToVBox(troops.getMap(), "enemy_icon", addTroops());
		base.addAllToVBox(base.getMap(), "undo_icon", addBase());
	}
	
	
	public EventHandler<MouseEvent> addShot() {
		return e -> System.out.println("adding shot");
	}
	
	public EventHandler<MouseEvent> addTower() {
		return e -> System.out.println("adding tower");
	}
	
	public EventHandler<MouseEvent> addTroops() {
		return e -> System.out.println("adding troops");
	}
	
	public EventHandler<MouseEvent> addBase() {
		return e -> System.out.println("adding base");
	}
}

