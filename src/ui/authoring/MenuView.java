package ui.authoring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import gamedata.PreferencesData;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ui.Preferences;
import ui.authoring.delegates.MenuDelegate;
import ui.general.CustomColors;
import ui.general.StackButton;
import ui.general.ToggleSwitch;
import ui.general.UIHelper;

public class MenuView extends AnchorPane {

	private static final String NUM_LIVES_LABEL = "Number of Lives";
	private static final String ENEMY_LOOP_LABEL = "Loop Enemies";
	private static final String TOWERS_ATTACKABLE_LABEL = "Attackable Towers";
	private static final String WANT_MONEY_LABEL = "Money";
	private static final String EXP_BY_LEVEL_LABEL = "Level Experience";
	private static final String PAUSE_BETWEEN_WAVES_LABEL = "Buffer Waves";
	private static final String CLEAN_LEVEL_LABEL = "Clean Level";
	
	private MenuDelegate myDelegate;
	private PreferencesData myData;
	private VBox myVBox;
	
	private Collection<String> mySwitchTitles;
	
	private Map<String, ToggleSwitch> myPreferences;

	public MenuView(MenuDelegate delegate) {
		super();
		myDelegate = delegate;
		myData = new PreferencesData();
		myPreferences = new HashMap<>();
		mySwitchTitles = new ArrayList<>(Arrays.asList(ENEMY_LOOP_LABEL, TOWERS_ATTACKABLE_LABEL, WANT_MONEY_LABEL, EXP_BY_LEVEL_LABEL,
				PAUSE_BETWEEN_WAVES_LABEL, CLEAN_LEVEL_LABEL)); 
		setupViews();
	}

	private void setupViews() {
		setupBackButton();
		setupVBox();
		setupSaveLoadButtons();
	}
	
	private void setupSaveLoadButtons() {
		StackPane save = UIHelper.buttonStack(e-> saveButtonClicked(), Optional.of(getPlainLabel("Save")),
				Optional.ofNullable(null), Pos.CENTER, false); //TODO remove
		StackPane load = UIHelper.buttonStack(e-> loadButtonClicked(), Optional.of(getPlainLabel("Load")),
				Optional.ofNullable(null), Pos.CENTER, false);
		save.setPrefHeight(40);
		load.setPrefHeight(40);
		UIHelper.setBackgroundColor(load, Color.TRANSPARENT);
		UIHelper.setBackgroundColor(save, Color.TRANSPARENT);
		
		Pane spacing = new Pane();
		spacing.setPrefHeight(40);
		myVBox.getChildren().addAll(spacing,load,save);
		
	}
	private Label getPlainLabel(String s){
		Label label = new Label(s);
		label.setFont(Preferences.FONT_SMALL);
		label.setTextFill(Color.WHITE);
		return label;
	}
	private void saveButtonClicked(){
		myDelegate.didPressSaveButton();
		myDelegate.didPressBackButton();
	}
	private void loadButtonClicked(){
		myDelegate.didPressLoadButton();
		myDelegate.didPressBackButton();
	}

	private void setupVBox() {
		myVBox = new VBox(3);
		myVBox.setAlignment(Pos.CENTER_LEFT);
		makeNumLivesField();
		//myVBox.setBackground(UIHelper.backgroundForColor(CustomColors.INDIGO));
		mySwitchTitles.stream().forEach(t -> makeSwitchEntry(t));
		double inset = 10.0;
		setTopAnchor(myVBox, inset * 6);
		setBottomAnchor(myVBox, inset * 4);
		setLeftAnchor(myVBox, inset * 1.5);
		setRightAnchor(myVBox, inset);
		this.getChildren().add(myVBox);
	}

	/**
	 * 
	 */
	private void makeSwitchEntry(String title) {
		// TODO Auto-generated method stub
		ToggleSwitch s = new ToggleSwitch(title);
		myVBox.getChildren().add(makeField(title, s.getNode()));
		myPreferences.put(title, s);
	}


	private void makeNumLivesField() {
		TextField field = new TextField();
		field.textProperty().addListener(new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	            if (!newValue.matches("\\d*")) {
	                field.setText(newValue.replaceAll("[^\\d]", ""));
	            }
	            try {
	            	myData.setNumLives(Optional.of(Integer.parseInt(newValue)));
	            } catch (Exception e) {}
	        }
	    });
		field.setMaxWidth(80);
		myVBox.getChildren().add(makeField(NUM_LIVES_LABEL, field));

	}

	private StackButton makeField(String title, Node entry) {
		StackButton button = new StackButton();
		button.setShadow();
		Label label = new Label(title);
		label.setFont(Preferences.FONT_SMALL_BOLD);
		label.setTextFill(Color.WHITE);
		label.setPrefWidth(136);
		label.setAlignment(Pos.CENTER_LEFT);
		button.setIconNode(label);
		button.setIconInsets(new Insets(8, 8, 8, 8));
		button.setCenterNode(entry);
		button.setCenterInsets(new Insets(8, 8, 8, 200));
		button.setHeight(56);
		return button;
	}

	private void setupBackButton() {
		ImageView iv = new ImageView(new Image("back_icon.png"));
		iv.setFitWidth(36);
		iv.setPreserveRatio(true);

		StackPane button = UIHelper.buttonStack(e -> myDelegate.didPressBackButton(), Optional.ofNullable(null),
				Optional.of(iv), Pos.CENTER, true);
		AnchorPane.setTopAnchor(button, 8.0);
		AnchorPane.setRightAnchor(button, 12.0);
		UIHelper.setBackgroundColor(button, Color.rgb(0, 0, 0, 0));
		this.getChildren().add(button);
	}

	
	public PreferencesData getData() {
		updateData();
		return myData;
	}
	
	private void updateData() {
		myData.setCleanLevel(Optional.of(myPreferences.get(CLEAN_LEVEL_LABEL).switchOnProperty()));
		myData.setEnemyLoop(Optional.of(myPreferences.get(ENEMY_LOOP_LABEL).switchOnProperty()));
		myData.setExpByLevel(Optional.of(myPreferences.get(EXP_BY_LEVEL_LABEL).switchOnProperty()));
		myData.setPauseBetweenWaves(Optional.of(myPreferences.get(PAUSE_BETWEEN_WAVES_LABEL).switchOnProperty()));
		myData.setTowersAttackable(Optional.of(myPreferences.get(TOWERS_ATTACKABLE_LABEL).switchOnProperty()));
		myData.setWantMoney(Optional.of(myPreferences.get(WANT_MONEY_LABEL).switchOnProperty()));
	}

}