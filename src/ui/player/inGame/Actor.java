package ui.player.inGame;

import java.util.Map;
import java.util.Optional;

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
	Map<Integer, Actor> mapOfActors;
	Actor clazz = this;
	String name;
	Pane actor;
	Integer option;
	double width;
	double height;
	Optional<Boolean> removeable;

	public Integer getOption() {
		return option;
	}
	
	public Pane getActor() {
		return actor;
	}
	
	public void deleteActor() {
		actor = null;
	}

	public String getName() {
		return name;
	}
	
	public Integer getID() {
		return Integer.parseInt(actor.getId());
	}

	//change so that it doesn't take in entire UIHandler for two methods???
	public Actor(ImageViewPane ivp, UIHandler uihandler, Map<Integer, Actor> mapOfActors, Integer option, String name,
			String image) {
		actor = UIHelper.buttonStack(e -> {
		}, Optional.ofNullable(null), Optional.of(new ImageView(new Image(image, 30, 30, true, true))), Pos.CENTER,
				true);
		actor.setBackground(Background.EMPTY);
		this.option = option;
		this.imp = ivp;
		this.uihandler = uihandler;
		this.name = name;
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
	EventHandler<MouseEvent> released = new EventHandler<MouseEvent>() {
		@Override
		public void handle(final MouseEvent ME) {
			if (actor.getId() != null && (mapOfActors.get(Integer.parseInt(actor.getId())) != null)) {
				System.out.println("W: " + actor.getLayoutX() / width + " " + actor.getLayoutY() / height + " "+ actor.getId() + " " + mapOfActors);
				try {
					uihandler.updateGameObjectLocation(Integer.parseInt(actor.getId()), actor.getLayoutX() / width,
							actor.getLayoutY() / height);
				} catch (NumberFormatException | VoogaException e) {
					System.out.println("Unable to add game object -- Actor ~ 103");
					//System.out.println("**********Unable to update location********** Actor(~80)");
					//e.printStackTrace();
				}
			}
		}
	};

	
	/**
	 * Upon secondary mouse click, actor will be placed in the actor map
	 * The new actor is mapped to it's original id (**OR?)
	 * Event handler is then removed -> can only update the node
	 */
	//Anh this is where it should throw an error first
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
						mapOfActors.put(actorID, clazz);
						actor.setId(actorID.toString());
						System.out.println("actorID: " + actor.getId());
					}
				} catch (NumberFormatException | VoogaException e) {
					System.out.println("Unable to add game object -- Actor ~ 132");
					e.printStackTrace();
				}
			}
		}
	};

}
