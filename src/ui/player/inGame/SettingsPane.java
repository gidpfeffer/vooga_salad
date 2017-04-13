package ui.player.inGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gameengine.controllers.GameController;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.handlers.UIHandler;
import ui.player.Login;

/**
 * Creates a pane for settings elements such as returning back to main, changing volume, etc
 * @author anngelyque
 *
 */
public class SettingsPane {

	private Stage stage;
	private Button button;
	private AnchorPane settings;
	private double paneWidth = 150.;
	private List<String> helpPaneOptions = new ArrayList<>(Arrays.asList("Help", "Settings"));
	private List<Hyperlink> helpLinks = new ArrayList<>();
	private static final String panel = "panel.css";
	private static final String loginCSS = "loginScreen.css";
	private static final String loginBundle = "login";

	public Button getHelpButton() {
		return button;
	}

	public AnchorPane getHelpPane() {
		return settings;
	}

	public SettingsPane(Stage myStage) {
		this.stage = myStage;
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
		OptionButton back = new OptionButton(0, "", "back_icon.png", closePane);
		settings.getChildren().add(back.getButton());
		AnchorPane.setTopAnchor(back.getButton(), 10.0);
		AnchorPane.setRightAnchor(back.getButton(), 10.0);
	}

	/**
	 * Creates a help button that on click, slides out the help pane
	 */
	private void createHelpButton() {
		OptionButton back = new OptionButton(0, "", "splash_icon.png", openPane);
		back.getButton().getStylesheets().add(panel);
		button = back.getButton();
	}

	/**
	 * Creates various hyperlinks needed in the pane including returning to main
	 */
	private void addHyperlinks() {
		Hyperlink backToLogin = new Hyperlink("Return to Main");
		backToLogin.setOnAction(e -> returnToMain());
		helpPaneOptions.forEach(name -> helpLinks.add(new Hyperlink(name)));
		VBox helpBox = new VBox(20);
		helpLinks.forEach(link -> helpBox.getChildren().add(link));
		helpBox.getChildren().add(backToLogin);
		settings.getChildren().add(helpBox);
		helpBox.setAlignment(Pos.CENTER_LEFT);
	}

	private void returnToMain() {
		System.out.println("here");
		Login login = new Login(stage, loginCSS, loginBundle);
		stage.setScene(login.getScene());
		stage.setTitle("Login");
	}

	EventHandler<MouseEvent> closePane = new EventHandler<MouseEvent>() {
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
	};

}
