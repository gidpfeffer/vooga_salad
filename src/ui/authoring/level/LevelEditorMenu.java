package ui.authoring.level;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import builders.OptionGenerator;

import java.util.Map.Entry;

import gamedata.ActorData;
import gamedata.FieldData;
import gamedata.PathData;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Pair;
import types.BasicActorType;
import ui.Preferences;
import ui.authoring.actor.ActorEditorView;
import ui.authoring.delegates.PopViewDelegate;
import ui.general.CustomColors;
import ui.general.ImageButton;
import ui.general.UIHelper;
import util.Location;

public class LevelEditorMenu extends AnchorPane {
	PopViewDelegate myDelegate;
	private List<ActorData> activeEnemies = new ArrayList<ActorData>();
	private List<Integer> activePaths = new ArrayList<Integer>();
	private Collection<ActorData> enemies;
	ScrollPane bottomSide;
	StackPane topSide;
	
	private PathData myPathData;
	public LevelEditorMenu(PopViewDelegate d, Collection<ActorData> enemies,PathData pathData) {
		super();
		myDelegate = d;
		this.enemies=enemies;
		myPathData=pathData;
		setupViews();
		
		
	}

	private void setupViews() {
	 bottomSide = new ScrollPane();
		 topSide = new StackPane();
		setupSides(bottomSide, topSide);

		setupBackButton();
		Map<String, List<FieldData>> map = OptionGenerator.getPropertyTypesWithArgs();
		populateEnemies();
		populatePaths();
	}
	private  void populatePaths(){
		VBox root=new VBox();
		Text label=new Text("Select Active Path");
		root.setAlignment(Pos.CENTER);
		ComboBox<Integer>pathChoice=new ComboBox<Integer>();
		pathChoice.getItems().addAll(myPathData.getMyPaths().keySet());
		pathChoice.valueProperty().addListener((x,y,newValue)->{choosePath(newValue);
			});
		root.getChildren().add(label);
		root.getChildren().add(pathChoice);
	topSide.getChildren().add(root);
	}
	private void choosePath(Integer newValue){
		activePaths=new ArrayList<Integer>();
		activePaths.add(newValue);
	}
	private  void populateEnemies(){
		HBox root=new HBox();
		root.setSpacing(10);
		for(ActorData enemy:enemies){
			ImageView image=new ImageView(new Image(enemy.getImagePath()));
			root.getChildren().add(UIHelper.buttonStack(e->toggleActive(enemy), Optional.of(new Label(enemy.getName())), Optional.of(image), Pos.BOTTOM_CENTER, true));
			
		}
		bottomSide.setContent(root);
		
	}
	private void toggleActive(ActorData enemy){
		if(activeEnemies.contains(enemy)){
			activeEnemies.remove(enemy);
		}
		else{
			activeEnemies.add(enemy);
		}
		System.out.println(activeEnemies);
	}

	private void setupBackButton() {
		ImageButton b = new ImageButton("back_icon.png", new Location(30., 30.));
		AnchorPane.setTopAnchor(b, 4.0);
		AnchorPane.setLeftAnchor(b, 4.0);
		b.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> myDelegate.closeView(this));
		this.getChildren().add(b);
	}

	private void setupSides(ScrollPane bottomSide, StackPane topSide) {

		double inset = 12.0;

		AnchorPane.setLeftAnchor(topSide, inset);
		AnchorPane.setLeftAnchor(bottomSide, inset);
		AnchorPane.setRightAnchor(topSide, 48.0);
		AnchorPane.setRightAnchor(bottomSide, 48.0);
		AnchorPane.setTopAnchor(topSide, inset);
		AnchorPane.setBottomAnchor(bottomSide, inset);
		topSide.setBackground(new Background(new BackgroundFill(CustomColors.GREEN_200,null,null)));
		topSide.setStyle("-fx-background-color: #" + UIHelper.colorToHex(CustomColors.GREEN_200) + ";");
		topSide.setStyle("-fx-background: #" + UIHelper.colorToHex(CustomColors.GREEN_200) + ";");
		bottomSide.setStyle("-fx-background-color: #" + UIHelper.colorToHex(CustomColors.GREEN_200) + ";");
		bottomSide.setStyle("-fx-background: #" + UIHelper.colorToHex(CustomColors.GREEN_200) + ";");

		bottomSide.setHbarPolicy(ScrollBarPolicy.NEVER);
		//topSide.setHbarPolicy(ScrollBarPolicy.NEVER);

		UIHelper.setDropShadow(topSide);
		UIHelper.setDropShadow(bottomSide);
		bottomSide.prefHeightProperty().bind(this.heightProperty().divide(3.0 / 2).subtract(inset * 3 / 2));
		topSide.prefHeightProperty().bind(this.heightProperty().divide(3.0).subtract(inset * 3 / 2));

		this.getChildren().addAll(bottomSide, topSide);

	}

}
