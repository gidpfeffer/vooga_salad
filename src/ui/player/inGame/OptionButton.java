package ui.player.inGame;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;

public class OptionButton {

	private EventHandler<MouseEvent> eventClicked;
	private EventHandler<MouseEvent> eventDragged;
	private EventHandler<DragEvent> eventDraggedDone;
	private Integer id;
	private String name;
	private String image;
	private Button button;
	
	public EventHandler<MouseEvent> getEventClicked() {
		return eventClicked;
	}
	
	public EventHandler<MouseEvent> getEventDragged() {
		return eventDragged;
	}
	
	public EventHandler<DragEvent> getEventDraggedDone() {
		return eventDraggedDone;
	}

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

	
	public OptionButton(Integer id, String name, String image,
			EventHandler<MouseEvent> dragged, EventHandler<DragEvent> done) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.eventClicked = (e -> doNothing());
		this.eventDragged = dragged;
		this.eventDraggedDone = done;
		setup();
	}
	
	public OptionButton(Integer id, String name, String image, EventHandler<MouseEvent> clicked) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.eventClicked = clicked;
		this.eventDragged = (e -> doNothing());
		this.eventDraggedDone = (e -> doNothing());
		setup();
	}
	
	public OptionButton(Integer id, String name, String image, EventHandler<MouseEvent> clicked,
			EventHandler<MouseEvent> dragged, EventHandler<DragEvent> done) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.eventClicked = clicked;
		this.eventDragged = dragged;
		this.eventDraggedDone = done;
		setup();
	}

	private void setup() {
		button = new Button(name);
		button.setId(id.toString());
		button.setBackground(Background.EMPTY);
		ImageView view = new ImageView(new Image(getClass().getResourceAsStream(image)));
		view.setPreserveRatio(true);
		button.setGraphic(view);
		button.setOnMouseClicked(eventClicked);
		button.setOnDragDetected(eventDragged);
		button.setOnDragDone(eventDraggedDone);
	}
	
	private void doNothing() {
		
	}

}
