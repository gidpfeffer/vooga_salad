package ui.player.inGame;

import ui.general.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import gamedata.ActorData;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import ui.handlers.UIHandler;

/**
 * Creates a pane of all actors of the same type that can be accessed by a sliding transition.
 * The options can then be clicked, generating a "duplicate" that can be dragged on to the screen.
 * 
 * @author anngelyque
 *
 */
public class OptionsPane {

	private ImageViewPane ivp;
	private Pane root;
	private AnchorPane buttonPane;
	private Map<Integer, Actor> actorsMap;
	private Map<Integer, ActorData> mapOfOptions;
	private UIHandler uihandler;
	private String paneName;

	public String getPaneName() {
		return paneName;
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

	public Map<Integer, ActorData> getMap() {
		return mapOfOptions;
	}

	/**
	 * Takes in uihandler to send to actor to update it's location, root to add
	 * the new actor, map of the current actors that exist, map of actor
	 * options, given pane name, and the background image view pane
	 * 
	 * @param uihandler
	 * @param root
	 * @param actorsMap
	 * @param map
	 * @param name
	 * @param ivp
	 */
	public OptionsPane(UIHandler uihandler, Pane root, Map<Integer, Actor> actorsMap, Map<Integer, ActorData> map,
			String name, ImageViewPane ivp) {
		this.ivp = ivp;
		this.paneName = name;
		this.root = root;
		this.actorsMap = actorsMap;
		this.uihandler = uihandler;
		this.mapOfOptions = map;
		buttonPane = new AnchorPane();
		buttonPane.setStyle("-fx-background-color: MediumAquamarine;" + " -fx-border-radius: 10 0 0 10;"
				+ "-fx-background-radius: 10 0 0 10;");
		setup();
	}

	private void setup() {
		addActorPane();
		addBackButton(closePane);
	}

	/**
	 * Loops through the given map i.e. troops, shots... and creates an image button for each
	 * The button is then added to the buttonPane
	 */
	private void addActorPane() {
		Collection<Button> listOfButtons = new ArrayList<>();
		VBox buttonBox = new VBox(50);
		for (Map.Entry<Integer, ActorData> entry : mapOfOptions.entrySet()) {
			listOfButtons.add(createImageButton(entry.getKey(), entry.getValue().getName(),
					entry.getValue().getImagePath(), pressed));
		}
		buttonBox.getChildren().add(new Rectangle(25, 25, Color.TRANSPARENT));
		buttonBox.getChildren().addAll(listOfButtons);
		buttonPane.getChildren().add(buttonBox);
	}

	/**
	 * Creates a back button for the pane and places in the upper left-hand
	 * corner Links to an event handler that closes the pane
	 * 
	 * @param clicked
	 */
	private void addBackButton(EventHandler<MouseEvent> clicked) {
		Button back = createImageButton(0, "", "back_icon_flipped.png", clicked);
		AnchorPane.setTopAnchor(back, 10.0);
		AnchorPane.setLeftAnchor(back, 10.0);
		buttonPane.getChildren().add(back);
	}

	/**
	 * Creates a new option button bases on a given id, name, image path, and
	 * event handler The id is the option (the can be multiple actors with
	 * identical IDs) The event handler deals with adding the new node to the
	 * screen
	 * 
	 * @param id
	 * @param name
	 * @param imagePath
	 * @param pressed
	 * @return
	 */
	private Button createImageButton(Integer id, String name, String imagePath, EventHandler<MouseEvent> pressed) {
		OptionButton optionButton = new OptionButton(id, name, imagePath, pressed);
		return optionButton.getButton();
	}

	/**
	 * takes the source's id, image, and name and copies the data to the new
	 * node instance the new node is then added to the root the new node appears
	 * near the original button clicked
	 */
	EventHandler<MouseEvent> pressed = new EventHandler<MouseEvent>() {
		@Override
		public void handle(final MouseEvent ME) {
			Object obj = ME.getSource();

			if (obj instanceof Button) {
				Integer id = Integer.parseInt(((Button) obj).getId());
				String name = ((Button) obj).getText();
				String image = mapOfOptions.get(id).getImagePath();
				Actor actor = new Actor(ivp, uihandler, actorsMap, id, name, image);
				// actorsMap.put(id, actor);
				root.getChildren().add(actor.getActor());
				double xLocation = root.getWidth() - buttonPane.getPrefWidth() + ((Button) obj).getLayoutX();
				actor.getActor().setLayoutX(xLocation);
				actor.getActor().setLayoutY(((Button) obj).getLayoutY());
			}
		}
	};

	/**
	 * closes an open pane based on it's preferred size
	 */
	EventHandler<MouseEvent> closePane = new EventHandler<MouseEvent>() {
		@Override
		public void handle(final MouseEvent ME) {
			Object obj = ME.getSource();
			if (obj instanceof Button) {
				TranslateTransition t = new TranslateTransition(Duration.seconds(0.2));
				t.setNode(buttonPane);
				t.setToX((buttonPane.getPrefWidth()));
				t.play();
			}
		}
	};
}
