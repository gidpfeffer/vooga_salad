package ui.authoring;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import ui.Preferences;
import ui.authoring.level.LevelEditorView;
import ui.general.CustomColors;
import ui.general.Frame;
import ui.general.ImageButton;
import ui.general.UIView;

public class AuthoringView extends UIView {

	private LevelEditorView myLevelView;
	private MapEditorView myMapView;
	private UIView myLeftPane; //purpose of this pane is to flip animate 
	private UIView myLeftPaneFront; //contains the buttons
	private UIView myLeftPaneBack; //contains the views for buttons 
	private PathEditorView myPathView;
	private TowerEditorView myTowerView;
	private EnemyEditorView myEnemyView;


	public AuthoringView() {
		this.setBackgroundColor(CustomColors.GREEN_200);
		setupViews();
	}

	private void setupViews() {
		setupTitle();
		setupMapView();
		setupLevelView();
		setupLeftPane();
	}
	

	private void setupTitle() {
		Label title = new Label("Game Authoring Environment");
		title.setFont(Preferences.FONT_BIG);
		title.setPrefWidth(Preferences.SCREEN_WIDTH);
		title.setTextFill(Color.rgb(0, 0, 0, 0.75));
		title.setAlignment(Pos.CENTER);
		title.setLayoutX(0);
		title.setLayoutY(12);
		this.getChildren().add(title);
	}

	private void setupMapView() {
		//this calculation assumes that height < width
		double inset = 60;
		double height = Math.round(Preferences.SCREEN_HEIGHT - 2*inset);
		int dim = 11;
		height = height - dim%2;
		myMapView = new MapEditorView(dim,dim, 
				new Frame((Preferences.SCREEN_WIDTH - height)/2.0, inset, height, height)
				);
		this.getChildren().add(myMapView);
	}

	private void setupLevelView() {
		myLevelView = new LevelEditorView(
				new Frame(myMapView.getFrame().getMaxX() + 12, myMapView.getFrame().getY(), 
						Preferences.SCREEN_WIDTH - myMapView.getFrame().getMaxX() - 24, myMapView.getFrame().getHeight()));
		this.getChildren().add(myLevelView);
	}


	
	private void setupLeftPane(){
		myLeftPane = new UIView(
				new Frame(12,myMapView.getFrame().getY(), myMapView.getFrame().getX() - 24, myMapView.getFrame().getHeight())
				);
		myLeftPaneFront = new UIView(myLeftPane.getBounds());
		myLeftPaneBack = new UIView(myLeftPane.getBounds());
		setupLeftPaneButtons();
		setupPathView();
		setupTowerView();
		setupEnemyView();
		myLeftPaneBack.setScaleX(0);
		myLeftPane.getChildren().add(myLeftPaneBack);
		myLeftPane.getChildren().add(myLeftPaneFront);
		myLeftPane.setBackgroundColor(CustomColors.GREEN);
		this.getChildren().add(myLeftPane);
	}

	private void setupLeftPaneButtons() {
		VBox v = new VBox();
		Button enemy = new Button();
		
	}

	private void setupTowerView() {
		myTowerView = new TowerEditorView();
		addBackButtonToView(myTowerView);
	}

	private void setupPathView() {
		myPathView = new PathEditorView();
		addBackButtonToView(myPathView);
	}
	
	private void setupEnemyView() {
		myEnemyView = new EnemyEditorView();
		addBackButtonToView(myEnemyView);
	}
	
	private void addBackButtonToView(UIView view){
		//TODO
	}
	
	private void flipFromNodeToNode(Node fromNode, Node toNode){
		ScaleTransition s1 = new ScaleTransition(Duration.millis(500), fromNode);
		s1.setFromX(1);
		s1.setToX(0);
		ScaleTransition s2 = new ScaleTransition(Duration.millis(500), toNode);
		s2.setFromX(0);
		s2.setToX(1);
		s1.setOnFinished(e -> {
			s2.play();
			});
		s1.play();
	}
	

}
