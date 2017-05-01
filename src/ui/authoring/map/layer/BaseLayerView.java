package ui.authoring.map.layer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import gamedata.ActorData;
import gamedata.BasePlacementData;
import gameengine.grid.classes.Coordinates;
import gameengine.grid.interfaces.Identifiers.Grid2D;
import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import ui.general.UIHelper;
import util.Location;
import util.Tuple;

public class BaseLayerView extends Layer {

	private boolean DID_LAUNCH;

	private boolean isActive = false;
	private BasePlacementData myData;
	private List<Base> myBases;
	private Optional<Base> myCurrentBase = Optional.ofNullable(null);

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
			myCurrentBase = Optional.ofNullable((Base) e.getSource());
		}

		// double click
		if (e.getEventType().equals(MouseEvent.MOUSE_CLICKED) && e.getClickCount() == 2) {
			System.out.println("double click detected...");
			Optional.ofNullable((Base) e.getSource()).ifPresent(base -> addBaseToLoc(base.getData().x,
					new Location(base.getData().y.getX() - 0.05, base.getData().y.getX() - 0.05)));
		}

	};

	private void dragBase(Base base, Location location) {
		base.updateLocationUI(location);
	}

	private void updateBaseLocation(Base base, Location location) {
		base.updateLocationData(compressLocation(location));
		base.updateLayout(this.getBoundsInParent());
	}

	public BaseLayerView(BasePlacementData data) {
		myData = data;
		myBases = new ArrayList<>();
		UIHelper.setBackgroundColor(this, Color.rgb(0, 0, 0, 0.3));
		this.addEventHandler(MouseEvent.ANY, myEvent);
		this.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> System.out.println(myData.getMyActorToLocation().size()));
	}

	@Override
	protected void layoutChildren() {
		super.layoutChildren();
		if (!DID_LAUNCH)
			loadBaseData();
		DID_LAUNCH = true;
	}

	private void loadBaseData() {
		myData.getMyActorToLocation().forEach((t) -> {
			addBaseUI(t.x, t.y);
		});
	}

	public void addBase(ActorData data, Location sceneLoc) {
		addBaseToLoc(data, sceneToLocal(sceneLoc));

	}

	private void addBaseToLoc(ActorData data, Location location) {
		System.out.println("local coordinates " + location);
		Coordinates coor = compressLocation(location);
		this.myData.addBase(new Tuple<>(data, coor));
		Base b = addBaseUI(data, coor);
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

	private Base addBaseUI(ActorData data, Grid2D loc) {
		Base base = new Base(data, loc, this.getBoundsInParent());
		base.addEventHandler(MouseEvent.ANY, myBaseEvent);
		this.getChildren().add(base);
		return base;
	}

	private Coordinates compressLocation(Location e) {
		Coordinates c = new Coordinates(e.getX() / this.getWidth(), e.getY() / this.getHeight());
		return c;
	}

	private void deleteBase(Base base) {
		System.out.println("Removing base " + base.getData().x.getName());
		this.myBases.remove(base);
		this.myData.removeBase(base.getData());
	}

	@Override
	public void activate() {
		this.myBases.forEach(base -> base.setOpacity(1.0));
		isActive = true;
	}

	@Override
	public void deactivate() {
		this.myBases.forEach(base -> base.setOpacity(0.1));
		isActive = false;
	}

	@Override
	public void clear() {
		for(Base b : myBases){
			deleteBase(b);
		}

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
