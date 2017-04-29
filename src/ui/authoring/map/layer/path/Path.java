package ui.authoring.map.layer.path;

import java.util.ArrayList;
import java.util.List;

import gameengine.grid.interfaces.Identifiers.Grid2D;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import ui.general.CustomColors;

public class Path {
	
	private List<Point> myPoints = new ArrayList<>();
	private List<Line> myLines = new ArrayList<>();
	
	public void addPointTo(Point point, Pane pane){
		pane.getChildren().add(point);
		myPoints.add(point);
		Line line = new Line(point.getCenterX(), point.getCenterY(),
				 point.getCenterX(), point.getCenterY());
		line.setStrokeWidth(3);
		line.setFill(CustomColors.BLACK_GRAY);
		pane.setOnMouseMoved(event -> {
			line.setEndX(event.getX());
			line.setEndY(event.getY());
		});
		pane.setOnMouseDragged(event -> {
			line.setEndX(event.getX());
			line.setEndY(event.getY());
		});
		pane.getChildren().add(line);
		myLines.add(line);
		print();
	}
	
	public List<Point> getPoints(){
		print();
		return myPoints;
	}
	
	public List<Line> getLines(){
		print();
		return myLines;
	}
	
	private void print(){
		System.out.println(myLines.size() + " size of lines");
		System.out.println(myPoints.size() + " size of points");
	}
	
}
