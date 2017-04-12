package ui.authoring.level;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import builders.OptionGenerator;

import java.util.Map.Entry;

import gamedata.ActorData;
import gamedata.FieldData;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Pair;
import types.BasicActorType;
import ui.Preferences;
import ui.authoring.delegates.PopViewDelegate;
import ui.general.CustomColors;
import ui.general.ImageButton;
import ui.general.UIHelper;
import util.Location;

public class LevelEditorMenu extends AnchorPane{
	PopViewDelegate myDelegate;
	public LevelEditorMenu(PopViewDelegate d){
super();
myDelegate=d;
setupViews();;
	}
	private void setupViews() {
		ScrollPane bottomSide = new ScrollPane();
		ScrollPane topSide = new ScrollPane();
		setupSides(bottomSide, topSide);
		
		setupBackButton();
		Map<String,List<FieldData>> map=OptionGenerator.getPropertyTypesWithArgs();
		System.out.println(map);
	}
	
	
	private void setupBackButton() {
		ImageButton b = new ImageButton("back_icon.png", new Location(30., 30.));
		AnchorPane.setTopAnchor(b, 4.0);
		AnchorPane.setLeftAnchor(b, 4.0);
		b.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> myDelegate.closeView(this));
		this.getChildren().add(b);
	}
	
private void setupSides(ScrollPane bottomSide,ScrollPane topSide){
	
		double inset = 12.0;
		
		AnchorPane.setLeftAnchor(topSide, inset);
		AnchorPane.setLeftAnchor(bottomSide, inset);
		AnchorPane.setRightAnchor(topSide, 48.0);
		AnchorPane.setRightAnchor(bottomSide, 48.0);
		AnchorPane.setTopAnchor(topSide, inset);
		AnchorPane.setBottomAnchor(bottomSide, inset);

		topSide.setStyle("-fx-background-color: #" + UIHelper.colorToHex(CustomColors.GREEN_200) + ";");
		topSide.setStyle("-fx-background: #" + UIHelper.colorToHex(CustomColors.GREEN_200) + ";");
		bottomSide.setStyle("-fx-background-color: #" + UIHelper.colorToHex(CustomColors.GREEN_200) + ";");
		bottomSide.setStyle("-fx-background: #" + UIHelper.colorToHex(CustomColors.GREEN_200) + ";");

		bottomSide.setHbarPolicy(ScrollBarPolicy.NEVER);
		topSide.setHbarPolicy(ScrollBarPolicy.NEVER);

		UIHelper.setDropShadow(topSide);
		UIHelper.setDropShadow(bottomSide);
		bottomSide.prefHeightProperty().bind(this.heightProperty().divide(3.0/2).subtract(inset * 3 / 2));
		topSide.prefHeightProperty().bind(this.heightProperty().divide(3.0).subtract(inset * 3 / 2));

		this.getChildren().addAll(bottomSide, topSide);

}



}


