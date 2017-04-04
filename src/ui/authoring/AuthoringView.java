package ui.authoring;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ui.Preferences;
import ui.authoring.level.LevelEditorView;
import ui.authoring.map.MapEditorView;
import ui.general.CustomColors;

import ui.general.UIHelper;


public class AuthoringView extends BorderPane {

	private final double SIDE_PANE_WIDTH = 200;
	private final double SIDE_PANE_WIDTH_MIN = 160;
	private final Color THEME_COLOR = CustomColors.GREEN_100;
	
	private LevelEditorView myLevelView;
	private MapEditorView myMapView;
	private LeftPaneView myLeftPane; //purpose of this pane is to flip animate 



	public AuthoringView() {
		UIHelper.setBackgroundColor(this, Color.WHITE);
	
		setupViews();
	}

	private void setupViews() {
		setupTitle();
		setupMapView();
		setupLevelView();
		setupLeftPane();
		setupBottomPane();
		setupMargins();
	}
	
	private void setupMargins(){
		double ins = 10;
		BorderPane.setMargin(myLeftPane, new Insets(ins));
		BorderPane.setMargin(myMapView, new Insets(ins));
		BorderPane.setMargin(myLevelView, new Insets(ins));
		
	}

	private void setupTitle() {
		Label title = new Label("Game Authoring Environment");
		title.setFont(Preferences.FONT_BIG);
		title.setPrefWidth(Preferences.SCREEN_WIDTH);
		title.setTextFill(Color.rgb(0, 0, 0, 0.75));
		title.setAlignment(Pos.CENTER);
		title.setPrefHeight(60);
		this.setTop(title);
	}

	private void setupMapView() {
		//this calculation assumes that height < width

		myMapView = new MapEditorView();
		myMapView.setMaxWidth(Preferences.SCREEN_WIDTH - 2*SIDE_PANE_WIDTH_MIN);
		UIHelper.setBackgroundColor(myMapView, THEME_COLOR);
		UIHelper.setDropShadow(myMapView);
		this.setCenter(myMapView);
		BorderPane.setAlignment(myMapView, Pos.CENTER);

	}

	private void setupLevelView() {
		myLevelView = new LevelEditorView();
		UIHelper.setBackgroundColor(myLevelView, THEME_COLOR);
		UIHelper.setDropShadow(myLevelView);
		myLevelView.setMinWidth(SIDE_PANE_WIDTH_MIN);
		myLevelView.setPrefWidth(SIDE_PANE_WIDTH);
		
		this.setRight(myLevelView);
		
	}
	
	
	
	


	
	private void setupLeftPane(){
		myLeftPane = new LeftPaneView();
		myLeftPane.setMinWidth(SIDE_PANE_WIDTH_MIN);
		myLeftPane.setPrefWidth(SIDE_PANE_WIDTH);
		AnchorPane.setBottomAnchor(myLeftPane, 12.0);
		AnchorPane.setTopAnchor(myLeftPane, 12.0);
		AnchorPane.setLeftAnchor(myLeftPane, 12.0);
		UIHelper.setBackgroundColor(myLeftPane,THEME_COLOR);
		UIHelper.setDropShadow(myLeftPane);
		this.setLeft(myLeftPane);
	}

	
	private void setupBottomPane() {
		Pane pane = new Pane();
		pane.setPrefHeight(60);
		this.setBottom(pane);
	}

	
	


}
