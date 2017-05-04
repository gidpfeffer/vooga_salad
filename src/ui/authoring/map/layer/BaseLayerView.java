package ui.authoring.map.layer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import gamedata.ActorData;
import gamedata.BasePlacementData;
import gameengine.grid.classes.Coordinates;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import util.Location;
import util.Tuple;

public class BaseLayerView extends Layer {

	private boolean DID_LAUNCH;
	private boolean isActive = false;
	private BasePlacementData myData;
	private List<UIBase> myBases;
	private Optional<UIBase> myCurrentBase = Optional.ofNullable(null);

	private EventHandler<MouseEvent> myEvent = e -> {
		
		// base is being dragged
		if (e.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
			myCurrentBase.ifPresent(base -> dragBase(base, new Location(e.getX(), e.getY())));
		}

		// base is released from drag
		else if (e.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
			System.out.println("Mouse release detected...");
			myCurrentBase.ifPresent(base -> updateBaseLocation(base, new Location(e.getX(), e.getY())));
			myCurrentBase = Optional.ofNullable(null);
		}

	};
	private EventHandler<MouseEvent> myBaseEvent = e -> {

		if (e.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
			System.out.println("MOUSE_PRESSED...");
			myCurrentBase = Optional.ofNullable((UIBase) e.getSource());
		}

		// double click
		if (e.getEventType().equals(MouseEvent.MOUSE_PRESSED) && e.getClickCount() == 2) {
			System.out.println("double click detected...");
			Optional.ofNullable((UIBase) e.getSource()).ifPresent(base -> addBaseToLoc(base.getData().x,
					new Location(base.getData().y.getX() - 0.05, base.getData().y.getX() - 0.05)));
		}

	};

	private void dragBase(UIBase base, Location location) {
		base.updateLocationUI(location);
	}

	/**
	 * 
	 * @param base the UIBase contains the actor data and location
	 * @param location The new location that UIBase will contain
	 */
	private void updateBaseLocation(UIBase base, Location location) {
		base.updateLocationData(compressLocation(location));
		base.updateLayout(this.getBoundsInParent());
	}

	public BaseLayerView(BasePlacementData data) {
		myData = data;
		myBases = new ArrayList<>();
		this.addEventHandler(MouseEvent.ANY, myEvent);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> System.out.println(myData.getMyActorToLocation().size()));
	}

	@Override
	protected void layoutChildren() {
		if (!DID_LAUNCH)
			loadBaseData();
		DID_LAUNCH = true;
	}

	private void loadBaseData() {
		System.out.println("");
		myData.getMyActorToLocation().forEach((t) -> {
			addBaseUI(t.x, decompressGrid2D(t.y));
			System.out.println("Grid Location: "+t.y);
		});
	}

	/**
	 * purpose is to translate the scene coordinates to local coordinates
	 * @param data
	 * @param sceneLoc
	 */
	public void addBase(ActorData data, Location sceneLoc) {
		addBaseToLoc(data, sceneToLocal(sceneLoc));
	}
	
	/**
	 * adds base to data 
	 * @param data
	 * @param location
	 */
	private void addBaseToLoc(ActorData data, Location location) {
		System.out.println("local coordinates " + location);
		Coordinates coor = compressLocation(location);
		this.myData.addBase(new Tuple<>(data, coor));
		UIBase b = addBaseUI(data, coor);
		this.myCurrentBase = Optional.of(b);
	}

	private Location sceneToLocal(Location loc) {
		double sceneH = this.getScene().getHeight();
		double sceneW = this.getScene().getWidth();
		double vInset = (sceneH - this.getHeight()) / 2.0;
		double hInset = (sceneW - this.getWidth()) / 2.0;
		// System.out.println("v: " + vInset + "\th:" + hInset);
		return new Location(loc.getX() - hInset, loc.getY() - vInset);
	}

	private UIBase addBaseUI(ActorData data, Grid2D loc) {
		System.out.println("\rUIBASE: "+loc);
		UIBase base = new UIBase(data, loc, this.getBoundsInParent());
		base.addEventHandler(MouseEvent.ANY, myBaseEvent);
		this.getChildren().add(base);
		return base;
	}

	private Coordinates compressLocation(Location e) {
		Coordinates c = new Coordinates(e.getX() / this.getWidth(), e.getY() / this.getHeight());
		return c;
	}
	
	private Location decompressGrid2D(Grid2D c){
		return new Location(c.getX()* this.widthProperty().get(), c.getY()*this.heightProperty().get());
	}

	private void deleteBase(UIBase base) {
		System.out.println("Removing base " + base.getData().x.getName());
		this.myBases.remove(base);
		this.myData.removeBase(base.getData());
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
		myBases.forEach(base -> deleteBase(base));

	}

	@Override
	public void undo() {
		if (myBases.isEmpty())
			return;
		deleteBase(myBases.get(myBases.size() - 1));
	}

	@Override
	public void setColor(Color c) {
		// do nothing
	}

	@Override
	public boolean isActive() {
		return isActive;
	}

}
