package ui.player.inGame;

import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ui.handlers.UIHandler;

/**
 * Creates a pane of all the possible options
 * @author Anngelyque
 *
 */
public class SidePanel {
	
	private UIHandler uihandler;
	private Map<String, String> iconImages;
	private Pane sidePane;
	private List<OptionsPane> listOfPanes;
	private Pane root;
	private List<Actor> actorsList;
	
	private Map<Integer, ActorData> towersMap;
	private Map<Integer, ActorData> shotsMap;
	private Map<Integer, ActorData> troopsMap;
	private Map<Integer, ActorData> basesMap;
	
	
	public Pane getSidePane(){
		return sidePane;
	}
	
	public SidePanel(UIHandler uihandler, List<Actor> actorsList, AnchorPane root, Map<Integer, ActorData> towersMap, 
			 Map<Integer, ActorData> shotsMap,  Map<Integer, ActorData> enemiesMap,
			 Map<Integer, ActorData> basesMap) {
		this.uihandler = uihandler;
		this.root = root;
		this.actorsList = actorsList;
		this.listOfPanes = new ArrayList<>();
		sidePane = new AnchorPane();
		iconImages = new HashMap<>();
		iconImages.put("Tower", "tower_icon.png");
		iconImages.put("Base", "base_icon.png");
		iconImages.put("Troop", "enemy_icon.png");
		iconImages.put("Shot", "projectile_icon.png");
		this.towersMap = towersMap;
		this.shotsMap = shotsMap;
		this.troopsMap = enemiesMap;
		this.basesMap = basesMap;
		setup();
	}
	
	public void setup() {
		createInternalPanes();
		linkMainPaneToInternalPanes();
	}
	
	private void createInternalPanes() {
		OptionsPane towers = getPane(towersMap);
		OptionsPane shots = getPane(shotsMap);
		OptionsPane	troops = getPane(troopsMap);
		OptionsPane bases = getPane(basesMap);
		OptionsPane[] otherList = new OptionsPane[] {towers, troops, shots, bases};
		listOfPanes.addAll(Arrays.asList(otherList));
		//listOfPanes.add((OptionsPane) Arrays.asList(towers, shots, troops, bases));
	}
	
	private void linkMainPaneToInternalPanes() {
		VBox mainBox = new VBox(20);
		for (Map.Entry<String, String> entry : iconImages.entrySet()) {
			OptionButton optionButton = new OptionButton(0, entry.getKey(), entry.getValue(), openPane);
			mainBox.getChildren().add(optionButton.getButton());
		}
		sidePane.getChildren().add(mainBox);
		mainBox.setAlignment(Pos.CENTER_RIGHT);
		AnchorPane.setRightAnchor(mainBox, 0.0);
		AnchorPane.setBottomAnchor(mainBox, 20.0);
		AnchorPane.setTopAnchor(mainBox, 20.0);
	}
	
	private OptionsPane getPane (Map<Integer, ActorData> map) {
		OptionsPane optionPane = new OptionsPane(uihandler, root, actorsList, map);
		optionPane.setHeight(300);
		optionPane.setWidth(200);
		//optionPane.setMap(map);
		return optionPane;
	}
	
	EventHandler<MouseEvent> openPane = new EventHandler<MouseEvent>()  {
	    @Override
	    public void handle( final MouseEvent ME ) {
	        Object obj = ME.getSource();

	        if ( obj instanceof Button ) {
	        	for (OptionsPane optionsPane : listOfPanes) {
	        		if (((Button) obj).getText().equals(optionsPane.getPaneName())) {
	    	    		TranslateTransition t = new TranslateTransition(Duration.seconds(0.3));
	    	    		t.setNode(optionsPane.getPane());
	    	    		t.setToX(optionsPane.getWidth());
	    	    		t.play();
	        		}
	        	}
	        }
	    }
	};
}
