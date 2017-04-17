package ui.authoring.level;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.swing.JTabbedPane;

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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import types.BasicActorType;
import ui.Preferences;
import ui.authoring.PopupSize;
import ui.authoring.actor.ActorTypeListSelectionView;
import ui.authoring.delegates.PopViewDelegate;
import ui.general.CustomColors;
import ui.general.ImageButton;
import ui.general.UIHelper;
import util.Location;

public class LevelEditorMenu extends AnchorPane {

	PopViewDelegate myDelegate;
	private WaveData editWave;
	private LevelData myData;
	private List<ActorData> enemies;
	private List<StackPane> waveBoxes;
	private List<TextField> textBoxes;
	ScrollPane waves;
	ScrollPane actors;
	
	public LevelEditorMenu(PopViewDelegate delegate, Collection<ActorData> enemies, LevelData level) {
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
		actors = new ScrollPane();
		setupBack(actors, waves);
	}
	
	private void populateViews(){
		setupBackButton();
		populateEnemies();
		populateWaves();
	}
	
	private void populateWaves(){
		HBox root = generateCloseHBox();
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
				Optional.of(label("Add Wave")), 
				Optional.of(image("add_icon.png")),
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
			Node toAdd = UIHelper.buttonStack(e->promptUser(enemy),
					Optional.of(label(enemy.getName())), Optional.of(image), Pos.CENTER, true);
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
		StackPane lblWrapper = new StackPane();
		TextField field = new TextField(value);
		field.setPrefWidth(150);
		field.setFont(Preferences.FONT_MEDIUM);
		field.setAlignment(Pos.CENTER);
		field.setBackground(UIHelper.backgroundForColor(CustomColors.BLUE_200));
		field.setStyle("-fx-text-fill-color: #FFFFFF");
		field.setStyle("-fx-background-color: #" +UIHelper.colorToHex(CustomColors.BLUE_200) + ";");
		lblWrapper.getChildren().add(field);
		field.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
               field.clear();
            }
        });
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
	}
	
	private Label label(String title){
		Label lbl = new Label(title);
		lbl.setTextFill(CustomColors.GREEN_100);
		lbl.setFont(Preferences.FONT_SMALL_BOLD);
		return lbl;
	}
	
	private ImageView image(String imagePath){
		Image img = new Image(imagePath);
		ImageView imageView = new ImageView(img);
		imageView.setFitWidth(40);
		imageView.setPreserveRatio(true);
		return imageView;
	}
	
	private HBox generateHBox(int spacing){
		HBox root=new HBox();
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);
		return root;
	}
	
	private HBox generateCloseHBox(){
		return generateHBox(5);
	}
	
	private StackPane addNewWave(){
		HBox root= generateCloseHBox();
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
		
		HBox root=generateCloseHBox();
		addWaveButton(root);
		
		for(StackPane box: waveBoxes){
			box.setOpacity(1);
		}
		
		for(int i =0; i<textBoxes.size(); i++){
			//TODO: Restore saved
			int quantity = editWave.getQuantity(enemies.get(i));
			textBoxes.get(i).setText(""+quantity);
		}
		selected.setOpacity(.5);
		root.getChildren().addAll(waveBoxes);
		
	}
	
	private StackPane nextWave(){
		return addWave(myData.getNumWaves());
	}
	
	private StackPane addWave(int waveNumber){
		StackPane nextWave= UIHelper.buttonStack(e->{},  
				Optional.of(label(String.format("      Wave %d       ", waveNumber + 1))), 
				Optional.ofNullable(null),Pos.CENTER_RIGHT, true);
		nextWave.addEventHandler(MouseEvent.MOUSE_CLICKED, 
				e -> selectWave(nextWave, waveNumber));
		nextWave.setPrefHeight(56);
		HBox.setMargin(nextWave, new Insets(20));
		return nextWave;
	}

	private void setupBackButton() {
		ImageButton b = new ImageButton("back_icon.png", new Location(30., 30.));
		AnchorPane.setTopAnchor(b, 4.0);
		AnchorPane.setLeftAnchor(b, 4.0);
		b.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> myDelegate.closeView(this));
		this.getChildren().add(b);
	}
	
	private void setupBack(ScrollPane bottomSide, ScrollPane topSide){
		double inset = 12.0;
		setVerticalAnchors(inset, bottomSide, topSide);
		setupBar(inset, 1.65, bottomSide);
		setupBar(inset, 2.5, topSide);
		this.getChildren().addAll(bottomSide, topSide);
	}
	
	private void setVerticalAnchors(double inset, ScrollPane bottomSide, ScrollPane topSide){
		AnchorPane.setTopAnchor(topSide, inset);
		AnchorPane.setBottomAnchor(bottomSide, inset);
	}
	
	private void setupBar(double inset, double size, ScrollPane pane){
		AnchorPane.setLeftAnchor(pane, inset);
		
		AnchorPane.setRightAnchor(pane, 48.0);
		
		pane.setBackground(new Background(new BackgroundFill(CustomColors.GREEN_200,null,null)));
		pane.setStyle("-fx-background: #" + UIHelper.colorToHex(CustomColors.GREEN_200) + ";");

		UIHelper.setDropShadow(pane);
		pane.prefHeightProperty().bind(this.heightProperty().divide(size).subtract(inset * 3 / 2));
	}
	
	public LevelData getLevelData(){
		return myData;
	}

}
