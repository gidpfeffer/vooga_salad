package ui.player.login;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import gameengine.controllers.GameController;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.Preferences;
import ui.authoring.AuthoringView;
import ui.player.GameSelector;
import ui.player.UserDatabase;
import ui.player.XStreamFileChooser;
import ui.player.login.Login.Game;

public class LoginMain {
	private Stage stage;
	private GameController gameController;
	private UserDatabase database;
	private ResourceBundle loginResource;
	private Login loginScreen;
	public static final String userDatabase = "userDatabase.xml";
	XStream mySerializer = new XStream(new DomDriver());
	XStreamFileChooser fileChooser = new XStreamFileChooser(userDatabase);

	public LoginMain(Stage stage, String css, String resource) {
		this.stage = stage;
		setupDatabase();
		stage.setMinHeight(Preferences.SCREEN_HEIGHT);
		stage.setMinWidth(Preferences.SCREEN_WIDTH);
		loginResource = ResourceBundle.getBundle(resource);
		setupLoginScreen(css, resource);
		stage.setScene(loginScreen.getScene());
	}
	
	private void setupDatabase() {
		XStream mySerializer = new XStream(new DomDriver());
		XStreamFileChooser fileChooser = new XStreamFileChooser(userDatabase);
		database = (UserDatabase) mySerializer.fromXML(fileChooser.readInClass());
	}
	
	private void setupLoginScreen(String css, String resource) {
		loginScreen = new Login(css, resource);
		EventHandler<ActionEvent> handleSignup = new EventHandler<ActionEvent>() { 
			@Override
			public void handle(ActionEvent event){
				loginClicked();
			}
		};
		loginScreen.setLoginAction(handleSignup);
		
		EventHandler<ActionEvent> handleLogin = new EventHandler<ActionEvent>() { 
			@Override
			public void handle(ActionEvent event){
				gotoSignupPage();
			}
		};
		loginScreen.setSignupAction(handleLogin);
		
		EventHandler<ActionEvent> handleAuth = new EventHandler<ActionEvent>() { 
			@Override
			public void handle(ActionEvent event){
				gotoAuth();
			}
		};
		loginScreen.setAuthAction(handleAuth);
		
		EventHandler<ActionEvent> handleSelector = new EventHandler<ActionEvent>() { 
			@Override
			public void handle(ActionEvent event){
				gotoGameSelector();
			}
		};
		loginScreen.setSelectorAction(handleSelector);
	}
	
	private void loginClicked(){
		if (database.getPasswords().login(loginScreen.getLoginGrid().getUsername().getText(), 
				loginScreen.getLoginGrid().getPassword().getText())) 
		{
			System.out.println(loginScreen.getLoginGrid().getUsername().getText());
			showAlert(AlertType.INFORMATION, "Welcome", "Welcome, " + 
					loginScreen.getLoginGrid().getUsername().getText() + ".", "Let's Play A Game!");
			loginScreen.getActionTarget().setFill(Color.GREEN);
			loginScreen.getActionTarget().setText(loginResource.getString("successfulLogin"));
			loginScreen.getLoginGrid().getUsername().clear();
			loginScreen.getLoginGrid().getPassword().clear();
			gotoGameSelector();
		} else {
			setBadActionTarget(loginScreen.getActionTarget(), Color.FIREBRICK, 
					loginResource.getString("incorrectLogin"));
		}
	}

	private void setBadActionTarget(Text node, Color color, String error){
		node.setFill(color);
		node.setText(error);
		loginScreen.getLoginGrid().getPassword().clear();
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
		stage.setScene(new Scene(view, Preferences.SCREEN_WIDTH, Preferences.SCREEN_HEIGHT, Color.WHITE));
	}

	private void gotoSignupPage() {
		Signup signupPage = new Signup(database, loginResource, "signupScreen.css");
		stage.setScene(signupPage.getScene());
		stage.setTitle(loginResource.getString("signup"));
	}
	
	private void gotoGameSelector() {
		//TODO: Replace with actual games list
		List<Game> gamesList = new ArrayList<>(Arrays.asList(
				loginScreen.new Game("Bloons", "default_map_background_0.jpg", e -> gotoGameScreen()),
				loginScreen.new Game("Plants vs. Zombies", "plants_vs_zombies.png", e -> {}), 
				loginScreen.new Game("Asteroids", "asteroids.png", e -> {})));
		GameSelector select = new GameSelector("English", "mainScreen.css", gamesList);
		stage.setScene(select.getScene());
		stage.setTitle("GameSelector");
		stage.show();
	}

	private void gotoGameScreen() {
		gameController = new GameController();
		gameController.start(stage);
		setUpGameScreenReturn();
		stage.setScene(gameController.getGameScreen().getScene());
		stage.setWidth(Preferences.SCREEN_WIDTH);
		stage.setHeight(Preferences.SCREEN_HEIGHT);
		stage.setTitle("GameSelector");
	}
	
	private void setUpGameScreenReturn() {
		EventHandler<ActionEvent> backToLogin = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				gameController.getGameScreen().getUIHandler().stop();
				stage.setScene(loginScreen.getScene());
				stage.setTitle("Login");
				stage.setWidth(Preferences.SCREEN_WIDTH);
				stage.setHeight(Preferences.SCREEN_HEIGHT);
			}
		};
		gameController.getGameScreen().setBackToLoginAction(backToLogin);
	}
}
