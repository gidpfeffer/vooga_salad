
package ui;

import java.util.ResourceBundle;

import ui.authoring.AuthoringView;
import ui.general.*;
import ui.handlers.UIHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class UIMain{

	
	private Group myRoot;
	private Scene myScene;
	private UIHandler uihandler;
	
	public UIMain(String language, UIHandler uihandler){
		super();
		//_resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		myRoot = new Group();
		myScene = new Scene(myRoot, Preferences.SCREEN_WIDTH, Preferences.SCREEN_HEIGHT, Color.WHITE);
		this.uihandler = uihandler;
		setup();
	}
	
	private void setup() {
		setupSelections();
		
	}

	private void setupSelections() {
		Button authButton = getOptionsButton("Authoring Environment");
		authButton.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> launchAuthorEnvView());
		Button gameButton = getOptionsButton("Load Game");
		
		VBox vBox = new VBox(16);
		vBox.getChildren().addAll(authButton, gameButton);
		vBox.setAlignment(Pos.TOP_CENTER);
		vBox.setLayoutX(Preferences.SCREEN_WIDTH/4.0);
		vBox.setLayoutY(Preferences.SCREEN_HEIGHT/4.0);
		
		myRoot.getChildren().addAll(vBox);
	}


	private Button getOptionsButton(String title){
		Button button = new Button(title);
		button.setPrefSize(Preferences.SCREEN_WIDTH/2, 60);
		button.setBackground(Background.EMPTY);	
		setButtonThemeNormal(button);
		button.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> setButtonThemeHighlighted(button));
		button.addEventHandler(MouseEvent.MOUSE_RELEASED, e -> setButtonThemeNormal(button));
		return button;
	}
	private void setButtonThemeNormal(Button b){
		setButtonTheme(b, CustomColors.GREEN, Color.GRAY, FontWeight.LIGHT, 
				20, BorderStrokeStyle.SOLID, 0.5);
		b.setScaleX(1);
		b.setScaleY(1);
	}
	private void setButtonThemeHighlighted(Button b){
		setButtonTheme(b, CustomColors.GREEN, Color.BLACK, FontWeight.MEDIUM, 
				20, BorderStrokeStyle.SOLID, 2);
		b.setScaleX(1.1);
		b.setScaleY(1.1);
	}
	
	private void setButtonTheme(Button button, Color borderStokeColor, Color textColor, FontWeight fontWeight,
			int fontSize, BorderStrokeStyle borderStrokeStyle, double borderWidth) {

		BorderStroke[] bs = { new BorderStroke(borderStokeColor, borderStrokeStyle, new CornerRadii(4),
				new BorderWidths(borderWidth)) };
		Border b = new Border(bs);
		button.setBorder(b);
		button.setTextFill(textColor);
		button.setFont(Font.font("HelveticaNeue", fontWeight, fontSize));

	}
	
	private void launchAuthorEnvView() {
		AuthoringView view = new AuthoringView();
		Stage s = (Stage) myScene.getWindow();
		s.setScene(new Scene(view, Preferences.SCREEN_WIDTH, Preferences.SCREEN_HEIGHT, Color.WHITE));
	}
	
	public Scene getScene(){
		return myScene;
	}
	
	
	
}
