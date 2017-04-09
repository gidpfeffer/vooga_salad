package ui.player.inGame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import gamedata.ActorData;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ui.general.UIHelper;
import ui.handlers.UIHandler;

/**
 * Creates a pane of all actors of the same type
 * @author Anngelyque
 *
 */
public class OptionsPane{

	private AnchorPane buttonPane;
	private Map<String, String> mapOfMainOptions;
	private Map<Integer, ActorData> mapOfOptions;
	private Collection<Button> listOfButtons;
	private UIHandler uihandler;
	
	
	public void setHeight(double height) {
		buttonPane.setPrefHeight(height);
	}
	
	public double getHeight() {
		return buttonPane.getPrefHeight();
	}
	
	public void setWidth(double width) {
		buttonPane.setPrefWidth(width);
	}
	
	public double getWidth() {
		return buttonPane.getPrefWidth();
	}

	public AnchorPane getPane() {
		return buttonPane;
	}
	
	public void setMap(Map<Integer, ActorData> map) {
		this.mapOfOptions = map;
	}
	
	public Collection<Button> getButtonList() {
		return listOfButtons;
	}
	
	public OptionsPane(UIHandler uihandler) {
		buttonPane = new AnchorPane();
		mapOfOptions = new HashMap<>();
		listOfButtons = new ArrayList<>();
		this.uihandler = uihandler;
		addActorPane();
	}
	
	private void addActorPane() {
		VBox buttonBox = new VBox(50);
		for (Map.Entry<Integer, ActorData> entry : mapOfOptions.entrySet()) {
			createImageButton(entry.getKey(), entry.getValue().getName(), entry.getValue().getImagePath(), pressed);
		}
		buttonBox.getChildren().addAll(listOfButtons);
		buttonPane.getChildren().add(buttonBox);
		addBackButton(closePane);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
		//AnchorPane.setRightAnchor(buttonBox, 10.0);
	}

	private Button createImageButton(Integer id, String name, String imagePath, EventHandler<MouseEvent> pressed) {
		OptionButton optionButton = new OptionButton(id, name, imagePath, pressed);
		listOfButtons.add(optionButton.getButton());
		return optionButton.getButton();
	}
	
	private void addBackButton(EventHandler<MouseEvent> clicked) {
		Button back = createImageButton(0, "back", "back_icon", clicked);
		AnchorPane.setTopAnchor(back, 10.0);
		AnchorPane.setLeftAnchor(back, 10.0);
		buttonPane.getChildren().add(back);
	}
	
	EventHandler<MouseEvent> pressed = new EventHandler<MouseEvent>()  {
	    @Override
	    public void handle( final MouseEvent ME ) {
	        Object obj = ME.getSource();

	        if ( obj instanceof Button ) {
	            Button newButtonInstance = new Button();
	            newButtonInstance.setId(((Button) obj).getId());
	            newButtonInstance.setText(((Button) obj).getText());
	            newButtonInstance.setGraphic(((Button) obj).getGraphic());
	            MainPaneRootHere.getChildren().add(newButtonInstance);
	            // this code drags the button
	            //TODO make new actor somehow accessible
	            newButtonInstance.setOnMouseDragged(e -> {
	            	newButtonInstance.setLayoutX(e.getSceneX());
	            	newButtonInstance.setLayoutY(e.getSceneY());
	             });
	            newButtonInstance.setOnMouseReleased(e -> {
	            	uihandler.addGameObject((Integer.parseInt(((Button) obj).getId())), 
	            			newButtonInstance.getLayoutX(), newButtonInstance.getLayoutY());
	            });
	        }
	    }
	};

	EventHandler<MouseEvent> closePane = new EventHandler<MouseEvent>()  {
		@Override
		public void handle( final MouseEvent ME ) {
			Object obj = ME.getSource();  //ME.getTarget()

			if ( obj instanceof Button ) {
				TranslateTransition t = new TranslateTransition(Duration.seconds(0.2));
				t.setNode(buttonPane);
				t.setToX(-(buttonPane.getWidth()));
				t.play();
			}
		}
	};
}
