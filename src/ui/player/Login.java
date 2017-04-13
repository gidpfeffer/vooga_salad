package ui.player;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import gameengine.controllers.GameController;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.*;
import ui.authoring.AuthoringView;
import ui.general.UIHelper;
import ui.player.login.LoginGrid;
import ui.player.login.SignupGrid;

//tweaked from http://docs.oracle.com/javafx/2/get_started/form.htm
public class Login{
	private Stage stage;
	private Scene scene;
	private GameController gameController;
	
	private AnchorPane root;
	private GridPane gridPane;
	private BorderPane borderPane;
	private Passwords passwords;
	private LoginGrid login;
	private SignupGrid signup;
	private ResourceBundle loginResource;
	private XStream mySerializer = new XStream(new DomDriver());
	private String mySavedPasswords = "";
	private static final String filename = "passwordXML.xml";
	private XStreamFileChooser fileChooser = new XStreamFileChooser(filename);


	public Scene getScene() {
		return scene;
	}

	public Login(Stage stage, String css, String resource) {
		this.stage = stage;
		stage.setMinHeight(Preferences.SCREEN_HEIGHT);
		stage.setMinWidth(Preferences.SCREEN_WIDTH);
		loginResource = ResourceBundle.getBundle(resource);
		try {
			mySavedPasswords = fileChooser.readInClass();
			passwords = (Passwords) mySerializer.fromXML(mySavedPasswords);
		}
		catch (Exception ex) {
			passwords = new Passwords();
			//ex.printStackTrace();
		}
		//passwords = fileChooser.readInPasswords(mySerializer);
		gridPane = new GridPane();
		gridPane.setHgap(50);
		gridPane.setVgap(20);
		borderPane = new BorderPane();
		root = new AnchorPane();
		setup(css);
	}

	public void setup(String css) {
		setupLayout(css);
		setupTitle();
		setupLoginNewAccountTitle();
		setupLoginGrid();
		setupSignupGrid();
		setupButtons();
		setupAltButtons();
	}

	private void setupLayout(String css) {
		borderPane.setCenter(gridPane);
		root.getChildren().add(borderPane);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.getStyleClass().add("grid");
		root.getStyleClass().add("anchor-pane");
		scene = new Scene(root);
		scene.getStylesheets().add(css);
		setupBorderPane();
	}
	private void setupBorderPane(){
		AnchorPane.setBottomAnchor(borderPane, 0.0);
		AnchorPane.setLeftAnchor(borderPane, 0.0);
		AnchorPane.setTopAnchor(borderPane, 0.0);
		AnchorPane.setRightAnchor(borderPane, 0.0);
		//gridPane.setId("root");
	}

	private void setupTitle(){
		Label towerDefenseTitle = createTitle(loginResource.getString("towerDefense"), 50);
		//towerDefenseTitle.setPrefWidth(Preferences.SCREEN_WIDTH);
		towerDefenseTitle.setId("title");
		borderPane.setTop(towerDefenseTitle);
		BorderPane.setAlignment(towerDefenseTitle, Pos.CENTER);
		
	}

	private void setupLoginNewAccountTitle() {
		Label welcomeBackTitle = createTitle(loginResource.getString("welcomeBack"), 30);
		Label newAccountTitle = createTitle(loginResource.getString("newAccount"), 30);
		gridPane.add(welcomeBackTitle, 0, 0);
		gridPane.add(newAccountTitle, 1, 0);
		GridPane.setHalignment(welcomeBackTitle, HPos.CENTER);
		GridPane.setHalignment(newAccountTitle, HPos.CENTER);
	}

	private void setupLoginGrid(){
		login = new LoginGrid(loginResource);
		login.getGrid().getStyleClass().add("grid");
		gridPane.add(login.getGrid(), 0, 2);
	}

	private void setupSignupGrid(){
		signup = new SignupGrid(loginResource);
		signup.getGrid().getStyleClass().add("grid");
		gridPane.add(signup.getGrid(), 1, 2);
	}

	private Label createTitle(String s, int size) {
		Label title =  new Label(s);
		title.setPrefHeight(size);;
		return title;
	}

	private void setupButtons(){
		Button loginEnter = new Button(loginResource.getString("login"));
		Button signupEnter = new Button(loginResource.getString("signup"));
		UIHelper.setDropShadow(loginEnter);
		UIHelper.setDropShadow(signupEnter);
		gridPane.add(loginEnter, 0, 3);
		gridPane.add(signupEnter, 1, 3);
		GridPane.setHalignment(loginEnter, HPos.CENTER);
		GridPane.setHalignment(signupEnter, HPos.CENTER);
		final Text actiontarget = new Text();
		gridPane.add(actiontarget, 1, 4);

		loginEnter.setOnAction(e -> loginClicked(actiontarget));
		signupEnter.setOnAction(e -> signupClicked(actiontarget));
	}

	private void setupAltButtons(){
		Button auth = new Button(loginResource.getString("gotoAuth"));
		Button selector = new Button(loginResource.getString("gotoSelector"));
		auth.setOnAction(e -> gotoAuth());
		UIHelper.setDropShadow(auth);
		UIHelper.setDropShadow(selector);
		selector.setOnAction(e -> gotoGameSelector());
		gridPane.add(auth, 0, 4);
		gridPane.add(selector, 1, 4);
		GridPane.setHalignment(auth, HPos.CENTER);
		GridPane.setHalignment(selector, HPos.CENTER);
	}

	private void loginClicked(Text actiontarget){
/*		for (Map.Entry<String, TextField> entry : login.entrySet()) {
		    String key = entry.getKey();
		    ArrayList<String> value = entry.getValue();
		    // now work with key and value...
		}
		for (Map.Entry<Text, TextField> entry : login.entrySet())
		{
			System.out.println(entry.getKey() + "/" + entry.getValue());
		}*/
		System.out.println(login.getUsername().getText());
		showAlert(AlertType.INFORMATION, "Welcome", "Welcome, " + login.getUsername().getText(),
				"Login currently does nothing");
/*		if (passwords.login(login.getUsername().getText(), login.getPassword().getText())) {
			actiontarget.setFill(Color.GREEN);
			actiontarget.setText(loginResource.getString("successfulLogin"));
			login.getUsername().clear();
			login.getPassword().clear();
		} else {
			actiontarget.setFill(Color.FIREBRICK);
			actiontarget.setText(loginResource.getString("incorrectLogin"));
		}*/
	}

	private void signupClicked(Text actiontarget){
		if (!passwords.existingUserCheck(signup.getUsername().getText())) {
			if (signup.getUsername().getText().equals("") || signup.getPassword().getText().equals("")) {
				showAlert(AlertType.ERROR, loginResource.getString("errorTitle"),
						loginResource.getString("error"), loginResource.getString("errorCorrection"));
			}
			else if (!signup.getPassword().getText().equals(signup.getRePassword().getText())){
				showAlert(AlertType.ERROR, loginResource.getString("errorTitle"),
						loginResource.getString("passwordError"), loginResource.getString("passwordErrorCorrection"));
			} 
			else if (!signup.getEmail().getText().equals(signup.getReEmail().getText())){
				showAlert(AlertType.ERROR, loginResource.getString("errorTitle"),
						loginResource.getString("emailError"), loginResource.getString("emailErrorCorrection"));
			} 
			else {
				actiontarget.setFill(Color.GREEN);
				actiontarget.setText(loginResource.getString("successfulSignUp"));
				passwords.signup(signup.getUsername().getText(), signup.getPassword().getText());
				try {
					mySavedPasswords = mySerializer.toXML(passwords);
					System.out.println(mySavedPasswords);
					fileChooser.writePasswords(mySavedPasswords);
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				clearSignupFields();
			}
		} else {
			actiontarget.setFill(Color.FIREBRICK);
			actiontarget.setText(loginResource.getString("incorrectSignUp"));
		};
	}

	private void clearSignupFields() {
		signup.getEmail().clear();
		signup.getPassword().clear();
		signup.getPassword().clear();
		signup.getReEmail().clear();
		signup.getRePassword().clear();
		signup.getUsername().clear();
	}
	
	private void showAlert(AlertType type, String title, String heading, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(heading);
		alert.setContentText(content);
		alert.showAndWait();
	}

	private void gotoAuth() {
 		AuthoringView view = new AuthoringView();
		Stage s = (Stage) scene.getWindow();
		s.setScene(new Scene(view, Preferences.SCREEN_WIDTH, Preferences.SCREEN_HEIGHT, Color.WHITE));
/*		UIMain view = new UIMain("English", null);
		stage.setScene(view.getScene());
		stage.setTitle("VOOGASalad");
		stage.setResizable(false);
		stage.show();*/
	}

	private void gotoGameSelector() {
		//TODO: Replace with actual games list
		ArrayList<Button> gamesList = new ArrayList<Button>();
		gamesList.add(new Button("Sample Game"));
		gamesList.add(new Button("Also Sample Game"));
		
		GameSelector select = new GameSelector("English", "mainScreen.css", setUpGames(gamesList));
		stage.setScene(select.getScene());
		stage.setTitle("GameSelector");
		stage.show();
	}
	
	private GridPane setUpGames(List<Button> games) {
		GridPane gameMenu = new GridPane();
		gameMenu.setVgap(20);
		int index = 0;
		for(Button game : games) {
			game.setOnAction(e -> gotoGameScreen());
			gameMenu.add(game, 0, index);
			index++;
		}
		return gameMenu;
	}

	private void gotoGameScreen() {
		gameController = new GameController();
		gameController.start(stage);
		//GameScreen inGame= new GameScreen(stage, null, new TempData());
		stage.setScene(gameController.getGameScreen().getScene());
		stage.setTitle("GameSelector");
		//stage.show();
	}
}