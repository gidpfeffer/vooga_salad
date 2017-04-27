package ui.authoring.map.layer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import gamedata.PathData;
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
	private PathData myPathData;
	public PathData getMyPathData() {
		return myPathData;
	}

	private Map<Integer, List<Line>> myLines = new HashMap<>();
	private boolean isFirstPoint = true;
	private Color myColor = CustomColors.BLACK_GRAY;
	Tuple<Double, Double> myInsets; //

	public PathLayerView(PathData data, Tuple<Double, Double> insets) {
		super();
		myPathData = data;
		myInsets = insets;
		addEventHandler(MouseEvent.MOUSE_RELEASED, e -> handleMouseRelease(e));
	}

	private void handleMouseRelease(MouseEvent e) {
		e.consume();
		addPointToMap(e);
	}

	// TODO refactor
	private void addPointToMap(MouseEvent e) {

		Tuple<Double, Double> size = new Tuple<Double, Double>(getWidth(), getHeight());
		Location loc = new Location(e.getX(), e.getY());
		Tuple<Double, Double> insets = myInsets;
		Point p = new Point(loc, size, insets);
		System.out.println(e.getX() + "\t" + e.getY());
		if (p.isValid()) {

			Line line = new Line(loc.getX(), loc.getY(), loc.getX(), loc.getY());
			line.setStrokeWidth(3);
			line.setFill(myColor);
			this.setOnMouseMoved(event -> {
				line.setEndX(event.getX());
				line.setEndY(event.getY());
			});
			this.setOnMouseDragged(event -> {
				line.setEndX(event.getX());
				line.setEndY(event.getY());
			});
			List<Grid2D> listOfPoints = myPathData.poll();
			// determines if the point is exit, entry, or regular path
			if (!isFirstPoint && e.getButton().equals(MouseButton.SECONDARY)) {// exit
																				// path
				isFirstPoint = true;
				p.setPointType(PointType.EXIT);
				setOnMouseMoved(irrelevantName -> {});
				setOnMouseDragged(irrelevantName -> {});
				
				System.out.println(myPathData.getMyPaths().values());
			} else if (isFirstPoint) {
				myPathData.addPath(new ArrayList<>());
				listOfPoints = myPathData.poll();
				p.setPointType(PointType.ENTRY);
				isFirstPoint = false;
				this.myLines.put(myLines.size(), new ArrayList<>());

			} else {
				p.setPointType(PointType.PATH);
			}
			
			listOfPoints.add(p);
			this.getChildren().add(line);
			this.getChildren().add(p);
			myLines.get(myLines.size() - 1).add(line);


		}

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
		for (Entry<Integer, List<Grid2D>> path : myPathData.getMyPaths().entrySet()) {
			for (Grid2D p : path.getValue()) {
				getChildren().remove((Point) p);
			}
		}
		myPathData.clear();
		for (List<Line> list : myLines.values()) {
			getChildren().removeAll(list);
		}

	}

	@Override
	public void undo() {
		
		List<Grid2D> path = myPathData.pop();
		System.out.println(path);
		for(Grid2D p:path){
			getChildren().remove((Point) p);
		}
		for(List<Line> list: myLines.values()){
			System.out.println(list);
		}
		getChildren().removeAll(myLines.get(myLines.size() - 1));
	}

	@Override
	public void setColor(Color c) {
		this.myColor = c;
	}

	@Override
	public boolean isActive() {
		return isActive;
	}

	@Override
	public void sizeDidChange(ImageViewPane imagepane) {
		myInsets = imagepane.getImageInsets();
		for (Entry<Integer, List<Grid2D>> path : myPathData.getMyPaths().entrySet())
			for (Grid2D p : path.getValue())
				((Point) p).updateSize(new Tuple<Double, Double>(imagepane.getWidth(), imagepane.getHeight()),
						imagepane.getImageInsets());
	}

}
