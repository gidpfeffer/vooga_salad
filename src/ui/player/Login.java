package ui.player;

import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//tweaked from http://docs.oracle.com/javafx/2/get_started/form.htm
public class Login extends Application{
	private Passwords passwords = new Passwords();
	private ResourceBundle loginResource;
	private XStream mySerializer = new XStream(new DomDriver());
	
	@Override
    public void start(Stage primaryStage) {
		loginResource = ResourceBundle.getBundle("login");
        primaryStage.setTitle(loginResource.getString("login"));
        Scene loginPage = setup();
        primaryStage.setScene(loginPage);
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(600);
        primaryStage.show();
    }
	
	public Scene setup() {
		GridPane root = new GridPane();
		GridPane loginGrid = createGrid(Pos.TOP_LEFT);
		GridPane signupGrid = createGrid(Pos.TOP_LEFT);
		root.add(loginGrid, 0, 0);
		root.add(signupGrid, 1, 0);
		root.setAlignment(Pos.CENTER);
		Scene scene = new Scene(root);
		scene.getStylesheets().add("loginScreen.css");
		//System.out.println(javafx.scene.text.Font.getFamilies());
		
		Text welcomeBackTitle = createTitle(loginResource.getString("welcomeBack"));
		Text newAccountTitle = createTitle(loginResource.getString("newAccount"));

		enterUser(loginGrid, welcomeBackTitle);
		newUser(signupGrid, newAccountTitle);

		Button loginEnter = new Button(loginResource.getString("login"));
		Button signupEnter = new Button(loginResource.getString("signup"));
		HBox loginHbox = new HBox(10);
		HBox signupHbox = new HBox(10);
		loginHbox.getChildren().add(loginEnter);
		signupHbox.getChildren().add(signupEnter);
		loginHbox.setAlignment(Pos.BOTTOM_RIGHT);
		signupHbox.setAlignment(Pos.BOTTOM_RIGHT);
		
		loginGrid.add(loginHbox, 1, 4);
		signupGrid.add(signupEnter, 1, 4);

		
		
		final Text actiontarget = new Text();
        root.add(actiontarget, 1, 2);
        
        loginEnter.setOnAction(e -> loginClicked(loginGrid, actiontarget));
        signupEnter.setOnAction(e -> signupClicked(signupGrid, actiontarget));
        
		return scene;
	}
	
	private Text createTitle(String s) {
		Text title =  new Text(s);
		title.setFont(Font.font(20));
		return title;
	}
	
	private GridPane createGrid(Pos position){
		GridPane grid = new GridPane();
		grid.getStyleClass().add("grid");
		grid.setAlignment(position);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		return grid;
		
	}
	
	private void enterUser(GridPane login, Text title){
		login.add(title, 0, 0, 2, 1);
		
		Label userName = new Label(loginResource.getString("username"));
		login.add(userName, 0, 1);

		TextField userTextField = new TextField();
		userTextField.setId("user");
		login.add(userTextField, 1, 1);

		Label password = new Label(loginResource.getString("password"));
		login.add(password, 0, 2);

		PasswordField passwordField = new PasswordField();
		passwordField.setId("password");
		login.add(passwordField, 1, 2);
	}
	
	private void newUser(GridPane sign, Text title){
		enterUser(sign, title);
		
		Label repassword = new Label(loginResource.getString("reenter"));
		sign.add(repassword, 0, 3);

		PasswordField repasswordField = new PasswordField();
		repasswordField.setId("repassword");
		sign.add(repasswordField, 1, 3);
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
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle(loginResource.getString("errorTitle"));
				alert.setHeaderText(loginResource.getString("error"));
				alert.setContentText(loginResource.getString("errorCorrection"));
				alert.showAndWait();
			}
			else if (!passwordField.getText().equals(repasswordField.getText())){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle(loginResource.getString("errorTitle"));
				alert.setHeaderText(loginResource.getString("passwordError"));
				alert.setContentText(loginResource.getString("passwordErrorCorrection"));
				alert.showAndWait();
			} 
			else {
				actiontarget.setFill(Color.GREEN);
				actiontarget.setText(loginResource.getString("successfulSignUp"));
				passwords.signup(userTextField.getText(), passwordField.getText());
				userTextField.clear();
				passwordField.clear();
				repasswordField.clear();
			}
		} else {
			actiontarget.setFill(Color.FIREBRICK);
			actiontarget.setText(loginResource.getString("incorrectSignUp"));
		};
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}