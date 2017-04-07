package ui.player.inGame;

import java.util.Collection;
import java.util.Map;

import gamedata.ActorData;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import ui.general.ImageButton;
import ui.handlers.UIHandler;
import ui.player.InGameModel;

public class PaneController {

	private InGameModel model;
	private OptionsPane pane;
	private UIHandler uihandler;
	
	public PaneController(InGameModel model, OptionsPane menu, UIHandler uihandler) {
		this.model = model;
		this.pane = menu;
		this.uihandler = uihandler;
	}
	
	public int getWidth() {
		return pane.getWidth();
	}
	
	public void setMap(Map<Integer, ActorData> map) {
		model.setMap(map);
		pane.setMap(model.getMap());
	}
	
	public void setMainMap(Map<String, String> map) {
		pane.setMainMap(map);
	}
	
	public Map<Integer, ActorData> getMap(){
		return model.getMap();
	}
	
	public AnchorPane getPane() {
		return pane.getPane();
	}
	
	public Collection<Button> getButtonList() {
		return pane.getButtonList();
	}
	
	public void addButtonPane(int spacing, EventHandler<MouseEvent> clicked, EventHandler<MouseEvent> dragged, EventHandler<DragEvent> done) {
		pane.addButtonPane(spacing, clicked, dragged, done);
	}
	
	public void addButtonPane(int spacing, EventHandler<MouseEvent> clicked) {
		pane.addButtonPane(spacing, clicked);
	}
	
	public void addBackButton(EventHandler<MouseEvent> closePane) {
		pane.addBackButton(closePane);
	}
	
	
	EventHandler<MouseEvent> clicked = new EventHandler<MouseEvent>()  {
	    @Override
	    public void handle( final MouseEvent ME ) {
	        Object obj = ME.getSource();

	        if ( obj instanceof Button ) {
	        	System.out.println("button was clicked");
	            System.out.println( ((Button) obj).getText() );
	        }
	    }
	};
	
	EventHandler<MouseEvent> dragged = new EventHandler<MouseEvent>() {
	    @Override
	    public void handle( final MouseEvent ME ) {
	        Object obj = ME.getSource();

	        if ( obj instanceof Button ) {
	        	System.out.println("button was dragged");
	            System.out.println( ((Button) obj).getText() );
	        }
	    }
	};
	
	EventHandler<DragEvent> done = new EventHandler<DragEvent>() {
	    @Override
	    public void handle( final DragEvent ME ) {
	        Object obj = ME.getSource();

	        if ( obj instanceof Button ) {
	        	//create new instance;
	        	System.out.println("button was dropped");
	            System.out.println( ((Button) obj).getText() );
	            //TODO figure out new value x , y
	            uihandler.addGameObject((Integer.parseInt(((Button) obj).getId())), 0, 0);
	        }
	    }
	};
}


