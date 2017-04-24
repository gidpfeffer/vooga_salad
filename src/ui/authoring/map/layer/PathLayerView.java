package ui.authoring.map.layer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import gamedata.GameData;
import gamedata.PathData;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import ui.authoring.internal_api.IAuthoringComponent;
import ui.authoring.map.Point;
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

	private ArrayList<Line> myLines = new ArrayList<Line>();
	private boolean isFirstPoint = true;
	private Color myColor = CustomColors.BLACK_GRAY;
	Tuple<Double, Double> myInsets; //

	public PathLayerView(Tuple<Double, Double> insets) {
		super();
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
			List<Grid2D> listOfPoints = myPathData.poll();
			listOfPoints.add(p);
			// determines if the point is exit, entry, or regular path
			if (!isFirstPoint && e.getButton().equals(MouseButton.SECONDARY)) {// exit
																				// path
				isFirstPoint = true;
				p.setPointType(PointType.EXIT);
				setOnMouseMoved(irrelevantName -> {});
				System.out.println(listOfPoints);
				myPathData.addPath(new ArrayList<Grid2D>());
			} else if (isFirstPoint) {
				p.setPointType(PointType.ENTRY);
				isFirstPoint = false;
			} else {
				p.setPointType(PointType.PATH);
			}

			this.getChildren().add(line);
			this.getChildren().add(p);
			this.myLines.add(line);

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
		for (Line l : myLines) {
			getChildren().remove(l);
		}

	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

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

	public void setPathData(PathData data) {
		myPathData = data;
	}

}
