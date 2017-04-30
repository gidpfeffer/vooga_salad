package ui.authoring.map.layer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import gamedata.MapLayersData;
import gamedata.PathData;
import gameengine.grid.classes.Coordinates;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import ui.authoring.map.PointType;
import ui.general.CustomColors;
import ui.general.ImageViewPane;
import util.Location;
import util.Tuple;

public class PathLayerView extends Layer {

	private boolean isActive = false;
	private boolean isLoaded = false;
	private PathData myPathData;
	private boolean isFirstPoint = true;
	private List<UIPath> myUIPath;
	private UIPath myCurrentPath;
	
	public PathLayerView(PathData data) {
		super();
		myPathData = data;
		myUIPath = new ArrayList<>();
		addEventHandler(MouseEvent.MOUSE_RELEASED, e -> handleMouseRelease(e));
		widthProperty().addListener(e -> sizeDidChange());
		heightProperty().addListener(e -> sizeDidChange());
	}
	
	@Override
	protected void layoutChildren(){
		super.layoutChildren();
		if(!isLoaded)
			didFinishLoading();

	}

	private void didFinishLoading() {
		isLoaded = true;
		loadPathData(myPathData);
		System.out.println("finished loading data for pathlayer");		
	}

	private void handleMouseRelease(MouseEvent e) {
		e.consume();
		addPointToMap(e);
	}

	// TODO refactor
	private void addPointToMap(MouseEvent e) {
		
		Coordinates coordinate = new Coordinates(e.getX()/this.getWidth(), e.getY()/this.getHeight());
		if (!coordinate.isValid())
			return;
		if(myCurrentPath == null){
			myCurrentPath = new UIPath();
			myPathData.addPath(new ArrayList<>());
			myUIPath.add(myCurrentPath);
		}

		
		UIPoint p = new UIPoint(coordinate, e.getX(), e.getY());
		myCurrentPath.addPointAndMouseDraggableLine(p, this);
		List<Grid2D> listOfPoints = myPathData.poll();
		listOfPoints.add(coordinate);

		// determines if the point is exit, entry, or regular path
		if (!isFirstPoint && e.getButton().equals(MouseButton.SECONDARY)) {// exit																// path
			isFirstPoint = true;
			p.setPointType(PointType.EXIT);
			setOnMouseMoved(i -> {});
			setOnMouseDragged(i -> {});
			myCurrentPath.finishPath(this);
			myCurrentPath = null;
		} 
		else if (isFirstPoint) {
			p.setPointType(PointType.ENTRY);
			isFirstPoint = false;
		} 
		else {
			p.setPointType(PointType.PATH);
		}
		
		/*
		 * printing map data
		 */
		System.out.println("myPathData:");
		for(List<Grid2D> l:myPathData.getMyPaths().values())
			System.out.println("\t-"+l);

	}

	@Override
	public void activate() {
		isActive = true;
	}

	@Override
	public void deactivate() {
		isActive = false;
	}

	@Override
	public void clear() {
//		while(!myPathData.getMyPaths().isEmpty()){
//			Path path = this.myDataToPath.get(myPathData.pop());
//			getChildren().removeAll(path.getLines());
//			getChildren().removeAll(path.getPoints());
//		}
		this.getChildren().clear();
		myPathData.clear();
		myUIPath.clear();
	}

	@Override
	public void undo() {
		
		List<Grid2D> data = myPathData.pop();
		System.out.println("REMOVING PATH: "+data);
		System.out.println(myUIPath.size());
		UIPath path = this.myUIPath.remove(myUIPath.size() - 1);
		System.out.println(path);
		myUIPath.forEach(d -> System.out.println(d));
		getChildren().removeAll(path.getLines());
		getChildren().removeAll(path.getPoints());
	}

	@Override
	public void setColor(Color c) {
		//nothing
	}

	@Override
	public boolean isActive() {
		return isActive;
	}
	
	private void sizeDidChange() {
		this.myUIPath.forEach(path -> {
			System.out.println(path.getPoints());
		});	
		this.myUIPath.forEach(path -> path.reload(this));
	}

//	@Override
//	public void load(MapLayersData mapData) {
//		loadPathData(mapData.getMyPathData());
//	}
	
	private void loadPathData(PathData pathData){
		pathData.getMyPaths().forEach((i,list) -> {
			addPathToSelf(list);
		});
	}
	
	private void addPathToSelf(List<Grid2D> points){
		System.out.println(points.size());
		System.out.println(this.getWidth() + " \t height: " + this.getHeight());
		this.myUIPath.add(new UIPath(points, this));
	}


}
