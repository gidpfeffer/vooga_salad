package ui.authoring.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import gamedata.PathData;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.shape.Line;
import ui.Preferences;
import ui.general.*;
import util.*;

/**
 * 
 * Creates and stores the PathData object
 * 
 * @author TNK
 *
 */
public class MapEditorView extends StackPane {

	private final String DEFAULT_BACKGROUND_PATH = "default_map_background_0.jpg";

	private PathData myPathData;
	private ArrayList<Line> myLines = new ArrayList<Line>();
	private ImageViewPane myBackgroundView = new ImageViewPane(new ImageView(new Image(DEFAULT_BACKGROUND_PATH)));
	private Pane pointsLayerView;
	private boolean isFirstPoint = true;
	private Tuple<Double, Double> myMapInsets;

	public MapEditorView() {
		super();
		myPathData = new PathData(new HashMap<Integer, List<Grid2D>>());
		setupViews();
		setupMouseEvents();
		this.widthProperty().addListener(e -> sizeDidChange());
	}

	private void setupMouseEvents() {
		this.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> this.getScene().setCursor(Cursor.CROSSHAIR));
		this.addEventHandler(MouseEvent.MOUSE_EXITED, e -> this.getScene().setCursor(Cursor.DEFAULT));
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> handleMouseClick(e));
	}

	private void handleMouseClick(MouseEvent e) {
		addPointToMap(e);
	}

	private void addPointToMap(MouseEvent e) {

		Tuple<Double, Double> size = new Tuple<Double, Double>(myBackgroundView.getWidth(),
				myBackgroundView.getHeight());
		Location loc = new Location(e.getX(), e.getY());
		Tuple<Double, Double> insets = this.myBackgroundView.getImageInsets();
		Point p = new Point(loc, size, insets);

		if (p.isValid()) {

			Line line = new Line(loc.getX(), loc.getY(), loc.getX(), loc.getY());
			line.setStrokeWidth(3);
			this.setOnMouseMoved(event -> {
				line.setEndX(event.getX());
				line.setEndY(event.getY());
			});
			// determines if the point is exit, entry, or regular path
			if (!isFirstPoint && e.getButton().equals(MouseButton.SECONDARY)) {
				isFirstPoint = true;
				p.setPointType(PointType.EXIT);
				this.setOnMouseMoved(fdsa -> {});

			} else if(isFirstPoint){
				myPathData.addPath(new ArrayList<Grid2D>());
				p.setPointType(PointType.ENTRY);
				isFirstPoint = false;
			}else{
				p.setPointType(PointType.PATH);
			} 

			List<Grid2D> listOfPoints = myPathData.poll();
			listOfPoints.add(p);
			this.pointsLayerView.getChildren().add(line);
			this.pointsLayerView.getChildren().add(p);
			this.myLines.add(line);

		}

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
		StackPane b = UIHelper.buttonStack(e -> undoAction(e), Optional.ofNullable(null), Optional.of(backImage),
				Pos.CENTER, true);
		StackPane.setAlignment(b, Pos.BOTTOM_RIGHT);
		StackPane.setMargin(b, new Insets(12));
		b.setMaxSize(40, 40);
		UIHelper.setBackgroundColor(b, CustomColors.GREEN_100);

		ImageView clearImage = new ImageView(new Image("clear_icon.png"));
		clearImage.setFitWidth(32);
		clearImage.setPreserveRatio(true);

		StackPane c = UIHelper.buttonStack(e -> clearPointsAndLines(e), Optional.ofNullable(null),
				Optional.of(clearImage), Pos.CENTER_RIGHT, true);
		c.setMaxSize(40, 40);
		StackPane.setAlignment(c, Pos.BOTTOM_RIGHT);
		StackPane.setMargin(c, new Insets(0, 72, 12, 0));
		UIHelper.setBackgroundColor(c, CustomColors.GREEN_100);

		this.getChildren().addAll(b, c);
	}

	private void undoAction(MouseEvent e) {
		e.consume();
		System.out.println("undo clicked");
	}

	private void clearPointsAndLines(MouseEvent e) {
		e.consume();
		for (Entry<Integer, List<Grid2D>> path : myPathData.getMyPaths().entrySet()) {
			for (Grid2D p : path.getValue()) {
				this.pointsLayerView.getChildren().remove((Point) p);
			}
		}
		myPathData = new PathData(new HashMap<>());
		for (Line l : myLines) {
			this.pointsLayerView.getChildren().remove(l);
		}

	}
	
	private void printMapData(){
		for (Entry<Integer, List<Grid2D>> path : myPathData.getMyPaths().entrySet()) {
			if(path.getValue().isEmpty())
				break;
			System.out.print(path.getKey() + " [ ");
			for (Grid2D p : path.getValue()) {
				System.out.print(p.toString() + ", ");
			}
			System.out.println(" ]");
		}
	}

	private void sizeDidChange() {

		for (Entry<Integer, List<Grid2D>> path : myPathData.getMyPaths().entrySet())
			for (Grid2D p : path.getValue())
				((Point) p).updateSize(
						new Tuple<Double, Double>(myBackgroundView.getWidth(), myBackgroundView.getHeight()),
						myBackgroundView.getImageInsets());
	}

}
