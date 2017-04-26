package ui.authoring.map.layer;

import java.util.ArrayList;
import java.util.List;

import gamedata.LayerData;
import gamedata.map.PolygonData;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import ui.general.CustomColors;
import ui.general.ImageViewPane;
import util.Location;

public class PolygonLayerView extends Layer {
	
	private EventHandler<MouseEvent> myMouseEvents = e -> {
		if(e.getButton().equals(MouseButton.SECONDARY)){
			mouseSecondary(e);
		}
		else if(e.getEventType().equals(MouseEvent.MOUSE_PRESSED)){
			mousePressed(e);
		}
		else if(e.getEventType().equals(MouseEvent.MOUSE_RELEASED)){
			mouseReleased(e);
		}
		else if(e.getEventType().equals(MouseEvent.MOUSE_MOVED)){
			mouseMoved(e);
		} 
		
		
	};
	
	private LayerData myLayerData;
	private Polygon myCurrentPolygon;
	private ObservableList<Node> myPolygons;
	private Color myColor = CustomColors.AMBER;
	private boolean isActive = false;
	
	public PolygonLayerView(LayerData layerData){
		super();
		myLayerData = layerData;
		myPolygons = this.getChildren();
		setupPolygonViews();
	}


	private void setupPolygonViews() {
		for( PolygonData data : myLayerData.getMyPolygons()){
			addPolygonFromData(data);
		}
		
	}
	
	/**
	 * 
	 * @param data the polygon data tha
	 */
	private void addPolygonFromData(PolygonData data) {
		Polygon p = makePolygon(data);
		p.setFill(myColor);
		myPolygons.add(p);
	}

	
	private Polygon makePolygon(PolygonData data){
		return new Polygon(pointsToArray(
				decompressedLocations(
						data.getMyPoints())));
	}
	
	/**
	 * 
	 * @param locations a list of locations each with xy-values between 0 and 1
	 * @return a copy of the list of locations scaled to the viewing size
	 */
	private List<Location> decompressedLocations(List<Location> locations){
		List<Location> copy = new ArrayList<Location>();
		locations.forEach(loc -> {
			copy.add(new Location(loc.getX()*this.getWidth(),
					loc.getY()*this.getHeight()));
		});
		return copy;
	}
	
	private double[] pointsToArray(List<Location> myPoints) {
		double[] d = new double[myPoints.size()*2];
		int count = 0;
		for(Location loc : myPoints){
			d[count++] = loc.getX();
			d[count++] = loc.getY();
		}
		return d;
	}

	
	
	/*
	 * MARK: -Mouse Actions 
	 */
	
	private void mousePressed(MouseEvent e) {
		// do nothing
	}
	
	/**
	 * If there isn't a polygon, then add one
	 * else, add a new point to that polygon
	 * @param e
	 */
	private void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(myCurrentPolygon == null){
			myCurrentPolygon = new Polygon(
					e.getX(), e.getY(),
					e.getX(), e.getY());
			setPolygonStyle(myCurrentPolygon);
			this.myPolygons.add(myCurrentPolygon);
		}else{
			myCurrentPolygon.getPoints().addAll(e.getX(), e.getY());
		}
	}
	
	private void setPolygonStyle(Polygon polygon) {
		polygon.setFill(myColor);
		polygon.setStroke(myColor.darker());
		polygon.setStrokeWidth(3.0);
	}


	/**
	 * updates the current polygon by removing the last two doubles and adding two more
	 * @param e
	 */
	private void mouseMoved(MouseEvent e) {
		if(myCurrentPolygon!=null){
			int size = myCurrentPolygon.getPoints().size();
			myCurrentPolygon.getPoints().remove(size - 2, size);//TODO i think
			myCurrentPolygon.getPoints().addAll(e.getX(), e.getY());
		}
		
	}
	
	/**
	 * This method is supposed to signify the end of the polygon
	 * making process and finalize it by setting the currentPolygon
	 * to null and adding the polygonData into the layerData.
	 * @param e
	 */
	private void mouseSecondary(MouseEvent e) {
		if(myCurrentPolygon != null){
			//this.myCurrentPolygon.getPoints().addAll(e.getX(),e.getY());
			PolygonData data = makePolygonData(myCurrentPolygon);
			this.myLayerData.getMyPolygons().add(data);
			printData();
			myCurrentPolygon = null;
		}
	}
	
	private void printData(){
		System.out.println("\nPrint myLayerData");
		for(PolygonData d: myLayerData.getMyPolygons())
			System.out.println("\t"+d);
	}
	
	
	private PolygonData makePolygonData(Polygon polygon) {
		List<Double> list = polygon.getPoints();
		List<Location> points = new ArrayList<>();
		double normX = 1.0/this.getWidth();
		double normY = 1.0/this.getHeight();
		for(int i =0; i<list.size(); i+=2){
			Location loc = new Location(list.get(i)*normX, list.get(i+1)*normY);
			points.add(loc);
		}
		PolygonData data = new PolygonData(points);

		return data;
	}


	//

	/**
	 * sets the plygons opacity to 100
	 * allows user to add or delete polygons
	 */
	public void activate(){
		this.addEventHandler(MouseEvent.ANY, myMouseEvents);
		isActive = true;
	}
	
	/**
	 * dims 
	 */
	public void deactivate(){
		this.removeEventHandler(MouseEvent.ANY, myMouseEvents);
		isActive = false;
	}


	@Override
	public void clear() {
		myPolygons.clear();
		myLayerData.getMyPolygons().clear();
	}


	@Override
	public void undo() {
		//Node polygon = myPolygons.get(myPolygons.size() - 1);
	}


	@Override
	public void setColor(Color c) {
		myColor = c;
		
	}


	@Override
	public boolean isActive() {
		return isActive;
	}


	@Override
	public void sizeDidChange(ImageViewPane imagepane) {
		// TODO Auto-generated method stub
		
	}

}
