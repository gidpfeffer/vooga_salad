package ui.player.login;

import java.util.ResourceBundle;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;
import ui.general.UIHelper;
import ui.handlers.LoginHandler;
import ui.player.users.User;

public class Login extends BorderedAnchorPane implements LoginElement {
	// how to be "logged in"
	private Scene scene;
	private Button auth;
	private Button selector;
	private Button loginEnter;
	private final Text actiontarget;
	private HBox bottomHBox;
	private LoginHandler loginhandler;
	private String css;
	private GridPane gridPane;
	private LoginGrid loginGrid;
	private ResourceBundle loginResource;

	public Scene getScene() {
		return scene;
	}
	
	public Login(LoginHandler loginhandler, String css, String resource) {
		this.css = css;
		this.loginhandler = loginhandler;
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
		setupCenter(gridPane);
	}

	private void setupLayout() {
		gridPane.setHgap(50);
		gridPane.setVgap(20);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.getStyleClass().add("grid");
		getRoot().getStyleClass().add("anchor-pane");
		getRoot().setId("towerBackground");
		scene = new Scene(getRoot());
		scene.getStylesheets().add(css);
		getRoot().getChildren().remove(getBackButton().getButton());
	}

	private void setupTitle() {
		Label towerDefenseTitle = new Label(loginResource.getString("towerDefense"));
		towerDefenseTitle.setPadding(new Insets(10., 0., 0., 0.));
		towerDefenseTitle.setId("title");
		getBorderPane().setTop(towerDefenseTitle);
		BorderPane.setAlignment(towerDefenseTitle, Pos.CENTER);
	}

	private void setupLoginNewAccountTitle() {
		Label welcomeBackTitle = new Label(loginResource.getString("welcomeBack"));
		gridPane.add(welcomeBackTitle, 0, 0);
		GridPane.setHalignment(welcomeBackTitle, HPos.CENTER);
	}

	private void setupLoginGrid() {
		loginGrid = new LoginGrid(loginResource);
		loginGrid.getGrid().getStyleClass().add("grid");
		gridPane.add(loginGrid.getGrid(), 0, 2);
	}

	private void setupButtons(){
		loginEnter = new Button(loginResource.getString("login"));
		loginEnter.setOnAction(e -> loginClicked());
		
		Hyperlink signupEnter = new Hyperlink(loginResource.getString("signup"));
		signupEnter.setOnAction(e -> loginhandler.gotoSignupPage());
		
		gridPane.add(loginEnter, 0, 3);
		gridPane.add(signupEnter, 0, 4);
		GridPane.setHalignment(loginEnter, HPos.CENTER);
		GridPane.setHalignment(signupEnter, HPos.CENTER);
		gridPane.add(actiontarget, 0, 5);
	}

	private void loginClicked(){
		if (loginhandler.login(loginGrid.getUsername().getText(), loginGrid.getPassword().getText())) {
			User user = loginhandler.findUser(loginGrid.getUsername().getText());
			//showProfileCard(user);
			transitionToLoggedIn();
			loginhandler.setActiveUser(user);
			loginGrid.getUsername().clear();
			loginhandler.setCornerProfileCard(loginhandler.getActiveUser());
			//loginScreen.getRoot().getChildren().add(new ImageView(new Image(user.getProfilePicture(), 50, 50, false, true)));
			//gotoGameSelector();
		} else {
			setBadActionTarget(actiontarget, Color.WHITE, 
					loginResource.getString("incorrectLogin"));
		}
		loginGrid.getPassword().clear();
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
	
	private void setupAltButtons(){
		auth = new Button(loginResource.getString("gotoAuth"));
		auth.setOnAction(e -> loginhandler.gotoAuth());
		
		selector = new Button(loginResource.getString("gotoSelector"));
		selector.setOnAction(e -> loginhandler.gotoGameSelector());
		
		UIHelper.setDropShadow(auth);
		UIHelper.setDropShadow(selector);
		bottomHBox = new HBox(100, auth, selector);
		getBorderPane().setBottom(bottomHBox);
		bottomHBox.setAlignment(Pos.CENTER);
		bottomHBox.setPadding(new Insets(0., 0., 30., 0.));
	}
	
	private void setupCenter(Node node) {
		StackPane top = new StackPane();
		top.getStyleClass().add("stack-pane");
		StackPane.setMargin(top, new Insets(0., 300., 30., 300.));
		StackPane sp = new StackPane(top);
		sp.getChildren().add(node);
		getBorderPane().setCenter(sp);
	}
	
	
	public void transitionToLoggedIn() {
	     ScaleTransition st = new ScaleTransition(Duration.millis(1000), gridPane);
	     st.setByX(-1f);
	     st.setByY(-1f);
	     //st.setCycleCount((int) 4f);
	    //st.setAutoReverse(true);
	     st.play();
	     st.setOnFinished(e -> createNewScreen());
	}

	private void createNewScreen() {
		getBorderPane().setBottom(null);
		bottomHBox.getChildren().clear();
		VBox vbox = new VBox(40, auth, selector);
		vbox.setAlignment(Pos.CENTER);
		getBorderPane().setCenter(vbox);
		Label us = new Label("'I Heart Singletons' - Duvall, probably");
		us.setStyle("-fx-font-size: 15");
		us.setPadding(new Insets(50, 0, 20, 0));
		getBorderPane().setBottom(us);
		setupCenter(vbox);
		BorderPane.setAlignment(us, Pos.CENTER);
		/*	     vbox.setScaleX(0);
	     vbox.setScaleY(0);*/
		ScaleTransition st = new ScaleTransition(Duration.millis(1000), vbox);
		st.setByX(2f);
		st.setByY(2f);
		addProfileImage();
	}

	private void addProfileImage() {
		
	}

	//temp
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

		public Game(String name, String imagePath, EventHandler<MouseEvent> clicked) {
			this.name = name;
			this.imagePath = imagePath;
			this.clicked = clicked;
		}
	}
}