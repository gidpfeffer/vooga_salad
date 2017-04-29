package ui.authoring.map.layer.path;

import java.util.ArrayList;
import java.util.List;

import gameengine.grid.interfaces.Identifiers.Grid2D;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class Path {
	
	private List<Point> myPoints = new ArrayList<>();
	private List<Line> myLines = new ArrayList<>();
	
	public void addPointTo(Point point, Pane pane){
		
		Point previous = myPoints.get(myPoints.size() - 1);
		Line line = new Line(previous.getCenterX(), previous.getCenterY(),
				 point.getCenterX(), point.getCenterY());
		pane.getChildren().add(line);
	}
	
	public List<Point> getPoints(){
		return myPoints;
	}
	
	public List<Line> getLines(){
		return myLines;
	}

	
}
