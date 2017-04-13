package ui.authoring.map;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import gamedata.PathData;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import ui.general.CustomColors;
import ui.general.ImageViewPane;
import ui.general.UIHelper;
import util.Location;
import util.Tuple;

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
			List<Grid2D> listOfPoints = myPathData.poll();
			listOfPoints.add(p);
			// determines if the point is exit, entry, or regular path
			if (!isFirstPoint && e.getButton().equals(MouseButton.SECONDARY)) {//exit path
				isFirstPoint = true;
				p.setPointType(PointType.EXIT);
				this.setOnMouseMoved(fdsa -> {});
				System.out.println(listOfPoints);
			} else if(isFirstPoint){
				myPathData.addPath(new ArrayList<Grid2D>());
				p.setPointType(PointType.ENTRY);
				isFirstPoint = false;
			}else{
				p.setPointType(PointType.PATH);
			} 


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
		List<StackPane> panes = new ArrayList<>();
		
		ImageView backImage = makeImageFromString("undo_icon.png");
		StackPane b = UIHelper.buttonStack(e -> undoAction(e), Optional.ofNullable(null), Optional.of(backImage),
				Pos.CENTER, true);
		StackPane.setMargin(b, new Insets(12));
		panes.add(b);

		ImageView clearImage = makeImageFromString("clear_icon.png");
		StackPane c = UIHelper.buttonStack(e -> clearPointsAndLines(e), Optional.ofNullable(null),
				Optional.of(clearImage), Pos.CENTER_RIGHT, true);
		StackPane.setMargin(c, new Insets(0, 72, 12, 0));
		panes.add(c);
		
		ImageView BGImage = makeImageFromString("background_icon.png");
		StackPane d = UIHelper.buttonStack(e -> toggleBackground(e), Optional.ofNullable(null),
				Optional.of(BGImage), Pos.CENTER_RIGHT, true);
		StackPane.setMargin(d, new Insets(0, 132, 12, 0));
		panes.add(d);
		
		for(StackPane s: panes){
			s.setMaxSize(40, 40);
			StackPane.setAlignment(s, Pos.BOTTOM_RIGHT);
			UIHelper.setBackgroundColor(s, CustomColors.GREEN_100);
			this.getChildren().add(s);
		}
	}
	
	private ImageView makeImageFromString(String toMake){
		ImageView IV = new ImageView(new Image(toMake));
		IV.setFitWidth(32);
		IV.setPreserveRatio(true);
		return IV;
	}
	
	private void toggleBackground(MouseEvent e){
		e.consume();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selectc Image File");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		File selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());
		if(selectedFile!= null){
			myBackgroundView.getImageView().setImage(new Image(selectedFile.getName()));
		}
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
