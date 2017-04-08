package ui.player.inGame;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import ui.handlers.UIHandler;

public class OptionButton {

/*	private EventHandler<MouseEvent> eventClicked;
	private EventHandler<MouseEvent> eventDragged;
	private EventHandler<DragEvent> eventDraggedDone;*/
	private Integer id;
	private String name;
	private String image;
	private Button button;
	private UIHandler uihandler;
	
/*	public EventHandler<MouseEvent> getEventClicked() {
		return eventClicked;
	}
	
	public EventHandler<MouseEvent> getEventDragged() {
		return eventDragged;
	}
	
	public EventHandler<DragEvent> getEventDraggedDone() {
		return eventDraggedDone;
	}*/

	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public String getImage() {
		return image;
	}

	public Button getButton() {
		return button;
	}

	public OptionButton(Integer id, String name, String image, UIHandler uihandler) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.uihandler = uihandler;
		setup();
	}
	
/*	public OptionButton(Integer id, String name, String image,
			EventHandler<MouseEvent> dragged, EventHandler<DragEvent> done) {
		this(id, name, image);
		this.eventClicked = (e -> {});
		this.eventDragged = dragged;
		this.eventDraggedDone = done;
		setup();
	}
	
	public OptionButton(Integer id, String name, String image, EventHandler<MouseEvent> clicked) {
		this(id, name, image);
		this.eventClicked = clicked;
		this.eventDragged = (e -> {});
		this.eventDraggedDone = (e -> {});
		setup();
	}
	
	public OptionButton(Integer id, String name, String image, EventHandler<MouseEvent> clicked,
			EventHandler<MouseEvent> dragged, EventHandler<DragEvent> done) {
		this(id, name, image);
		this.eventClicked = clicked;
		this.eventDragged = dragged;
		this.eventDraggedDone = done;
		setup();
	}*/

	private void setup() {
		button = new Button(name);
		button.setId(id.toString());
		button.setBackground(Background.EMPTY);
		ImageView view = new ImageView(new Image(getClass().getResourceAsStream(image)));
		view.setPreserveRatio(true);
		button.setGraphic(view);
		button.addEventHandler(MouseEvent.MOUSE_PRESSED, pressed);
/*		button.setOnMouseClicked(eventClicked);
		button.setOnDragDetected(eventDragged);
		button.setOnDragDone(eventDraggedDone);*/
	}
	
	EventHandler<MouseEvent> pressed = new EventHandler<MouseEvent>()  {
	    @Override
	    public void handle( final MouseEvent ME ) {
	        Object obj = ME.getSource();

	        if ( obj instanceof Button ) {
	            Button newButtonInstance = new Button();
	            newButtonInstance.setId(((Button) obj).getId());
	            newButtonInstance.setText(((Button) obj).getText());
	            newButtonInstance.setGraphic(((Button) obj).getGraphic());
	            MainPaneRootHere.getChildren().add(newButtonInstance);
	            // this code drags the button
	            newButtonInstance.setOnMouseDragged(e -> {
	            	newButtonInstance.setLayoutX(e.getSceneX());
	            	newButtonInstance.setLayoutY(e.getSceneY());
	             });
	            newButtonInstance.setOnMouseReleased(e -> {
	            	uihandler.addGameObject((Integer.parseInt(((Button) obj).getId())), 
	            			newButtonInstance.getLayoutX(), newButtonInstance.getLayoutY());
	            });
	        }
	    }
	};

}
