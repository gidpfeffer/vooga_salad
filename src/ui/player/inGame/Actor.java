package ui.player.inGame;

import java.util.Map;
import java.util.Optional;

import gamedata.ActorData;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import ui.general.ImageViewPane;
import ui.general.UIHelper;
import ui.handlers.UIHandler;
import util.VoogaException;

public class Actor{

	ImageViewPane imp;
	UIHandler uihandler;
	ScreenHandler screenhandler;
	Map<Integer, Actor> mapOfActors;
	Actor clazz = this;
	ActorData actorData;
	Pane actor;
	Integer option;
	double width;
	double height;
	Optional<Boolean> removeable;

	public Pane getPane() {
		return actor;
	}
	
	public void deleteActor() {
		actor = null;
	}
	
	public Integer getID() {
		return Integer.parseInt(actor.getId());
	}
	
	public Actor(UIHandler uihandler, ScreenHandler screenhandler, Integer option, ActorData actorData, ImageViewPane ivp, Map<Integer, Actor> mapOfActors) {
		//System.out.println("IN ACTOR: making the option: " + option);
		actor = UIHelper.buttonStack(e -> {
		}, Optional.ofNullable(null), Optional.of(new ImageView(new Image(actorData.getImagePath(), 30, 30, true, true))), Pos.CENTER,
				true);
		actor.setBackground(Background.EMPTY);
		this.screenhandler = screenhandler;
		this.actorData = actorData;
		this.option = option;
		this.imp = ivp;
		this.uihandler = uihandler;
		this.mapOfActors = mapOfActors;
		this.removeable = Optional.of(true);
		setup();
	}

	public void setup() {
		width = imp.getWidth() - 2 * imp.getImageInsets().x;
		height = imp.getHeight() - 2 * imp.getImageInsets().y;
		setupEvents();
	}

	/**
	 * Creates events for when the node is dragged, released, or clicked secondarily
	 */
	public void setupEvents() {
		actor.addEventHandler(MouseEvent.MOUSE_DRAGGED, drag);
		actor.addEventHandler(MouseEvent.MOUSE_CLICKED, place);
		actor.addEventHandler(MouseEvent.MOUSE_RELEASED, released);
	}

	EventHandler<MouseEvent> drag = new EventHandler<MouseEvent>() {
		@Override
		public void handle(final MouseEvent ME) {
			actor.setLayoutX(ME.getSceneX());
			actor.setLayoutY(ME.getSceneY());
		}
	};
	
	/**
	 * if location is appropriate, actor will update to a new location when released from drag 
	 */
	//TODO: how is off grid checked?
	EventHandler<MouseEvent> released = new EventHandler<MouseEvent>() {
		@Override
		public void handle(final MouseEvent ME) {
			if (actor.getId() != null && (mapOfActors.get(Integer.parseInt(actor.getId())) != null)) {
				try {
					uihandler.updateGameObjectLocation(Integer.parseInt(actor.getId()), actor.getLayoutX() / width,
							actor.getLayoutY() / height);
				} catch (NumberFormatException | VoogaException e) {
					screenhandler.showError("You cannot place an item there!");
					System.out.println("Unable to add game object -- Actor ~ 103");
					//System.out.println("**********Unable to update location********** Actor(~80)");
					//e.printStackTrace();
				}
			}
		}
	};

	
	/**
	 * Upon secondary mouse click, actor will be placed in the actor map
	 * The new actor id is now the unique ID returned from the uihandler.
	 * Event handler is then removed optionally.
	 */
	EventHandler<MouseEvent> place = new EventHandler<MouseEvent>() {
		@Override
		public void handle(final MouseEvent ME) {
			if (((MouseEvent) ME).getButton().equals(MouseButton.SECONDARY)) {
				try {
					Integer actorID = uihandler.addGameObject(option, actor.getLayoutX() / width, actor.getLayoutY() / height);
					Object obj = ME.getSource();
					if (obj instanceof Pane) {
						((Pane) obj).removeEventHandler(MouseEvent.MOUSE_CLICKED, place);
						if (removeable.isPresent() && !removeable.get()) ((Pane) obj).removeEventHandler(MouseEvent.MOUSE_DRAGGED, drag);
						//screenhandler.addActorToMap(actorID, clazz);
						mapOfActors.put(actorID, clazz);
						actor.setId(actorID.toString());
					}
				} catch (NumberFormatException | VoogaException e) {
					screenhandler.showError("You cannot place an item there!");
					System.out.println("Unable to add game object -- Actor ~ 132");
					//e.printStackTrace();
				}
			}
			
		}
	};

}
