package ui.authoring;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import factories.OptionGenerator;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import ui.Preferences;
import ui.authoring.delegates.*;
import ui.authoring.level.LevelEditorView;
import ui.authoring.map.MapEditorView;
import ui.general.CustomColors;
import ui.general.ImageButton;
import ui.general.UIHelper;
import util.Location;


public class AuthoringView extends AnchorPane {

	private final double SIDE_PANE_WIDTH = 200;
	private final double SIDE_PANE_WIDTH_MIN = 160;
	private final Color THEME_COLOR = CustomColors.GREEN_200;
	
	
	private BorderPane myBorderPane = new BorderPane();
	private LevelEditorView myLevelView;
	private MapEditorView myMapView;
	private LeftPaneView myLeftPane; //purpose of this pane is to flip animate 
	private MenuView myMenuView;
	private Pane myDimmerView;
	private FadeTransition dimAnimator;


	public AuthoringView() {
		UIHelper.setBackgroundColor(this, Color.WHITE);	
		setupViews();
	}

	private void setupViews() {
		setupTitle();
		setupMapView();
		setupLeftPane();
		setupLevelView();
		
		setupBottomPane();
		setupMargins();
		setupBorderPane();
		setupMenuView();
		setupDimmerView();
	}


	private void setupDimmerView() {
		myDimmerView = new Pane();
		UIHelper.setBackgroundColor(myDimmerView, Color.rgb(0, 0, 0, 0.75));
		AnchorPane.setBottomAnchor(myDimmerView, 0.0);
		AnchorPane.setTopAnchor(myDimmerView, 0.0);
		AnchorPane.setRightAnchor(myDimmerView, 0.0);
		AnchorPane.setLeftAnchor(myDimmerView, 0.0);
		this.getChildren().add(myDimmerView);
		myDimmerView.setPickOnBounds(false);
		dimAnimator = new FadeTransition(Duration.seconds(0.4));
		dimAnimator.setNode(myDimmerView);
		setDim(false, Duration.seconds(1));
		
	}
	
	private void setDim(boolean b, Duration d){
		if(b){
			dimAnimator.setToValue(1.0);
			dimAnimator.setOnFinished(e -> {});
			this.getChildren().add(myDimmerView);
		}
		else{
			dimAnimator.setToValue(0.0);
			dimAnimator.setOnFinished(e -> this.getChildren().remove(myDimmerView));
		}
		dimAnimator.setDuration(d);
		dimAnimator.play();
			
	}

	private void setupBorderPane() {
		AnchorPane.setBottomAnchor(myBorderPane, 0.0);
		AnchorPane.setTopAnchor(myBorderPane, 0.0);
		AnchorPane.setLeftAnchor(myBorderPane, 0.0);
		AnchorPane.setRightAnchor(myBorderPane, 0.0);
		this.getChildren().add(myBorderPane);
		
	}

	private void setupMenuView() {
		
		ImageButton menuButton = new ImageButton("menu_icon.png", new Location(40.0,40.0));
		menuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> slideMenuIn());
		AnchorPane.setLeftAnchor(menuButton, 4.0);
		AnchorPane.setTopAnchor(menuButton, 12.0);
		UIHelper.setDropShadow(menuButton);
		this.getChildren().add(menuButton);
		
		double width = 300;
		myMenuView = new MenuView(new MenuViewDelegate());
		myMenuView.setLayoutX(-width - 5);
		myMenuView.setPrefWidth(width);
		UIHelper.setBackgroundColor(myMenuView, CustomColors.GREEN);
		UIHelper.setDropShadow(myMenuView);
		AnchorPane.setTopAnchor(myMenuView, 0.0);
		AnchorPane.setBottomAnchor(myMenuView, 0.0);
		this.getChildren().add(myMenuView);

	}

	private void setupMargins(){
		double ins = 10;
		BorderPane.setMargin(myLeftPane, new Insets(ins));
		BorderPane.setMargin(myMapView, new Insets(ins));
		BorderPane.setMargin(myLevelView, new Insets(ins));
		
	}

	private void setupTitle() {
		Label title = new Label("Authoring Environment");
		title.setFont(Preferences.FONT_BIG_BOLD);
		title.setPrefWidth(Preferences.SCREEN_WIDTH);
		title.setTextFill(Color.rgb(0, 0, 0, 0.8));
		title.setAlignment(Pos.CENTER);
		title.setPrefHeight(60);
		UIHelper.setDropShadow(title);
		this.myBorderPane.setTop(title);
	}

	private void setupMapView() {
		//this calculation assumes that height < width

		myMapView = new MapEditorView();
		myMapView.setMaxWidth(Preferences.SCREEN_WIDTH - 2*SIDE_PANE_WIDTH_MIN);
		UIHelper.setBackgroundColor(myMapView, THEME_COLOR);
		UIHelper.setDropShadow(myMapView);
		myBorderPane.setCenter(myMapView);
		BorderPane.setAlignment(myMapView, Pos.CENTER);

	}

	private void setupLevelView() {
		myLevelView = new LevelEditorView(new PopDelegate());
		UIHelper.setBackgroundColor(myLevelView, THEME_COLOR);
		UIHelper.setDropShadow(myLevelView);
		myLevelView.setMinWidth(SIDE_PANE_WIDTH_MIN);
		myLevelView.setPrefWidth(SIDE_PANE_WIDTH);
		
		this.myBorderPane.setRight(myLevelView);
		
	}
	
	private void setupLeftPane(){
		myLeftPane = new LeftPaneView(new PopDelegate());
		myLeftPane.setMinWidth(SIDE_PANE_WIDTH_MIN);
		myLeftPane.setPrefWidth(SIDE_PANE_WIDTH);
		AnchorPane.setBottomAnchor(myLeftPane, 12.0);
		AnchorPane.setTopAnchor(myLeftPane, 12.0);
		AnchorPane.setLeftAnchor(myLeftPane, 12.0);
		UIHelper.setBackgroundColor(myLeftPane,THEME_COLOR);
		UIHelper.setDropShadow(myLeftPane);
		this.myBorderPane.setLeft(myLeftPane);
	}

	
	private void setupBottomPane() {
		Pane pane = new Pane();
		pane.setPrefHeight(60);
		this.myBorderPane.setBottom(pane);
	}
	
	private void slideMenuIn(){
		System.out.println("menu pressed");
		TranslateTransition t = new TranslateTransition(Duration.seconds(0.2));
		t.setNode(myMenuView);
		t.setByX(myMenuView.widthProperty().doubleValue());
		t.play();
	}
	private void slideMenuOut(){
		TranslateTransition t = new TranslateTransition(Duration.seconds(0.2));
		t.setNode(myMenuView);
		t.setToX(0);
		t.play();
	}
	private void openPaneWithAnimation(Pane pane){
		setDim(true, Duration.seconds(0.4));//dim background

		double inset = 32;
		AnchorPane.setBottomAnchor(pane, inset);
		AnchorPane.setTopAnchor(pane, inset);
		AnchorPane.setLeftAnchor(pane, inset);
		AnchorPane.setRightAnchor(pane, inset);
		pane.setScaleX(0);
		pane.setScaleY(0);
		this.getChildren().add(pane);
		
		Duration dur = Duration.seconds(0.5);
		ScaleTransition st = new ScaleTransition(dur);
		st.setNode(pane);
		st.setToX(1);
		st.setToY(1);
		st.play();
	}
	private void closePaneWithAnimation(Pane pane){
		setDim(false, Duration.seconds(0.4));
		ScaleTransition s = new ScaleTransition(Duration.seconds(0.5));
		s.setNode(pane);
		s.setToX(0);
		s.setToY(0);
		s.play();
		s.setOnFinished(e -> this.getChildren().remove(pane));
	}
	
	
	//MARK: delegate classs
	
	class MenuViewDelegate implements MenuDelegate{

		@Override
		public void didPressBackButton() {
			slideMenuOut();
			
		}
		
	}

	
	class PopDelegate implements PopViewDelegate{

		@Override
		public void openView(Pane pane) {
			openPaneWithAnimation(pane);
		}

		@Override
		public void closeView(Pane pane) {
			closePaneWithAnimation(pane);
		}

		
		
	}

	/**
	 * This method is meant to used by the components of the authoring environment.
	 * This is basically a helper method 
	 * 
	 * @param inputType the class type that is required for the user to input
	 * @return a node that will allow the user to input whatever kind of data
	 * is needed.
	 */
	public static Pane getInputNodeForInputType(Class inputType) {
		if(
				inputType.isInstance(double.class) ||
				inputType.isInstance(int.class)||
				inputType.isInstance(Integer.class)||
				inputType.isInstance(Double.class)
				){
			Pane p = new Pane();
			p.getChildren().add(new TextField());
			return p;
		}
		System.out.println("ERROR: class type not accounted for");
		return null;
	}

}
