package ui.authoring.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import ui.general.*;
import util.*;


public class MapEditorView extends StackPane{
	
	private final String DEFAULT_BACKGROUND_PATH = "default_map_background_0.jpg";
	
	private List<Location> myPoints = new ArrayList<Location>();
	private ImageViewPane myBackgroundView = new ImageViewPane(new ImageView(new Image(DEFAULT_BACKGROUND_PATH)));
	public MapEditorView(){
		super();
		setupViews();
		setupMouseEvents();
	}

	private void setupMouseEvents(){
		this.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> this.getScene().setCursor(Cursor.CROSSHAIR));
		this.addEventHandler(MouseEvent.MOUSE_EXITED, e -> this.getScene().setCursor(Cursor.DEFAULT));
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> handleMouseClick(e));
	}
	
	private void handleMouseClick(MouseEvent e){
			addPointToMap(e);
		
	}
	
	private void addPointToMap(MouseEvent e){
		//TODO backend data
		Location loc = new Location(e.getX(),e.getY());
		Point p;
		if(e.getButton().equals(MouseButton.SECONDARY)){
			p = new Point(loc, PointType.EXIT);
		}else{
			if(this.myPoints.isEmpty())
				p = new Point(loc,PointType.ENTRY); //TODO remove magic nums
			else
				p = new Point(loc, PointType.PATH);

		}
		this.getChildren().add(p);
		this.myPoints.add(loc);
		
	}
	
	private void setupViews() {
		setupBackground();
		setupUndoButton();
	}
	
	private void setupBackground() {
		AnchorPane.setLeftAnchor(myBackgroundView, 0.0);
		AnchorPane.setRightAnchor(myBackgroundView, 0.0);
		this.getChildren().add(myBackgroundView);
	}

	private void setupUndoButton() {
		ImageButton b = new ImageButton( "undo_icon.png", new Location(32.0,32.0));
		b.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> undoAction(e));
		StackPane.setAlignment(b, Pos.BOTTOM_RIGHT);
		StackPane.setMargin(b, new Insets(10));
		this.getChildren().add(b);
	}

	private void undoAction(MouseEvent e){
		e.consume();
		System.out.println("undo clicked");
	}
	
	
	//PUBLIC METHODS
	
	public void setBackgroundImage(Image image){
		this.myBackgroundView.setImageView(new ImageView(image));;
	}
	
}
