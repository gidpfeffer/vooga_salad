package ui.player.inGame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import gamedata.ActorData;
import javafx.scene.layout.BorderPane;
import ui.handlers.UIHandler;

public class GameScreen {

	private BorderPane borderPane;
	private UIHandler uihandler;
	private Map<String, String> iconImages;
	private SimpleHUD hud;
	
	public GameScreen(UIHandler uihandler, Map<Integer, ActorData> shots, Map<Integer, ActorData> towers,
			Map<Integer, ActorData> troops, Map<Integer, ActorData> bases){
		this.borderPane = new BorderPane();
		this.uihandler = uihandler;
		iconImages = new HashMap<>();
		iconImages.put("towers", "tower_icon");
		iconImages.put("bases", "undo_icon");
		iconImages.put("troops", "enemy_icon");
		iconImages.put("shots", "splash_icon");
		
		hud = new SimpleHUD();
		
		setup(uihandler, shots, towers, troops, bases);
	}
	
	public void setup(UIHandler uihandler, Map<Integer, ActorData> shots, Map<Integer, ActorData> towers,
			Map<Integer, ActorData> troops, Map<Integer, ActorData> bases) {
		SidePanelPanes optionsPane = new SidePanelPanes(uihandler, shots, towers, troops, bases);
		SidePanelPanes mainPane = new SidePanelPanes(uihandler, iconImages, optionsPane.getListOfControllers());
		borderPane.setRight(mainPane.getMainMenu());
		borderPane.setBottom(hud.getGrid());
	}
	
	
	
}
