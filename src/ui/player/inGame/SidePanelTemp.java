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
import javafx.scene.text.Text;
import javafx.util.Duration;
import ui.handlers.UIHandler;

/**
 * Creates a pane of all the possible options
 * @author Anngelyque
 *
 */
public class SidePanelTemp {
	
	private UIHandler uihandler;
	private Map<String, String> iconImages;
	private Pane sidePane;
	private List<OptionsPane> listOfPanes;
	private Pane root;
	private List<Actor> actorsList;
	
	//temp
	private Map<Integer, List<String>> towersMapT;
	private Map<Integer, List<String>> shotsMapT;
	private Map<Integer, List<String>> troopsMapT;
	private Map<Integer, List<String>> basesMapT;
	
	
	public Pane getSidePane(){
		return sidePane;
	}
	
	//temp
	public SidePanelTemp(UIHandler uihandler, List<Actor> actorsList, AnchorPane root, TempData tempData) {
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
		this.towersMapT = tempData.getTempTowers();
		this.shotsMapT = tempData.getTempShots();
		this.troopsMapT = tempData.getTempTroops();
		this.basesMapT = tempData.getTempBases();
		setup();
	}
	
	public void setup() {
		createInternalPanes();
		linkMainPaneToInternalPanes();
	}
	
	private void createInternalPanes() {
		OptionsPane towers = getPane(towersMapT);
		OptionsPane shots = getPane(shotsMapT);
		OptionsPane	troops = getPane(troopsMapT);
		OptionsPane bases = getPane(basesMapT);
		OptionsPane[] otherList = new OptionsPane[] {towers, troops, shots, bases};
		listOfPanes.addAll(Arrays.asList(otherList));
		//listOfPanes.add((OptionsPane) Arrays.asList(towers, shots, troops, bases));
	}
	
	private void linkMainPaneToInternalPanes() {
		VBox mainBox = new VBox(20);
		for (Map.Entry<String, String> entry : iconImages.entrySet()) {
			//System.out.println(entry.getKey()+ entry.getValue());
			OptionButton optionButton = new OptionButton(0, entry.getKey(), entry.getValue(), openPane);
			mainBox.getChildren().add(optionButton.getButton());
		}
		root.getChildren().add(mainBox);
		sidePane.getChildren().add(mainBox);
		//root.getChildren().add(sidePane);
		mainBox.setAlignment(Pos.CENTER_RIGHT);
		AnchorPane.setRightAnchor(mainBox, 0.0);
		AnchorPane.setBottomAnchor(mainBox, 20.0);
		AnchorPane.setTopAnchor(mainBox, 20.0);
	}
	
	public void addInternalPanesToRoot() {
		for (OptionsPane op : listOfPanes) {
			root.getChildren().add(op.getStackPane());
			AnchorPane.setRightAnchor(op.getStackPane(), -op.getWidth());
		}
	}
	
	private OptionsPane getPane (Map<Integer, List<String>> map) {
		OptionsPane optionPane = new OptionsPane(uihandler, root, actorsList, map);
		optionPane.setHeight(300);
		optionPane.setWidth(100);
		//optionPane.setTempMap(map);
/*		root.getChildren().add(optionPane.getStackPane());
		AnchorPane.setRightAnchor(optionPane.getStackPane(), -optionPane.getWidth());*/
		//figure out side panel length
		//optionPane.getPane().setLayoutX(500 optionPane.getWidth());
		return optionPane;
	}
	
	EventHandler<MouseEvent> openPane = new EventHandler<MouseEvent>()  {
	    @Override
	    public void handle( final MouseEvent ME ) {
	        Object obj = ME.getSource();

	        if ( obj instanceof Button ) {
	        	for (OptionsPane optionsPane : listOfPanes) {
	        		if (((Button) obj).getText().equals(optionsPane.getTempPaneName())) {
	    	    		TranslateTransition t = new TranslateTransition(Duration.seconds(0.3));
	    	    		
	    	    		t.setNode(optionsPane.getPane());
	    	    		t.setToX(-optionsPane.getPane().getPrefWidth());
	    	    		t.play();
	        		}
	        	}
	        }
	    }
	};
}
