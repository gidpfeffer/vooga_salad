package ui.player.inGame;

import ui.general.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
 * Creates a pane containing buttons linking to the possible user options.
 * Upon click, the pane with the relative nodes will slide out.
 * 
 * @author anngelyque
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
	
	private Map<Integer, ActorData> options;
	private Map<Integer, ActorData> towersMap;
	private Map<Integer, ActorData> shotsMap;
	private Map<Integer, ActorData> troopsMap;
	private Map<Integer, ActorData> basesMap;
	
	private static final String panel = "panel.css";
	
	public Pane getSidePane(){
		return sidePane;
	}
	
	public SidePanel(UIHandler uihandler, Map<Integer, Actor> actorsMap, AnchorPane root, Map<Integer, ActorData> options, ImageViewPane ivp) {
		this.ivp = ivp;
		this.options = options;
		this.uihandler = uihandler;
		this.root = root;
		this.actorsMap = actorsMap;
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
	
	/**
	 * Creates the panes to link to their respective main buttons.
	 */
	private void createInternalPanes() {
		Set<String> types = new HashSet<>();
		listOfPanes = new ArrayList<>();
		options.keySet().forEach(option -> types.add(options.get(option).getType().toString()));
		//HashSet<String> types2 = options.keySet().forEach(option -> types.add(options.get(option).getType().toString());
		
		types.forEach(type -> {
			Map<Integer, ActorData> map = new HashMap<>();
			options.keySet().forEach(option -> {
				if (options.get(option).getType().toString().equals(type)) {
					map.put(option, options.get(option));
				}
				
			});
			OptionsPane actors = getPane(map, type);
			listOfPanes.add(actors);
		});
	}
	
	/**
	 * Links the internal pane that exists off screen to the main button on screen.
	 * Upon click, the pane will slide out
	 * If pane is empty, no button will be generated / placed on screen
	 */
	private void linkMainPaneToInternalPanes() {
		mainBox.getStylesheets().add(panel);
		for (Map.Entry<String, String> entry : iconImages.entrySet()) {
			OptionButton optionButton = new OptionButton(0, entry.getKey(), entry.getValue(), openPane);
			listOfPanes.stream().filter(pane -> pane.getPaneName().equals(entry.getKey())).forEach(pane -> GUIBindingUtil.bindVisisble(optionButton.getButton(), pane.getMap().keySet()));;
			mainBox.getChildren().add(optionButton.getButton());
		}
		sidePane.getChildren().add(mainBox);
		mainBox.setAlignment(Pos.CENTER_RIGHT);
		
		AnchorPane.setRightAnchor(mainBox, 0.0);
		AnchorPane.setBottomAnchor(mainBox, 20.0);
		AnchorPane.setTopAnchor(mainBox, 20.0);
	}
	
	/**
	 * Creates the bottom animation buttons for playing, pausing, and stoping the animation
	 */
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
			root.getChildren().add(op.getPane());
			AnchorPane.setRightAnchor(op.getPane(), -op.getWidth() - 10);
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
	    	    		TranslateTransition t = new TranslateTransition(Duration.seconds(0.3));
	    	    		t.setNode(optionsPane.getPane());
	    	    		t.setToX(-optionsPane.getWidth());
	    	    		t.play();
	        		}
	        	}
	        }
	    }
	};
}
