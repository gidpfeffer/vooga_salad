package ui.player.inGame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gamedata.ActorData;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import ui.handlers.UIHandler;

/**
 * Creates a pane of all actors of the same type.
 * On click, a draggable pane will appear.
 * @author Anngelyque
 *
 */
public class OptionsPane{

	private Pane root;
	private AnchorPane buttonPane;
	private StackPane holder;
	private List<Actor> actorsList;
	private Map<Integer, ActorData> mapOfOptions;
	//temp
	private Map<Integer, List<String>> tempMap;
	private Collection<Button> listOfButtons;
	private UIHandler uihandler;
	
	private String paneName;
	private String tempPaneName;
	
	public String getPaneName() {
		return paneName;
	}
	//temp
	public String getTempPaneName() {
		return tempPaneName;
	}
	
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
	
	public StackPane getStackPane() {
		return holder;
	}
	
	public Map<Integer, List<String>> getMap() {
		return tempMap;
	}
	
	//temp
	public void setTempMap(Map<Integer, List<String>> tempMap) {
		this.tempMap = tempMap;
	}
	
	public Collection<Button> getButtonList() {
		return listOfButtons;
	}
	
	//uncomment for real side panel
/*	public OptionsPane(UIHandler uihandler, AnchorPane root, List<Actor> actorsList, Map<Integer, ActorData> map) {
		this.root = root;
		this.actorsList = actorsList;
		buttonPane = new AnchorPane();
		mapOfOptions = new HashMap<>();
		listOfButtons = new ArrayList<>();
		this.uihandler = uihandler;
		mapOfOptions = map;
		addActorPane();
	}*/
	
	public OptionsPane(UIHandler uihandler, Pane root, List<Actor> actorsList, Map<Integer, List<String>> temp) {
		this.root = root;
		this.holder = new StackPane();
		this.actorsList = actorsList;
		buttonPane = new AnchorPane();
		buttonPane.setStyle("-fx-background-color: aqua");
		mapOfOptions = new HashMap<>();
		listOfButtons = new ArrayList<>();
		this.uihandler = uihandler;
		this.tempMap = temp;
		addActorPane();
		holder.getChildren().add(buttonPane);
	}
	
	private void addActorPane() {
		VBox buttonBox = new VBox(50);
		//uncomment section to use actor data
/*		for (Map.Entry<Integer, ActorData> entry : mapOfOptions.entrySet()) {
			paneName = entry.getValue().getActor().toString();
			createImageButtonAndAddToList(entry.getKey(), entry.getValue().getName(), entry.getValue().getImagePath(), pressed);
		}*/
		//temp
		for (Map.Entry<Integer, List<String>> entry : tempMap.entrySet()) {
			tempPaneName = entry.getValue().get(2);
			createImageButtonAndAddToList(entry.getKey(), entry.getValue().get(0), entry.getValue().get(1), pressed);
		}
		Rectangle buffer = new Rectangle();
		buffer.setFill(Color.TRANSPARENT);
		buttonBox.getChildren().add(buffer);
		buttonBox.getChildren().addAll(listOfButtons);
		buttonPane.getChildren().add(buttonBox);
		buttonPane.setLayoutY(50);
		addBackButton(closePane);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
		//AnchorPane.setRightAnchor(buttonBox, 10.0);
	}

	private Button createImageButtonAndAddToList(Integer id, String name, String imagePath, EventHandler<MouseEvent> pressed) {
		OptionButton optionButton = new OptionButton(id, name, imagePath, pressed);
		listOfButtons.add(optionButton.getButton());
		return optionButton.getButton();
	}
	
	private void addBackButton(EventHandler<MouseEvent> clicked) {
		Button back = createImageButtonAndAddToList(0, "back", "back_icon_flipped.png", clicked);
		AnchorPane.setTopAnchor(back, 10.0);
		AnchorPane.setLeftAnchor(back, 10.0);
		buttonPane.getChildren().add(back);
	}
	
	EventHandler<MouseEvent> pressed = new EventHandler<MouseEvent>()  {
	    @Override
	    public void handle( final MouseEvent ME) {
	        Object obj = ME.getSource();

	        if ( obj instanceof Button ) {
	        	Integer id = Integer.parseInt(((Button) obj).getId());
	        	String name = ((Button) obj).getText();
	        	//temp
	        	String image = tempMap.get(id).get(1);
	        	//correct --> String image = listOfOptions.get(id).getImagePath();
	        	System.out.println(id +" " + name + " " + image);
	        	Actor actor = new Actor(root, uihandler, id, name, image);
	            actorsList.add(actor);
	            System.out.println(actor.getActor() + " " + actorsList.size());
	        	root.getChildren().add(actor.getActor());
	        }
	    }
	};

	EventHandler<MouseEvent> closePane = new EventHandler<MouseEvent>()  {
		@Override
		public void handle( final MouseEvent ME ) {
			Object obj = ME.getSource();  //ME.getTarget()

			System.out.println(buttonPane.getPrefWidth());
			if ( obj instanceof Button ) {
				TranslateTransition t = new TranslateTransition(Duration.seconds(0.2));
				t.setNode(buttonPane);
				t.setToX((buttonPane.getPrefWidth()));
				t.play();
			}
		}
	};
}
