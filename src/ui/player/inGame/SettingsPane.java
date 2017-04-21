package ui.player.inGame;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.media.MediaPlayer;
import javafx.scene.layout.VBox;
import ui.general.ToggleSwitch;

/**
 * Creates a pane for settings elements such as returning back to main, changing
 * volume, etc
 * 
 * @author anngelyque
 *
 */
public class SettingsPane extends SlidingPane{
	private Hyperlink backToLogin;
	private double paneWidth = 150.;
	
	

	public void setBackToLoginAction(EventHandler<ActionEvent> value) {
		backToLogin.setOnAction(value);
	}

	public SettingsPane(Optional<String> backImage, double slideTo) {
		super(backImage, slideTo, Corner.TOPRIGHT);
		setup();
	}

	private void setup() {
		setupPane();
		addHelp();
	}
	
	private void setupPane() {
		this.setStyle("-fx-background-color: MediumAquamarine;" + " -fx-border-radius: 0 10 10 0;"
				+ "-fx-background-radius: 0 10 10 0;");
		this.setPrefWidth(paneWidth);
		this.setId("settings");
	}

	private void addHelp() {
		//ToggleSwitch musicToggle = new ToggleSwitch("Music");
		Hyperlink helpLink = new Hyperlink("Help");
		backToLogin = new Hyperlink("Return to Main");
		
		getVBox().getChildren().addAll(backToLogin, helpLink);
		this.getChildren().add(getVBox());
		getVBox().setAlignment(Pos.CENTER_LEFT);
		//mp = new MusicPlayer(musicToggle, Optional.ofNullable(null));
	}
	
	public void addObject(Node object) {
		getVBox().getChildren().add(object);
	}

	
}
