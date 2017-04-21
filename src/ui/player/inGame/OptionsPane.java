package ui.player.inGame;

import ui.general.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import gamedata.ActorData;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
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
public class OptionsPane extends SlidingPane {

	private ImageViewPane ivp;
	private Pane root;
	private Map<Integer, Actor> actorsMap;
	private Map<Integer, ActorData> mapOfOptions;
	private UIHandler uihandler;
	private String paneName;
	private OptionsPane op = this;
	
	public String getPaneName() {
		return paneName;
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
			String name, ImageViewPane ivp, double width) {
		super(Optional.of("back_icon_flipped.png"), width, Corner.TOPLEFT);
		this.ivp = ivp;
		this.paneName = name;
		this.root = root;
		this.actorsMap = actorsMap;
		this.uihandler = uihandler;
		this.mapOfOptions = map;
		this.setStyle("-fx-background-color: MediumAquamarine;" + " -fx-border-radius: 10 0 0 10;"
				+ "-fx-background-radius: 10 0 0 10;");
		this.setPrefWidth(width);
		setup();
	}

	private void setup() {
		addActorPane();
	}

	/**
	 * Loops through the given map i.e. troops, shots... and creates an image button for each
	 * The button is then added to the buttonPane
	 */
	private void addActorPane() {
		Collection<Button> listOfButtons = new ArrayList<>();
		mapOfOptions.keySet().stream().forEach(entry -> listOfButtons.add(new OptionButton(entry, mapOfOptions.get(entry).getName(),mapOfOptions.get(entry).getImagePath(), pressed).getButton()));
		VBox buttonBox = this.getVBox();
		buttonBox.getChildren().addAll(listOfButtons);
		this.getChildren().add(buttonBox);
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
				Actor actor = new Actor(ivp, uihandler, actorsMap, id, ((Button) obj).getText(), mapOfOptions.get(id).getImagePath());
				root.getChildren().add(actor.getActor());
				actor.getActor().setLayoutX(root.getWidth() - op.getPrefWidth() + ((Button) obj).getLayoutX());
				actor.getActor().setLayoutY(((Button) obj).getLayoutY());
			}
		}
	};
}
