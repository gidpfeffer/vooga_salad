package ui.authoring;

import java.util.List;
import java.util.Optional;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import ui.authoring.delegates.MenuDelegate;
import ui.general.ImageButton;
import ui.general.UIHelper;
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
		ImageView iv = new ImageView(new Image("back_icon.png"));
		iv.setFitWidth(40);
		iv.setPreserveRatio(true);
		
		StackPane button = UIHelper.buttonStack(e -> myDelegate.didPressBackButton(), 
				Optional.ofNullable(null), Optional.of(iv), Pos.CENTER, true);
		AnchorPane.setTopAnchor(button, 12.0);
		AnchorPane.setRightAnchor(button, 12.0);
		UIHelper.setBackgroundColor(button, Color.rgb(0, 0, 0, 0));
		this.getChildren().add(button);
	}
}
