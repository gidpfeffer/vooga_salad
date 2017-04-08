package ui.authoring.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import ui.Preferences;
import ui.general.*;
import util.*;


public class MapEditorView extends StackPane{
	
	private final String DEFAULT_BACKGROUND_PATH = "default_map_background_0.jpg";
	
	private List<Point> myPoints = new ArrayList<Point>();
	private ImageViewPane myBackgroundView = new ImageViewPane(new ImageView(new Image(DEFAULT_BACKGROUND_PATH)));
	private Pane pointsLayerView;
	
	
	public MapEditorView(){
		super();

		setupViews();
		setupMouseEvents();
		this.widthProperty().addListener(e -> sizeDidChange());
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
		Location loc = pixelToGridLocation( new Location(e.getX(),e.getY()));
		Point p;
		double xratio = getScaleXRatio(this.myBackgroundView.getImageView());
		double yratio =getScaleYRatio(this.myBackgroundView.getImageView());
		System.out.println(xratio + " " + yratio);
		double width = this.myBackgroundView.getWidth();
		double height = this.myBackgroundView.getHeight();
		System.out.println("width " + width + "\theight " + height);
		
		if(e.getButton().equals(MouseButton.SECONDARY)){
			p = new Point(loc, PointType.EXIT, width, height);
		}else{
			if(this.myPoints.isEmpty())
				p = new Point(loc,PointType.ENTRY, width, height); //TODO remove magic nums
			else
				p = new Point(loc, PointType.PATH, width, height);


		}
		this.pointsLayerView.getChildren().add(p);
		this.myPoints.add(p);
		System.out.println(p.getLocation().getX()+" "+p.getLocation().getY());
		
	}
	
	private double getScaleXRatio(ImageView imageView){ 
	    return imageView.getBoundsInParent().getWidth()/imageView.getImage().getWidth();
	}

	private double getScaleYRatio(ImageView imageView){ 
	    return imageView.getBoundsInParent().getHeight()/imageView.getImage().getHeight();
	}
	
	private void setupViews() {
		setupBackground();
		setupPointsLayer();
		setupButtons();
	}
	
	private void setupPointsLayer() {
		pointsLayerView = new Pane();
		this.getChildren().add(pointsLayerView);
	}

	private void setupBackground() {
		AnchorPane.setLeftAnchor(myBackgroundView, 0.0);
		AnchorPane.setRightAnchor(myBackgroundView, 0.0);
		this.getChildren().add(myBackgroundView);
	}

	private void setupButtons() {
		ImageView backImage = new ImageView(new Image("undo_icon.png"));
		backImage.setFitWidth(32);
		backImage.setPreserveRatio(true);
		StackPane b = UIHelper.buttonStack(e -> undoAction(e), Optional.ofNullable(null), Optional.of(backImage), Pos.CENTER, true);
		StackPane.setAlignment(b, Pos.BOTTOM_RIGHT);
		StackPane.setMargin(b, new Insets(12));
		b.setMaxSize(40, 40);
		UIHelper.setBackgroundColor(b,CustomColors.GREEN_100);
		
		ImageView clearImage = new ImageView(new Image("clear_icon.png"));
		clearImage.setFitWidth(32);
		clearImage.setPreserveRatio(true);
//		Label lbl = new Label("Clear All");
//		lbl.setTextFill(Color.BLACK);
//		lbl.setFont(Preferences.FONT_SMALL_BOLD);
		StackPane c = UIHelper.buttonStack(e -> clearPointsAndLines(e), Optional.ofNullable(null), Optional.of(clearImage), Pos.CENTER_RIGHT, true);
		c.setMaxSize(40, 40);
		StackPane.setAlignment(c, Pos.BOTTOM_RIGHT);
		StackPane.setMargin(c, new Insets(0,72,12,0));
		UIHelper.setBackgroundColor(c, CustomColors.GREEN_100);
		
		this.getChildren().addAll(b,c);
	}

	private void undoAction(MouseEvent e){
		e.consume();
		System.out.println("undo clicked");
	}
	
	private void clearPointsAndLines(MouseEvent e){
		e.consume();
		for(Point p: this.myPoints)
			this.pointsLayerView.getChildren().remove(p);
	}
	
	private void sizeDidChange(){
		for(Point p: this.myPoints)
			p.updateLocation(myBackgroundView.getWidth(), myBackgroundView.getHeight());
	}
	
	private Location pixelToGridLocation(Location pixelLocation){
		double width = this.myBackgroundView.widthProperty().get();
		double height = this.myBackgroundView.heightProperty().get();
		return new Location(pixelLocation.getX()/width, pixelLocation.getY()/height);
	}
	
	
	//PUBLIC METHODS
	
	public void setBackgroundImage(Image image){
		this.myBackgroundView.setImageView(new ImageView(image));;
	}
	

}
