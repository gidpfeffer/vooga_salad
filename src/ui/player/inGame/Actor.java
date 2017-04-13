package ui.player.inGame;

import java.util.Map;
import java.util.Optional;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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

public class Actor {

	ImageViewPane root;
	UIHandler uihandler;
	Map<Integer, Actor> mapOfActors;
	Actor clazz = this;
	String name;
	Pane actor;
	Integer option;

	public Integer getOption() {
		return option;
	}
	
	public Pane getActor() {
		return actor;
	}

	public String getName() {
		return name;
	}
	
	public Integer getID() {
		return Integer.parseInt(actor.getId());
	}

	public Actor(ImageViewPane root, UIHandler uihandler, Map<Integer, Actor> mapOfActors, Integer option, String name,
			String image) {

		actor = UIHelper.buttonStack(e -> {
		}, Optional.ofNullable(null), Optional.of(new ImageView(new Image(image, 30, 30, true, true))), Pos.CENTER,
				true);
		actor.setBackground(Background.EMPTY);
		this.option = option;
		this.root = root;
		this.uihandler = uihandler;
		this.name = name;
		this.mapOfActors = mapOfActors;
		setup();
	}

	public void setup() {
		setupEvents();
	}

	/**
	 * Creates events for when the node is dragged, released, or clicked secondarily
	 */
	public void setupEvents() {
		actor.setOnMouseDragged(e -> {
			actor.setLayoutX(e.getSceneX());
			actor.setLayoutY(e.getSceneY());
		});
		//actor.addEventHandler(MouseEvent.MOUSE_DRAGGED, drag);
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
	 * 
	 * NOTE: Make settings to make this optional
	 * 
	 */
	EventHandler<MouseEvent> released = new EventHandler<MouseEvent>() {
		@Override
		public void handle(final MouseEvent ME) {
			if (actor.getId() != null && (mapOfActors.get(Integer.parseInt(actor.getId())) != null)) {
				System.out.println("NEWEST ID: " + Integer.parseInt(actor.getId()));

				double width = root.getWidth() - 2 * root.getImageInsets().x;
				double height = root.getHeight() - 2 * root.getImageInsets().y;

				System.out.println("Scaled released coordinates to " + actor.getLayoutX() / width + ", "
						+ actor.getLayoutY() / height);

				try {
					uihandler.updateGameObjectLocation(Integer.parseInt(actor.getId()), actor.getLayoutX() / width,
							actor.getLayoutY() / height);
				} catch (NumberFormatException | VoogaException e) {
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
	EventHandler<MouseEvent> place = new EventHandler<MouseEvent>() {
		@Override
		public void handle(final MouseEvent ME) {
			if (((MouseEvent) ME).getButton().equals(MouseButton.SECONDARY)) {
				double width = root.getWidth() - 2 * root.getImageInsets().x;
				double height = root.getHeight() - 2 * root.getImageInsets().y;

				/*
				 * if(ME.getX() < root.getImageInsets().x || ME.getX() >
				 * root.getWidth() - root.getImageInsets().x || ME.getY() <
				 * root.getImageInsets().y || ME.getY() > root.getHeight() -
				 * root.getImageInsets().y) { //throw new
				 * VoogaException("Invalid location for actor"); } else {
				 */
				System.out.println("Scaled original coordinates to " + actor.getLayoutX() / width + ", "
						+ actor.getLayoutY() / height);

				try {
					Integer actorID = uihandler.addGameObject(option, actor.getLayoutX() / width,
							actor.getLayoutY() / height);
					Object obj = ME.getSource();
					if (obj instanceof Pane) {
						((Pane) obj).removeEventHandler(MouseEvent.MOUSE_CLICKED, place);
						//((Pane) obj).removeEventHandler(MouseEvent.MOUSE_DRAGGED, drag);
						mapOfActors.put(actorID, clazz);
						actor.setId(actorID.toString());
						System.out.println(actor.getId());
					}
				} catch (NumberFormatException | VoogaException e) {
					System.out.println("Unable to add game object -- Actor ~ 111");
					e.printStackTrace();
				}
			}
		}
	};

}
