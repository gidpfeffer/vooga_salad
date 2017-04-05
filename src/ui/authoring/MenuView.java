package ui.authoring;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import ui.authoring.delegates.MenuDelegate;
import ui.general.ImageButton;
import util.Location;

public class MenuView extends AnchorPane{
	
	private MenuDelegate myDelegate;
	
	public MenuView(MenuDelegate delegate){
		super();
		myDelegate = delegate;
		setupViews();
	}

	private void setupViews() {
		setupBackButton();
		
	}

	private void setupBackButton() {
		ImageButton back = new ImageButton("back_icon.png", new Location(48.0,48.0));
		AnchorPane.setTopAnchor(back, 12.0);
		AnchorPane.setRightAnchor(back, 12.0);
		back.setOnMouseClicked(e -> myDelegate.didPressBackButton());
		this.getChildren().add(back);
	}
}
