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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.Preferences;
import ui.authoring.AuthoringView;
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
	public static final String userDatabase = "userDatabase.xml";

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
				System.out.println(u.getUsername());
				System.out.println(loginScreen.getLoginGrid().getUsername().getText());
				if (u.getUsername().equals(loginScreen.getLoginGrid().getUsername().getText())) {
					user = u;
					break;
				}
			}

			//User user = database.getDatabase().stream().filter(u -> u.equals(loginScreen.getLoginGrid().getUsername()));
			ProfileCard card = new ProfileCard("profile", user, "profile.css");
			HBox hb = card.getCard();
			loginScreen.getRoot().getChildren().add(hb);
			//showAlert(AlertType.INFORMATION, "Welcome", "Welcome, " + 
			//		loginScreen.getLoginGrid().getUsername().getText() + ".", "Let's Play A Game!");
			loginScreen.getActionTarget().setFill(Color.GREEN);
			loginScreen.getActionTarget().setText(loginResource.getString("successfulLogin"));
			loginScreen.getLoginGrid().getUsername().clear();
			//gotoGameSelector();
		} else {
			setBadActionTarget(loginScreen.getActionTarget(), Color.WHITE, 
					loginResource.getString("incorrectLogin"));
		}
		loginScreen.getLoginGrid().getPassword().clear();
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
