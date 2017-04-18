package ui.player.login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import gameengine.controllers.GameController;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.*;
import ui.authoring.AuthoringView;
import ui.general.UIHelper;
import ui.player.GameSelector;
import ui.player.UserDatabase;
import ui.player.XStreamFileChooser;

public class Login extends BorderedAnchorPane{
	private Stage stage;
	private Scene scene;
	private GameController gameController;
	private UserDatabase database;
	
	private String css;
	private GridPane gridPane;
	private LoginGrid login;
	private ResourceBundle loginResource;

	public Scene getScene() {
		return scene;
	}

	public Login(UserDatabase database, Stage stage, String css, String resource) {
		this.stage = stage;
		this.css = css;
		this.database = database;
		stage.setMinHeight(Preferences.SCREEN_HEIGHT);
		stage.setMinWidth(Preferences.SCREEN_WIDTH);
		loginResource = ResourceBundle.getBundle(resource);
		gridPane = new GridPane();
		setup();
	}

	public void setup() {
		setupLayout();
		setupTitle();
		setupLoginNewAccountTitle();
		setupLoginGrid();
		setupButtons();
		setupAltButtons();
		setupCenter();
	}

	private void setupLayout() {
		gridPane.setHgap(50);
		gridPane.setVgap(20);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.getStyleClass().add("grid");
		root.getStyleClass().add("anchor-pane");
		root.setId("towerBackground");
		scene = new Scene(root);
		scene.getStylesheets().add(css);
	}
	
	private void setupTitle(){
		Label towerDefenseTitle = new Label(loginResource.getString("towerDefense"));
		towerDefenseTitle.setPadding(new Insets(10., 0., 0., 0.));
		towerDefenseTitle.setId("title");
		borderPane.setTop(towerDefenseTitle);
		BorderPane.setAlignment(towerDefenseTitle, Pos.CENTER);
	}

	private void setupLoginNewAccountTitle() {
		Label welcomeBackTitle = new Label(loginResource.getString("welcomeBack"));
		gridPane.add(welcomeBackTitle, 0, 0);
		GridPane.setHalignment(welcomeBackTitle, HPos.CENTER);
	}

	private void setupLoginGrid(){
		login = new LoginGrid(loginResource);
		login.getGrid().getStyleClass().add("grid");
		gridPane.add(login.getGrid(), 0, 2);
	}

	private void setupButtons(){
		Button loginEnter = new Button(loginResource.getString("login"));
		Hyperlink signupEnter = new Hyperlink(loginResource.getString("signup"));
		//UIHelper.setDropShadow(loginEnter);
		gridPane.add(loginEnter, 0, 3);
		gridPane.add(signupEnter, 0, 4);
		GridPane.setHalignment(loginEnter, HPos.CENTER);
		GridPane.setHalignment(signupEnter, HPos.CENTER);
		final Text actiontarget = new Text();
		gridPane.add(actiontarget, 0, 5);

		loginEnter.setOnAction(e -> loginClicked(actiontarget));
		signupEnter.setOnAction(e -> gotoSignupPage());
	}

	private void setupAltButtons(){
		Button auth = new Button(loginResource.getString("gotoAuth"));
		Button selector = new Button(loginResource.getString("gotoSelector"));
		auth.setOnAction(e -> gotoAuth());
		UIHelper.setDropShadow(auth);
		UIHelper.setDropShadow(selector);
		selector.setOnAction(e -> gotoGameSelector());
		HBox hbox = new HBox(100, auth, selector);
		borderPane.setBottom(hbox);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(0., 0., 30., 0.));
	}

	private void setupCenter(){
		StackPane top = new StackPane();
		top.getStyleClass().add("stack-pane");
		StackPane.setMargin(top, new Insets(0., 300., 30., 300.));
		StackPane sp = new StackPane(top);
		sp.setBackground(Background.EMPTY);
		sp.getChildren().add(gridPane);
		borderPane.setCenter(sp);
	}
	
	private void loginClicked(Text actiontarget){
		if (database.getPasswords().login(login.getUsername().getText(), login.getPassword().getText())) {
			System.out.println(login.getUsername().getText());
			showAlert(AlertType.INFORMATION, "Welcome", "Welcome, " + login.getUsername().getText() + ".",
					"Let's Play A Game!");
			actiontarget.setFill(Color.GREEN);
			actiontarget.setText(loginResource.getString("successfulLogin"));
			login.getUsername().clear();
			login.getPassword().clear();
			gotoGameSelector();
		} else {
			setBadActionTarget(actiontarget, Color.FIREBRICK, loginResource.getString("incorrectLogin"));
		}
	}


	private void setBadActionTarget(Text node, Color color, String error){
		node.setFill(color);
		node.setText(error);
		login.getPassword().clear();
		FadeTransition fade = createFader(node);
		fade.play();
	}
	
	private void showAlert(AlertType type, String title, String heading, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(heading);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
    private FadeTransition createFader(Node node) {
        FadeTransition fade = new FadeTransition(Duration.millis(2000), node);
        fade.setFromValue(1);
        fade.setToValue(0);

        return fade;
    }

	private void gotoAuth() {
 		AuthoringView view = new AuthoringView();
		Stage s = (Stage) scene.getWindow();
		s.setScene(new Scene(view, Preferences.SCREEN_WIDTH, Preferences.SCREEN_HEIGHT, Color.WHITE));
	}

	private void gotoSignupPage() {
		Signup signupPage = new Signup(database, loginResource, "signupScreen.css");
		stage.setScene(signupPage.getScene());
		stage.setTitle(loginResource.getString("signup"));
	}
	
	private void gotoGameSelector() {
		//TODO: Replace with actual games list
/*		ArrayList<Button> gamesList = new ArrayList<Button>();
		gamesList.add(new Button("Sample Game"));
		gamesList.add(new Button("Also Sample Game"));
		*/
		List<Game> gamesList = new ArrayList<>(Arrays.asList(new Game("Bloons", "default_map_background_0.jpg", e -> gotoGameScreen()),
				new Game("Plants vs. Zombies", "plants_vs_zombies.png", e -> {}), new Game("Asteroids", "asteroids.png", e -> {})));
		GameSelector select = new GameSelector("English", "mainScreen.css", gamesList);
		stage.setScene(select.getScene());
		stage.setTitle("GameSelector");
		stage.show();
		//gotoGameScreen();
	}
	
	public class Game{
		String name;
		String imagePath;
		EventHandler<MouseEvent> clicked;
		
		public String getName() {
			return name;
		}

		public String getImagePath() {
			return imagePath;
		}

		public EventHandler<MouseEvent> getClicked() {
			return clicked;
		}

		public Game(String name, String imagePath, EventHandler<MouseEvent> clicked){
			this.name = name;
			this.imagePath = imagePath;
			this.clicked = clicked;
		}
	}

	private void gotoGameScreen() {
		gameController = new GameController();
		gameController.start(stage);
		stage.setScene(gameController.getGameScreen().getScene());
		stage.setTitle("GameSelector");
	}
}