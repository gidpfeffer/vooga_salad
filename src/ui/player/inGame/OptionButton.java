package ui.player.inGame;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import ui.handlers.UIHandler;

/**
 * Creates a new button for the pane of actors.
 * Open click, a new actor will be generated based open the old actor.
 * The new actor will then be added to the accessible database of actors.
 * @author Anngelyque
 *
 */
public class OptionButton {

	private Integer id;
	private String name;
	private String image;
	private Button button;
	private EventHandler<MouseEvent> pressed;
	

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

	public OptionButton(Integer id, String name, String image, EventHandler<MouseEvent> pressed) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.pressed = pressed;
		setup();
	}
	

	private void setup() {
		button = new Button(name);
		button.setId(id.toString());
		button.setBackground(Background.EMPTY);
		ImageView view = new ImageView(new Image(getClass().getResourceAsStream(image)));
		view.setPreserveRatio(true);
		button.setGraphic(view);
		button.addEventHandler(MouseEvent.MOUSE_PRESSED, pressed);
	}

}
