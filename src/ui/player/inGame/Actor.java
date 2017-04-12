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
		actor.addEventHandler(MouseEvent.MOUSE_RELEASED, released);
		//actor.setOnMouseReleased(e -> System.out.println("sdfjsdkljflksdf"));//released(e));
	}

	EventHandler<MouseEvent> released = new EventHandler<MouseEvent>()  {
		@Override
		public void handle( final MouseEvent ME ) {
				try {
					System.out.println("releasing");
					//need to have list here
					double width = root.getWidth() - 2*root.getImageInsets().x;
					double height = root.getHeight() - 2*root.getImageInsets().y;
					if (mapOfActors.get(actor) != null) {
						// and if the area is okay to put the stackpane down
						
						System.out.println("Scaled coordinates to " + actor.getLayoutX()/width + ", " 
								+ actor.getLayoutY()/height);
						
						uihandler.updateGameObjectLocation(Integer.parseInt(actor.getId()), 
								actor.getLayoutX()/width, actor.getLayoutY()/height);
						//remove from root if can't place;
						
						//idea -- remove event handler here so can't re-drag object?
						//delete obj from make then re-add or else i'll need to know where it is on map??
					}
					else {
						//need to know size of screen here
						if(ME.getX() < root.getImageInsets().x || 
								ME.getX() > root.getWidth() - root.getImageInsets().x || 
								ME.getY() < root.getImageInsets().y || 
								ME.getY() > root.getHeight() - root.getImageInsets().y) {
							throw new VoogaException("Invalid location for actor");
						} 
						else {
							System.out.println("Scaled coordinates to " + actor.getLayoutX()/width + ", " 
									+ actor.getLayoutY()/height);
							
							uihandler.addGameObject(Integer.parseInt(actor.getId()), 
									actor.getLayoutX()/width, actor.getLayoutY()/height);
						}
					}
				} catch (VoogaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
		}
	};
	
}





