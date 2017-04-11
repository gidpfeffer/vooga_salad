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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import ui.general.UIHelper;
import ui.handlers.UIHandler;
import util.VoogaException;

public class Actor {

	Pane root;
	UIHandler uihandler;
	String name;
	Pane actor;

	public Pane getActor() {
		return actor;
	}
	public String getName(){
		return name;
	}


	public Actor(Pane root, UIHandler uihandler, Integer id, String name, String image) {
		
		actor = UIHelper.buttonStack(e->{}, Optional.ofNullable(null), Optional.of(new ImageView(new Image(image, 30, 30, true, true))), Pos.CENTER, true);
		//actor = new StackPane();
		actor.setBackground(Background.EMPTY);
		//actor.setStyle("-fx-background-color: blue");
		//actor.getChildren().add(new Text("help"));
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
			System.out.println("releasing");
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
	
}
