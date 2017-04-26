package ui.player.login;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import XML.xmlmanager.classes.XStreamSerializer;
import XML.xmlmanager.interfaces.serialization.VoogaSerializer;
import gamedata.GameData;
import gameengine.controllers.GameController;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ui.Preferences;
import ui.authoring.AuthoringView;
import ui.handlers.LoginHandler;
import ui.player.GameSelector;
import ui.player.ProfileCornerPicture;
import ui.player.XStreamFileChooser;
import ui.player.login.Login.Game;
import ui.player.users.ProfileCard;
import ui.player.users.User;
import ui.player.users.UserDatabase;
import util.FileSelector;

public class LoginMain {
	
	private Stage stage;
	private GameController gameController;
	private UserDatabase database;
	private ResourceBundle loginResource;
	private Login loginScreen;
	private Signup signupPage;
	private LoginHandler loginhandler;
	private String css;
	private String resource;
	public static final String userDatabase = "userDatabase.xml";
	public static final String CONFIG_EXTENSION = "*.xml";
	
	public LoginMain(Stage stage, String css, String resource) {
		this.stage = stage;
		this.css = css;
		this.resource = resource;
		setupDatabase();
		setupLoginHandler();
		stage.setMinHeight(Preferences.SCREEN_HEIGHT);
		stage.setMinWidth(Preferences.SCREEN_WIDTH);
		loginResource = ResourceBundle.getBundle(resource);
		loginScreen = new Login(loginhandler, css, resource);
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
			
			@Override
			public void returnToMain() {
				stage.setScene(loginScreen.getScene());
				stage.setTitle("Login");
				stage.setWidth(Preferences.SCREEN_WIDTH);
				stage.setHeight(Preferences.SCREEN_HEIGHT);
			}
			
			@Override
			public void gotoSignupPage() {
				signupPage = new Signup(loginhandler, database, loginResource, "signupScreen.css");
				stage.setScene(signupPage.getScene());
				stage.setTitle(loginResource.getString("signup"));
			}
			
			@Override
			public Boolean login(String username, String password) {
				return database.getPasswords().login(username, password);
			}
			
			@Override
			public User findUser(String username) {
				for (User u : database.getDatabase()) {
					if (u.getUsername().equals(username)) {
						return u;
					}
				}
				return null;
			}
			
			@Override
			public void setActiveUser(User user) {
				database.setActiveUser(user);
			}
			
			@Override
			public void gotoAuth(){ 
			 	AuthoringView view = new AuthoringView(loginhandler);
				stage.setScene(new Scene(view, Preferences.SCREEN_WIDTH, Preferences.SCREEN_HEIGHT, Color.WHITE));
			}
			
			@Override
			public void gotoGameSelector() {
				//TODO: Replace with actual games list
				List<Game> gamesList = new ArrayList<>(Arrays.asList(
						loginScreen.new Game("Bloons", "default_map_background_0.jpg", e -> {}),
						loginScreen.new Game("Plants vs. Zombies", "plants_vs_zombies.png", e -> {}), 
						loginScreen.new Game("Asteroids", "asteroids.png", e -> {}),
						//file path
						loginScreen.new Game("Load Custom Game","black.jpg",e -> promptUserToChooseGame())));
				GameSelector select = new GameSelector(loginhandler, "English", "mainScreen.css", gamesList);
				stage.setScene(select.getScene());
				stage.setTitle("Game Selector");
				stage.show();
			}
		
			@Override
			public void setCornerProfileCard(User user) {
				ProfileCornerPicture cornerCard = new ProfileCornerPicture(user.getProfilePicture(), e -> showProfileCard(user));
				loginScreen.getRoot().getChildren().add(cornerCard);
				AnchorPane.setRightAnchor(cornerCard, 15.);
				AnchorPane.setTopAnchor(cornerCard, 15.);
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
	
	private void showProfileCard(User user) {
		if(user != null) {
			ProfileCard card = new ProfileCard("profile", user, "profile.css");
			card.setLogoutAction(e -> {
				loginhandler.setActiveUser(null);
				loginScreen = new Login(loginhandler, css, resource);
				loginhandler.returnToMain();
			});
			HBox hb = card.getCard();
			((Pane) stage.getScene().getRoot()).getChildren().add(hb);
			AnchorPane.setBottomAnchor(hb, 25.);
			AnchorPane.setLeftAnchor(hb, 25.);
		} else {
			new Alert(AlertType.ERROR, loginResource.getString("pleaselogin")).showAndWait();
		}
	}
	
	private void promptUserToChooseGame(){
		try {
			FileSelector mySelector = new FileSelector(CONFIG_EXTENSION);
			File dataFile = mySelector.open(new Stage());
			if(dataFile != null) {
				String XML = new String(Files.readAllBytes(Paths.get(dataFile.getAbsolutePath())));
				VoogaSerializer serializer = new XStreamSerializer();
				GameData gameData = serializer.makeObjectFromXMLString(XML, GameData.class);
				goToGameScreen(gameData);
			}
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("Invalid GameData file chosen");
		}
	}
	
	private void goToGameScreen(GameData gameData) {
		gameController = new GameController(gameData);
		gameController.getGameScreen().setLoginHandler(loginhandler);
		gameController.start();
		stage.setScene(new Scene(gameController.getGameScreen(), Preferences.SCREEN_WIDTH, Preferences.SCREEN_HEIGHT, Color.WHITE));
		stage.setTitle("Game Screen");
	}
}