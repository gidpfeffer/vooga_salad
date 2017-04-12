package ui.player.inGame;

import ui.general.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gamedata.ActorData;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ui.handlers.UIHandler;
import util.GUIBindingUtil;

/**
 * Creates a pane of all the possible options
 * @author Anngelyque
 *
 */
public class SidePanel {
	
	private ImageViewPane ivp;
	private UIHandler uihandler;
	private Map<String, String> iconImages;
	private Pane sidePane;
	private Pane root;
	private VBox mainBox;
	private List<OptionsPane> listOfPanes;
	private Map<Integer, Actor> actorsMap;
	
	private Map<Integer, ActorData> towersMap;
	private Map<Integer, ActorData> shotsMap;
	private Map<Integer, ActorData> troopsMap;
	private Map<Integer, ActorData> basesMap;
	
	private static final String panel = "panel.css";
	
	public Pane getSidePane(){
		return sidePane;
	}
	
	public SidePanel(UIHandler uihandler, Map<Integer, Actor> actorsMap, AnchorPane root, Map<Integer, ActorData> towersMap, 
			 Map<Integer, ActorData> shotsMap,  Map<Integer, ActorData> enemiesMap,
			 Map<Integer, ActorData> basesMap, ImageViewPane ivp) {
		this.ivp = ivp;
		this.uihandler = uihandler;
		this.root = root;
		this.actorsMap = actorsMap;
		this.towersMap = towersMap;
		this.shotsMap = shotsMap;
		this.troopsMap = enemiesMap;
		this.basesMap = basesMap;
		this.listOfPanes = new ArrayList<>();
		this.sidePane = new AnchorPane();
		this.iconImages = new HashMap<>();
		this.mainBox = new VBox(20);
		addMainIcons();
		setup();
	}
	
	private void addMainIcons() {
		iconImages.put("Tower", "tower_icon.png");
		iconImages.put("Base", "base_icon.png");
		iconImages.put("Troop", "enemy_icon.png");
		iconImages.put("Shot", "projectile_icon.png");
	}
	
	public void setup() {
		createInternalPanes();
		linkMainPaneToInternalPanes();
		addAnimationButtons();
	}
	
	private void createInternalPanes() {
		OptionsPane towers = getPane(towersMap, "Tower");
		OptionsPane shots = getPane(shotsMap, "Shot");
		OptionsPane	troops = getPane(troopsMap, "Troop");
		OptionsPane bases = getPane(basesMap, "Base");
		OptionsPane[] otherList = new OptionsPane[] {towers, troops, shots, bases};
		listOfPanes.addAll(Arrays.asList(otherList));
	}
	
	private void linkMainPaneToInternalPanes() {
		mainBox.getStylesheets().add(panel);
		for (Map.Entry<String, String> entry : iconImages.entrySet()) {
			OptionButton optionButton = new OptionButton(0, entry.getKey(), entry.getValue(), openPane);
			listOfPanes.stream().filter(pane -> pane.getPaneName().equals(entry.getKey())).forEach(pane -> GUIBindingUtil.bindVisisble(optionButton.getButton(), pane.getMap().keySet()));;
			mainBox.getChildren().add(optionButton.getButton());
		}
		//DIFFERENT
		//root.getChildren().add(mainBox);
		sidePane.getChildren().add(mainBox);
		mainBox.setAlignment(Pos.CENTER_RIGHT);
		
		AnchorPane.setRightAnchor(mainBox, 0.0);
		AnchorPane.setBottomAnchor(mainBox, 20.0);
		AnchorPane.setTopAnchor(mainBox, 20.0);
	}
	
	private void addAnimationButtons() {
		HBox animationButtons = new HBox(20);
		animationButtons.getStylesheets().add(panel);
		OptionButton play = new OptionButton(0, "Play", "play_icon.png", e -> uihandler.play());
		OptionButton pause = new OptionButton(0, "Pause", "pause_icon.png", e -> uihandler.pause());
		OptionButton stop = new OptionButton(0, "Stop", "stop_icon.png", e -> uihandler.stop());
		animationButtons.getChildren().addAll(play.getButton(), pause.getButton(), stop.getButton());
		root.getChildren().add(animationButtons);
		AnchorPane.setBottomAnchor(animationButtons, 10.0);
		AnchorPane.setRightAnchor(animationButtons, 10.0);
	}
	
	public void addInternalPanesToRoot() {
		for (OptionsPane op : listOfPanes) {
			root.getChildren().add(op.getStackPane());
			AnchorPane.setRightAnchor(op.getStackPane(), -op.getWidth());
		}
	}
	
	private OptionsPane getPane (Map<Integer, ActorData> map, String name) {
		OptionsPane optionPane = new OptionsPane(uihandler, root, actorsMap, map, name, ivp);
		optionPane.setHeight(300);
		optionPane.setWidth(100);
		return optionPane;
	}
	
	EventHandler<MouseEvent> openPane = new EventHandler<MouseEvent>()  {
	    @Override
	    public void handle( final MouseEvent ME ) {
	        Object obj = ME.getSource();

	        if ( obj instanceof Button ) {
	        	for (OptionsPane optionsPane : listOfPanes) {
	        		if (((Button) obj).getText().equals(optionsPane.getPaneName())) {
	        			//GUIBindingUtil.bindVisisble((Button) obj, optionsPane.getMap().keySet());
	        			System.out.println("testcollection");
	    	    		System.out.println(optionsPane.getMap().entrySet());
	    	    		TranslateTransition t = new TranslateTransition(Duration.seconds(0.3));
	    	    		System.out.println(optionsPane.getPaneName());
	    	    		t.setNode(optionsPane.getPane());
	    	    		t.setToX(-optionsPane.getWidth());
	    	    		t.play();
	        		}
	        	}
	        }
	    }
	};
}
