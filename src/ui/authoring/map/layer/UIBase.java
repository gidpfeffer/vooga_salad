package ui.authoring.map.layer;

import gamedata.ActorData;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import ui.general.ImageViewPane;
import util.Location;
import util.Tuple;

/**
 * Purpose is to allow the user to modify individual entries
 * in the BasePlacementData. 
 * 
 * This class holds a reference to an entry in BasePlacementData
 * This class also automates the conversion between BasicData to
 * actual javafx data
 * 
 * IMPORTANT: pass a reference of   
 * 
 * @author TNK
 *
 */
public class UIBase extends ImageViewPane{
	
	private Tuple<ActorData, Grid2D> myData;
	
	public UIBase(ActorData data, Grid2D loc, Bounds parentBounds){
		setMyData(new Tuple<>(data, loc));
		setImageView(new ImageView(new Image(data.getImagePath())));
		updateLayout(parentBounds);
	}

	
	/**
	 * updates layout based on information contained in myData 
	 * by decompressing points to fit the dimensions of the bounds
	 * @param parentBounds
	 */
	public void updateLayout(Bounds parentBounds){
		double xNorm = parentBounds.getWidth();
		double yNorm = parentBounds.getHeight();
		double width = xNorm*myData.x.getBasic().getImageDimensions().getX();
		double height = yNorm*myData.x.getBasic().getImageDimensions().getY();
		double x = myData.y.getX()*xNorm - width/2;
		double y = myData.y.getY()*yNorm - height/2;
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setPrefHeight(height);
		this.setPrefWidth(width);
		ImageView view = new ImageView(new Image(myData.x.getImagePath()));
		view.setPreserveRatio(true);
		setImageView(view);

	}
	
	/**
	 * centers base onto the specified location
	 * @param parentBounds bounds of parent is required
	 * @param location xy in terms of javafx
	 */
	public void updateLocationUI(Location loc){
		this.setLayoutX(loc.getX() - getWidth()/2.0);
		this.setLayoutY(loc.getY() - getHeight()/2.0);
		//System.out.println("loc,H,W: (" + loc +"), "+ height+", " + width);

	}
	
	public void updateLocationData(Grid2D newLoc){
		myData.y = newLoc;
	}

	public void setMyData(Tuple<ActorData, Grid2D> myData) {
		this.myData = myData;
	}


	public Tuple<ActorData, Grid2D> getData() {
		return this.myData;
	}

}
