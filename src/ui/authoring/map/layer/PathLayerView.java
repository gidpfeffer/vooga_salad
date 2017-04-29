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

	private Color COLOR = CustomColors.BLACK_GRAY;

	private boolean isActive = false;
	private PathData myPathData;
//	private Map<Integer, List<Line>> myLines = new HashMap<>();
	private boolean isFirstPoint = true;
	private Map<List<Grid2D>, Path> myDataToPath;
	private Path myCurrentPath;
	
	public PathLayerView(PathData data) {
		super();
		myPathData = data;
		myDataToPath = new HashMap<>();
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
		
		Point p = new Point(coordinate, e.getX(), e.getY());
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
		List<Grid2D> listOfPoints = myPathData.poll();
		// determines if the point is exit, entry, or regular path
		if (!isFirstPoint && e.getButton().equals(MouseButton.SECONDARY)) {// exit
																			// path
			isFirstPoint = true;
			p.setPointType(PointType.EXIT);
			setOnMouseMoved(irrelevantName -> {});
			setOnMouseDragged(irrelevantName -> {});
			
		} 
		else if (isFirstPoint) {
			myPathData.addPath(new ArrayList<>());
			listOfPoints = myPathData.poll();
			
			myCurrentPath = new Path();
			myDataToPath.put(listOfPoints, myCurrentPath);
			
			p.setPointType(PointType.ENTRY);
			isFirstPoint = false;
		} 
		else {
			p.setPointType(PointType.PATH);
		}
		
		listOfPoints.add(coordinate);
		myCurrentPath.addPointTo(p, this);

//		this.getChildren().add(line);
//		this.getChildren().add(p);
//		myLines.get(myLines.size() - 1).add(line);
		
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
		while(!myPathData.getMyPaths().isEmpty()){
			Path path = this.myDataToPath.get(myPathData.pop());
			getChildren().removeAll(path.getLines());
			getChildren().removeAll(path.getPoints());
		}
		this.getChildren().clear();
		myPathData.clear();
		myDataToPath.clear();
	}

	@Override
	public void undo() {
		
		List<Grid2D> data = myPathData.pop();
		System.out.println("REMOVING PATH: "+data);
		Path path = this.myDataToPath.get(data);
		getChildren().removeAll(path.getLines());
		getChildren().removeAll(path.getPoints());
		myDataToPath.remove(data);
	}

	@Override
	public void setColor(Color c) {
		this.COLOR = c;
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
