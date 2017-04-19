package ui.authoring.map;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import gamedata.MapData;
import gamedata.PathData;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import ui.Preferences;
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
	private MapData myMapData;
	private ArrayList<Line> myLines = new ArrayList<Line>();
	private ImageViewPane myBackgroundView;
	private Pane myPathLayer;
	private List<Pane> myLayers = new ArrayList<>();
	private HBox myLayersBox;
	private boolean isFirstPoint = true;

	public MapEditorView(PathData pathData) { //TODO pass mapData
		super();
		myPathData = pathData;
		myMapData = new MapData();
		setupViews();
		setupMouseEvents();
		this.widthProperty().addListener(e -> sizeDidChange());
	}

	private void setupMouseEvents() {
		setMouseToPath();
	}
	private void setMouseToPath(){
		this.myPathLayer.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> this.getScene().setCursor(Cursor.CROSSHAIR));
		this.myPathLayer.addEventHandler(MouseEvent.MOUSE_EXITED, e -> this.getScene().setCursor(Cursor.DEFAULT));
		this.myPathLayer.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> handleMouseClick(e));
	}
	private void setMouseToPolygonMakerForLayer(Pane layer){
		
	}

	private void handleMouseClick(MouseEvent e) {
		addPointToMap(e);
	}
	
	//TODO refactor
	private void addPointToMap(MouseEvent e) {

		Tuple<Double, Double> size = new Tuple<Double, Double>(myBackgroundView.getWidth(),
				myBackgroundView.getHeight());
		Location loc = new Location(e.getX(), e.getY());
		Tuple<Double, Double> insets = this.myBackgroundView.getImageInsets();
		Point p = new Point(loc, size, insets);
		System.out.println(e.getX() +"\t"+ e.getY());
		if (p.isValid()) {

			Line line = new Line(loc.getX(), loc.getY(), loc.getX(), loc.getY());
			line.setStrokeWidth(3);
			myPathLayer.setOnMouseMoved(event -> {
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
				myPathLayer.setOnMouseMoved(fdsa -> {
				});
				System.out.println(listOfPoints);
				myPathData.addPath(new ArrayList<Grid2D>());
			} else if (isFirstPoint) {
				p.setPointType(PointType.ENTRY);
				isFirstPoint = false;
			} else {
				p.setPointType(PointType.PATH);
			}

			this.myPathLayer.getChildren().add(line);
			this.myPathLayer.getChildren().add(p);
			this.myLines.add(line);

		}

	}

	private void setupViews() {
		myBackgroundView = new ImageViewPane(new ImageView(new Image(DEFAULT_BACKGROUND_PATH)));
		UIHelper.setBackgroundColor(myBackgroundView, CustomColors.GREEN);
		StackPane.setAlignment(myBackgroundView, Pos.TOP_CENTER);
		StackPane.setMargin(myBackgroundView, new Insets(8,8,72,8));
		this.getChildren().add(myBackgroundView);

		setupButtons();
		setupLayerSelector();
		
		myPathLayer = new Pane();
		addLayerToMap(myPathLayer, "Path");
	}
	
	private void addLayerToMap(Pane layer, String layerName){
		StackPane.setAlignment(layer, Pos.TOP_CENTER);
		StackPane.setMargin(layer, new Insets(8,8,72,8));
		this.getChildren().add(layer);
		this.myLayers.add(layer);
		//UIHelper.setBackgroundColor(layer, LAYER_COLORS[index/size]);
		Label layerNumber = new Label(layerName);
		layerNumber.setFont(Preferences.FONT_MEDIUM_BOLD);
		layerNumber.setTextFill(CustomColors.GREEN_900);
		StackPane.setMargin(layerNumber, new Insets(8));
		StackPane layerIcon = UIHelper.buttonStack(e -> switchToLayer(layer), Optional.of(layerNumber), Optional.ofNullable(null),Pos.CENTER, false);
		HBox.setMargin(layerIcon, new Insets(8));
		//layerIcon.setPrefWidth(48);
		UIHelper.setBackgroundColor(layerIcon, CustomColors.GREEN_100);
		myLayersBox.getChildren().add(myLayersBox.getChildren().size() - 1, layerIcon);
	}

	private void switchToLayer(Pane layer){
		//TODO
	}

	private void setupLayerSelector() {
		//setup HBox
		myLayersBox = new HBox();
		//add button that switches path maker
		StackPane.setAlignment(myLayersBox, Pos.BOTTOM_CENTER);
		StackPane.setMargin(myLayersBox, new Insets(8,192,8,8));
		myLayersBox.setMaxHeight(56);
		UIHelper.setBackgroundColor(myLayersBox, CustomColors.GREEN);
		//add new layer button
		addNewLayerButton();
		this.getChildren().add(myLayersBox);
		
	}
	
	private void addNewLayerButton() {
		ImageView img = new ImageView(new Image("add_icon_w.png"));
		img.setFitHeight(32);
		img.setPreserveRatio(true);
		StackPane button = UIHelper.buttonStack(e -> {}, Optional.ofNullable(null), Optional.of(img),
				Pos.CENTER, true);
		UIHelper.setBackgroundColor(button, Color.TRANSPARENT);
		this.myLayersBox.getChildren().add(button);
	}

	private void didClickNewLayerButton(){
		//TODO
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
		StackPane d = UIHelper.buttonStack(e -> toggleBackground(e), Optional.ofNullable(null), Optional.of(BGImage),
				Pos.CENTER_RIGHT, true);
		StackPane.setMargin(d, new Insets(0, 132, 12, 0));
		panes.add(d);

		for (StackPane s : panes) {
			s.setMaxSize(40, 40);
			StackPane.setAlignment(s, Pos.BOTTOM_RIGHT);
			UIHelper.setBackgroundColor(s, CustomColors.GREEN_100);
			this.getChildren().add(s);
		}
	}

	private ImageView makeImageFromString(String toMake) {
		ImageView IV = new ImageView(new Image(toMake));
		IV.setFitWidth(32);
		IV.setPreserveRatio(true);
		return IV;
	}

	private void toggleBackground(MouseEvent e) {
		e.consume();
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selectc Image File");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		File selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());
		if (selectedFile != null) {
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
				this.myPathLayer.getChildren().remove((Point) p);
			}
		}
		myPathData.clear(); //TODO AF
		for (Line l : myLines) {
			this.myPathLayer.getChildren().remove(l);
		}

	}
	
	/**
	 * This method updates the location of the points on the map
	 * This method is needed because the scaling of the points will 
	 * change as the user resizes the map
	 */
	private void sizeDidChange() {
		
		for (Entry<Integer, List<Grid2D>> path : myPathData.getMyPaths().entrySet())
			for (Grid2D p : path.getValue())
				((Point) p).updateSize(
						new Tuple<Double, Double>(myBackgroundView.getWidth(), myBackgroundView.getHeight()),
						myBackgroundView.getImageInsets());
	}

}
