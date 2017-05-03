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
import gamedata.BasicData;
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
	
	private static final Map<String, LineageData> DEFAULT_TOWERS;
	static {
		//String path = "Pokemon Icons/";
		String path = "";
		HashMap<String, LineageData> map = new HashMap<String, LineageData>();
		/*map.put("Pikachu", path + "pikachu.png");
		map.put("Bullbasaur", path + "bullbasaur.png");
		map.put("Charmander", path + "charmander.png");
		map.put("Snorlax", path + "snorlax.png");
		map.put("Jigglypuff", path + "jigglypuff.png");*/
		
		BasicActorType proj = new BasicActorType("Tower", "tower_icon.png", false);
		
		map.put("Duvall", new LineageData(new ActorData(proj, 
				new BasicData("Duvall",  path + "duvall.png"), new LimitedHealthData())));
		
		DEFAULT_TOWERS = map;
	}
	private static final Map<String, LineageData> DEFAULT_TROOPS;
	static {
		//String path = "balloons/";
		String path = "";
		HashMap<String, LineageData> map = new HashMap<String, LineageData>();
		/*map.put("Red", path + "red.png");
		map.put("Green", path + "green.png");
		map.put("Blue", path + "blue.png");*/
		
		BasicActorType proj = new BasicActorType("Troop", "enemy_icon.png", false);
		
		map.put("Singleton", new LineageData(new ActorData(proj, 
				new BasicData("Singleton",  path + "Singleton.png"), new LimitedHealthData())));
		map.put("Static", new LineageData(new ActorData(proj, 
				new BasicData("Static",  path + "Static.png"), new LimitedHealthData())));
		map.put("STUPID", new LineageData(new ActorData(proj, 
				new BasicData("STUPID",  path + "STUPID.png"), new LimitedHealthData())));

		DEFAULT_TROOPS = map;
	}
	private static final Map<String, LineageData> DEFAULT_PROJECTILES;
	static {
		//String path = "projectiles/";
		String path = "";
		HashMap<String, LineageData> map = new HashMap<String, LineageData>();
		/*map.put("Fire", path + "flame.png");
		map.put("Water", path + "raindrop.png");
		map.put("Ice", path + "snowflake.png");
		map.put("Nature", path + "leaf.png");*/
		
		BasicActorType proj = new BasicActorType("Projectile", "projectile_icon.png", false);
		
		map.put("YES", new LineageData(new ActorData(proj, 
				new BasicData("YES",  path + "piazza_proj_3.png"), new LimitedHealthData())));
		map.put("NO", new LineageData(new ActorData(proj, 
				new BasicData("NO",  path + "piazza_proj_2.png"), new LimitedHealthData())));
		map.put("Reference2", new LineageData(new ActorData(proj, 
				new BasicData("Reference2",  path + "piazza_proj_1.png"), new LimitedHealthData())));
		map.put("Reference1", new LineageData(new ActorData(proj, 
				new BasicData("Reference1",  path + "piazza_proj_4.png"), new LimitedHealthData())));
		
		DEFAULT_PROJECTILES = map;
	}
	
	private PopViewDelegate myPopDelegate;
	private ActorEditorDelegate myEditorDelegate;
	private VBox myVBox; //contains the buttons
	private Map<BasicActorType, ActorEditorView> actorTypeToView = new HashMap<>();
	private GameData myGameData;
	private boolean isLoaded;

	/**
	 * Constructs a panel with a list of button that
	 * lead to ActorEditorView
	 * Provides the ability to add new actor types.
	 * 
	 * @param delegate required so that this class can launch the ActorEditorView's
	 */
	public LeftPaneView(ActorEditorDelegate delegate, GameData gameData, boolean loaded){
		super();
		myEditorDelegate = delegate;
		myPopDelegate = delegate;
		myGameData = gameData;
		isLoaded = loaded;
		setupViews();
	}
	
	private void setupViews() {
		setupVBox();
		setupTitle();
		setupAddButton();
		if(!isLoaded)
			setupDefaultActors();
		else{
			loadActors();
		}

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
	
	private void loadActors(){
		for(BasicActorType type: myGameData.getTypes()){
			Map<String, LineageData> mapping = myGameData.getAllLinOfType(type)
;			addActor(type.getType(), type.getImagePath(), mapping);
		}
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

	private void addActor(String actorType, String imagePath, 
			Map<String, LineageData> defaultActors){
		BasicActorType newType = new BasicActorType(actorType, imagePath);
		ActorEditorView view = new ActorEditorView(myEditorDelegate, newType, myGameData);
		
		if(!isLoaded){
			myGameData.getTypes().add(newType);
		}
		//view.setupActors(defaultActors, isLoaded);
		
		UIHelper.setBackgroundColor(view, COLOR_ROTATION[this.actorTypeToView.size()%COLOR_ROTATION.length]);
		this.actorTypeToView.put(newType, view);
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
		this.addActor(actorName, imagePath, new HashMap<String, LineageData>());
		closeSelf(pane);
	}
	
	@Override
	public void closeSelf(Pane pane){
		myPopDelegate.closeView(pane);
	}
	
}
