package ui.player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.*;
import ui.general.UIHelper;
import ui.player.inGame.GameScreen;
import ui.player.login.DataEntryGrid;
import ui.player.login.LoginGrid;
import ui.player.login.SignupGrid;

//tweaked from http://docs.oracle.com/javafx/2/get_started/form.htm
public class Login{
	private Stage stage;
	private Scene scene;
	private AnchorPane root;
	private GridPane gridPane;
	private BorderPane borderPane;
	private Passwords passwords;
	private UIHelper uihelper;
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
		towerDefenseTitle.setPrefWidth(Preferences.SCREEN_WIDTH);
		towerDefenseTitle.setAlignment(Pos.CENTER);
		borderPane.setTop(towerDefenseTitle);
	}

	private void setupLoginNewAccountTitle() {
		Label welcomeBackTitle = createTitle(loginResource.getString("welcomeBack"), 30);
		Label newAccountTitle = createTitle(loginResource.getString("newAccount"), 30);
		welcomeBackTitle.getStyleClass().add("text");
		gridPane.add(welcomeBackTitle, 0, 0);
		gridPane.add(newAccountTitle, 1, 0);
	}

	private void setupLoginGrid(){
		DataEntryGrid login = new LoginGrid(loginResource);
		login.getGrid().getStyleClass().add("grid");
		gridPane.add(login.getGrid(), 0, 2);
	}

	private void setupSignupGrid(){
		DataEntryGrid signup = new SignupGrid(loginResource);
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
		final Text actiontarget = new Text();
		gridPane.add(actiontarget, 1, 4);

		//loginEnter.setOnAction(e -> loginClicked(loginGrid, actiontarget));
		//signupEnter.setOnAction(e -> signupClicked(signupGrid, actiontarget));
	}

	private void setupAltButtons(){
		Button auth = new Button(loginResource.getString("gotoAuth"));
		Button selector = new Button(loginResource.getString("gotoSelector"));
		auth.setOnAction(e -> gotoUIMain());
		UIHelper.setDropShadow(auth);
		UIHelper.setDropShadow(selector);
		selector.setOnAction(e -> gotoGameSelector());
		gridPane.add(auth, 0, 4);
		gridPane.add(selector, 1, 4);
	}



	private Node getNode (GridPane gridPane, String id) {
		for (Node node : gridPane.getChildren()) {
			if(node.getId() != null && node.getId().equals(id)) {
				return node;
			}
		}
		return null;
	}


	private void loginClicked(GridPane grid, Text actiontarget){
		TextField userTextField = (TextField) getNode(grid, "user");
		PasswordField passwordField = (PasswordField) getNode(grid, "password");
		if (passwords.login(userTextField.getText(), passwordField.getText())) {
			actiontarget.setFill(Color.GREEN);
			actiontarget.setText(loginResource.getString("successfulLogin"));
			userTextField.clear();
			passwordField.clear();
		} else {
			actiontarget.setFill(Color.FIREBRICK);
			actiontarget.setText(loginResource.getString("incorrectLogin"));
		}
	}

	private void signupClicked(GridPane grid, Text actiontarget){
		TextField userTextField = (TextField) getNode(grid, "user");
		PasswordField passwordField = (PasswordField) getNode(grid, "password");
		PasswordField repasswordField = (PasswordField) getNode(grid, "repassword");
		if (!passwords.existingUserCheck(userTextField.getText())) {
			if (userTextField.getText().equals("") || passwordField.getText().equals("")) {
				showAlert(AlertType.ERROR, loginResource.getString("errorTitle"),
						loginResource.getString("error"), loginResource.getString("errorCorrection"));
			}
			else if (!passwordField.getText().equals(repasswordField.getText())){
				showAlert(AlertType.ERROR, loginResource.getString("errorTitle"),
						loginResource.getString("passwordError"), loginResource.getString("passwordErrorCorrection"));
			} 
			else {
				actiontarget.setFill(Color.GREEN);
				actiontarget.setText(loginResource.getString("successfulSignUp"));
				passwords.signup(userTextField.getText(), passwordField.getText());
				try {
					mySavedPasswords = mySerializer.toXML(passwords);
					System.out.println(mySavedPasswords);
					fileChooser.writePasswords(mySavedPasswords);
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				userTextField.clear();
				passwordField.clear();
				repasswordField.clear();
			}
		} else {
			actiontarget.setFill(Color.FIREBRICK);
			actiontarget.setText(loginResource.getString("incorrectSignUp"));
		};
	}

	private void showAlert(AlertType type, String title, String heading, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(heading);
		alert.setContentText(content);
		alert.showAndWait();
	}

	private void gotoUIMain() {
		UIMain view = new UIMain("English");
		stage.setScene(view.getScene());
		stage.setTitle("VOOGASalad");
		stage.setResizable(false);
		stage.show();
	}

	private void gotoGameSelector() {
		GameSelector select = new GameSelector("English", "mainScreen.css");
		stage.setScene(select.getScene());
		stage.setTitle("GameSelector");
		stage.show();
	}

	private void gotoGameScreen() {
		GameScreen inGame = new GameScreen(stage, null, null, null, null, null);
		stage.setScene(inGame.getScene());
		stage.setTitle("GameSelector");
		stage.show();
	}
}