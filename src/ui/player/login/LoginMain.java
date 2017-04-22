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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.Preferences;
import ui.authoring.AuthoringView;
import ui.handlers.LoginHandler;
import ui.player.GameSelector;
import ui.player.XStreamFileChooser;
import ui.player.login.Login.Game;
import ui.player.users.ProfileCard;
import ui.player.users.User;
import ui.player.users.UserDatabase;

public class LoginMain {
	private Stage stage;
	private GameController gameController;
	private UserDatabase database;
	private ResourceBundle loginResource;
	private Login loginScreen;
	private Signup signupPage;
	private LoginHandler loginhandler;
	public static final String userDatabase = "userDatabase.xml";

	public LoginMain(Stage stage, String css, String resource) {
		this.stage = stage;
		setupDatabase();
		setupLoginHandler();
		stage.setMinHeight(Preferences.SCREEN_HEIGHT);
		stage.setMinWidth(Preferences.SCREEN_WIDTH);
		loginResource = ResourceBundle.getBundle(resource);
		setupLoginScreen(css, resource);
		stage.setScene(loginScreen.getScene());
	}
	
	private void setupLoginHandler() {
		this.loginhandler = new LoginHandler() {
			@Override
			public User getActiveUser() {
				return database.getActiveUser();
			}
			
			@Override
			public void showProfile() {
				showProfileCard(getActiveUser());
			}
		};
	}
	
	private void setupDatabase() {
		try {
			XStream mySerializer = new XStream(new DomDriver());
			XStreamFileChooser fileChooser = new XStreamFileChooser(userDatabase);
			database = (UserDatabase) mySerializer.fromXML(fileChooser.readInClass());
		} catch (Exception e) {
			database = new UserDatabase();
		}
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
	
	private void setUpSignupPage() {
		EventHandler<ActionEvent> signupAction = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				signupPage.getAction().handle(e);
				stage.setScene(loginScreen.getScene());
			}
		};
		signupPage.setLoginReturn(signupAction);
	}
	
	
	//Action listeners ------------------------------------------------------------------------------
	private void loginClicked(){
		if (database.getPasswords().login(loginScreen.getLoginGrid().getUsername().getText(), 
				loginScreen.getLoginGrid().getPassword().getText())) 
		{
			User user = null;
			for (User u : database.getDatabase()) {
				if (u.getUsername().equals(loginScreen.getLoginGrid().getUsername().getText())) {
					user = u;
					break;
				}
			}
			//showProfileCard(user);
			loginScreen.transitionToLoggedIn();
			database.setActiveUser(user);
			loginScreen.getLoginGrid().getUsername().clear();
			//loginScreen.getRoot().getChildren().add(new ImageView(new Image(user.getProfilePicture(), 50, 50, false, true)));
			//gotoGameSelector();
		} else {
			setBadActionTarget(loginScreen.getActionTarget(), Color.WHITE, 
					loginResource.getString("incorrectLogin"));
		}
		loginScreen.getLoginGrid().getPassword().clear();
	}
	
	private void showProfileCard(User user) {
		ProfileCard card = new ProfileCard("profile", user, "profile.css");
		HBox hb = card.getCard();
		((Pane) stage.getScene().getRoot()).getChildren().add(hb);
	}

	private void setBadActionTarget(Text node, Color color, String error){
		node.setFill(color);
		node.setText(error);
		FadeTransition fade = createFader(node);
		fade.play();
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
		signupPage = new Signup(database, loginResource, "signupScreen.css");
		setUpSignupPage();
		stage.setScene(signupPage.getScene());
		stage.setTitle(loginResource.getString("signup"));
	}
	
	private void gotoGameSelector() {
		//TODO: Replace with actual games list
		List<Game> gamesList = new ArrayList<>(Arrays.asList(
				loginScreen.new Game("Bloons", "default_map_background_0.jpg", e -> gotoGameScreen()),
				loginScreen.new Game("Plants vs. Zombies", "plants_vs_zombies.png", e -> {}), 
				loginScreen.new Game("Asteroids", "asteroids.png", e -> {})));
		GameSelector select = new GameSelector(loginhandler, "English", "mainScreen.css", gamesList);
		stage.setScene(select.getScene());
		stage.setTitle("Game Selector");
		stage.show();
	}

	private void gotoGameScreen() {
		gameController = new GameController();
		gameController.start(stage);
		setUpGameScreenReturn();
		stage.setScene(new Scene(gameController.getGameScreen(), Preferences.SCREEN_WIDTH, Preferences.SCREEN_HEIGHT, Color.WHITE));
		stage.setTitle("Game Screen");
	}
	
	private void setUpGameScreenReturn() {
		EventHandler<ActionEvent> gameScreenHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				gameController.getGameScreen().getAction();
				stage.setScene(loginScreen.getScene());
				stage.setTitle("Login");
				stage.setWidth(Preferences.SCREEN_WIDTH);
				stage.setHeight(Preferences.SCREEN_HEIGHT);
			}
		};
		gameController.getGameScreen().setLoginReturn(gameScreenHandler);
	}
}
