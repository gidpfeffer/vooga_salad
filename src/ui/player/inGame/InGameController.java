package ui.player.inGame;

import java.util.Collection;
import java.util.Map;

import gamedata.ActorData;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import ui.general.ImageButton;
import ui.player.InGameModel;

public class InGameController {

	private InGameModel model;
	private Menu menu;
	
	public InGameController(InGameModel model, Menu menu) {
		this.model = model;
		this.menu = menu;
	}
	
	public void setMap(Map<Integer, ActorData> map) {
		model.setMap(map);
	}
	
	public Map<Integer, ActorData> getMap(){
		return model.getMap();
	}
	
	public VBox getMenu() {
		return menu.getMenu();
	}
	
	public Collection<ImageButton> getButtonList() {
		return menu.getButtonList();
	}
	
	public void addAllToVBox(Map<Integer, ActorData> map, String image, EventHandler<MouseEvent> event) {
		menu.addAllToVBox(map, image, event);
	}
	
	public void addToMenu(ImageButton newImageButton, EventHandler<MouseEvent> event) {
		menu.addToMenu(newImageButton, event);
	}
	
	public void onAction(ImageButton imageButton) {
		//instantiate new node via id as key
		//model.getMap().get(new Integer(Integer.parseInt(imageButton.getId())));
	}
}


