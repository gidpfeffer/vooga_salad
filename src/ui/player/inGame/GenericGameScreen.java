package ui.player.inGame;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import ui.general.ImageViewPane;
import ui.handlers.UIHandler;

public class GenericGameScreen extends AnchorPane{

	private UIHandler uihandler;
	private Optional<String> songString;
	private Optional<String> css;
	private Optional<String> backgroundImage;
	private SettingsPane settingsPane;
	private ImageViewPane ivp;
	public static final String backIcon = "splash_icon.png";
	public static final String cssPath = "panel.css";
	public static final String backgroundImagePath = "default_map_background_0.jpg";
	
	public ImageViewPane getIVP() {
		return ivp;
	}
	
	public MediaPlayer getMediaPlayer() {
		return settingsPane.getMediaPlayer();
	}
	
	public void setLoginReturn(EventHandler<ActionEvent> value) {
		settingsPane.setBackToLoginAction(value);
	}
	
	public GenericGameScreen(UIHandler uihandler, Optional<String> songString, Optional<String> css, Optional<String> backgroundImage) {
		this.uihandler = uihandler;
		this.songString = songString;
		this.css = css;
		this.backgroundImage = backgroundImage;
		setup();
	}
	
	private void setup(){
		setupBackground();
		addSettings();
		addAnimationButtons();
	}

	private void setupBackground() {
		ivp = new ImageViewPane(new ImageView(new Image(backgroundImage.orElse(backgroundImagePath))));
		this.getChildren().add(ivp);
		setNodeInAnchorPane(ivp);
	}
	
	private void addSettings() {
		settingsPane = new SettingsPane(songString, 0);
		OptionButton helpButton = new OptionButton(0, "", backIcon, e -> settingsPane.slidePane(settingsPane, settingsPane.getPrefWidth()));
		AnchorPane.setLeftAnchor(helpButton.getButton(), 10.);
		AnchorPane.setTopAnchor(helpButton.getButton(), 10.);
		this.getChildren().addAll(helpButton.getButton(), settingsPane);
		settingsPane.setLayoutX(-settingsPane.getPrefWidth());
	}
	
	private void addAnimationButtons() {
		HBox animationButtons = new HBox(20);
		animationButtons.setPadding(new Insets(10,10,10,10));
		animationButtons.getStylesheets().add(css.orElse(cssPath));
		Map<String, EventHandler<MouseEvent>> animationIcons = addIcons();
		animationIcons.keySet().forEach(icon -> {
			OptionButton animationButton = new OptionButton(0, "", icon, animationIcons.get(icon));
			animationButtons.getChildren().addAll(animationButton.getButton());
		});
		this.getChildren().add(animationButtons);
		AnchorPane.setBottomAnchor(animationButtons, 10.0);
		AnchorPane.setRightAnchor(animationButtons, 10.0);
	}
	
	private Map<String, EventHandler<MouseEvent>> addIcons() {
		Map<String, EventHandler<MouseEvent>> animationIcons = new LinkedHashMap<>();
		animationIcons.put("play_icon.png", e -> uihandler.play());
		animationIcons.put("pause_icon.png", e -> uihandler.pause());
		animationIcons.put("stop_icon.png", e -> uihandler.stop());
		return animationIcons;
	}

	private void setNodeInAnchorPane(Node node) {
		AnchorPane.setBottomAnchor(node, 0.0);
		AnchorPane.setTopAnchor(node, 0.0);
		AnchorPane.setLeftAnchor(node, 0.0);
		AnchorPane.setRightAnchor(node, 0.0);
	}
}
