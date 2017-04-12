package ui.authoring;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import gamedata.PreferencesData;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import ui.authoring.delegates.MenuDelegate;
import ui.general.CustomColors;
import ui.general.ImageButton;
import ui.general.StackButton;
import ui.general.UIHelper;
import ui.general.vbox.ItemData;
import ui.general.vbox.VBoxCustom;
import ui.general.vbox.VBoxHelper;
import ui.general.vbox.VBoxStyle;
import util.Location;

public class MenuView extends AnchorPane{
	
	private MenuDelegate myDelegate;
	private PreferencesData myData;
	private VBoxCustom myVBox;
//TODO
//	private Optional<Integer> numLives;
//	private Optional<Boolean> enemyLoop;
//	private Optional<Boolean> towersAttackable;
//	private Optional<Boolean> wantMoney;
//	private Optional<Boolean> expByLevel;
//	private Optional<Boolean> pauseBetweenWaves;
//	private Optional<Boolean> cleanLevel;
	
	public MenuView(MenuDelegate delegate){
		super();
		myDelegate = delegate;
		myData = new PreferencesData();
		setupViews();
	}

	private void setupViews() {
		setupBackButton();
		setupVBox();
	}

	private void setupVBox() {
		myVBox = new VBoxCustom(8);

		double inset = 8.0;
		AnchorPane.setTopAnchor(myVBox, 64.);
		AnchorPane.setBottomAnchor(myVBox, inset);
		AnchorPane.setLeftAnchor(myVBox, inset);
		AnchorPane.setRightAnchor(myVBox, inset);
		this.getChildren().add(myVBox);
	}
	


	private void setupBackButton() {
		ImageView iv = new ImageView(new Image("back_icon.png"));
		iv.setFitWidth(36);
		iv.setPreserveRatio(true);
		
		StackPane button = UIHelper.buttonStack(e -> myDelegate.didPressBackButton(), 
				Optional.ofNullable(null), Optional.of(iv), Pos.CENTER, true);
		AnchorPane.setTopAnchor(button, 8.0);
		AnchorPane.setRightAnchor(button, 12.0);
		UIHelper.setBackgroundColor(button, Color.rgb(0, 0, 0, 0));
		this.getChildren().add(button);
	}
}
