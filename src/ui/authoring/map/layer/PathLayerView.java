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
import ui.authoring.map.layer.path.Path;
import ui.authoring.map.layer.path.Point;
import ui.general.CustomColors;
import ui.general.ImageViewPane;
import util.Location;
import util.Tuple;

public class PathLayerView extends Layer {

	private boolean isActive = false;
	private PathData myPathData;
	private boolean isFirstPoint = true;
	private List<Path> myDataToPath;
	private Path myCurrentPath;
	
	public PathLayerView(PathData data) {
		super();
		myPathData = data;
		myDataToPath = new ArrayList<>();
		addEventHandler(MouseEvent.MOUSE_RELEASED, e -> handleMouseRelease(e));
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
			myCurrentPath = new Path();
			myPathData.addPath(new ArrayList<>());
			myDataToPath.add(myCurrentPath);
		}

		
		Point p = new Point(coordinate, e.getX(), e.getY());
		myCurrentPath.addPointTo(p, this);
		List<Grid2D> listOfPoints = myPathData.poll();
		listOfPoints.add(coordinate);
//		Line line = new Line(e.getX(), e.getY(), e.getX(), e.getY());
//
//		this.setOnMouseMoved(event -> {
//			line.setEndX(event.getX());
//			line.setEndY(event.getY());
//		});
//		this.setOnMouseDragged(event -> {
//			line.setEndX(event.getX());
//			line.setEndY(event.getY());
//		});


		// determines if the point is exit, entry, or regular path
		if (!isFirstPoint && e.getButton().equals(MouseButton.SECONDARY)) {// exit																// path
			isFirstPoint = true;
			p.setPointType(PointType.EXIT);
			setOnMouseMoved(i -> {});
			setOnMouseDragged(i -> {});
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
		myDataToPath.clear();
	}

	@Override
	public void undo() {
		
		List<Grid2D> data = myPathData.pop();
		System.out.println("REMOVING PATH: "+data);
		System.out.println(myDataToPath.size());
		Path path = this.myDataToPath.remove(myDataToPath.size() - 1);
		System.out.println(path);
		myDataToPath.forEach(d -> System.out.println(d));
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

	@Override
	public void sizeDidChange() {
			clear();
	}

	@Override
	public void load(MapLayersData mapData) {
		// TODO Auto-generated method stub
		
	}

}
