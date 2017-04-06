package ui.player.inGame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import gamedata.ActorData;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import ui.general.ImageButton;
import util.Location;

public class Menu {

	private VBox menu;
	private Collection<ImageButton> buttonList = new ArrayList<ImageButton>();
	private final static String image = "tower-icon.png";
	
	public VBox getMenu(){
		return menu;
	}
	
	public Collection<ImageButton> getButtonList(){
		return buttonList;
	}
	
	public Menu() {
		menu = new VBox();

	}
	
	public void addAllToVBox(Map<Integer, ActorData> map, String image, EventHandler<MouseEvent> event){
		for (Map.Entry<Integer, ActorData> entry : map.entrySet()) {
			//entry.getValue.getImage()
			ImageButton imageButton = new ImageButton(image, new Location(0.0, 0.0));
			imageButton.setId(entry.getKey().toString());
			menu.getChildren().add(imageButton);
			buttonList.add(imageButton);
			imageButton.setOnMouseDragReleased(event);
			//System.out.println(entry.getKey() + "/" + entry.getValue());
		}
	}
	
	public void addToMenu(ImageButton newImageButton, EventHandler<MouseEvent> event) {
		newImageButton.setOnMouseDragReleased(event);
		menu.getChildren().add(newImageButton);
	}
	
}
