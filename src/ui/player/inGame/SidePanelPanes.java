package ui.player.inGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ui.handlers.UIHandler;
import gamedata.ActorData;
import gamedata.GameData;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import ui.player.InGameModel;

public class SidePanelPanes {
	
	private UIHandler uihandler;
	private PaneController main;
	private PaneController shots;
	private PaneController towers;
	private PaneController troops;
	private PaneController bases;
	private List<PaneController> listOfControllers;
	private int spacing = 10;
	private static final double paneWidth = 150.;
	
	public List<PaneController> getListOfControllers(){
		return listOfControllers;
	}
	
	public AnchorPane getMainPane() {
		return main.getPane();
	}
	
/*	public AnchorPane getShotsMenu() {
		return shots.getPane();
	}
	
	public AnchorPane getTowersMenu() {
		return towers.getPane();
	}
	
	public AnchorPane getTroopsMenu() {
		return troops.getPane();
	}
	
	public AnchorPane getBaseMenu() {
		return bases.getPane();
	}*/
	
	public SidePanelPanes(UIHandler uihandler, Map<String, String> mainMenu, List<PaneController> listOfControllers) {
		this.uihandler = uihandler;
		this.listOfControllers = listOfControllers;
		setupMain(mainMenu);
	}
	
	public SidePanelPanes(UIHandler uihandler, Map<Integer, ActorData> shots, Map<Integer, ActorData> towers,
			Map<Integer, ActorData> troops, Map<Integer, ActorData> bases) {
		this.uihandler = uihandler;
		this.listOfControllers = new ArrayList<>();
		setup(shots, towers, troops, bases);
	}
	
	
	public void setupMain(Map<String, String> map) {
		main = new PaneController(new InGameModel(), new OptionsPane(), uihandler);
		//listOfControllers.add(main);
		main.setMainMap(map);
		main.addButtonPane(spacing, clickedToPane);
	}
	
	public void setup(Map<Integer, ActorData> shots, Map<Integer, ActorData> towers,
			Map<Integer, ActorData> troops, Map<Integer, ActorData> bases) {
		setupControllers();
		setupMaps(shots, towers, troops, bases);
		createPanes();
	}

	private void setupControllers() {
		shots = new PaneController(new InGameModel(), new OptionsPane(), uihandler);
		towers = new PaneController(new InGameModel(), new OptionsPane(), uihandler);
		troops = new PaneController(new InGameModel(), new OptionsPane(), uihandler);
		bases = new PaneController(new InGameModel(), new OptionsPane(), uihandler);
		listOfControllers.add((PaneController) Arrays.asList(shots, towers, troops, bases));
		
	}
	
	private void setupMaps(Map<Integer, ActorData> shot, Map<Integer, ActorData> tower,
			Map<Integer, ActorData> troop, Map<Integer, ActorData> base) {
		shots.setMap(shot);
		towers.setMap(tower);
		troops.setMap(troop);
		bases.setMap(base);
	}
	
	private void createPanes() {//images saved in actor data get string path
		for (PaneController controller : listOfControllers) {
			controller.setWidth(paneWidth);
			controller.addButtonPane(spacing, controller.clicked, controller.dragged, controller.done);
			controller.addBackButton(closePane);
			
		}
	}
	
	EventHandler<MouseEvent> closePane = new EventHandler<MouseEvent>()  {
	    @Override
	    public void handle( final MouseEvent ME ) {
	        Object obj = ME.getSource();  // you can also try ME.getTarget()

	        if ( obj instanceof Button ) {
	        	System.out.println("button should slide back in");
	        	for (PaneController controller : listOfControllers) {
	        		if (((Button) obj).getText().equals(controller.getClass().getSimpleName())) {
	    	    		TranslateTransition t = new TranslateTransition(Duration.seconds(0.2));
	    	    		t.setNode(controller.getPane());
	    	    		t.setToX(-(controller.getWidth()));
	    	    		t.play();
	        		}
	        	}
	        }
	    }
	};
	
	EventHandler<MouseEvent> clickedToPane = new EventHandler<MouseEvent>()  {
	    @Override
	    public void handle( final MouseEvent ME ) {
	        Object obj = ME.getSource();  //how to link to correct pane?? have to do correct controllers.getPane()

	        if ( obj instanceof Button ) {
	        	System.out.println("button should slide out");
	        	for (PaneController controller : listOfControllers) {
	        		if (((Button) obj).getText().equals(controller.getClass().getSimpleName())) {
	    	    		TranslateTransition t = new TranslateTransition(Duration.seconds(0.2));
	    	    		t.setNode(controller.getPane());
	    	    		t.setToX(controller.getWidth());
	    	    		t.play();
	        		}
	        	}
	        }
	    }
	};
}

