package ui.player.login;

import java.util.ResourceBundle;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import ui.general.UIHelper;

public class Login extends BorderedAnchorPane{
	private Scene scene;
	
	private Button loginEnter;
	private Hyperlink signupEnter;
	private Button auth;
	private Button selector;
	private final Text actiontarget;
	
	private String css;
	private GridPane gridPane;
	private LoginGrid login;
	private ResourceBundle loginResource;

	public Scene getScene() {
		return scene;
	}
	
	public void setLoginAction(EventHandler<ActionEvent> value) {
		loginEnter.setOnAction(value);
	}
	
	public void setSignupAction(EventHandler<ActionEvent> value) {
		signupEnter.setOnAction(value);
	}
	
	public void setAuthAction(EventHandler<ActionEvent> value) {
		auth.setOnAction(value);
	}
	
	public void setSelectorAction(EventHandler<ActionEvent> value) {
		selector.setOnAction(value);
	}

	public Text getActionTarget() {
		return actiontarget;
	}
	
	public LoginGrid getLoginGrid() {
		return login;
	}
	
	public Login(String css, String resource) {
		this.css = css;
		actiontarget = new Text();
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
		loginEnter = new Button(loginResource.getString("login"));
		signupEnter = new Hyperlink(loginResource.getString("signup"));
		//UIHelper.setDropShadow(loginEnter);
		gridPane.add(loginEnter, 0, 3);
		gridPane.add(signupEnter, 0, 4);
		GridPane.setHalignment(loginEnter, HPos.CENTER);
		GridPane.setHalignment(signupEnter, HPos.CENTER);
		gridPane.add(actiontarget, 0, 5);
	}

	private void setupAltButtons(){
		auth = new Button(loginResource.getString("gotoAuth"));
		selector = new Button(loginResource.getString("gotoSelector"));
		UIHelper.setDropShadow(auth);
		UIHelper.setDropShadow(selector);
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
}