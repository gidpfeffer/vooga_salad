package ui.player.inGame;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;
import ui.general.ImageViewPane;
import ui.handlers.LoginHandler;
import ui.handlers.UIHandler;
import util.VoogaException;

public class GenericGameScreen extends AnchorPane{

	private UIHandler uihandler;
	private LoginHandler loginhandler;
	private Optional<String> songString;
	private Optional<String> css;
	private Optional<String> backgroundImage;
	private SettingsPane settingsPane;
	private ImageViewPane ivp;
	public static final String backIcon = "splash_icon.png";
	public static final String cssPath = "panel.css";
	public static final String backgroundImagePath = "default_map_background_0.jpg";
	private MusicPlayer musicPlayer; 
	
	public ImageViewPane getIVP() {
		return ivp;
	}
	
	public GenericGameScreen(UIHandler uihandler, Optional<String> songString, Optional<String> css, 
			Optional<String> backgroundImage, LoginHandler loginhandler) {
		this.uihandler = uihandler;
		this.loginhandler = loginhandler;
		this.songString = songString;
		this.css = css;
		this.backgroundImage = backgroundImage;
		setup();
	}
	
	private void setup(){
		setupBackground();
		addSettings();
		addStartLevelButton();
		settingsPane.setBackToLoginAction(returnToMain());
		//addAnimationButtons();
	}

	private EventHandler<ActionEvent> returnToMain() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				uihandler.stop();
				loginhandler.returnToMain();
				System.out.println(musicPlayer.getMediaPlayer().getStatus());
				if(musicPlayer.getMediaPlayer().getStatus().equals(Status.PLAYING)) {
					musicPlayer.getMediaPlayer().stop();
				}
			}
		};
	}
	
	private void setupBackground() {
		ivp = new ImageViewPane(new ImageView(new Image(backgroundImage.orElse(backgroundImagePath))));
		this.getChildren().add(ivp);
		setNodeInAnchorPane(ivp);
	}
	
	private void addSettings() {
		musicPlayer = new MusicPlayer(Optional.ofNullable(null));
		settingsPane = new SettingsPane(this, songString, 0, Optional.ofNullable(null));
		settingsPane.addObject(musicPlayer.getNode());
		OptionButton helpButton = new OptionButton(0, "", backIcon, e -> settingsPane.slidePane(settingsPane, settingsPane.getPrefWidth()));
		AnchorPane.setLeftAnchor(helpButton.getButton(), 10.);
		AnchorPane.setTopAnchor(helpButton.getButton(), 10.);
		this.getChildren().addAll(helpButton.getButton(), settingsPane);
		settingsPane.setLayoutX(-settingsPane.getPrefWidth());
	}
	
	private void addStartLevelButton() {
		OptionButton startLevel = new OptionButton(0, "", "power_icon.png", e -> {
			try {
				uihandler.launchGame();
			} catch (VoogaException e1) {
				new VoogaException("Could not change levels ~ 105 GenericGame");
			}
		});
		startLevel.getButton().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> transitionButtons(startLevel.getButton()));
		setRightBottom(startLevel.getButton());
	}
	
	private void transitionButtons(Node node) {
		 ScaleTransition st = new ScaleTransition(Duration.millis(1000), node);
	     st.setByX(-1f);
	     st.setByY(-1f);
	     st.play();
	     st.setOnFinished(e -> playPauseStop());
	}
	
	private void playPauseStop() {
		Map<String, EventHandler<MouseEvent>> animationIcons = addIcons();
		addAnimationButtons(animationIcons);
	}
	
	private void addAnimationButtons(Map<String, EventHandler<MouseEvent>> iconList) {
		HBox animationButtons = new HBox(20);
		animationButtons.setPadding(new Insets(10,10,10,10));
		animationButtons.getStylesheets().add(css.orElse(cssPath));
		iconList.keySet().forEach(icon -> {
			OptionButton animationButton = new OptionButton(0, "", icon, iconList.get(icon));
			animationButtons.getChildren().addAll(animationButton.getButton());
		});
		setRightBottom(animationButtons);
	}
	
	private void setRightBottom(Node node) {
		this.getChildren().add(node);
		AnchorPane.setBottomAnchor(node, 10.0);
		AnchorPane.setRightAnchor(node, 10.0);
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
