package ui.authoring.level;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.swing.JTabbedPane;

import gamedata.ActorData;
import gamedata.LevelData;
import gamedata.WaveData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import ui.Preferences;
import ui.authoring.delegates.PopViewDelegate;
import ui.general.CustomColors;
import ui.general.ImageButton;
import ui.general.UIHelper;
import util.Location;

public class LevelEditorMenu extends AnchorPane {

	PopViewDelegate myDelegate;
	private WaveData editWave;
	private LevelData myData;
	private List<ActorData> activeEnemies = new ArrayList<ActorData>();
	private Collection<ActorData> enemies;
	private Collection<StackPane> waveBoxes;
	ScrollPane waves;
	ScrollPane actors;
	
	//TODO: PathData needed?
	public LevelEditorMenu(PopViewDelegate delegate, Collection<ActorData> enemies, LevelData level) {
		super();
		myDelegate = delegate;
		waveBoxes = new ArrayList<StackPane>();
		this.enemies = enemies;
		editWave = null;
		myData = level;
		setupViews();
		populateViews();
	}

	private void setupViews() {
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
		HBox root=new HBox();
		root.setSpacing(30);
		int numWaves = myData.getNumWaves();
		for(int i=0; i<numWaves; i++){
			StackPane wave = nextWave();
			waveBoxes.add(wave);
			root.getChildren().add(wave);
		}
		addWaveButton(root);
	}
	
	private void addWaveButton(HBox root){
		StackPane newWave = UIHelper.buttonStack(e->addNewWave(), 
				Optional.of(label("Add Wave")), 
				Optional.of(image("add_icon.png")),
				Pos.CENTER_RIGHT, true);
		newWave.setPrefHeight(56);
		VBox.setMargin(newWave, new Insets(8));
		root.getChildren().add(newWave);
		waves.setContent(root);
	}
	
	private  void populateEnemies(){
		HBox root=new HBox();
		root.setSpacing(30);
		for(ActorData enemy:enemies){
			ImageView image=new ImageView(new Image(enemy.getImagePath()));
			root.getChildren().add(UIHelper.buttonStack(e->toggleActive(enemy),
					Optional.of(label(enemy.getName())), Optional.of(image), Pos.CENTER, true));
		}
		actors.setContent(root);
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
	
	private HBox generateHBox(){
		HBox root=new HBox();
		root.setSpacing(30);
		return root;
	}
	
	private void addNewWave(){
		HBox root= generateHBox();
		StackPane newWave = nextWave();
		Node content = waves.getContent();
		root.getChildren().add(content);
		root.getChildren().add(newWave);
		waveBoxes.add(newWave);
		myData.addWave(new WaveData());
		waves.setContent(root);
	}
	
	private void selectWave(StackPane selected, int wave){

		editWave = myData.getMyWaves().get(wave);
		
		HBox root=generateHBox();
		addWaveButton(root);
		
		for(StackPane box: waveBoxes){
			box.setOpacity(1);
		}
		selected.setOpacity(.5);
		root.getChildren().addAll(waveBoxes);
		
	}
	
	private StackPane nextWave(){
		StackPane nextWave= UIHelper.buttonStack(e->{},  
				Optional.of(label(String.format("      Wave %d       ", myData.getNumWaves()+1))), 
				Optional.ofNullable(null),Pos.CENTER_RIGHT, true);
		nextWave.addEventHandler(MouseEvent.MOUSE_CLICKED, 
				e -> selectWave(nextWave, myData.getNumWaves()-1));
		nextWave.setPrefHeight(56);
		VBox.setMargin(nextWave, new Insets(8));
		return nextWave;
	}
	
	
	private void toggleActive(ActorData enemy){
		if(activeEnemies.contains(enemy)){
			activeEnemies.remove(enemy);
		}
		else{
			activeEnemies.add(enemy);
		}
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
		setupBar(inset,bottomSide);
		setupBar(inset, topSide);
		this.getChildren().addAll(bottomSide, topSide);
	}
	
	private void setVerticalAnchors(double inset, ScrollPane bottomSide, ScrollPane topSide){
		AnchorPane.setTopAnchor(topSide, inset);
		AnchorPane.setBottomAnchor(bottomSide, inset);
	}
	
	private void setupBar(double inset, ScrollPane pane){
		AnchorPane.setLeftAnchor(pane, inset);
		
		AnchorPane.setRightAnchor(pane, 48.0);
		
		pane.setBackground(new Background(new BackgroundFill(CustomColors.GREEN_200,null,null)));
		pane.setStyle("-fx-background: #" + UIHelper.colorToHex(CustomColors.GREEN_200) + ";");

		UIHelper.setDropShadow(pane);
		pane.prefHeightProperty().bind(this.heightProperty().divide(2.0).subtract(inset * 3 / 2));
	}
	
	public LevelData getLevelData(){
		return myData;
	}

}
