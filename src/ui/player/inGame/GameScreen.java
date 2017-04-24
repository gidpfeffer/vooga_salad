package ui.player.inGame;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import gamedata.ActorData;
import gameengine.grid.interfaces.frontendinfo.FrontEndInformation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;
import ui.general.ImageViewPane;
import ui.handlers.UIHandler;
import ui.player.login.LoginElement;
import util.observerobservable.VoogaObserver;


public class GameScreen extends GenericGameScreen 
	implements VoogaObserver<Map<Integer,FrontEndInformation>>, LoginElement{
	
	private ImageViewPane ivp;
	private UIHandler uihandler;
	private SimpleHUD hud;
	private Map<Integer, Actor> actorsMap;
	private GameScreen gs = this;
	private ScreenHandler screenHandler;
	
	private void initializeScreenHandler() {
		screenHandler = new ScreenHandler(){
			@Override
			public void createActor(double x, double y, int option, ActorData actorData ) {
				Actor actor = new Actor(uihandler,option,actorData,ivp, actorsMap);
				actor.getPane().setLayoutX(getWidth() - x);
				actor.getPane().setLayoutY(y);
				getChildren().add(actor.getPane());
			}
		};
	}
	
	@Override
	public EventHandler<ActionEvent> getAction() {
		EventHandler<ActionEvent> backToLogin = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				uihandler.stop();
			}
		};
		System.out.println(gs.getMediaPlayer().getStatus());
		if(gs.getMediaPlayer().getStatus().equals(Status.PLAYING)) gs.getMediaPlayer().stop();
		return backToLogin;
	}
	
	public GameScreen(UIHandler uihandler) {
		super(uihandler, Optional.ofNullable(null), Optional.ofNullable(null), Optional.ofNullable(null));
		this.uihandler = uihandler;
		this.actorsMap = new HashMap<Integer, Actor>();
		this.ivp = this.getIVP();
		initializeScreenHandler();
		hud = uihandler.getSimpleHUD().get();
		setup();
		fadeTransition();
	}
	
	private void setup() {
		setupPanels();
		setupHUD();
	}
	
	private void setupPanels() {
		SidePanel sidePanel = new SidePanel(screenHandler, uihandler.getOptions());
		AnchorPane.setRightAnchor(sidePanel.getSidePane(), 10.0);
		this.getChildren().add(sidePanel.getSidePane());
		addInternalPanesToRoot(sidePanel.getListOfPanes());
	}
	
	public void addInternalPanesToRoot(Collection<OptionsPane> listOfPanes) {
		listOfPanes.forEach(op -> {
			this.getChildren().add(op);
			AnchorPane.setRightAnchor(op, -op.getPrefWidth() - 10);
			op.setStyle(("-fx-background-color: MediumAquamarine;" + " -fx-border-radius: 10 0 0 10;"
					+ "-fx-background-radius: 10 0 0 10;"));
		});
	}
	
	private void setupHUD() {
		AnchorPane.setBottomAnchor(hud.getGrid(), 10.);
		AnchorPane.setLeftAnchor(hud.getGrid(), 10.);
		this.getChildren().add(hud.getGrid());
	}
	
	private void fadeTransition() {
		FadeTransition ft = new FadeTransition(Duration.millis(1000), this);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
	}

	@Override
	public void update(Map<Integer, FrontEndInformation> arg) {
		actorsMap.keySet().removeIf(id -> {
			if(arg.containsKey(id)) {
				return false;
			}
			this.getChildren().remove(actorsMap.get(id).getPane());
			//actorsMap.get(id).deleteActor();
			return true;
		});
		
		arg.keySet().stream().forEach(id -> {
			Integer actorOption = arg.get(id).getActorOption();
			if(!actorsMap.containsKey(id)) {
				Actor newActor = new Actor(uihandler, actorOption, uihandler.getOptions().get(actorOption), ivp, actorsMap);
				actorsMap.put(id, newActor);
				this.getChildren().add(newActor.getPane());
			}
			Actor actor = actorsMap.get(id);
			double xCoor = util.Transformer.ratioToCoordinate(arg.get(id).getActorLocation().getX(), (ivp.getWidth() - ivp.getImageInsets().x));
			double yCoor = util.Transformer.ratioToCoordinate(arg.get(id).getActorLocation().getY(), (ivp.getHeight() - ivp.getImageInsets().y));
			actor.getPane().setLayoutX(xCoor);
			actor.getPane().setLayoutY(yCoor);
			//System.out.println("Layout: " + actor.getActor().getLayoutX() + " " + xCoor + " " + actor.getActor().getLayoutY() + " " + yCoor);
		});
	}
}