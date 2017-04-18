package ui.player.inGame;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import ui.general.ToggleSwitch;
import ui.player.login.Login;

/**
 * Creates a pane for settings elements such as returning back to main, changing
 * volume, etc
 * 
 * @author anngelyque
 *
 */
public class SettingsPane {
	private Button button;
	private AnchorPane settings;
	private Hyperlink backToLogin;
	private double paneWidth = 150.;
	private List<String> helpPaneOptions = new ArrayList<>(Arrays.asList("Help", "Settings"));
	private List<Hyperlink> helpLinks = new ArrayList<>();
	private static final String panel = "panel.css";
	static final String heroSong = "data/resource/hero_song.mp3";
	private Media song;
	private MediaPlayer mediaPlayer;
	private SlidingPane sp;

	public Button getHelpButton() {
		return button;
	}

	public AnchorPane getHelpPane() {
		return settings;
	}
	
	public void setBackToLoginAction(EventHandler<ActionEvent> value) {
		backToLogin.setOnAction(value);
	}


	public SettingsPane() {
		sp = new SlidingPane();
		song = new Media(new File(heroSong).toURI().toString());
		mediaPlayer = new MediaPlayer(song);
		setup();
	}

	private void setup() {
		setupPane();
		createHelpButton();
		addHyperlinks();
	}

	/**
	 * Creates empty pane to add hyperlinks to
	 */
	private void setupPane() {
		settings = new AnchorPane();
		settings.getStylesheets().add(panel);
		settings.setPrefWidth(paneWidth);
		settings.setStyle("-fx-background-color: MediumAquamarine;" + " -fx-border-radius: 0 10 10 0;"
				+ "-fx-background-radius: 0 10 10 0;");
		addBackButton();
	}

	/**
	 * adds back button to the empty pane in the top-right corner
	 */
	private void addBackButton() {
		OptionButton back = new OptionButton(0, "", "back_icon.png", e -> sp.slidePane(settings, 0));
		settings.getChildren().add(back.getButton());
		AnchorPane.setTopAnchor(back.getButton(), 10.0);
		AnchorPane.setRightAnchor(back.getButton(), 10.0);
	}

	/**
	 * Creates a help button that on click, slides out the help pane
	 */
	private void createHelpButton() {
		OptionButton back = new OptionButton(0, "", "splash_icon.png", e -> sp.slidePane(settings, settings.getPrefWidth()));
		back.getButton().getStylesheets().add(panel);
		button = back.getButton();
	}

	/**
	 * Creates various hyperlinks needed in the pane including returning to main
	 */
	private void addHyperlinks() {
		ToggleSwitch musicToggle = new ToggleSwitch("Music");
		Hyperlink helpLink = new Hyperlink("Help");
		backToLogin = new Hyperlink("Return to Main");
		VBox helpBox = new VBox(20);
		helpBox.getChildren().addAll(backToLogin, helpLink, musicToggle.getNode());
		settings.getChildren().add(helpBox);
		helpBox.setAlignment(Pos.CENTER_LEFT);
	}
	
	private void playMusic() {
		mediaPlayer.setMute(!mediaPlayer.isMute());
	}
	

/*	EventHandler<MouseEvent> closePane = new EventHandler<MouseEvent>() {
		@Override
		public void handle(final MouseEvent ME) {
			Object obj = ME.getSource();
			if (obj instanceof Button) {
				TranslateTransition t = new TranslateTransition(Duration.seconds(0.2));
				t.setNode(settings);
				t.setToX(0);
				t.play();
			}
		}
	};

	EventHandler<MouseEvent> openPane = new EventHandler<MouseEvent>() {
		@Override
		public void handle(final MouseEvent ME) {
			Object obj = ME.getSource();
			if (obj instanceof Button) {
				TranslateTransition t = new TranslateTransition(Duration.seconds(0.2));
				t.setNode(settings);
				t.setToX(paneWidth);
				t.play();
			}
		}
	};*/

}
