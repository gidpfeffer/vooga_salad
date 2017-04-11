package ui.player.inGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ui.handlers.UIHandler;

public class SettingsPane {

	private Button button;
	private AnchorPane settings;
	private static final double paneWidth = 150.;
	private List<String> helpPaneOptions = new ArrayList<>(Arrays.asList("Help", "Settings", "Return to Main"));
	private List<Hyperlink> helpLinks = new ArrayList<>();
	
	
	public Button getHelpButton(){
		return button;
	}
	
	public AnchorPane getHelpPane() {
		return settings;
	}
	
	public SettingsPane() {
		setup();
	}
	
	private void setup(){
		setupPane();
		createHelpButton();
		addHyperlinks();
		}
	
	private void setupPane() {
		settings = new AnchorPane();
		settings.setPrefWidth(paneWidth);
		addBackButton();
	}
	
	private void addBackButton(){
		OptionButton back = new OptionButton(0, "back", "back_icon.png", closePane);
		settings.getChildren().add(back.getButton());
		AnchorPane.setTopAnchor(back.getButton(), 10.0);
		AnchorPane.setRightAnchor(back.getButton(), 10.0);
	}
	
	private void createHelpButton() {
		OptionButton back = new OptionButton(0, "back", "splash_icon.png", openPane);
		button = back.getButton();
	}
	
	private void addHyperlinks() {
		helpPaneOptions.forEach(name -> helpLinks.add(new Hyperlink(name)));
		VBox helpBox = new VBox(20);
		helpLinks.forEach(link -> helpBox.getChildren().add(link));
		settings.getChildren().add(helpBox);
		helpBox.setAlignment(Pos.CENTER_LEFT);
	}
	
	EventHandler<MouseEvent> closePane = new EventHandler<MouseEvent>()  {
		@Override
		public void handle( final MouseEvent ME ) {
			Object obj = ME.getSource();  // you can also try ME.getTarget()

			if ( obj instanceof Button ) {
				System.out.println("button should slide back in");
				TranslateTransition t = new TranslateTransition(Duration.seconds(0.2));
				t.setNode(settings);
				t.setToX(0);
				t.play();
			}
		}
	};
	
	EventHandler<MouseEvent> openPane = new EventHandler<MouseEvent>()  {
	    @Override
	    public void handle( final MouseEvent ME ) {
	        Object obj = ME.getSource();  //how to link to correct pane?? have to do correct controllers.getPane()

	        if ( obj instanceof Button ) {
	    		TranslateTransition t = new TranslateTransition(Duration.seconds(0.2));
	    		t.setNode(settings);
	    		t.setToX(paneWidth);
	    		t.play();
	        	}
	    }
	};
	
}
