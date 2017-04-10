package ui.player.inGame;

import java.util.Optional;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import ui.general.UIHelper;
import ui.handlers.UIHandler;
import util.VoogaException;

public class Actor {

	Pane root;
	UIHandler uihandler;
	String name;
	StackPane actor;

	public StackPane getActor() {
		return actor;
	}
	public String getName(){
		return name;
	}


	public Actor(Pane root, UIHandler uihandler, Integer id, String name, Node image) {
		//how to I do optional?
		actor = UIHelper.buttonStack(clicked, null, (ImageView) image, Pos.CENTER, true);
		actor = new StackPane();
		this.root = root;
		this.uihandler = uihandler;
		this.name = name;
		setup();
	}

	public void setup(){
		setupEvents();
	}

	public void setupEvents(){
		actor.setOnMouseDragged(e -> {
			actor.setLayoutX(e.getSceneX());
			actor.setLayoutY(e.getSceneY());
		});

		actor.setOnMouseDragReleased(e -> released(e));
	}

	public void released(MouseEvent e) {
		try {
			if (obj exists already) {
				// and if the area is okay to put the stackpane down
				uihandler.updateGameObjectLocation(Integer.parseInt(actor.getId()), actor.getLayoutX(), actor.getLayoutY());
				//remove from root if can't place;
				
				//idea -- remove event handler here so can't re-drag object?
				//delete obj from make then re-add or else i'll need to know where it is on map??
			}
			else {
				uihandler.addGameObject(Integer.parseInt(actor.getId()), actor.getLayoutX(), actor.getLayoutY());
			}
		} catch (VoogaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	EventHandler<MouseEvent> clicked = new EventHandler<MouseEvent>()  {
		@Override
		public void handle( final MouseEvent ME ) {
			Object obj = ME.getSource();  // you can also try ME.getTarget()

			if ( obj instanceof Button ) {
				System.out.println("clicked");
			}
		}
	};



}
