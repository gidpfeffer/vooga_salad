package ui.player.login;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import ui.Preferences;
import ui.player.Passwords;
import ui.player.User;
import ui.player.UserDatabase;
import ui.player.XStreamFileChooser;

public class Signup extends BorderedAnchorPane{

	private Scene scene;
	//private Pane root;
	//private BorderPane borderPane;
	private StackPane profileImage;
	private ResourceBundle resource;
	private SignupGrid signupGrid;
	private Button signupButton;
	public final static String generic_profile = "profile_icon.png";
	private String css;
	
	private UserDatabase database;
	private XStream mySerializer = new XStream(new DomDriver());
	private String mySavedUsers = "";
	private static final String userDatabase = "userDatabase.xml";
	private XStreamFileChooser fileChooser = new XStreamFileChooser(userDatabase);
	
	
	public Scene getScene() {
		return scene;
	}
	
	public Signup(ResourceBundle resource, String css) {
		this.resource = resource;
		this.css = css;
		try {
			mySavedUsers = fileChooser.readInClass();
			database = (UserDatabase) mySerializer.fromXML(mySavedUsers);
		}
		catch (Exception ex) {
			database = new UserDatabase();
			//ex.printStackTrace();
		}
		scene = new Scene(this.getRoot(), Preferences.SCREEN_WIDTH, Preferences.SCREEN_HEIGHT);
		scene.getStylesheets().add(css);
		setup();
	}
		
	
	
	private void setup(){
		setupLayout();
		setupTop();
		setupMiddle();
	}


	private void setupLayout() {
		root.setId("towerBackground");
	}

	
	private void setupTop(){
		StackPane top = new StackPane();
		top.setStyle("-fx-background-color: #f4b942; -fx-background-radius: 20;");
		StackPane.setMargin(top, new Insets(10., 150., 5., 150.));
		StackPane sp = new StackPane(top);
		sp.setBackground(Background.EMPTY);
		
		Text signupTitle = new Text(resource.getString("newAccount"));
		//signupTitle.setStyle("-fx-font-family: Baby_Eskimo_Kisses_Black");
		signupTitle.setId("title");
	    BorderPane.setAlignment(signupTitle, Pos.CENTER);
	    BorderPane.setMargin(signupTitle, new Insets(40,20,30,20));
	    
		sp.getChildren().add(signupTitle);
		borderPane.setTop(sp);
		BorderPane.setAlignment(sp, Pos.CENTER);
	}


	private void setupMiddle() {
		StackPane top = new StackPane();
		top.setStyle("-fx-background-color: black; -fx-opacity: .8; -fx-background-radius: 20;");
		StackPane.setMargin(top, new Insets(50., 150., 50., 150.));
		StackPane sp = new StackPane(top);
		sp.setBackground(Background.EMPTY);
		
		GridPane middle = new GridPane();
		middle.setPadding(new Insets(25, 25, 25, 25));
		middle.setHgap(25.);
		middle.setVgap(25.);
		setupGrid();
		createLoginButton();
		setupProfileImage();
		middle.add(profileImage, 0, 0);
		middle.add(signupGrid.getGrid(), 1, 0);
		middle.add(signupButton, 1, 1);
		middle.setAlignment(Pos.CENTER);
		//GridPane.setHalignment(signupGrid.getGrid(), HPos.CENTER);
		GridPane.setHalignment(signupButton, HPos.RIGHT);
		//middle.setStyle("-fx-background-color: red");
		BorderPane.setMargin(middle, new Insets(12,12,12,12));
		sp.getChildren().add(middle);
		borderPane.setCenter(sp);
		BorderPane.setAlignment(sp, Pos.CENTER);
	}
	
	private void setupGrid() {
		signupGrid = new SignupGrid(resource, css);
		signupGrid.getGrid().setBackground(Background.EMPTY);
		//signupGrid.getGrid().setGridLinesVisible(true);
	}
	
	private void createLoginButton() {
		signupButton = new Button(resource.getString("signup"));
		signupButton.addEventHandler(MouseEvent.MOUSE_CLICKED, signupUser);
	}

	private void setupProfileImage() {
		profileImage = new StackPane();
		profileImage.setBackground(Background.EMPTY);
		Image pic = new Image(generic_profile, 200, 200, true, true);
		ImageView image = new ImageView(pic);
		profileImage.getChildren().add(image);
		borderPane.setLeft(profileImage);
		BorderPane.setAlignment(profileImage, Pos.TOP_RIGHT);
	}
	
	private EventHandler<MouseEvent> signupUser = new EventHandler<MouseEvent>()  {
		@Override
		public void handle( final MouseEvent ME ) {
			if (fieldsFilledIn()) {
				if (!checkExistingUser() && checkPasswords() && checkEmails()) {
					try {
						database.getPasswords().signup(signupGrid.getUsername().getText(), signupGrid.getPassword().getText());
						User newUser = new User(signupGrid.getUsername().getText(), database.getPasswords().getUserPassword(signupGrid.getUsername().getText()),
								generic_profile, signupGrid.getEmail().getText());
						database.addUser(newUser);
						mySavedUsers = mySerializer.toXML(database);
						System.out.println(mySavedUsers);
						fileChooser.writeFile(mySavedUsers);
					}
					catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			} else {
				showAlert(AlertType.ERROR, resource.getString("noField"), resource.getString("noFieldCorrection"));
			}
	}

		private Boolean checkExistingUser() {
			if (database.getPasswords().existingUserCheck(signupGrid.getUsername().getText())){
				System.out.println(database.getPasswords().existingUserCheck(signupGrid.getUsername().getText()));
				showAlert(AlertType.ERROR, resource.getString("incorrectSignUp"), resource.getString("pleaselogin"));
			}
			return database.getPasswords().existingUserCheck(signupGrid.getUsername().getText());
		}

		private boolean checkPasswords() {
			if (!signupGrid.getPassword().getText().equals(signupGrid.getRePassword().getText())) {
				showAlert(AlertType.ERROR, resource.getString("passwordError"), resource.getString("passwordErrorCorrection"));
				signupGrid.getPassword().clear();
				signupGrid.getRePassword().clear();
				return false;
			}
			return true;
		}

		private boolean checkEmails() {
			boolean valid = true;
			System.out.println(!validEmail(signupGrid.getEmail().getText()));
			System.out.println("re: " + !validEmail(signupGrid.getReEmail().getText()));
			if (!validEmail(signupGrid.getEmail().getText()) || !validEmail(signupGrid.getReEmail().getText())) {
				showAlert(AlertType.ERROR, resource.getString("invalidEmailError"), resource.getString("invalidEmailErrorCorrection"));
				valid = false;
			}
			else if (!signupGrid.getEmail().getText().equals(signupGrid.getReEmail().getText())) {
				showAlert(AlertType.ERROR, resource.getString("emailError"), resource.getString("emailErrorCorrection"));
				valid = false;
			}
			if (!valid) clearEmails();
			return valid;
		}
		
		
		private void showAlert(AlertType type, String heading, String content) {
			Alert alert = new Alert(type);
			alert.setTitle(resource.getString("errorTitle"));
			alert.setHeaderText(heading);
			alert.setContentText(content);
			alert.showAndWait();
		}
		
		//http://stackoverflow.com/questions/8204680/java-regex-email
		private boolean validEmail(String email){
			Pattern ptr = Pattern.compile("(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)");
			Matcher matcher = ptr.matcher(email);
			//System.out.println(matcher.find());
	        return matcher.find();
		}
		
		private void clearEmails(){
			signupGrid.getEmail().clear();
			signupGrid.getReEmail().clear();
		}
		private boolean fieldsFilledIn() {
			return (!signupGrid.getUsername().getText().trim().isEmpty() && !signupGrid.getEmail().getText().trim().isEmpty() &&
					!signupGrid.getReEmail().getText().trim().isEmpty() && !signupGrid.getPassword().getText().trim().isEmpty()
					&& !signupGrid.getRePassword().getText().trim().isEmpty());
		}
	};
}
