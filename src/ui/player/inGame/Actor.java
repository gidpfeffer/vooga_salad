package ui.player.inGame;

import java.util.Map;
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
import ui.general.ImageViewPane;
import ui.general.UIHelper;
import ui.handlers.UIHandler;
import util.VoogaException;

public class Actor {

	ImageViewPane root;
	UIHandler uihandler;
	String name;
	Pane actor;
	Map<Integer, Actor> mapOfActors;

	public Pane getActor() {
		return actor;
	}
	public String getName(){
		return name;
	}


	public Actor(ImageViewPane root, UIHandler uihandler, Map<Integer, Actor> mapOfActors, Integer id, String name, String image) {
		
		actor = UIHelper.buttonStack(e->{}, Optional.ofNullable(null), Optional.of(new ImageView(new Image(image, 30, 30, true, true))), Pos.CENTER, true);
		actor.setBackground(Background.EMPTY);
		actor.setId(id.toString());
		this.root = root;
		this.uihandler = uihandler;
		this.name = name;
		this.mapOfActors = mapOfActors;
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

		actor.setOnMouseClicked(e -> released(e));
	}

	public void released(MouseEvent e) {
		try {
			System.out.println("releasing");
			//need to have list here
			if (mapOfActors.get(actor) != null) {
				// and if the area is okay to put the stackpane down
				uihandler.updateGameObjectLocation(Integer.parseInt(actor.getId()), actor.getLayoutX(), actor.getLayoutY());
				//remove from root if can't place;
				
				//idea -- remove event handler here so can't re-drag object?
				//delete obj from make then re-add or else i'll need to know where it is on map??
			}
			else {
				//need to know size of screen here
/*				if(e.getX() < root.getImageInsets().x || 
						e.getX() > root.getWidth() - root.getImageInsets().x || 
						e.getY() < root.getImageInsets().y || 
						e.getY() > root.getHeight() - root.getImageInsets().y) {
					throw new VoogaException("Invalid location for actor");
				} 
				else {*/
					uihandler.addGameObject(Integer.parseInt(actor.getId()), 
							actor.getLayoutX(), actor.getLayoutY());
				//}
			}
		} catch (VoogaException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}
