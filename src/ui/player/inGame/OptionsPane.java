package ui.player.inGame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import gamedata.ActorData;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class OptionsPane{

	private AnchorPane buttonPane;
	private Map<String, String> mapOfMainOptions;
	private Map<Integer, ActorData> mapOfOptions;
	private Collection<Button> listOfButtons;
	private int width;
	
	public int getWidth() {
		return width;
	}

	public AnchorPane getPane() {
		return buttonPane;
	}
	
	public void setMainMap(Map<String, String> map) {
		this.mapOfMainOptions = map;
	}
	
	public void setMap(Map<Integer, ActorData> map) {
		this.mapOfOptions = map;
	}
	
	public Collection<Button> getButtonList() {
		return listOfButtons;
	}
	
	public OptionsPane() {
		buttonPane = new AnchorPane();
		buttonPane.setPrefWidth(width);
		mapOfOptions = new HashMap<>();
		listOfButtons = new ArrayList<>();
	}

	public void addBackButton(EventHandler<MouseEvent> clicked) {
		Button back = createImageButton(0, "back", "back_icon", clicked, null, null);
		AnchorPane.setTopAnchor(back, 10.0);
		AnchorPane.setLeftAnchor(back, 10.0);
		buttonPane.getChildren().add(back);
	}
	
	public void addButtonPane(int spacing, EventHandler<MouseEvent> clicked) {
		VBox buttonBox = new VBox(spacing);
		mapOfMainOptions.forEach((n, p) -> createImageButton(0, n, p, clicked, null, null));
		buttonBox.getChildren().addAll(listOfButtons);
		buttonPane.getChildren().add(buttonBox);
		AnchorPane.setRightAnchor(buttonBox, 10.0);
	}
	
	public void addButtonPane(int spacing, EventHandler<MouseEvent> clicked, 
			EventHandler<MouseEvent> dragged, EventHandler<DragEvent> done) {
		VBox buttonBox = new VBox(spacing);
		for (Map.Entry<Integer, ActorData> entry : mapOfOptions.entrySet()) {
			createImageButton(entry.getKey(), entry.getValue().getName(), entry.getValue().getImagePath(), clicked, dragged, done);
		}
		buttonBox.getChildren().addAll(listOfButtons);
		buttonPane.getChildren().add(buttonBox);
		AnchorPane.setRightAnchor(buttonBox, 10.0);
	}

	private Button createImageButton(Integer id, String name, String imagePath,
			EventHandler<MouseEvent> clicked, EventHandler<MouseEvent> dragged, EventHandler<DragEvent> done) {
		OptionButton optionButton = new OptionButton(id, name, imagePath, clicked, dragged, done);
		listOfButtons.add(optionButton.getButton());
		return optionButton.getButton();
	}
}
