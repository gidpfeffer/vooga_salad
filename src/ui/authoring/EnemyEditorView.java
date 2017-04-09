package ui.authoring;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import gamedata.ActorData;
import gamedata.FieldData;
import gamedata.composition.BasicData;
import gamedata.util.OptionGenerator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import types.BasicActorType;
import ui.Preferences;
import ui.authoring.delegates.PopViewDelegate;
import ui.general.CustomColors;
import ui.general.ImageButton;
import ui.general.UIHelper;
import util.Location;

//sprite
//health
//speed

//name
//canattacktowers
//projectiles
//attack		
public class EnemyEditorView extends AnchorPane {
	private PopViewDelegate myDelegate;
	private VBox  myEnemyView;
	private HashMap<StackPane, List<ActorData>> myEnemies;
	private static final Map<String,String> DEFAULT_ENEMIES=getDefaultTowers();
	private static Map<String,String >getDefaultTowers(){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("add", "add_icon.png");
		map.put("clear","clear_icon.png");
		return map;
	}
	// TODO: make abstract class for editorviews
	public EnemyEditorView(PopViewDelegate delegate) {
		super();
		myDelegate = delegate;
		myEnemies=new HashMap<StackPane, List<ActorData>>();
		setupViews();

	}
	private void setupViews() {
		ScrollPane leftSide = new ScrollPane();
		ScrollPane rightSide = new ScrollPane();
		setupSides(leftSide, rightSide);
		setupVBox(leftSide);
		setupAddEnemyButton();
		setupDefaultEnemies();
		setupBackButton();
		Map<String,List<FieldData>> map=OptionGenerator.getPropertyTypesWithArgs();
		System.out.println(map);
	}
	private void setupDefaultEnemies() {

		for (Entry<String, String> entry : DEFAULT_ENEMIES.entrySet()) {
			addEnemy(entry.getValue(), entry.getKey());
		}
	}
private void addEnemy(String imgPath, String name){
	System.out.println(imgPath);
	Image img = new Image(imgPath);
	ImageView imageView = new ImageView(img);
	imageView.setFitWidth(40);
	imageView.setPreserveRatio(true);
	StackPane lblWrapper = new StackPane();
	TextField field = new TextField(name);
	field.setFont(Preferences.FONT_MEDIUM);
	field.setAlignment(Pos.CENTER);
	field.setBackground(UIHelper.backgroundForColor(CustomColors.GREEN));
	field.setStyle("-fx-text-fill-color: #FFFFFF");
	field.setStyle("-fx-background-color: #" +UIHelper.colorToHex(CustomColors.GREEN_200) + ";");
	StackPane.setMargin(field, new Insets(8,8,8,64));
	lblWrapper.getChildren().add(field);
	
	StackPane view = UIHelper.buttonStack(e -> {}, 
			Optional.of(field), Optional.of(imageView), 
			Pos.CENTER_LEFT, true);
	view.setPrefHeight(64);
	view.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> selectEnemy(view));
	UIHelper.setBackgroundColor(view, CustomColors.GREEN);
	VBox.setMargin(view, new Insets(8,24,8,8));
	myEnemies.put(view, Arrays.asList(new ActorData[] { new ActorData(BasicActorType.Troop, new BasicData(name, imgPath))}));
	this.myEnemyView.getChildren().add(myEnemyView.getChildren().size()-1, view);		
}
	
	private void setupBackButton() {
		ImageButton b = new ImageButton("back_icon.png", new Location(30., 30.));
		AnchorPane.setTopAnchor(b, 4.0);
		AnchorPane.setLeftAnchor(b, 4.0);
		b.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> myDelegate.closeView(this));
		this.getChildren().add(b);
	}
	private void setupAddEnemyButton() {
		
		Label label = new Label("Add New Tower");
		label.setFont(Preferences.FONT_MEDIUM);
		label.setTextFill(CustomColors.GREEN_100);
		ImageView imageView = new ImageView(new Image("add_icon_w.png"));
		imageView.setFitHeight(40);
		imageView.setPreserveRatio(true);
		StackPane view = UIHelper.buttonStack(e -> {}, 
				Optional.of(label), Optional.of(imageView), 
				Pos.CENTER_LEFT, true);
		view.setPrefHeight(64);
		UIHelper.setBackgroundColor(view, CustomColors.GREEN);
		VBox.setMargin(view, new Insets(8,24,8,8));
		view.setOnMouseClicked(e ->addNewEnemy());
		this.myEnemyView.getChildren().add( view);

	}
	private void addNewEnemy(){
		FileChooser fileChooser=new FileChooser();
		fileChooser.setTitle("Select image");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files","*.png","*.gif","*.jpg"));
		fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		Stage ownerWindow=new Stage();
		Optional<File> 
		 result = Optional.ofNullable( fileChooser.showOpenDialog(ownerWindow));
		result.ifPresent(e->addEnemy(result.get().getName(),"Name Unit"));
		
	}
private void setupSides(ScrollPane leftSide,ScrollPane rightSide){
	
		double inset = 12.0;

		AnchorPane.setBottomAnchor(rightSide, inset);
		AnchorPane.setBottomAnchor(leftSide, inset);
		AnchorPane.setTopAnchor(rightSide, 48.0);
		AnchorPane.setTopAnchor(leftSide, 48.0);
		AnchorPane.setRightAnchor(rightSide, inset);
		AnchorPane.setLeftAnchor(leftSide, inset);

		rightSide.setStyle("-fx-background-color: #" + UIHelper.colorToHex(CustomColors.GREEN_200) + ";");
		rightSide.setStyle("-fx-background: #" + UIHelper.colorToHex(CustomColors.GREEN_200) + ";");
		leftSide.setStyle("-fx-background-color: #" + UIHelper.colorToHex(CustomColors.GREEN_200) + ";");
		leftSide.setStyle("-fx-background: #" + UIHelper.colorToHex(CustomColors.GREEN_200) + ";");

		leftSide.setHbarPolicy(ScrollBarPolicy.NEVER);
		rightSide.setHbarPolicy(ScrollBarPolicy.NEVER);

		UIHelper.setDropShadow(rightSide);
		UIHelper.setDropShadow(leftSide);
		rightSide.prefWidthProperty().bind(this.widthProperty().divide(3.0/2).subtract(inset * 3 / 2));
		leftSide.prefWidthProperty().bind(this.widthProperty().divide(3.0).subtract(inset * 3 / 2));

		this.getChildren().addAll(leftSide, rightSide);

}

private void setupVBox(ScrollPane pane){
	myEnemyView=new VBox();
	myEnemyView.setAlignment(Pos.CENTER);
	myEnemyView.prefWidthProperty().bind(pane.widthProperty());
	pane.setContent(myEnemyView);
}
private void selectEnemy(StackPane stackButton){
	List<ActorData> data = this.myEnemies.get(stackButton);
	//myTowerInfoView.setActorData(data);
}
}
