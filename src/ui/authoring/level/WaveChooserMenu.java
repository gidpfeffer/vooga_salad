package ui.authoring.level;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


import gamedata.ActorData;
import gamedata.EnemyInWaveData;
import gamedata.LevelData;
import gamedata.WaveData;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import ui.authoring.delegates.PopViewDelegate;
import ui.general.UIHelper;

/**
 * Wave chooser allows for setting the type and number of each enemy
 * in each wave within a level. Modifies a LevelData that
 * is passed in via the constructor.
 * 
 * @author maddiebriere
 */

public class WaveChooserMenu extends AnchorPane {

	PopViewDelegate myDelegate;
	private WaveData editWave;
	private LevelData myData;
	private List<ActorData> enemies;
	private List<StackPane> waveBoxes;
	private List<TextField> textBoxes;
	ScrollPane waves;
	ScrollPane actors;
	
	public WaveChooserMenu(PopViewDelegate delegate, Collection<ActorData> enemies, LevelData level) {
		super();
		myDelegate = delegate;
		waveBoxes = new ArrayList<StackPane>();
		this.enemies = new ArrayList<ActorData>(enemies);
		myData = level;
		setupViews();
		populateViews();
		if(myData.getNumWaves()==0)
			selectWave(addNewWave(), 0);
		else{
			selectWave(waveBoxes.get(0), 0);
		}
	}

	private void setupViews() {
		//TODO: Fix scroll bar
		waves = new ScrollPane();
		waves.setHbarPolicy(ScrollBarPolicy.NEVER);
		actors = new ScrollPane();
		actors.setHbarPolicy(ScrollBarPolicy.NEVER);
		setupBack(actors, waves);
	}
	
	private void populateViews(){
		LevelUtil.setupBackButton(myDelegate, this);
		populateEnemies();
		populateWaves();
	}
	
	private void populateWaves(){
		HBox root = LevelUtil.generateHBox();
		addWaveButton(root);
		int numWaves = myData.getNumWaves();
		for(int i=0; i<numWaves; i++){
			StackPane wave = addWave(i);
			waveBoxes.add(wave);
			root.getChildren().add(wave);
		}
	}
	
	private void addWaveButton(HBox root){
		StackPane newWave = UIHelper.buttonStack(e->addNewWave(), 
				Optional.of(LevelUtil.labelForStackButton("Add Wave")), 
				Optional.of(LevelUtil.imageForStackButton("add_icon.png")),
				Pos.CENTER_RIGHT, true);
		newWave.setPrefHeight(56);
		HBox.setMargin(newWave, new Insets(20));
		root.getChildren().add(newWave);
		waves.setContent(root);
	}
	
	private  void populateEnemies(){
		HBox root=new HBox();
		root.setSpacing(25);
		textBoxes = new ArrayList<TextField>();
		for(ActorData enemy:enemies){
			VBox enemyBox = new VBox();
			enemyBox.setSpacing(10);
			enemyBox.setAlignment(Pos.CENTER);
			ImageView image=new ImageView(new Image(enemy.getImagePath()));
			image.setFitWidth(60);
			image.setPreserveRatio(true);
			Node toAdd = UIHelper.buttonStack(e->promptUser(enemy),
					Optional.of(LevelUtil.labelForStackButton(enemy.getName())), 
					Optional.of(image), Pos.CENTER, true);
			enemyBox.getChildren().add(toAdd);
			
			//TODO: Restore saved
			String quantity = "0";
			if(editWave !=null){
				quantity = ""+editWave.getQuantity(enemy);
			}
			TextField text = addField(enemy, quantity);
			textBoxes.add(text);
			enemyBox.getChildren().add(text);
			root.getChildren().add(enemyBox);
			HBox.setMargin(enemyBox, new Insets(30));
		}
		actors.setContent(root);
	}
	
	private TextField addField(ActorData data, String value){
		TextField field = LevelUtil.addField(value);
		field.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
               field.clear();
            }
        });
		
		//TODO: why did I do this
		field.textProperty().addListener((o,oldText,newText) -> 
			this.updateQuantity(field.textProperty().getValue(), data));
		return field;
	}
	
	private void updateQuantity(String newVal, ActorData data){
		try{
			int quantity = Integer.parseInt(newVal);
			if(editWave.contains(data)){
				editWave.setQuantity(data, quantity);
			}else{
				editWave.addWaveEnemy(new EnemyInWaveData(data, quantity));
			}
		}catch(Exception e){
			//TODO: Error checking
		}
	}
	
	private void promptUser(ActorData enemy){
		System.out.println("Prompt user");
		//TODO: Implement
	}
	
	private StackPane addNewWave(){
		HBox root= LevelUtil.generateHBox();
		StackPane newWave = nextWave();
		Node content = waves.getContent();
		root.getChildren().add(content);
		root.getChildren().add(newWave);
		waveBoxes.add(newWave);
		waves.setContent(root); 
		myData.addWave(new WaveData());
		return newWave;
	}
	
	private void selectWave(StackPane selected, int wave){

		editWave = myData.getMyWaves().get(wave);
		
		HBox root=LevelUtil.generateHBox();
		addWaveButton(root);
		highlight(selected);
		
		for(int i =0; i<textBoxes.size(); i++){
			//TODO: Restore saved
			int quantity = editWave.getQuantity(enemies.get(i));
			textBoxes.get(i).setText(""+quantity);
		}
		root.getChildren().addAll(waveBoxes);
	}
	
	private void highlight(StackPane selected){
		for(StackPane box: waveBoxes){
			box.setOpacity(.5);
		}
		selected.setOpacity(1);
	}
	
	private StackPane nextWave(){
		return addWave(myData.getNumWaves());
	}
	
	private StackPane addWave(int waveNumber){
		StackPane nextWave= UIHelper.buttonStack(e->{},  
				Optional.of(LevelUtil.labelForStackButton
				(String.format("      Wave %d       ", waveNumber + 1))), 
				Optional.ofNullable(null),Pos.CENTER_RIGHT, true);
		highlight(nextWave);
		nextWave.addEventHandler(MouseEvent.MOUSE_CLICKED, 
				e -> selectWave(nextWave, waveNumber));
		nextWave.setPrefHeight(56);
		HBox.setMargin(nextWave, new Insets(20));
		return nextWave;
	}
	
	private void setupBack(ScrollPane bottomSide, ScrollPane topSide){
		double inset = 12.0;
		LevelUtil.setVerticalAnchors(inset, bottomSide, topSide);
		LevelUtil.setupBar(inset, 1.65, bottomSide, this, 3 / 2);
		LevelUtil.setupBar(inset, 3, topSide, this, 3 / 2);
		this.getChildren().addAll(bottomSide, topSide);
	}
}
