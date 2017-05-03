package ui.authoring;


import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.imageio.ImageIO;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import gamedata.ActorData;
import gamedata.EnemyInWaveData;
import gamedata.GameData;
import gamedata.LineageData;
import gamedata.composition.LimitedHealthData;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import types.BasicActorType;
import ui.Preferences;
import ui.authoring.actor.ActorEditorView;
import ui.authoring.actor.CreateBasicTypeView;
import ui.authoring.delegates.ActorEditorDelegate;
import ui.authoring.delegates.CreateActorDelegate;
import ui.authoring.delegates.PopViewDelegate;
import ui.general.CustomColors;
import ui.general.UIHelper;
import util.Tuple;

public class LeftPaneView extends StackPane implements CreateActorDelegate{
	
	private static final double ICON_WIDTH = 24;
	private static final double BUTTON_HEIGHT = 56;
	private final Color[] COLOR_ROTATION = {
			CustomColors.BLUE_800,
			CustomColors.INDIGO,
			CustomColors.GREEN,
			CustomColors.AMBER
	};
	
	private static final Map<String, String> DEFAULT_TOWERS;
	static {
		//String path = "Pokemon Icons/";
		String path = "";
		HashMap<String, String> map = new HashMap<String, String>();
		/*map.put("Pikachu", path + "pikachu.png");
		map.put("Bullbasaur", path + "bullbasaur.png");
		map.put("Charmander", path + "charmander.png");
		map.put("Snorlax", path + "snorlax.png");
		map.put("Jigglypuff", path + "jigglypuff.png");*/
		map.put("Duvall", path + "duvall.png");
		//map.put("Second Duvall", path + "duvall2.png");
		//map.put("Final Duvall", path + "duvall3.png");
		
		DEFAULT_TOWERS = map;
	}
	private static final Map<String, String> DEFAULT_TROOPS;
	static {
		//String path = "balloons/";
		String path = "";
		HashMap<String, String> map = new HashMap<String, String>();
		/*map.put("Red", path + "red.png");
		map.put("Green", path + "green.png");
		map.put("Blue", path + "blue.png");*/
		
		map.put("Singleton", path + "Singleton.png");
		map.put("Static", path + "Static.png");
		map.put("STUPID", path + "STUPID.png");

		DEFAULT_TROOPS = map;
	}
	private static final Map<String, String> DEFAULT_PROJECTILES;
	static {
		//String path = "projectiles/";
		String path = "";
		HashMap<String, String> map = new HashMap<String, String>();
		/*map.put("Fire", path + "flame.png");
		map.put("Water", path + "raindrop.png");
		map.put("Ice", path + "snowflake.png");
		map.put("Nature", path + "leaf.png");*/
		
		map.put("YES", path + "piazza_proj_3.png");
		map.put("NO", path + "piazza_proj_2.png");
		map.put("Reference2", path + "piazza_proj_1.png");
		map.put("Reference1", path + "piazza_proj_4.png");
		
		DEFAULT_PROJECTILES = map;
	}
	
	private PopViewDelegate myPopDelegate;
	private ActorEditorDelegate myEditorDelegate;
	private VBox myVBox; //contains the buttons
	private Map<BasicActorType, ActorEditorView> actorTypeToView = new HashMap<>();
	private GameData myGameData;

	/**
	 * Constructs a panel with a list of button that
	 * lead to ActorEditorView
	 * Provides the ability to add new actor types.
	 * 
	 * @param delegate required so that this class can launch the ActorEditorView's
	 */
	public LeftPaneView(ActorEditorDelegate delegate, GameData gameData){
		super();
		myEditorDelegate = delegate;
		myPopDelegate = delegate;
		myGameData = gameData;
		setupViews();
	}
	
	private void setupViews() {
		setupVBox();
		setupTitle();
		setupAddButton();
		setupDefaultActors();

	}
	
	private void setupTitle() {
		Label title = new Label("Actor Editor");//TODO
		title.setTextFill(CustomColors.BLUE_50);
		title.setAlignment(Pos.CENTER);
		title.setFont(Preferences.FONT_MEDIUM);
		StackPane.setAlignment(title, Pos.TOP_CENTER);
		StackPane.setMargin(title, new Insets(12.0));
		this.getChildren().add(title);
	}

	private void setupVBox() {
		myVBox = new VBox(16);
		myVBox.setAlignment(Pos.CENTER);
		StackPane.setMargin(myVBox, new Insets(64,12,12,12));
		this.getChildren().add(myVBox);		
	}

	private void setupAddButton() {
		ImageView add = new ImageView(new Image("add_icon_w.png"));
		add.setFitWidth(ICON_WIDTH);
		add.setPreserveRatio(true);
		StackPane button = UIHelper.buttonStack(e -> addNewActor(), Optional.ofNullable(null), Optional.of(add), Pos.CENTER, true);
		button.setPrefHeight(BUTTON_HEIGHT);
		UIHelper.setBackgroundColor(button, Color.TRANSPARENT);
		myVBox.getChildren().add(button);
	}
	
	/**
	 * asks for an image icon and name for actor type
	 * creates an ActorEditorView for the new type of actor
	 */
	private void addNewActor() {
		Pane pane = new CreateBasicTypeView(this);
		pane.setPrefHeight(200);
		pane.setPrefWidth(200);
		
		myPopDelegate.openViewWithSize(pane, PopupSize.SMALL);
		
	}

	private void setupDefaultActors() {
		addActor("Troop","enemy_icon.png", DEFAULT_TROOPS);
		addActor("Tower", "tower_icon.png", DEFAULT_TOWERS); //TODO resources
		addActor("Projectile","projectile_icon.png", DEFAULT_PROJECTILES);
		
	}
	
	private void printCurrent(){
		System.out.print("****GameData review******\n");
		if(myGameData.getLevels().size()!=0 && myGameData.getLevel(1).getNumWaves()!=0)
			for(EnemyInWaveData enemy: myGameData.getLevel(1).getMyWaves().get(0).getWaveEnemies()){
				System.out.println("Level 1 ENEMY: " + enemy.getMyActor().getName());
			}
		for(ActorData actor: myGameData.getOptions().values()){
			System.out.print("ACTOR: "+actor.getName() +"\t");
			if(actor.getHealth() instanceof LimitedHealthData){
				System.out.println("Health: "+ ((LimitedHealthData)(actor.getHealth())).getStartHealth());
			}
		}
	}

	private void addActor(String actorType, String imagePath, Map<String,String> defaultActors){
		ActorEditorView view = new ActorEditorView(myEditorDelegate, new BasicActorType(actorType), myGameData);
		view.setupDefaultActors(defaultActors);
		UIHelper.setBackgroundColor(view, COLOR_ROTATION[this.actorTypeToView.size()%COLOR_ROTATION.length]);
		this.actorTypeToView.put(new BasicActorType(actorType), view);
		StackPane button = UIHelper.buttonStack(e -> launchEditor(view), 
				Optional.of(labelForStackButton(actorType + " Editor")), //TODO resources
				Optional.of(imageForStackButton(imagePath)), 
				Pos.CENTER_RIGHT, true);
		button.setPrefHeight(BUTTON_HEIGHT);
		myVBox.getChildren().add(myVBox.getChildren().size() - 1, button);
		
	}
	
	private void launchEditor(ActorEditorView view) {
		view.setGameData(myGameData);
		view.setActorTypeOptions(this.actorTypeToView.keySet());
		myPopDelegate.openView(view);
		printCurrent();
	}

	private Label labelForStackButton(String title){
		Label lbl = new Label(title);
		lbl.setTextFill(CustomColors.GREEN_100);
		lbl.setFont(Preferences.FONT_SMALL_BOLD);
		return lbl;
	}
	private ImageView imageForStackButton(String imagePath){
		Image image = null;
		try{
			image = new Image(imagePath);
		} catch(Exception e){
			BufferedImage im = null;
			try {
				im = ImageIO.read(new File(imagePath));
			} catch (IOException ee) {
				e.printStackTrace();
			}
			image = SwingFXUtils.toFXImage(im, null);
		}
		ImageView iv = new ImageView(image);
		iv.setFitWidth(ICON_WIDTH);
		iv.setPreserveRatio(true);
		return iv;
	}
	
	@Override
	public void closeSelfAndReturn(Pane pane, String actorName, String imagePath) {
		this.addActor(actorName, imagePath, new HashMap<String, String>());
		closeSelf(pane);
	}
	
	@Override
	public void closeSelf(Pane pane){
		myPopDelegate.closeView(pane);
	}
	
}
